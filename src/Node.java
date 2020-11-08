import java.util.Random;

/**
 * A node within a decision tree.
 * A leaf node is signified by left and right subtrees
 * with the value -1.
 * 
 * Only leaf nodes have a non-zero score.
 *
 * @author David J. Barnes
 * @version 2020.03.03
 */
public class Node implements Comparable<Node>
{
    private static final Random rand = new Random();
    // The decision node's value, to be compared
    // against the data item.
    // Leaf nodes have a zero-length string.
    private final String value;
    // The number of this node.
    private final int nodeNumber;
    // Node numbers of the left and right subtrees.
    // Values of -1,-1 indicate a leaf node.
    private final int left, right;
    // Score for this node: non-zero for leaf nodes.
    private final int score;

    /**
     * A non-leaf node.
     * @param nodeNumber The number of the node.
     * @param value The feature's value.
     * @param left Left subtree node number.
     * @param right Right subtree node number.
     */
    public Node(int nodeNumber, String value, int left, int right)
    {
        this(nodeNumber, value, left, right, 0);
    }
    
    /**
     * A leaf node.
     * @param nodeNumber The number of the node.
     */
    public Node(int nodeNumber)
    {
        // Assign a random score.
        this(nodeNumber, "", -1, -1, 100 + rand.nextInt(100));
    }
    
    /**
     * A non-leaf node.
     * @param nodeNumber The number of the node.
     * @param value The feature's value.
     * @param left Left subtree node number.
     * @param right Right subtree node number.
     * @param score The node's score.
     */
    private Node(int nodeNumber, String value, int left, int right, int score)
    {
        this.value = value;
        this.nodeNumber = nodeNumber;
        this.left = left;
        this.right = right;
        this.score = score;
    }

    /**
     * Whether this node is a leaf.
     * @return true if the node is a leaf.
     */
    public boolean isLeaf()
    {
        return left == -1 && right == -1;
    }
    
    /**
     * Return the node's score.
     * This will be non-zero only for leaves.
     * @return the score.
     */
    public int getScore()
    {
        return score;
    }

    @Override
    /**
     * Return the node in CSV format.
     * @return the node in CSV format.
     */
    public String toString()
    {
        return String.format("%d,%s,%d,%d,%d", 
                nodeNumber, value, left, right, score);
    }

    @Override
    /**
     * Compare this node's number with that of the other.
     * Lower numbers come earlier in sorted order.
     * @param other Node to compare to.
     * @return 'distance' of this node number from the other's.
     */
    public int compareTo(Node other) {
        return nodeNumber - other.nodeNumber;
    }
}
