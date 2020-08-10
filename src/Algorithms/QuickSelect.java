package Algorithms;


public class QuickSelect extends Algorithms {
    @Override
    public int findK(int[] array, int k) {
        return kth(array, 0, array.length - 1, k);
    }


    private static int partition(int[] arr, int i, int j) {
        int pivot = arr[j], pivotloc = i;
        for (int a = i; a <= j; a++) {

            if (arr[a] < pivot) {
                int temp = arr[a];
                arr[a] = arr[pivotloc];
                arr[pivotloc] = temp;
                pivotloc++;
            }
        }

        int temp = arr[j];
        arr[j] = arr[pivotloc];
        arr[pivotloc] = temp;

        return pivotloc;
    }


    private static int kth(int[] arr, int start, int end, int k) {

        int partition = partition(arr, start, end);

        if (partition == k)
            return arr[partition];
        else if (partition < k)
            return kth(arr, partition + 1, end, k);
        else {
            return kth(arr, start, partition - 1, k);
        }
    }
}
