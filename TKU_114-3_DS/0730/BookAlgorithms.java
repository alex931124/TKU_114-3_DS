import java.util.ArrayList;

public class BookAlgorithms {
    public static void mergeSort(Book[] books, boolean byIdAsc) {
        if (books == null || books.length < 2) return;
        Book[] temp = new Book[books.length];
        mergeSort(books, temp, 0, books.length - 1, byIdAsc);
    }

    private static void mergeSort(Book[] books, Book[] temp, int left, int right, boolean byIdAsc) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(books, temp, left, mid, byIdAsc);
        mergeSort(books, temp, mid + 1, right, byIdAsc);
        merge(books, temp, left, mid, right, byIdAsc);
    }

    private static void merge(Book[] books, Book[] temp, int left, int mid, int right, boolean byIdAsc) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            boolean condition = byIdAsc 
                ? books[i].getId().compareTo(books[j].getId()) <= 0
                : books[i].getBorrowCount() >= books[j].getBorrowCount();
            temp[k++] = condition ? books[i++] : books[j++];
        }
        while (i <= mid) temp[k++] = books[i++];
        while (j <= right) temp[k++] = books[j++];
        for (int idx = left; idx <= right; idx++) books[idx] = temp[idx];
    }

    public static int binarySearchById(Book[] books, String targetId) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = targetId.compareTo(books[mid].getId());
            if (cmp == 0) return mid;
            if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static ArrayList<Book> sequentialSearchByCategory(ArrayList<Book> books, String category) {
        ArrayList<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getCategory().equalsIgnoreCase(category)) {
                result.add(b);
            }
        }
        return result;
    }
}