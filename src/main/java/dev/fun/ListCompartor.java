package dev.fun;

import java.util.Comparator;
import java.util.List;

/**
 *
 * ListCompartor is compare the first element value of List
 * Created by Selina on 5/25/15.
 */
public class ListCompartor implements Comparator<List> {

    @Override
    public int compare(List left, List right) {

        if (left == null) return -1;
        if (right== null) return 1;
        String leftVal = (String)left.get(0);
        String rightValue = (String)right.get(0);

        return leftVal.compareTo(rightValue);
    }
}
