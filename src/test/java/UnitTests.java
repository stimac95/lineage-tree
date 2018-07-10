
import lineage.LineageForest;
import lineage.LineageTree;
import lineage.Node;
import lineage.exception.LineageLoopException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {
    LineageForest lineageForest;

    @BeforeEach
    void init(){
        this.lineageForest = new LineageForest();
    }

    @Test
    void testNodeCreate(){
        Node parent = new Node("NodeName");
        assertEquals("NodeName", parent.getName());
    }

    @Test
    void testNodeEquals(){
        Node parent1 = new Node("Michael");
        Node parent2 = new Node("Michael");

        assert (parent1.equals(parent2));
    }

    @Test
    void testNodeAddChild(){
        Node parent = new Node("Mitch");
        parent.addChild("Child1");
        Node child2 = new Node("Child2");
        parent.addChild(child2);
        assertEquals(2, parent.getChildren().size());
        assertEquals("Child1", parent.getChildren().get(0).getName());
        assertEquals("Child2", parent.getChildren().get(1).getName());
    }

    @Test
    void testAddElements(){
        Node parent = new Node("Mitch");
        Node child = new Node ("Anna");
        try {
            this.lineageForest.addNode("Mitch", "Anna");
        } catch (LineageLoopException e) {
        }
        assertEquals(1, lineageForest.getLineageTreeList().size());
        assertEquals(1, lineageForest.getLineageTreeList().get(0).getHead().getChildren().size());
    }

    @Test
    void testAddLoop() throws LineageLoopException {
        LineageForest forest = new LineageForest();
        forest.addNode("Parent", "Child");
        assertThrows(LineageLoopException.class, () -> forest.addNode("Child", "Parent"));

        LineageForest forest2 = new LineageForest();
        forest2.addNode("A", "B");
        forest2.addNode("B", "C");
        forest2.addNode("C", "D");
        assertThrows(LineageLoopException.class, () -> forest2.addNode("D", "C"));
    }

    @Test
    void testTreeCreation() throws LineageLoopException {
        lineageForest.addNode("A", "B");
        lineageForest.addNode("C", "D");
        lineageForest.addNode("E", "F");
        lineageForest.addNode("G", "H");
        lineageForest.addNode("I", "J");
        lineageForest.addNode("K", "L");
        assertEquals(6, lineageForest.getLineageTreeList().size());
        ArrayList<LineageTree> trees = lineageForest.getLineageTreeList();
        assertEquals("A", trees.get(0).getHead().getName());
        assertEquals("C", trees.get(1).getHead().getName());
        assertEquals("E", trees.get(2).getHead().getName());
        assertEquals("G", trees.get(3).getHead().getName());
    }

    @Test
    void testTreeMerge() throws LineageLoopException {
        lineageForest.addNode("A", "B");
        lineageForest.addNode("C", "D");
        assertEquals(2, lineageForest.getLineageTreeList().size());
        lineageForest.addNode("B", "C");
        assertEquals(1, lineageForest.getLineageTreeList().size());
    }

    @Test
    void testMultipleParents() throws LineageLoopException {
        lineageForest.addNode("A", "B");
        lineageForest.addNode("B", "C");
        assertEquals(1, lineageForest.getLineageTreeList().size());
        lineageForest.addNode("D","B");
        assertEquals(2, lineageForest.getLineageTreeList().size());
    }



}
