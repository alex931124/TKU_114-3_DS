public class TaskLinkedListSystem {
    public static void main(String[] args) {
        System.out.println("=== 工作項目系統 (TaskLinkedListSystem) 測試開始 ===");
        TaskLinkedList taskList = new TaskLinkedList();

        System.out.println("\n--- [測試 1] 初始空串列檢查 ---");
        taskList.printAllTasks();
        taskList.printUncompletedTasks();
        taskList.removeTask("T001");

        System.out.println("\n--- [測試 2] 加入一般工作 (尾端) ---");
        taskList.addNormalTask("T001", "撰寫 Java 講義程式碼");
        taskList.addNormalTask("T002", "測試 Linked List 邊界條件");
        taskList.printAllTasks();

        System.out.println("\n--- [測試 3] 加入緊急工作 (前端插隊) ---");
        taskList.addEmergencyTask("E001", "修復伺服器崩潰 Bug");
        taskList.addEmergencyTask("E002", "處理客戶緊急抱怨");
        taskList.printAllTasks();

        System.out.println("\n--- [測試 4] 代碼重複性測試 ---");
        taskList.addNormalTask("T001", "重複的 Task 1");

        System.out.println("\n--- [測試 5] 標記工作為完成 ---");
        taskList.completeTask("E002"); 
        taskList.completeTask("T001"); 
        taskList.completeTask("T999");

        System.out.println("\n--- [測試 6] 列出未完成工作與總體統計 ---");
        taskList.printUncompletedTasks();
        taskList.printAllTasks();

        System.out.println("\n--- [測試 7] 刪除工作項目 ---");
        taskList.removeTask("E002");
        taskList.removeTask("T002"); 
        taskList.printAllTasks();

        System.out.println("\n--- [測試 8] 完成剩餘所有工作 ---");
        taskList.completeTask("E001");
        taskList.printUncompletedTasks();
        taskList.printAllTasks();
    }
}