public class Q09_MatrixReport {
    public static void main(String[] args) {
        int[][] data = {
            {5, 8, 2},
            {9, 4, 7},
            {3, 6, 10}
        };
        System.out.println("第 1 列總和：" + rowSum(data, 1));
        System.out.println("第 2 欄總和：" + columnSum(data, 2));
        System.out.println("大於等於 7 的筆數：" + countAtLeast(data, 7));
        System.out.println("總和最大的列索引：" + findRowWithLargestTotal(data));
    }

    public static int rowSum(int[][] data, int row) {

        if (data == null || row < 0 || row >= data.length) {
            return -1;
        }
        
        int sum = 0;
        for (int j = 0; j < data[row].length; j++) {
            sum += data[row][j];
        }
        return sum;
    }

    public static int columnSum(int[][] data, int column) {
        if (data == null || data.length == 0 || column < 0) {
            return -1;
        }

        int sum = 0;
        boolean foundColumnInAtLeastOneRow = false;

        for (int i = 0; i < data.length; i++) {

            if (data[i] != null && column < data[i].length) {
                sum += data[i][column];
                foundColumnInAtLeastOneRow = true;
            }
        }

        if (!foundColumnInAtLeastOneRow) {
            return -1;
        }

        return sum;
    }

    public static int countAtLeast(int[][] data, int target) {
        if (data == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (int j = 0; j < data[i].length; j++) {
                    if (data[i][j] >= target) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    public static int findRowWithLargestTotal(int[][] data) {
        if (data == null || data.length == 0) {
            return -1;
        }

        int maxRowIndex = -1;
        int maxSum = -1; 

        for (int i = 0; i < data.length; i++) {
            int currentSum = rowSum(data, i);

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxRowIndex = i;
            }
        }

        return maxRowIndex;
    }
}