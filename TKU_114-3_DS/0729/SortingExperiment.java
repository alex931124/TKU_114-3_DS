import java.util.Arrays;

public class SortingExperiment {
    public static void main(String[] args) {
        int[] sorted = {10, 20, 30, 40, 50, 60};
        int[] reversed = {60, 50, 40, 30, 20, 10};
        int[] random = {35, 12, 48, 20, 55, 10};

        test("已排序資料", sorted);
        test("反向排序資料", reversed);
        test("隨機排列資料", random);
    }

    public static void test(String label, int[] orig) {
        System.out.println("【" + label + "】: " + Arrays.toString(orig));
        
        int[] selRes = selectionSort(orig.clone());
        System.out.printf(" Selection Sort -> 比較: %d 次, 交換: %d 次%n", selRes[0], selRes[1]);

        int[] insRes = insertionSort(orig.clone());
        System.out.printf(" Insertion Sort -> 比較: %d 次, 移動: %d 次%n", insRes[0], insRes[1]);

        if (insRes[1] < selRes[1]) {
            System.out.println(" 結論：資料接近有序時，Insertion Sort 表現較佳。\n");
        } else if (insRes[1] > selRes[1]) {
            System.out.println(" 結論：資料反向時，Insertion Sort 的移動次數遠高於 Selection Sort 的交換。\n");
        } else {
            System.out.println(" 結論：兩者操作開銷相近。\n");
        }
    }

    public static int[] selectionSort(int[] values) {
        int comparisons = 0, swaps = 0;
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                comparisons++;
                if (values[index] < values[minIndex]) minIndex = index;
            }
            if (minIndex != start) {
                int temp = values[start];
                values[start] = values[minIndex];
                values[minIndex] = temp;
                swaps++;
            }
        }
        return new int[]{comparisons, swaps};
    }

    public static int[] insertionSort(int[] values) {
        int comparisons = 0, moves = 0;
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0) {
                comparisons++;
                if (values[position] <= key) break;
                values[position + 1] = values[position];
                moves++;
                position--;
            }
            values[position + 1] = key;
        }
        return new int[]{comparisons, moves};
    }
}