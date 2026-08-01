import java.util.Random;

public class AlgorithmComparisonReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};
        for (int n : sizes) {
            System.out.println("==========================================");
            System.out.println(" 資料筆數 N = " + n);
            System.out.println("==========================================");
            
            runExperiment("已排序資料", generateSorted(n));
            runExperiment("反向排序資料", generateReversed(n));
            runExperiment("固定亂序資料", generateRandom(n, 42));
        }
    }

    private static void runExperiment(String label, int[] orig) {
        System.out.println("【" + label + "】");
        long selCmp = selectionSortCount(orig.clone());
        long insCmp = insertionSortCount(orig.clone());
        long merCmp = mergeSortCount(orig.clone());

        System.out.printf("  Selection Sort 次數 : %d%n", selCmp);
        System.out.printf("  Insertion Sort 次數 : %d%n", insCmp);
        System.out.printf("  Merge Sort 次數     : %d%n", merCmp);

        if (insCmp < merCmp) {
            System.out.println("  結論：資料接近有序時，Insertion Sort 比較次數最少。");
        } else {
            System.out.println("  結論：資料混亂/反向時，Merge Sort 的 O(N log N) 比較次數展現巨大優勢。");
        }
        System.out.println();
    }

    private static long selectionSortCount(int[] arr) {
        long count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                count++;
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int tmp = arr[i]; arr[i] = arr[minIdx]; arr[minIdx] = tmp;
        }
        return count;
    }

    private static long insertionSortCount(int[] arr) {
        long count = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i], j = i - 1;
            while (j >= 0) {
                count++;
                if (arr[j] <= key) break;
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        return count;
    }

    private static long mergeSortCount(int[] arr) {
        long[] count = new long[1];
        int[] temp = new int[arr.length];
        mergeSort(arr, temp, 0, arr.length - 1, count);
        return count[0];
    }

    private static void mergeSort(int[] arr, int[] temp, int left, int right, long[] count) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid, count);
        mergeSort(arr, temp, mid + 1, right, count);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            count[0]++;
            temp[k++] = (arr[i] <= arr[j]) ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }

    private static int[] generateSorted(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i * 2;
        return a;
    }

    private static int[] generateReversed(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = (n - i) * 2;
        return a;
    }

    private static int[] generateRandom(int n, long seed) {
        int[] a = new int[n];
        Random r = new Random(seed);
        for (int i = 0; i < n; i++) a[i] = r.nextInt(10000);
        return a;
    }
}