public class ProductSortingSystem {
    public static void main(String[] args) {
        StoreProduct[] products = {
            new StoreProduct("Laptop", 35000, 10),
            new StoreProduct("Mouse", 650, 50),
            new StoreProduct("Keyboard", 1290, 30),
            new StoreProduct("Monitor", 5500, 15),
            new StoreProduct("Headset", 2300, 25),
            new StoreProduct("Webcam", 1290, 40),
            new StoreProduct("USB Cable", 190, 100),
            new StoreProduct("Pad", 350, 80),
            new StoreProduct("Speaker", 2500, 12),
            new StoreProduct("Microphone", 3200, 18)
        };

        printSorted("價格升冪", products.clone(), 1);
        printSorted("價格降冪", products.clone(), 2);
        printSorted("庫存降冪", products.clone(), 3);
    }

    public static void printSorted(String title, StoreProduct[] list, int mode) {
        insertionSort(list, mode);
        System.out.println("=== " + title + " ===");
        for (StoreProduct p : list) System.out.println(p);
        System.out.println();
    }

    public static void insertionSort(StoreProduct[] arr, int mode) {
        for (int i = 1; i < arr.length; i++) {
            StoreProduct key = arr[i];
            int j = i - 1;
            while (j >= 0 && needMove(arr[j], key, mode)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    private static boolean needMove(StoreProduct prev, StoreProduct key, int mode) {
        if (mode == 1) return prev.getPrice() > key.getPrice();
        if (mode == 2) return prev.getPrice() < key.getPrice();
        if (mode == 3) return prev.getStock() < key.getStock();
        return false;
    }
}