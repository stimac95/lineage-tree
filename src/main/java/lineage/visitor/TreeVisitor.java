package lineage.visitor;

import lineage.LineageTree;
import lineage.Node;
import java.util.ArrayList;

/**
 * Has function to walk through the tree and call visitNode function for every node.
 * It Knows how to walk through all the children of the given node
 */
public abstract class TreeVisitor {
    public void walkTree(LineageTree tree) {
        walkNodes(tree.getHead());
    }

    public void walkNodes(Node node){
        TreeVisitorResult result = visitNode(node);
        if (result == TreeVisitorResult.END || node.getChildren().isEmpty()) return;
        ArrayList<Node> children = node.getChildren();
        int size = children.size();
        for (int childIndex = 0; childIndex < children.size(); childIndex++){
            walkNodes(children.get(childIndex));
        }
    }

    public abstract TreeVisitorResult visitNode(Node node);
}
