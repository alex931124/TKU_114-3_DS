import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RepairSchedulingSystem {
    public static void main(String[] args) {
        ArrayList<RepairTask> allTasks = new ArrayList<>();
        Deque<RepairTask> waitingQueue = new ArrayDeque<>();
        Deque<RepairTask> completedStack = new ArrayDeque<>();

        addTask(allTasks, waitingQueue, new RepairTask("R01", "筆電", 2));
        addTask(allTasks, waitingQueue, new RepairTask("R02", "螢幕", 5));
        addTask(allTasks, waitingQueue, new RepairTask("R03", "主機", 5));
        addTask(allTasks, waitingQueue, new RepairTask("R04", "印表機", 1));

        RepairTask[] arr = allTasks.toArray(new RepairTask[0]);
        RepairAlgorithms.mergeSortByPriority(arr);
        System.out.println("=== 工作依優先度降冪排序 ===");
        for (RepairTask t : arr) System.out.println(t);

        System.out.println("\n=== 處理工作與復原測試 ===");
        processNext(waitingQueue, completedStack);
        processNext(waitingQueue, completedStack);
        undoLast(waitingQueue, completedStack);

        System.out.println("\n=== 系統統計 ===");
        System.out.println("總工作數: " + allTasks.size());
        System.out.println("等待處理: " + waitingQueue);
        System.out.println("已完成數: " + completedStack.size());
    }

    public static void addTask(ArrayList<RepairTask> all, Deque<RepairTask> queue, RepairTask task) {
        all.add(task);
        queue.offer(task);
    }

    public static void processNext(Deque<RepairTask> queue, Deque<RepairTask> stack) {
        RepairTask task = queue.poll();
        if (task == null) {
            System.out.println("無待處理工作");
            return;
        }
        stack.push(task);
        System.out.println("完成維修: " + task);
    }

    public static void undoLast(Deque<RepairTask> queue, Deque<RepairTask> stack) {
        RepairTask task = stack.poll();
        if (task == null) {
            System.out.println("無可復原的工作");
            return;
        }
        queue.offerFirst(task);
        System.out.println("復原工作: " + task);
    }
}