package lineage;

import lineage.visitor.TreeVisitor;
import lineage.visitor.TreeVisitorResult;

import java.util.ArrayList;
import java.util.Objects;

public class LineageTree {
    private Node head;

    public LineageTree(Node head) {
        this.head = head;
    }

    public LineageTree(String parent, String child){
        head = new Node(parent);
        head.addChild(child);
    }

    public boolean addNode(String parent, String child){
        Node parentNode = getNode(parent);
        return parentNode.addChild(child);
    }

    public Node getNode(String node){
        TreeVisitorNodeGetter tvNodeGetter = new TreeVisitorNodeGetter(node);
        tvNodeGetter.walkTree(this);
        return tvNodeGetter.foundNode;
    }

    public boolean contains(Node node){
        if (getNode(node.getName())!= null){
            return true;
        } else {
            return false;
        }
    }

    public Node getHead() {
        return head;
    }

    private class TreeVisitorNodeGetter extends TreeVisitor{
        private Node searchedNode;
        private Node foundNode;

        public TreeVisitorNodeGetter(String node) {
            this.searchedNode = new Node(node);
        }

        @Override
        public TreeVisitorResult visitNode(Node node) {
            if (this.searchedNode.equals(node)){
                this.foundNode = node;
                return TreeVisitorResult.END;
            } else {
                return TreeVisitorResult.CONTINUE;
            }
        }
    }

}
