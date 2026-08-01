import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class EventRegistrationSystem {
    private static final int CAPACITY = 2;

    public static void main(String[] args) {
        ArrayList<Registration> mainList = new ArrayList<>();
        Deque<Registration> waitQueue = new ArrayDeque<>();
        Deque<Registration> cancelStack = new ArrayDeque<>();

        register(mainList, waitQueue, new Registration("R102", "Alice"));
        register(mainList, waitQueue, new Registration("R101", "Bob"));
        register(mainList, waitQueue, new Registration("R103", "Charlie")); 

        Registration[] arr = mainList.toArray(new Registration[0]);
        RegistrationAlgorithms.mergeSortById(arr);
        int idx = RegistrationAlgorithms.binarySearchById(arr, "R101");
        System.out.println("Binary Search 查詢 R101: " + (idx != -1 ? arr[idx] : "無此紀錄"));

        cancelRegistration(mainList, waitQueue, cancelStack, "R101");
        undoCancel(mainList, waitQueue, cancelStack);
    }

    public static void register(ArrayList<Registration> main, Deque<Registration> wait, Registration reg) {
        for (Registration r : main) {
            if (r.getId().equals(reg.getId())) {
                System.out.println("報名失敗：重複編號 " + reg.getId());
                return;
            }
        }
        if (main.size() < CAPACITY) {
            main.add(reg);
            System.out.println("報名成功: " + reg);
        } else {
            wait.offer(reg);
            System.out.println("正取已滿，進入候補: " + reg);
        }
    }

    public static void cancelRegistration(ArrayList<Registration> main, Deque<Registration> wait, Deque<Registration> cancelStack, String id) {
        Registration target = null;
        for (Registration r : main) {
            if (r.getId().equals(id)) { target = r; break; }
        }
        if (target != null) {
            main.remove(target);
            cancelStack.push(target);
            System.out.println("取消成功: " + target);
            if (!wait.isEmpty()) {
                Registration promoted = wait.poll();
                main.add(promoted);
                System.out.println("候補補上: " + promoted);
            }
        } else {
            System.out.println("取消失敗：找不到報名紀錄");
        }
    }

    public static void undoCancel(ArrayList<Registration> mainList, Deque<Registration> wait, Deque<Registration> cancelStack) {
        Registration restored = cancelStack.poll();
        if (restored == null) {
            System.out.println("無可復原的取消動作");
            return;
        }
        System.out.println("復原取消: " + restored);
    }
}