class IntNode {
    int data;
    IntNode next;

    public IntNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class NumberHistoryList {
    private IntNode head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int value) {
        IntNode newNode = new IntNode(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[操作] 前端新增: " + value);
    }

    public void addLast(int value) {
        IntNode newNode = new IntNode(value);
        if (head == null) {
            head = newNode;
        } else {
            IntNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[操作] 尾端新增: " + value);
    }

    public boolean contains(int target) {
        IntNode current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            System.out.println("[操作] 嘗試刪除 " + target + " -> 失敗：串列為空");
            return false;
        }

        if (head.data == target) {
            head = head.next;
            size--;
            System.out.println("[操作] 成功刪除頭節點: " + target);
            return true;
        }

        IntNode previous = head;
        IntNode current = head.next;

        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                System.out.println("[操作] 成功刪除節點: " + target);
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("[操作] 嘗試刪除 " + target + " -> 失敗：找不到此數字");
        return false;
    }

    public void print() {
        System.out.print("目前串列內容: ");
        if (head == null) {
            System.out.println("null (空串列)");
            return;
        }
        IntNode current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public long sum() {
        long total = 0;
        IntNode current = head;
        while (current != null) {
            total += current.data;
            current = current.next;
        }
        return total;
    }

    public Integer max() {
        if (head == null) {
            return null;
        }
        int maxVal = head.data;
        IntNode current = head.next;
        while (current != null) {
            if (current.data > maxVal) {
                maxVal = current.data;
            }
            current = current.next;
        }
        return maxVal;
    }

    public Integer min() {
        if (head == null) {
            return null;
        }
        int minVal = head.data;
        IntNode current = head.next;
        while (current != null) {
            if (current.data < minVal) {
                minVal = current.data;
            }
            current = current.next;
        }
        return minVal;
    }

    public void printStats() {
        System.out.println("----------------------------------------");
        System.out.println("【串列統計資訊】");
        System.out.println("Size (節點數量): " + size());
        
        if (isEmpty()) {
            System.out.println("總和 (Sum): 0");
            System.out.println("最大值 (Max): 無資料 (空串列)");
            System.out.println("最小值 (Min): 無資料 (空串列)");
        } else {
            System.out.println("總和 (Sum): " + sum());
            System.out.println("最大值 (Max): " + max());
            System.out.println("最小值 (Min): " + min());
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== 數字紀錄串列 (NumberHistoryList) 測試程式 ===");
        NumberHistoryList list = new NumberHistoryList();

        System.out.println("\n--- [操作 1] 初始狀態檢查 ---");
        list.print();
        list.printStats();

        System.out.println("\n--- [操作 2] 前端新增 ---");
        list.addFirst(25);
        list.print();

        System.out.println("\n--- [操作 3] 尾端新增 ---");
        list.addLast(40);
        list.print();

        System.out.println("\n--- [操作 4] 前端新增 ---");
        list.addFirst(10);
        list.print();
        list.printStats();

        System.out.println("\n--- [操作 5] 尾端新增 ---");
        list.addLast(85);
        list.print();

        System.out.println("\n--- [操作 6] 搜尋元素 ---");
        System.out.println("搜尋 25: " + (list.contains(25) ? "找到資料" : "找不到資料"));
        System.out.println("搜尋 99: " + (list.contains(99) ? "找到資料" : "找不到資料"));

        System.out.println("\n--- [操作 7] 刪除中間節點 ---");
        list.removeValue(25);
        list.print();
        list.printStats();

        System.out.println("\n--- [操作 8] 刪除不存在的數字 ---");
        list.removeValue(99);
        list.print();

        System.out.println("\n--- [操作 9] 刪除頭節點 ---");
        list.removeValue(10);
        list.print();

        System.out.println("\n--- [操作 10] 清空串列測試 ---");
        list.removeValue(40);
        list.removeValue(85);
        list.print();
        list.printStats();
    }
}