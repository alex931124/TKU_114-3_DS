public class RepairAlgorithms {
    public static void mergeSortByPriority(RepairTask[] tasks) {
        if (tasks == null || tasks.length < 2) return;
        RepairTask[] temp = new RepairTask[tasks.length];
        mergeSort(tasks, temp, 0, tasks.length - 1);
    }

    private static void mergeSort(RepairTask[] arr, RepairTask[] temp, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;
        mergeSort(arr, temp, left, mid);
        mergeSort(arr, temp, mid + 1, right);
        merge(arr, temp, left, mid, right);
    }

    private static void merge(RepairTask[] arr, RepairTask[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            temp[k++] = (arr[i].getPriority() >= arr[j].getPriority()) ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (int idx = left; idx <= right; idx++) arr[idx] = temp[idx];
    }
}