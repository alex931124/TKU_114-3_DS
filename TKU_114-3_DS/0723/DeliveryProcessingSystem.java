import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {
    private Deque<DeliveryTask> waitingQueue;
    private Deque<DeliveryTask> completedStack; 

    public DeliveryProcessingSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
    }

    public void addDeliveryTask(String taskId, String address, String item) {
        DeliveryTask task = new DeliveryTask(taskId, address, item);
        waitingQueue.offer(task);
        System.out.println("[新增工作] " + task);
    }

    public boolean processNextTask() {
        DeliveryTask task = waitingQueue.poll();
        if (task == null) {
            System.out.println("[處理失敗] 當前沒有等待配送的工作！");
            return false;
        }
        completedStack.push(task);
        System.out.println("[完成配送] " + task);
        return true;
    }

    public DeliveryTask peekNextTask() {
        return waitingQueue.peek();
    }

    public boolean undoLastCompleted() {
        DeliveryTask task = completedStack.poll();
        if (task == null) {
            System.out.println("[復原失敗] 目前沒有可復原的完成紀錄！");
            return false;
        }
        waitingQueue.offer(task); 
        System.out.println("[復原操作] 已將工作 [" + task.getTaskId() + "] 移回等待隊列尾端");
        return true;
    }

    public void printSystemReport() {
        System.out.println("========================================");
        System.out.println("【配送工作流程系統報告】");
        System.out.println("等待配送數量: " + waitingQueue.size());
        System.out.println("已完成配送數量: " + completedStack.size());

        System.out.println("\n>> [等待配送隊列 (Queue)]:");
        if (waitingQueue.isEmpty()) {
            System.out.println("  (無待配送工作)");
        } else {
            int idx = 1;
            for (DeliveryTask task : waitingQueue) {
                System.out.println("  " + idx + ". " + task);
                idx++;
            }
        }

        System.out.println("\n>> [最近完成歷史 (Stack Top 在最上方)]:");
        if (completedStack.isEmpty()) {
            System.out.println("  (無完成紀錄)");
        } else {
            for (DeliveryTask task : completedStack) {
                System.out.println("  * " + task);
            }
        }
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        System.out.println("=== 配送工作流程系統 (DeliveryProcessingSystem) 測試 ===");
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("\n--- [測試 1] 初始狀態檢查 ---");
        system.printSystemReport();
        system.processNextTask();
        system.undoLastCompleted();

        System.out.println("\n--- [測試 2] 新增配送工作 ---");
        system.addDeliveryTask("D001", "新北市淡水區水源街二段1號", "筆記型電腦");
        system.addDeliveryTask("D002", "新北市淡水區水源街二段2號", "智慧型手機");
        system.addDeliveryTask("D003", "新北市淡水區水源街二段3號", "藍芽耳機");
        system.printSystemReport();

        System.out.println("\n--- [測試 3] 依序完成工作 (D001, D002) ---");
        System.out.println("下一位待配送: " + system.peekNextTask());
        system.processNextTask(); 
        system.processNextTask(); 
        system.printSystemReport();

        System.out.println("\n--- [測試 4] 復原最近完成的工作 (Undo) ---");
        system.undoLastCompleted(); 
        system.printSystemReport();

        System.out.println("\n--- [測試 5] 繼續處理配送 ---");
        system.processNextTask(); 
        system.processNextTask(); 
        system.printSystemReport();
    }
}