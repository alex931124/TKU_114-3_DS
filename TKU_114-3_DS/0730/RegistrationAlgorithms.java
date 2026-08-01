import java.util.ArrayList;

public class RegistrationAlgorithms {
    public static void mergeSortById(Registration[] reg) {
        if (reg == null || reg.length < 2) return;
        Registration[] temp = new Registration[reg.length];
        mergeSort(reg, temp, 0, reg.length - 1);
    }

    private static void mergeSort(Registration[] arr, Registration[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            temp[k++] = (arr[i].getId().compareTo(arr[j].getId()) <= 0) ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }

    public static int binarySearchById(Registration[] arr, String id) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int cmp = id.compareTo(arr[mid].getId());
            if (cmp == 0) return mid;
            if (cmp < 0) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

    public static ArrayList<Registration> sequentialSearchByName(ArrayList<Registration> list, String name) {
        ArrayList<Registration> res = new ArrayList<>();
        for (Registration r : list) {
            if (r.getName().equalsIgnoreCase(name)) res.add(r);
        }
        return res;
    }
}