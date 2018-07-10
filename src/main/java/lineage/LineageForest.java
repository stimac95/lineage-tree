package lineage;

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

    public boolean addNode(String parent, String child){
        Optional<LineageTree> parentTree = lineageTreeList.stream()
                                                .filter((tree) -> tree.contains(parent))
                                                .findAny();
        Optional<LineageTree> childTree = lineageTreeList.stream()
                                                .filter((tree) -> tree.contains(child))
                                                .findAny();
        boolean addResult = false;
        Node parentNode;
        if (parentTree.isPresent()) {
            parentNode = parentTree.get().getNode(parent);

        } else {
            parentNode = new Node(parent);
            LineageTree newParentTree = new LineageTree(parentNode);
            lineageTreeList.add(newParentTree);
        }
        if (childTree.isPresent()){
            // If childNode is the head of childTree merge parentTree and childTree, and delete childTree
            if (childTree.get().getHead().equals(new Node(child))){
                addResult = parentNode.addChild(childTree.get().getHead());
                lineageTreeList.remove(childTree.get());
            } else {
                Node childNode = childTree.get().getNode(child);
                addResult = parentNode.addChild(childNode);
            }
        } else {
            addResult = parentNode.addChild(new Node(child));
        }
        checkLoops();
        return addResult;
    }

    private void checkLoops() {
    }

}
