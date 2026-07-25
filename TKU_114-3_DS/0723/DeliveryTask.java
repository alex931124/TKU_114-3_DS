public class DeliveryTask {
    private String taskId; 
    private String address; 
    private String item; 

    public DeliveryTask(String taskId, String address, String item) {
        this.taskId = taskId;
        this.address = address;
        this.item = item;
    }

    public String getTaskId() { return taskId; }
    public String getAddress() { return address; }
    public String getItem() { return item; }

    @Override
    public String toString() {
        return "[" + taskId + "] 物品: " + item + " -> 地址: " + address;
    }
}