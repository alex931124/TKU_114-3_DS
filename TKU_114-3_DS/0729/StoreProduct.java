public class StoreProduct {
    private String name;
    private int price;
    private int stock;

    public StoreProduct(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() { return price; }
    public int getStock() { return stock; }

    @Override
    public String toString() {
        return String.format("%-10s | 價格: %5d | 庫存: %3d", name, price, stock);
    }
}