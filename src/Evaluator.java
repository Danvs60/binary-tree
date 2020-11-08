import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Evaluator class that takes calculates a the result of a binary
 * decision tree, given the nodes and a data set to compare them with.
 *
 * @author Daniel Bartolini
 * @version 1.0
 */
public class Evaluator
{
    private List<List<String>> data;
    private HashMap<Integer, List<String>> nodes;
    private List<String> dataLine;
    private int sum;

    /**
     * Constructor for the class Evaluator.
     * Takes in the nodes and data lists and turns them into indexed
     * data sets to easily process the CSV Strings.
     * For each line in the data list, compares it with the nodes in 
     * the tree.
     * 
     * @param data a list of Strings in CSV format.
     * @param nodes a list of nodes in CSV format from a binary decision tree.
     */
    public Evaluator(List<String> data, List<String> nodes)
    {
        this.data = toListOfList(data);
        this.nodes = toHashOfList(nodes);
        sum = 0;
        /*For each line in the data document, run the evaluation
         * method and add its result to a variable sum.
         */
        for(int i = 0; i < this.data.size(); i++) {
            dataLine = this.data.get(i);
            sum += evaluate(0);
        }
    }

    /**
     * Get the calculated evaluation.
     * 
     * @return integer number of evaluation.
     */
    public int getSum()
    {
        return sum;
    }

    /**
     * Recursive method that evaluates the binary decision tree.
     * Base-case: leaf node has been found, return its value.
     * If evaluation is true evaluate left node, evaluate right node
     * otherwise.
     * 
     * @param nodeNumber the node to be evaluated.
     * @return the value of a leaf node.
     */
    public int evaluate(int nodeNumber)
    {
        List<String> curr = nodes.get(nodeNumber);
        String f = curr.get(1);
        int left = Integer.parseInt(curr.get(2));
        int right = Integer.parseInt(curr.get(3));
        int value = Integer.parseInt(curr.get(4));
        if(right == -1 && left == -1) { //Leaf node
            return value;
        }
        else if(dataLine.contains(f)){ //true, go left node
            return evaluate(left);
        }
        else { //false go right node
            return evaluate(right);
        }
    }

    /**
     * Takes a list of String lines in CSV format and returns a 
     * an arraylist of lists that contain those previous lines,
     * but indexed through the list class.
     * 
     * @param inputList the list of String lines in CSV format.
     * @return an arraylist of lists of lines.
     */
    public static List<List<String>> toListOfList(List<String> inputList)
    {
        List<List<String>> outputList = new ArrayList<>();
        inputList.forEach(s -> {
                String[] arr = s.split(",");
                List<String> list = new ArrayList<>();
                for(String str : arr) list.add(str);
                outputList.add(list);
            });
        return outputList;
    }

    /**
     * Takes a list of String lines in CSV format and returns a 
     * Map that contains the node's content, indexed by the node 
     * number.
     * 
     * @param inputList the list of String lines in CSV format.
     * @return HashMap of the nodes numbers as index and each node's content.
     */
    public static HashMap<Integer, List<String>> toHashOfList(List<String> inputList)
    {
        HashMap<Integer, List<String>> outputList = new HashMap<>();
        inputList.forEach(s -> {
                String[] arr = s.split(",");
                int k = Integer.parseInt(arr[0]);
                List<String> list = new ArrayList<>();
                for(String str : arr) list.add(str);
                outputList.put(k, list);
            });
        return outputList;
    }
}
