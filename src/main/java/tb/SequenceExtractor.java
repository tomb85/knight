package tb;

import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class SequenceExtractor {

    private final Stack<Node> sequence = new Stack<>();
    private final MovementTable table;
    private final char from;
    private final int length;
    private final int maxVowels;

    private int currentLength;
    private int currentVowels;

    public SequenceExtractor(MovementTable table, char from, int length, int maxVowels) {
        this.table = table;
        this.from = from;
        this.length = length;
        this.maxVowels = maxVowels;
    }

    public long getNumberOfSequences() {

        long sequences = 0;

        Node node = new Node(from);
        if (isVowel(node.getCode()) && maxVowels == 0) {
            return sequences;
        } else {
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
                    StringBuilder builder = new StringBuilder();
                    sequence.stream().forEach(n -> builder.append(n.getCode()));
                    System.out.println(builder.toString());
                    sequences++;
                }
                current.done();
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
        List<Character> destinations = table.getDestinationsFrom(node.getCode());
        Set<Character> visited = node.getVisitedChildren().stream().map(n -> n.getCode()).collect(Collectors.toSet());
        for (char destination : destinations) {
            if (!visited.contains(destination)) {
                if (isVowel(destination)) {
                    if (currentVowels != maxVowels) {
                        return new Node(destination);
                    }
                } else {
                    return new Node(destination);
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

    static class Builder {

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
            if (!table.containsKey(from)) {
                throw new IllegalStateException("Key " + from + " is not in the movement table");
            }
            return new SequenceExtractor(table, from, length, vowels);
        }
    }
}
