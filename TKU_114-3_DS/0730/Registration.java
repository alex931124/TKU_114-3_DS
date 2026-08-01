public class Registration {
    private String id;
    private String name;

    public Registration(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "編號: " + id + " | 姓名: " + name;
    }
}