import java.util.*;

/**
 * Generate a decision tree
 * @author David J. Barnes
 * @version 2020.03.03
 */
public class TreeGenerator {
    // The features to be used in the decision tree.
    private final String[][] features;
    // NB: Change the setting of Randomizer.useShared to be false
    // if a random tree is required.
    private final Random rand = Randomizer.getRandom();
    // Nodes of the generated tree.
    private final List<Node> nodes = new ArrayList<>();
    
    /**
     * Create a generator for the given feature set.
     * @param features Feature set for the decision tree.
     */
    public TreeGenerator(String[][] features)
    {
        this.features = features;
    }

    /**
     * Generate a decision tree.
     * @return A string of nodes, one per line.
     */
    public String generate()
    {
        List<List<String>> featuresLeft = new ArrayList<>(features.length);
        for(int f = 0; f < features.length; f++) {
            String[] values = features[f];
            List featureList = new ArrayList<>(values.length);
            featuresLeft.add(featureList);
            for(String value : values) {
                featureList.add(value);
            }
        }
        nodes.clear();
        generateNode(featuresLeft, 0);
        Collections.sort(nodes);
        StringBuilder builder = new StringBuilder();
        for(Node n : nodes) {
            builder.append(n).append('\n');
        }
        return builder.toString();
    }

    /**
     * Use the list of features left to generate the given node and
     * its descendants.
     * Generated nodes are added to the nodes list.
     * @param featuresLeft Features available.
     * @param nodeNumber The number of the node to be generated.
     */
    private void generateNode(List<List<String>> featuresLeft, int nodeNumber) {
        // Work out which features are available for a decision node.
        List<Integer> available = new ArrayList<>(featuresLeft.size());
        for(int f = 0; f < features.length; f++) {
            if(!featuresLeft.get(f).isEmpty()) {
                available.add(f);
            }
        }
        
        if(available.isEmpty()) {
            // None left.
        }
        else {
            // Choose which feature list to use, and one of its values.
            int selectedFeature = available.get(rand.nextInt(available.size()));
            List<String> values = featuresLeft.get(selectedFeature);
            String selectedValue = values.get(rand.nextInt(values.size()));
            
            // Node numbers for the descendents.
            int left = 2 * nodeNumber + 1;
            int right = values.size() > 1 ? 2 * nodeNumber + 2 : -1;

            if(available.size() > 1 || values.size() > 1) {
                nodes.add(new Node(nodeNumber, selectedValue, left, right));
                if(available.size() == 1) {
                    // Add its leaf score.
                    nodes.add(new Node(left));
                }
                // Make a copy of featuresLeft to pass on to the recursive calls.
                List<List<String>> remainingFeatures = new ArrayList<>(features.length);
                for(int f = 0; f < features.length; f++) {
                    remainingFeatures.add(new ArrayList<>(featuresLeft.get(f)));
                }
                remainingFeatures.get(selectedFeature).remove(selectedValue);
                if(values.size() > 1) {
                    generateNode(remainingFeatures, right);
                }
                remainingFeatures.get(selectedFeature).clear();
                generateNode(remainingFeatures, left);
            }
            else {
                // Final option. A mismatch would be an error.
                nodes.add(new Node(nodeNumber, selectedValue, left, -1));                
                // Add its leaf, containing the score.
                nodes.add(new Node(left));
            }
        }
    }      
}
