package lineage;

import java.util.ArrayList;
import java.util.Objects;

public class Node {
    private String name;
    private ArrayList<Node> children;

    public Node(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public Node(String name, ArrayList<Node> children) {
        this.name = name;
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    /**
     * Adds a child if it's not already in the children list.
     * @param child child node to add.
     * @return true if success, false otherwise.
     */
    public boolean addChild(Node child){
        if (!children.contains(child)) {
            return children.add(child);
        } else {
            return false;
        }
    }

    public boolean addChild(String child){
        return addChild(new Node(child));
    }

    public boolean removeChild(Node child){
        return children.remove(child);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}