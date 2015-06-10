package orwell.tank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Michaël Ludmann on 6/10/15.
 */
public class Utils {
    /**
     * Split a String around a byte separator, within a given limit
     *
     * @param separator byte separator
     * @param input     String to split
     * @param limit     max number of elements allowed in the returned list
     *                  If limit is < 0, then proceed as if there were no limit
     * @return list of bytes arrays, its size being of maximum {limit} elements
     *         if limit = 0: returns empty list
     *         if limit = 1: returns the first element result of the split (i.e.
     *         a list of one byte[] stopping at the first separator)
     */
    public static List<String> split(final byte separator, final String input, final int limit) {
        final byte[] inputBytes = input.getBytes();
        final List<String> list = new LinkedList<>();
        if (0 == limit) {
            return list;
        }

        int blockStart = 0;
        if (1 != limit) {
            int blockEnd = 0;
            final boolean limited = 0 < limit;
            while (blockEnd < inputBytes.length && (!limited || limit > list.size())) {
                if (separator == inputBytes[blockEnd]) {
                    list.add(Arrays.copyOfRange(inputBytes, blockStart, blockEnd).toString());
                    blockStart = blockEnd + 1;
                }
                blockEnd++;
            }
        }
        list.add(Arrays.copyOfRange(inputBytes, blockStart, inputBytes.length).toString());
        return list;
    }

    public static List<String> split(final byte separator, final String input) {
        return split(separator, input, -1);
    }
}
