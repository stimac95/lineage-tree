package lineage.visitor;

/**
 * Specifies when to stop the tree traversal.
 *
 */
public enum TreeVisitorResult {
    /**
     * Tree walking will continue
     */
    CONTINUE,
    /**
     * Tree walking will stop
     */
    END
}
