package tb;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;

public class Node {

    private final char code;
    private final Set<Node> children = Sets.newHashSet();
    private boolean done;

    public Node(char code) {
        this.code = code;
    }

    public void done() {
        this.done = true;
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public char getCode() {
        return code;
    }

    public Set<Node> getVisitedChildren() {
        return children;
    }

    @Override
    public String toString() {
        return String.valueOf(code);
    }
}

