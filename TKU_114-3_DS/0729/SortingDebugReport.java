import java.util.Arrays;

public class SortingDebugReport {
    public static void main(String[] args) {
        System.out.println("=== 1. Selection Sort 內層範圍錯誤 ===");
        int[] data1 = {30, 10, 20};
        System.out.println("原始數據: " + Arrays.toString(data1));
        System.out.println("錯誤版結果: " + Arrays.toString(buggySelection(data1.clone())));
        System.out.println("正確版結果: " + Arrays.toString(fixedSelection(data1.clone())));
        System.out.println("原因說明: 內層迴圈從 0 開始掃描，導致已排好的元素重複參與比較並被覆蓋。\n");

        System.out.println("=== 2. Insertion Sort key 未保存 ===");
        int[] data2 = {30, 10, 20};
        System.out.println("原始數據: " + Arrays.toString(data2));
        System.out.println("錯誤版結果: " + Arrays.toString(buggyInsertionNoKey(data2.clone())));
        System.out.println("正確版結果: " + Arrays.toString(fixedInsertion(data2.clone())));
        System.out.println("原因說明: 未先用變數保存 key，導致元素右移時直接將原 key 的內容覆蓋丟失。\n");

        System.out.println("=== 3. Insertion Sort 比較方向錯誤 ===");
        int[] data3 = {10, 20, 30};
        System.out.println("原始數據: " + Arrays.toString(data3));
        System.out.println("錯誤版結果: " + Arrays.toString(buggyInsertionWrongDir(data3.clone())));
        System.out.println("正確版結果: " + Arrays.toString(fixedInsertion(data3.clone())));
        System.out.println("原因說明: 升冪排序應用 > key 判斷右移，誤寫為 < key 導致結果變為降冪。");
    }

    public static int[] buggySelection(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = 0; index < values.length; index++) { 
                if (values[index] < values[minIndex]) minIndex = index;
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
        return values;
    }

    public static int[] buggyInsertionNoKey(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int position = index - 1;
            while (position >= 0 && values[position] > values[index]) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = values[index];
        }
        return values;
    }

    public static int[] buggyInsertionWrongDir(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] < key) { 
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
        return values;
    }

    public static int[] fixedSelection(int[] values) {
        for (int start = 0; start < values.length - 1; start++) {
            int minIndex = start;
            for (int index = start + 1; index < values.length; index++) {
                if (values[index] < values[minIndex]) minIndex = index;
            }
            int temp = values[start];
            values[start] = values[minIndex];
            values[minIndex] = temp;
        }
        return values;
    }

    public static int[] fixedInsertion(int[] values) {
        for (int index = 1; index < values.length; index++) {
            int key = values[index];
            int position = index - 1;
            while (position >= 0 && values[position] > key) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
        return values;
    }
}