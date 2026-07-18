import java.util.Scanner;

public class ProductManagementSystem {

    private static final int MAX_CAPACITY = 10;
    private static Product[] products = new Product[MAX_CAPACITY];
    private static int productCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    private static int totalOperations = 0;

    public static void main(String[] args) {

        initDefaultProducts();

        runTestCases();

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readUserChoice();
            running = executeChoice(choice);
        }
    }

    private static void initDefaultProducts() {
        addProductDirectly(new Product("Keyboard", 890, 12));
        addProductDirectly(new Product("Mouse", 490, 20));
        addProductDirectly(new Product("Monitor", 5200, 5));
        addProductDirectly(new Product("Headphones", 1290, 8));
        addProductDirectly(new Product("MousePad", 290, 30));
        System.out.println("【系統】已成功初始化 5 項預設商品。 (目前剩餘空間: 5)");
    }

    private static void printMenu() {
        System.out.println("\n===== 物件導向商品管理系統 =====");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("0. 結束並顯示操作摘要");
        System.out.print("請選擇操作項目 (0-8): ");
    }

    private static int readUserChoice() {
        try {
            String input = scanner.nextLine();
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }

    private static boolean executeChoice(int choice) {
        totalOperations++;
        switch (choice) {
            case 1:
                showAllProducts();
                break;
            case 2:
                searchProductUI();
                break;
            case 3:
                addProductUI();
                break;
            case 4:
                sellProductUI();
                break;
            case 5:
                restockProductUI();
                break;
            case 6:
                modifyPriceUI();
                break;
            case 7:
                showLowStockProducts();
                break;
            case 8:
                showTotalInventoryValue();
                break;
            case 0:
                showSummaryAndExit();
                return false;
            default:
                totalOperations--;
                System.out.println("【錯誤】無效的選項，請輸入 0 到 8 之間的數字。");
        }
        return true;
    }

    private static Product findProductByName(String name) {
        if (name == null) return null;
        String cleanName = name.trim();
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].getName().equalsIgnoreCase(cleanName)) {
                return products[i];
            }
        }
        return null;
    }

    private static boolean addProductDirectly(Product p) {
        if (productCount >= MAX_CAPACITY || p == null) {
            return false;
        }
        if (findProductByName(p.getName()) != null) {
            return false; // 重複名稱
        }
        products[productCount] = p;
        productCount++;
        return true;
    }

    private static void showAllProducts() {
        System.out.println("\n--- 全部商品清單 ---");
        for (int i = 0; i < productCount; i++) {
            System.out.println("[" + (i + 1) + "] " + products[i]);
        }
    }

    private static void searchProductUI() {
        System.out.print("請輸入欲搜尋的商品名稱: ");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p != null) {
            System.out.println("【尋獲商品】-> " + p);
        } else {
            System.out.println("【提示】找不到此商品。");
        }
    }

    private static void addProductUI() {
        if (productCount >= MAX_CAPACITY) {
            System.out.println("【錯誤】陣列已滿，無法新增商品！(上限 10 項)");
            return;
        }
        System.out.print("請輸入新商品名稱: ");
        String name = scanner.nextLine();
        if (findProductByName(name) != null) {
            System.out.println("【錯誤】不可新增重複名稱的商品！");
            return;
        }
        try {
            System.out.print("請輸入價格: ");
            int price = Integer.parseInt(scanner.nextLine().trim());
            System.out.print("請輸入初始庫存: ");
            int stock = Integer.parseInt(scanner.nextLine().trim());

            Product p = new Product(name, price, stock);
            if (addProductDirectly(p)) {
                System.out.println("【成功】成功新增商品: " + p.getName());
            }
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】輸入格式不正確，數字請輸入整數。");
        }
    }

    private static void sellProductUI() {
        System.out.print("請輸入要出售的商品名稱: ");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("【錯誤】找不到該商品。");
            return;
        }
        try {
            System.out.print("請輸入出售數量: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());
            if (p.sell(qty)) {
                System.out.println("【成功】出售成功！當前狀態: " + p);
            } else {
                System.out.println("【失敗】出售數量錯誤或庫存不足！");
            }
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】請輸入正確的整數數量。");
        }
    }

    private static void restockProductUI() {
        System.out.print("請輸入要補貨的商品名稱: ");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("【錯誤】找不到該商品。");
            return;
        }
        try {
            System.out.print("請輸入補貨數量: ");
            int qty = Integer.parseInt(scanner.nextLine().trim());
            if (p.restock(qty)) {
                System.out.println("【成功】補貨成功！當前狀態: " + p);
            } else {
                System.out.println("【失敗】補貨數量必須大於 0！");
            }
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】請輸入正確的整數數量。");
        }
    }

    private static void modifyPriceUI() {
        System.out.print("請輸入要修改價格的商品名稱: ");
        String name = scanner.nextLine();
        Product p = findProductByName(name);
        if (p == null) {
            System.out.println("【錯誤】找不到該商品。");
            return;
        }
        try {
            System.out.print("請輸入新價格: ");
            int newPrice = Integer.parseInt(scanner.nextLine().trim());
            if (p.setPrice(newPrice)) {
                System.out.println("【成功】價格修改成功！當前狀態: " + p);
            } else {
                System.out.println("【失敗】新價格必須大於 0！");
            }
        } catch (NumberFormatException e) {
            System.out.println("【錯誤】請輸入正確的整數價格。");
        }
    }

    private static void showLowStockProducts() {
        System.out.println("\n--- 低庫存商品清單 (庫存 < 10) ---");
        boolean hasLowStock = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].isLowStock()) {
                System.out.println(products[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前沒有任何商品處於低庫存狀態。");
        }
    }

    private static void showTotalInventoryValue() {
        long totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                totalValue += products[i].getInventoryValue();
            }
        }
        System.out.println("\n【報表】當前全店商品庫存總價值為: " + totalValue + " 元");
    }

    private static void showSummaryAndExit() {
        System.out.println("\n=================================");
        System.out.println("系統已結束。感謝您的使用！");
        System.out.println("本次執行階段有效操作次數: " + (totalOperations - 1) + " 次");
        System.out.println("=================================");
    }

    private static void runTestCases() {
        System.out.println("\n========== 開始執行內部測試案例 (10組) ==========");

        System.out.println("測試 1 (搜尋 '  mOuSe '): " + (findProductByName("  mOuSe ") != null ? "通過" : "失敗"));

        System.out.println("測試 2 (搜尋不存在商品): " + (findProductByName("Apple") == null ? "通過" : "失敗"));

        Product mouse = findProductByName("Mouse");
        int oldStock = mouse.getStock();
        mouse.restock(10);
        System.out.println("測試 3 (Mouse 補貨 10 件): " + (mouse.getStock() == oldStock + 10 ? "通過" : "失敗"));

        System.out.println("測試 4 (Mouse 補貨 -5 件): " + (!mouse.restock(-5) ? "通過" : "失敗"));

        oldStock = mouse.getStock();
        System.out.println("測試 5 (Mouse 出售 5 件): " + (mouse.sell(5) && mouse.getStock() == oldStock - 5 ? "通過" : "失敗"));
  
        System.out.println("測試 6 (Mouse 惡意超額出售 999 件): " + (!mouse.sell(999) ? "通過" : "失敗"));

        System.out.println("測試 7 (修改 Mouse 價格為 550): " + (mouse.setPrice(550) && mouse.getPrice() == 550 ? "通過" : "失敗"));
   
        System.out.println("測試 8 (修改 Mouse 價格為 -10): " + (!mouse.setPrice(-10) && mouse.getPrice() == 550 ? "通過" : "失敗"));
  
        System.out.println("測試 9 (嘗試新增重複商品 'Keyboard'): " + (!addProductDirectly(new Product("Keyboard", 100, 10)) ? "通過" : "失敗"));

        addProductDirectly(new Product("Pad", 10000, 5));   
        addProductDirectly(new Product("Phone", 25000, 3));   
        addProductDirectly(new Product("Cable", 190, 50));  
        addProductDirectly(new Product("Hub", 1490, 11));   
        addProductDirectly(new Product("Charger", 590, 15)); 
        boolean overLimit = addProductDirectly(new Product("Laptop", 45000, 2));
        System.out.println("測試 10 (陣列滿載上限 10 項防禦測試): " + (!overLimit && productCount == 10 ? "通過" : "失敗"));
        
        System.out.println("================================================\n");
    }
}