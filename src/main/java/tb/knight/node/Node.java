package tb.knight.node;

import com.google.common.collect.Sets;

import java.util.Set;

public class Node {

    private final char code;
    private final Set<Character> children = Sets.newHashSet();

    public Node(char code) {
        this.code = code;
    }

    public void clear() {
        children.clear();
    }

    public void addChild(Node child) {
        children.add(child.getCode());
    }

    public char getCode() {
        return code;
    }

    public Set<Character> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}