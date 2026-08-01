import java.util.ArrayList;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        ArrayList<Book> bookList = new ArrayList<>();
        addBook(bookList, new Book("B103", "Java教學", "資訊", 15));
        addBook(bookList, new Book("B101", "資料結構", "資訊", 40));
        addBook(bookList, new Book("B105", "心理學", "社科", 8));
        addBook(bookList, new Book("B102", "演算法", "資訊", 25));
        addBook(bookList, new Book("B101", "重複書籍", "測試", 1)); // 重複測試

        Book[] arr = bookList.toArray(new Book[0]);

        BookAlgorithms.mergeSort(arr, true);
        System.out.println("=== 依編號升冪排序 ===");
        for (Book b : arr) System.out.println(b);

        BookAlgorithms.mergeSort(arr, false);
        System.out.println("\n=== 依借閱次數降冪排序 ===");
        for (Book b : arr) System.out.println(b);

        // 3. Binary Search
        BookAlgorithms.mergeSort(arr, true);
        System.out.println("\n=== Binary Search 查詢 ===");
        int idx = BookAlgorithms.binarySearchById(arr, "B102");
        System.out.println(idx != -1 ? "找到: " + arr[idx] : "找不到指定書籍");

        System.out.println("\n=== Sequential Search 分類查詢 (資訊) ===");
        ArrayList<Book> csBooks = BookAlgorithms.sequentialSearchByCategory(bookList, "資訊");
        for (Book b : csBooks) System.out.println(b);
    }

    public static void addBook(ArrayList<Book> list, Book newBook) {
        for (Book b : list) {
            if (b.getId().equals(newBook.getId())) {
                System.out.println("新增失敗：編號 " + newBook.getId() + " 已存在！");
                return;
            }
        }
        list.add(newBook);
    }
}