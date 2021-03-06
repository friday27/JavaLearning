import java.util.*;

public class BucketSort {
    public static void bucketSort(int[] arr, final int minVal, final int maxVal) {
        if(arr == null | arr.length <= 1 | minVal == maxVal)
            return;
        int bucketNum = (maxVal-minVal) / arr.length + 1;
        List<List<Integer>> buckets = new ArrayList<>(bucketNum);
        // Create empty list for each bucket
        for(int i=0; i<bucketNum; i++) {
            buckets.add(new ArrayList<>());
        }
        // Put elements into buckets
        for(int i=0; i<arr.length; i++) {
            int idx = (i-minVal)/(maxVal-minVal);
            List<Integer> b = buckets.get(idx);
            b.add(arr[i]);
        }
        // Sort elements in each bucket and merge all buckets
        for(int i=0, k=0; i<bucketNum; i++) {
            List<Integer> b = buckets.get(i);
            if(b != null) {
                Collections.sort(b);
                for(int j=0; j<b.size(); j++) {
                    arr[k++] = b.get(j);
                }
            }
        }
    }

    public static void bucketSort2(int[] arr, int min, int max, int bucketNum) {
        if(arr.length <= 1)
            return;
        List<List<Integer>> buckets = new ArrayList<>();
        for(int i=0; i<bucketNum; i++)
            buckets.add(new ArrayList<Integer>());
        for(int n: arr) {
            int b = (n-min)/(max-min);
            buckets.get(b).add(n);
        }
        int k = 0;
        for(int i=0; i<bucketNum; i++) {
            Collections.sort(buckets.get(i));
            List<Integer> bucket = buckets.get(i);
            for(int j=0; j<bucket.size(); j++)
                arr[k++] = bucket.get(j);
        }
    }

    public static void main(String[] args) {

        int[] array = {10, 4, 6, 8, 13, 2, 3};
        bucketSort(array, 2, 13);
        System.out.println(java.util.Arrays.toString(array));

        array = new int[] {10, 10, 10, 10, 10};
        bucketSort(array, 10, 10);
        System.out.println(java.util.Arrays.toString(array));

        runTests();
    }

    static Random RANDOM = new Random();

    public static void runTests() {
        final int NUM_TESTS = 1000;
        for (int i = 1; i <= NUM_TESTS; i++) {

            int[] array = new int[i];
            int maxVal = Integer.MIN_VALUE, minVal = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                array[j] = randInt(-1000000, +1000000);
                maxVal = Math.max(maxVal, array[j]);
                minVal = Math.min(minVal, array[j]);
        }
        int[] arrayCopy = array.clone();

        bucketSort(array, minVal, maxVal);
        // bucketSort2(array, minVal, maxVal, 10);
        Arrays.sort(arrayCopy);

        if (!Arrays.equals(array, arrayCopy)) System.out.println("ERROR");
        }
    }

    static int randInt(int min, int max) {
        return RANDOM.nextInt((max - min) + 1) + min;
    }
}