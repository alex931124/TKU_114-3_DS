public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C01", "Alice", 85, 120),
            new Contestant("C02", "Bob", 95, 110),
            new Contestant("C03", "Charlie", 85, 95),
            new Contestant("C04", "David", 95, 105),
            new Contestant("C05", "Eve", 70, 150)
        };

        insertionSort(contestants);

        System.out.println("=== 參賽者排名結果 ===");
        int rank = 1;
        for (int i = 0; i < contestants.length; i++) {
            if (i > 0 && (contestants[i].getScore() != contestants[i - 1].getScore() 
                || contestants[i].getSeconds() != contestants[i - 1].getSeconds())) {
                rank = i + 1;
            }
            System.out.println("第 " + rank + " 名: " + contestants[i]);
        }
    }

    public static void insertionSort(Contestant[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Contestant key = arr[i];
            int j = i - 1;
            while (j >= 0 && needSwap(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static boolean needSwap(Contestant prev, Contestant key) {
        if (prev.getScore() < key.getScore()) return true;
        if (prev.getScore() == key.getScore() && prev.getSeconds() > key.getSeconds()) return true;
        return false;
    }
}