package Algorithms;


public class HeapSelect extends Algorithms {

    int[] h1, parametri;
    int[][] h2;
    int heapSizeH1, heapSizeH2;
    int[][] resource;


    public HeapSelect() {
    }

    public void setFromSize(int size) {
        resource = new int[2][2];
        parametri = new int[]{0, 0};
        for (int i = 0; i < resource.length; i++)
            resource[0] = new int[]{0, 0};
        heapSizeH1 = size;
        //create heap and set dimension
        h2 = new int[size][2];
        for (int i = 0; i < h2.length; i++)
            h2[0] = new int[]{0, 0};
    }

    @Override
    public int findK(int[] arr, int k) {
        h1 = buildHeap(arr, arr.length);
        heapSizeH2 = 1;
        h2[0][0] = h1[0];
        h2[0][1] = 0;
        // value of h1 cancelled after extraction
        h1[0] = Integer.MIN_VALUE;
        for (int j = 0; j < k - 1; j++) {
            resource = popHeap(h2, heapSizeH2, resource);
            heapSizeH2 = resource[1][0];


            if (resource[0][1] != -1) {
                if (resource[0][1] * 2 + 1 < arr.length) {
                    parametri[0] = h1[(resource[0][1] * 2) + 1];
                    parametri[1] = (resource[0][1] * 2) + 1;
                    heapSizeH2 = insertHeap(h2, heapSizeH2, parametri);
                }
                if (resource[0][1] * 2 + 2 < arr.length) {
                    parametri[0] = h1[resource[0][1] * 2 + 2];
                    parametri[1] = (resource[0][1] * 2) + 2;
                    heapSizeH2 = insertHeap(h2, heapSizeH2, parametri);
                }
                h1[resource[0][1]] = Integer.MIN_VALUE;
            }

        }
        return h2[0][0];

    }

    public int[] buildHeap(int arr[], int heapSize) {
        h1 = arr;

        for (int i = heapSize / 2 + 1; i >= 0; i--)
            heapify(h1, i, heapSize);

        return h1;
    }

    private void heapify(int[] h2, int i, int heapSize) {
        int minore = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < heapSize && h2[l] < h2[i])
            minore = l;
        if (r < heapSize && h2[r] < h2[minore])
            minore = r;

        if (minore != i) {
            swap(h2, i, minore);

            heapify(h2, minore, heapSize);
        }
    }

    public int insertHeap(int[][] h2, int heapSize, int[] k) {

        h2[heapSize][0] = k[0];
        h2[heapSize][1] = k[1];
        int i = heapSize;
        int p = (int) (Math.ceil(Double.valueOf(i) / 2) - 1);
        while (i > 0 && h2[i][0] < h2[p][0]) {
            swap(h2, i, p);
            i = p;
            p = (int) Math.ceil(Double.valueOf(i) / 2) - 1;
        }
        heapSize++;
        return heapSize;
    }


    public int[][] popHeap(int[][] h2, int heapSize, int[][] resource) {
        swap(h2, 0, Math.max(heapSize - 1, 0));
        heapSize--;
        heapify(h2, 0, heapSize);
        resource[0] = h2[heapSize].clone();
        resource[1][0] = heapSize;
        return resource;
    }

    private void heapify(int[][] h2, int i, int heapSize) {
        int minore = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < heapSize && h2[l][0] < h2[i][0])
            minore = l;
        if (r < heapSize && h2[r][0] < h2[minore][0])
            minore = r;

        if (minore != i) {
            swap(h2, i, minore);

            heapify(h2, minore, heapSize);
        }
    }


    private void swap(int[][] h2, int i, int j) {
        int[] t = h2[i];
        h2[i] = h2[j];
        h2[j] = t;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
