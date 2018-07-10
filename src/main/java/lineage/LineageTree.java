package lineage;

import lineage.visitor.TreeVisitor;
import lineage.visitor.TreeVisitorResult;

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
        Node childNode = getNode(child);

        boolean addSuccess = false;
        if (parentNode != null){
            if (childNode != null){
                addSuccess = parentNode.addChild(childNode);
            } else {
                addSuccess = parentNode.addChild(new Node(child));
            }
        }
        return addSuccess;
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
    public boolean contains(String node){
        return contains(new Node(node));
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
