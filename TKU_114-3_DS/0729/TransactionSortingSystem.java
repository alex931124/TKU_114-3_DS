public class TransactionSortingSystem {
    public static void main(String[] args) {
        Transaction[] txs = {
            new Transaction("T01", "AccA", 5000, 1),
            new Transaction("T02", "AccB", 12000, 2),
            new Transaction("T03", "AccC", 5000, 3),
            new Transaction("T04", "AccD", 20000, 4),
            new Transaction("T05", "AccE", 12000, 5)
        };

        System.out.println("=== 排序前 ===");
        for (Transaction t : txs) System.out.println(t);

        insertionSort(txs);

        System.out.println("\n=== 排序後 (金額降冪，同金額時間序號升冪) ===");
        for (Transaction t : txs) System.out.println(t);
    }

    public static void insertionSort(Transaction[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Transaction key = arr[i];
            int j = i - 1;
            while (j >= 0 && needMove(arr[j], key)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static boolean needMove(Transaction prev, Transaction key) {
        if (prev.getAmount() < key.getAmount()) return true;
        if (prev.getAmount() == key.getAmount() && prev.getSeq() > key.getSeq()) return true;
        return false;
    }
}