package unify.basket.utils;

import org.apache.commons.collections4.CollectionUtils;
import unify.basket.entities.Product;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasketUtils {

    public static int countSublistOccurrences(List<Product> largeList, List<Product> sublist) {
        List<Product> productList = new ArrayList<>(largeList);
        int count = 0;
        while (CollectionUtils.isSubCollection(sublist, productList)) {
            for(Product product: sublist) {
                productList.remove(product);
            }
            count++;
        };
        return count;
    }

}
