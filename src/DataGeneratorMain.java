
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Generate example data for use with a decision tree.
 * The data is output in CSV format.
 * NB: The feature set should match that used with the
 *     the tree generator.
 * @author David J. Barnes
 * @version 2020.03.03
 */
public class DataGeneratorMain {
    // Where to write the generated data.
    private static String filename = "data.txt";
    
    public static void main(String[] args)
    {
        // The feature set to generate for.
        String[][] features = Features.SELECTED_FEATURES;
        // The number of possible combinations.
        int numCombinations =
            Arrays.stream(features).map(s -> s.length).reduce(1, (t, n) -> t * n);
        // The number of data samples to generate.
        int numToGenerate = numCombinations / 2;
        // NB: Change the setting of Randomizer.useShared to be false
        // if a random data set is required.
        Random rand = Randomizer.getRandom();
        
        if(args.length == 1) {
            // Assume the tree is to be written to a given file.
            filename = args[0];
        }
        
        List<Integer> order = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < numToGenerate; i++) {
            for(int f = 0; f < features.length; f++) {
                order.add(f);
            }
            Collections.shuffle(order);
            for(int f : order) {
                String[] values = features[f];
                String value = values[rand.nextInt(values.length)];
                builder.append(value).append(',');
            }
            builder.setLength(builder.length() - 1);
            builder.append('\n');
            order.clear();
        }
        String data = builder.toString();
        System.out.println(data);
        writeData(data);
    }
    
    /**
     * Write the data string to file.
     * @param data The data in the form of a string.
     */
    private static void writeData(String data)
    {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(data);
        }
        catch(IOException ex) {
            System.err.println(ex);
        }
    }

}
