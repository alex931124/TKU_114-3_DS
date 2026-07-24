public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public TaskNode searchById(String taskId) {
        TaskNode current = head;
        while (current != null) {
            if (current.taskId.equalsIgnoreCase(taskId)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean addEmergencyTask(String taskId, String description) {
        if (searchById(taskId) != null) {
            System.out.println("[新增失敗] 工作代碼 [" + taskId + "] 已存在！");
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[緊急工作加入] [" + taskId + "] " + description);
        return true;
    }

    public boolean addNormalTask(String taskId, String description) {
        if (searchById(taskId) != null) {
            System.out.println("[新增失敗] 工作代碼 [" + taskId + "] 已存在！");
            return false;
        }

        TaskNode newNode = new TaskNode(taskId, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[一般工作加入] [" + taskId + "] " + description);
        return true;
    }

    public boolean completeTask(String taskId) {
        TaskNode task = searchById(taskId);
        if (task == null) {
            System.out.println("[標記失敗] 找不到工作代碼: " + taskId);
            return false;
        }
        if (task.isCompleted) {
            System.out.println("[提示] 工作 [" + taskId + "] 先前已是完成狀態。");
            return true;
        }
        task.isCompleted = true;
        System.out.println("[完成工作] 已將 [" + taskId + "] " + task.description + " 標記為完成！");
        return true;
    }

    public boolean removeTask(String taskId) {
        if (head == null) {
            System.out.println("[刪除失敗] 工作清單為空！");
            return false;
        }

        if (head.taskId.equalsIgnoreCase(taskId)) {
            System.out.println("[刪除成功] 已移除工作: [" + head.taskId + "] " + head.description);
            head = head.next;
            size--;
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;

        while (current != null) {
            if (current.taskId.equalsIgnoreCase(taskId)) {
                System.out.println("[刪除成功] 已移除工作: [" + current.taskId + "] " + current.description);
                previous.next = current.next;
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("[刪除失敗] 找不到工作代碼: " + taskId);
        return false;
    }

    // 計算未完成工作數量
    public int getUncompletedCount() {
        int count = 0;
        TaskNode current = head;
        while (current != null) {
            if (!current.isCompleted) {
                count++;
            }
            current = current.next;
        }
        return count;
    }

    public void printAllTasks() {
        System.out.println("========================================");
        System.out.println("【所有工作清單】 (總數: " + size + " | 未完成: " + getUncompletedCount() + ")");
        if (head == null) {
            System.out.println(" (目前尚無任何工作)");
            System.out.println("========================================");
            return;
        }

        TaskNode current = head;
        while (current != null) {
            String status = current.isCompleted ? "[V] 已完成" : "[ ] 未完成";
            System.out.println(" " + status + " - [" + current.taskId + "] " + current.description);
            current = current.next;
        }
        System.out.println("========================================");
    }

    public void printUncompletedTasks() {
        System.out.println("----------------------------------------");
        System.out.println("【未完成工作項目】 (未完成數量: " + getUncompletedCount() + ")");
        if (head == null) {
            System.out.println(" (目前尚無任何工作)");
            System.out.println("----------------------------------------");
            return;
        }

        TaskNode current = head;
        boolean hasUncompleted = false;
        while (current != null) {
            if (!current.isCompleted) {
                System.out.println(" [ ] - [" + current.taskId + "] " + current.description);
                hasUncompleted = true;
            }
            current = current.next;
        }

        if (!hasUncompleted) {
            System.out.println(" (太棒了！目前所有工作皆已完成)");
        }
        System.out.println("----------------------------------------");
    }
}