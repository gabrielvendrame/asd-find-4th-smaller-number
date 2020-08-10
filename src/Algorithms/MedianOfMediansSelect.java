package Algorithms;

import java.util.Arrays;

public class MedianOfMediansSelect extends Algorithms {

    //array totale, start è il punto in cui iniziare ad ordinare, n indica per quanti valori
     int findMedian(int array[], int n) {
        Arrays.sort(array);    //ordina il vettore
        return array[n / 2];
    }



     int partition(int array[], int start, int end, int pivot) {

         for (int i = 0; i < array.length; i++) {
             if (array[i] == pivot) {
                 swap(array, i, end);
                 break;
             }
         }
         int index = start - 1;
         int i = start;
         while (i < end) {
             if (array[i] < pivot) {
                 index++;
                 swap(array, i, index);
             }
             i++;
         }
         index++;
         swap(array, index, end);
         return index;
    }

    @Override
    public int findK(int[] array, int k) {
        return findK(array, 0, array.length - 1, k);
    }
    //funzione principale che riceve in input l'array, start, end e il valore di k
    public int findK(int array[], int start, int end, int k) {
        if (k > 0 && k <= end - start + 1) {
            int n = end - start + 1;

            int i;
            int[] medArray = new int[(n + 4) / 5];
            for (i = 0; i < medArray.length - 1; i++) {
                medArray[i] = findMedian(Arrays.copyOfRange(array, 5 * i + start, 5 * i + start + 4), 5);
            }
            if (n % 5 == 0) {
                medArray[i] = findMedian(Arrays.copyOfRange(array, 5 * i + start, 5 * i + start + 4), 5);
                i++;

            } else {
                medArray[i] = findMedian(Arrays.copyOfRange(array, 5 * i + start, 5 * i + start + (n % 5)), n % 5);
                i++;

            }

            int medOfMed = -1;
            if (i == 1) {
                medOfMed = medArray[i - 1];
            } else {
                medOfMed = findK(medArray, 0, i - 1, i / 2);
            }

            int posPart = partition(array, start, end, medOfMed);

            if (posPart - start == k - 1)
                return array[posPart];
            if (posPart - start > k - 1) {
                return findK(array, start, posPart - 1, k);

            } else {
                return findK(array, posPart + 1, end, k - posPart + start - 1);

            }

        }
        return Integer.MAX_VALUE;
    }

    public int[] swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
        return array;
    }

}
