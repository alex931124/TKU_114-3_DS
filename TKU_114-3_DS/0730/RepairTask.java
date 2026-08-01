public class RepairTask {
    private String id;
    private String device;
    private int priority; 

    public RepairTask(String id, String device, int priority) {
        this.id = id;
        this.device = device;
        this.priority = priority;
    }

    public String getId() { return id; }
    public String getDevice() { return device; }
    public int getPriority() { return priority; }

    @Override
    public String toString() {
        return String.format("[%s] 設備: %-8s | 優先級: %d", id, device, priority);
    }
}