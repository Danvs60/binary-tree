
import java.io.FileWriter;
import java.io.IOException;

/**
 * Generate a decision tree based on feature sets defined in the Features class.
 * 
 * @author David J. Barnes
 * @version 2020.03.03
 */
public class TreeGeneratorMain {
    // Where to write the generated tree.
    private static String filename = "tree.txt";
    
    public static void main(String[] args)
    {
        if(args.length == 1) {
            // Assume the tree is to be written to a given file.
            filename = args[0];
        }
        // Create a generator for the given set of features.
        TreeGenerator generator = 
            new TreeGenerator(Features.SELECTED_FEATURES);
        // Generate a decision tree.
        String nodes = generator.generate();
        System.out.print(nodes);
        writeTree(nodes);
    }
    
    /**
     * Write the node string to file.
     * @param nodes The nodes in the form of a string.
     */
    private static void writeTree(String nodes)
    {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(nodes);
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

}
