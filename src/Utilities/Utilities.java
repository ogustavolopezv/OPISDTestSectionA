package utilities;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utilities {
    public static <T extends Number> float getMin(List<T> data) {
        float min = Float.MAX_VALUE;
        //int index = -1;
        for (T i : data) {
            Float f = i.floatValue();
            if (Float.compare(f, min) < 0) {
                min = f;
                //index = i;
            }
        }
        return min;
    }

    public static <T extends Number> float getMax(List<T> data) {
        float max = Float.MIN_VALUE;
        //int index = -1;
        for (T i : data) {
            Float f = i.floatValue();
            if (Float.compare(f.floatValue(), max) > 0) {
                max = f;
                //index = i;
            }
        }
        return max;
    }

    public static List<List<? extends Number>> transpose(List<List<? extends Number>> table) {
        final int N = table.stream().mapToInt(l -> l.size()).max().orElse(-1);
        List<Iterator<? extends Number>> iterList = table.stream().map(it->it.iterator()).collect(Collectors.toList());
        return IntStream.range(0, N)
                .mapToObj(n -> iterList.stream()
                        .filter(it -> it.hasNext())
                        .map(m -> m.next())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }    
    
    public static List<List<? extends Object>> transposeO(List<List<? extends Object>> table) {
        final int N = table.stream().mapToInt(l -> l.size()).max().orElse(-1);
        List<Iterator<? extends Object>> iterList = table.stream().map(it->it.iterator()).collect(Collectors.toList());
        return IntStream.range(0, N)
                .mapToObj(n -> iterList.stream()
                        .filter(it -> it.hasNext())
                        .map(m -> m.next())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }        

    public static int[] permute(int n) {
        int[] permutation = new int[n];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) { list.add(i); }
        java.util.Collections.shuffle(list);
        for (int i = 0; i < n; i++) { permutation[i] = list.get(i); }

        return permutation;
    }

    public static <T extends Number> float bound(T number, Range range) {
        float n = number.floatValue();
        final float uR = range.getMax().floatValue();
        final float lR = range.getMin().floatValue();

        if (n > uR)
            n = lR + ((n + uR) % (Math.abs(lR - uR))) ;
        if (n < lR)
            n = uR + ((n - lR) % (Math.abs(lR - uR))) ;

        return n;
    }
    
    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = -1;
        do {
            pos = str.indexOf(substr, pos + 1);
        } while (n-- > 0 && pos != -1);
        return pos;
    }    
    
    public static void shiftRight(int[] list) {
       if (list.length < 2) return;
       int last = list[list.length - 1];
       for(int i = list.length - 1; i > 0; i--) {
          list[i] = list[i - 1];
       }
       list[0] = last;
    }    
    
}
