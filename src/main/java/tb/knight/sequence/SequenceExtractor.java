package tb.knight.sequence;

import com.google.common.collect.Maps;
import tb.knight.model.MovementTable;
import tb.knight.node.Node;
import tb.knight.node.NodePool;

import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class SequenceExtractor {

    private NodePool pool;

    private final Stack<Node> sequence = new Stack<>();
    private final MovementTable table;
    private final char from;
    private final int length;
    private final int maxVowels;

    private int currentLength;
    private int currentVowels;

    private Map<Character, SequenceInfo> sequenceInfos = Maps.newHashMap();
    private SequenceInfo minSequenceInfo;

    public SequenceExtractor(MovementTable table, char from, int length, int maxVowels) {
        this.table = table;
        this.from = from;
        this.length = length;
        this.maxVowels = maxVowels;
        pool = new NodePool(table.getKeys(), length);
    }

    public long getNumberOfSequences2() {

        long total = 0;

        // create initial node

        Node node = pool.checkOut(from);
        if (isVowel(from)) {
            sequenceInfos.put(from, new SequenceInfo(from, 1, 1));
        } else {
            sequenceInfos.put(from, new SequenceInfo(from, 1, 0));
        }
        minSequenceInfo = sequenceInfos.get(from);
        sequence.push(node);

        while (!sequence.isEmpty()) {
            Node current = sequence.peek();
            Node next = getNext2(current);
            if (next != null) {
                boolean discovered = incrementSequenceInfos(next);
                if (discovered) {
                    total++;
                }
                if (!sequenceInfos.containsKey(next.getCode())) {
                    if (isVowel(next.getCode())) {
                        sequenceInfos.put(next.getCode(), new SequenceInfo(next.getCode(), 1, 1));
                    } else {
                        sequenceInfos.put(next.getCode(), new SequenceInfo(next.getCode(), 1, 0));
                    }
                    minSequenceInfo = sequenceInfos.get(next.getCode());
                }
                current.addChild(next);
                sequence.push(next);
            } else {
                decrementSequenceInfos(current);
                pool.checkIn(current);
                sequence.pop();
            }
        }

        return total;
    }

    private boolean incrementSequenceInfos(Node node) {
        boolean discovered = false;
        SequenceInfo min = null;
        for (SequenceInfo info : sequenceInfos.values()) {
            if (info.active()) {
                info.incrementLength();
                if (isVowel(node.getCode())) {
                    info.incrementVowel();
                }
                if (info.getLength() == length && info.getVowels() <= maxVowels) {
                    discovered = true;
                }
                if (min != null) {
                    if (info.getLength() < min.getLength()) {
                        min = info;
                    } else if (info.getLength() == min.getLength()) {
                        if (info.getVowels() < min.getVowels()) {
                            min = info;
                        }
                    }
                } else {
                    min = info;
                }
            }
        }
        minSequenceInfo = min;
        return discovered;
    }

    private void decrementSequenceInfos(Node node) {
        for (SequenceInfo info : sequenceInfos.values()) {
            if (info.active()) {
                info.decrementLength();
                if (isVowel(node.getCode())) {
                    info.decrementVowel();
                }
                if (info.getLength() == 0) {
                    info.deactivate();
                }
            }
        }
    }

    private Node getNext2(Node node) {
        if (minSequenceInfo.getLength() < length) {
            Set<Character> visited = node.getChildren();
            for (char destination : table.getDestinationsFrom(node.getCode())) {
                if (!visited.contains(destination)) {
                    if (isVowel(destination)) {
                        if (minSequenceInfo.getVowels() < maxVowels) {
                            return pool.checkOut(destination);
                        }
                    } else {
                        return pool.checkOut(destination);
                    }
                }
            }
        }
        return null;
    }

    public long getNumberOfSequences() {

        long sequences = 0;

        if (!pool.contains(from)) {
            return sequences;
        }

        Node node = pool.checkOut(from);
        if (isVowel(node.getCode()) && maxVowels == 0) {
            return sequences;
        }

        if (isVowel(node.getCode())) {
            currentVowels++;
        }

        sequence.push(node);
        currentLength++;

        while (!sequence.isEmpty()) {
            Node current = sequence.peek();
            Node next = getNext(current);
            if (next != null) {
                current.addChild(next);
                sequence.push(next);
                currentLength++;
                if (isVowel(next.getCode())) {
                    currentVowels++;
                }
            } else {
                if (currentLength == length) {
                    sequences++;
                }
                pool.checkIn(current);
                sequence.pop();
                currentLength--;
                if (isVowel(current.getCode())) {
                    currentVowels--;
                }
            }
        }
        return sequences;
    }

    private Node getNext(Node node) {
        if (currentLength == length) {
            return null;
        }
        Set<Character> visited = node.getChildren();
        for (char destination : table.getDestinationsFrom(node.getCode())) {
            if (!visited.contains(destination)) {
                if (isVowel(destination)) {
                    if (currentVowels != maxVowels) {
                        return pool.checkOut(destination);
                    }
                } else {
                    return pool.checkOut(destination);
                }
            }
        }
        return null;
    }

    public static Builder forMovementTable(MovementTable table) {
        return new Builder(table);
    }

    private static boolean isVowel(char code) {
        return "AEIOUaeiou".indexOf(code) != -1;
    }

    public static class Builder {

        private final MovementTable table;
        private char from;
        private int length;
        private int vowels;

        public Builder(MovementTable table) {
            this.table = table;
        }

        public Builder from(char from) {
            this.from = from;
            return this;
        }

        public Builder length(int length) {
            this.length = length;
            return this;
        }

        public Builder vowels(int vowels) {
            this.vowels = vowels;
            return this;
        }

        public SequenceExtractor create() {
            if (length <= 0) {
                throw new IllegalStateException("Sequence length must be greater than zero");
            }
            if (vowels < 0) {
                throw new IllegalStateException("Maximum allowed vowels in the sequence must be non negative");

            }
            return new SequenceExtractor(table, from, length, vowels);
        }
    }
}
