package tb.knight.node;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class NodePool {

    private final Map<Character, LinkedList<Node>> nodes;

    public NodePool(Set<Character> keys, int length) {
        nodes = Maps.newHashMap();
        keys.forEach(k -> nodes.put(k, createNodes(k, length)));
    }

    private LinkedList<Node> createNodes(Character code, int length) {
        LinkedList<Node> nodes = Lists.newLinkedList();
        for (int i = 0; i < length; i++) {
            nodes.push(new Node(code));
        }
        return nodes;
    }

    public Node checkOut(char code) {
        return nodes.get(code).pop();
    }

    public void checkIn(Node node) {
        node.clear();
        nodes.get(node.getCode()).push(node);
    }

    public boolean contains(char code) {
        return nodes.keySet().contains(code);
    }
}