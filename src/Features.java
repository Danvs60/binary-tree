/**
 * Features to be used in decision-tree examples.
 * 
 * New sets of features may be added.
 * NB: There must be no duplication among the feature values
 *     in a single feature set.
 * 
 * The required feature set is selected in TreeGeneratorMain
 * and DataGeneratorMain.
 * 
 * @author David J. Barnes
 * @version 2020.03.03
 */
public class Features {
    // One feature.
    private static final String[][] features1 = {
        {"red", "orange", "yellow", "green"}, 
    };
    // Two features.
    private static final String[][] features2 = {
        {"red", "yellow", }, 
        {"circle", "rectangle",}
    };
    // Three features.
    private static final String[][] features3 = {
        {"red", "orange", "yellow", "green"}, 
        {"small", "medium", "large"}, 
        {"circle", "rectangle", "triangle", "hexagon", "pentagon"}
    };
    // Four features.
    private static final String[][] features4 = {
        {"red", "orange", "yellow", "green"}, 
        {"small", "medium", "large"}, 
        {"circle", "rectangle", "triangle", "hexagon", "pentagon"},
        {"convex", "concave"}
    };
    
    // The feature selected for tree and data generation.
    public static final String[][] SELECTED_FEATURES = features3;

}
