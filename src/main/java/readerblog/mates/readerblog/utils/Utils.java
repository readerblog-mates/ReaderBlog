package readerblog.mates.readerblog.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Method gets string with last visited products,
     * and if count of products more then @maxCount -
     * then shift products and return string with @maxCount
     * elements
     */
    public static LinkedList<String> cutVisitedProductsHistory(String lastProducts, int maxSize){
        LinkedList list = new LinkedList(Arrays.asList(lastProducts.split("q")));
        if (list.size() > maxSize) {
            list.removeFirst();
        }
        System.out.println("Utils.cutVisitedProductsHistory: " + list);
        return list;
    }

    public static String listToString(LinkedList list){
        return list.stream().collect(Collectors.joining("q")).toString();
    }
}
