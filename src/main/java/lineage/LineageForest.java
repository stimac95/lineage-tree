package lineage;

import lineage.exception.LineageLoopException;
import lineage.visitor.TreeVisitor;
import lineage.visitor.TreeVisitorResult;

import java.util.ArrayList;
import java.util.Optional;


/**
 * This class creates a collection of lineage trees.
 */
public class LineageForest {
    private ArrayList<LineageTree> lineageTreeList = new ArrayList<>();

    public ArrayList<LineageTree> getLineageTreeList() {
        return lineageTreeList;
    }

    public boolean addNode(String parent, String child) throws LineageLoopException {
        Optional<LineageTree> parentTree = lineageTreeList.stream()
                                                .filter((tree) -> tree.contains(parent))
                                                .findAny();
        Optional<LineageTree> childTree = lineageTreeList.stream()
                                                .filter((tree) -> tree.contains(child))
                                                .findAny();
        boolean addResult = false;
        Node parentNode;
        LineageTree parentTreeVal = null;
        if (parentTree.isPresent()) {
            parentTreeVal = parentTree.get();
            parentNode = parentTreeVal.getNode(parent);
        } else {
            parentNode = new Node(parent);
            parentTreeVal = new LineageTree(parentNode);
            lineageTreeList.add(parentTreeVal);
        }
        if (childTree.isPresent()){
            // If childNode is the head of childTree merge parentTree and childTree, and delete childTree
            if (childTree.get().getHead().equals(new Node(child))){
                Node childNode = childTree.get().getHead();
                addResult = parentNode.addChild(childNode);
                checkForLoops(parentNode, childNode);
                lineageTreeList.remove(childTree.get());
            } else {
                Node childNode = childTree.get().getNode(child);
                addResult = parentNode.addChild(childNode);
                checkForLoops(parentNode, childNode);
            }
        } else {
            addResult = parentNode.addChild(new Node(child));
        }

        return addResult;
    }

    public void printLineageForest(){
        for(LineageTree tree : lineageTreeList){
            tree.printLineageTree();
        }
    }

    private void checkForLoops(Node parentNode, Node childNode) throws LineageLoopException {
        LoopCheckerTreeVisitor lcTreeVisitor = new LoopCheckerTreeVisitor();
        if (lcTreeVisitor.checkForLoops(parentNode, childNode)){
            throw new LineageLoopException("Loop detected in lineage");
        }
    }
    private class LoopCheckerTreeVisitor extends TreeVisitor{
        private Node parentNode;
        private Node childNode;
        private boolean loopDetected;

        @Override
        public TreeVisitorResult visitNode(Node node) {
            if (parentNode.equals(node)){
                loopDetected = true;
                return TreeVisitorResult.END;
            } else {
                return TreeVisitorResult.CONTINUE;
            }
        }
        public boolean checkForLoops(Node parentNode, Node childNode){
            for(Node child : parentNode.getChildren()){
                this.parentNode = parentNode;
                this.childNode = childNode;
                loopDetected = false;
                walkNodes(child);
                if (loopDetected){
                    return true;
                }
            }
            return false;
        }
    }

}
