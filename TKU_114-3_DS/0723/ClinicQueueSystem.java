import java.util.ArrayDeque;
import java.util.Deque;

public class ClinicQueueSystem {
    private Deque<Patient> queue;
    private int totalServedCount;

    public ClinicQueueSystem() {
        this.queue = new ArrayDeque<>();
        this.totalServedCount = 0;
    }

    public boolean containsNumber(String number) {
        for (Patient p : queue) {
            if (p.getNumber().equalsIgnoreCase(number)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(String number, String name, String department) {
        if (containsNumber(number)) {
            System.out.println("[掛號失敗] 號碼 [" + number + "] 已重複！");
            return false;
        }
        Patient p = new Patient(number, name, department);
        queue.offer(p);
        System.out.println("[掛號成功] " + p);
        return true;
    }

    public Patient callNext() {
        Patient p = queue.poll();
        if (p == null) {
            System.out.println("[叫號提示] 當前無等待患者。");
            return null;
        }
        totalServedCount++;
        System.out.println("[叫號服務] 請 " + p + " 至診間就診");
        return p;
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public void printClinicStatus() {
        System.out.println("========================================");
        System.out.println("【診所叫號系統狀態】");
        System.out.println("當前等待總人數: " + queue.size());
        System.out.println("已完成服務總人數: " + totalServedCount);

        int internalMedCount = 0; 
        int surgeryCount = 0;    
        int otherCount = 0;       

        for (Patient p : queue) {
            if ("內科".equals(p.getDepartment())) {
                internalMedCount++;
            } else if ("外科".equals(p.getDepartment())) {
                surgeryCount++;
            } else {
                otherCount++;
            }
        }

        System.out.println(" -> 內科等待人數: " + internalMedCount);
        System.out.println(" -> 外科等待人數: " + surgeryCount);
        if (otherCount > 0) {
            System.out.println(" -> 其他科別等待人數: " + otherCount);
        }

        System.out.println("\n【當前等待清單】:");
        if (queue.isEmpty()) {
            System.out.println(" (目前無人等待)");
        } else {
            int idx = 1;
            for (Patient p : queue) {
                System.out.println("  " + idx + ". " + p);
                idx++;
            }
        }
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        System.out.println("=== 診所叫號系統 (ClinicQueueSystem) 測試 ===");
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("\n--- [測試 1] 初始狀態檢查 ---");
        clinic.printClinicStatus();
        clinic.callNext();

        System.out.println("\n--- [測試 2] 患者掛號 ---");
        clinic.register("A001", "張三", "內科");
        clinic.register("B001", "李四", "外科");
        clinic.register("A002", "王五", "內科");
        clinic.register("B002", "趙六", "外科");
        clinic.printClinicStatus();

        System.out.println("\n--- [測試 3] 測試重複號碼掛號 ---");
        clinic.register("A001", "陳七", "內科");

        System.out.println("\n--- [測試 4] 叫號服務 ---");
        System.out.println("下一位準備就診: " + clinic.peekNext());
        clinic.callNext();
        clinic.callNext();
        clinic.printClinicStatus();

        System.out.println("\n--- [測試 5] 服務剩餘患者 ---");
        clinic.callNext();
        clinic.callNext(); 
        clinic.printClinicStatus();
    }
}