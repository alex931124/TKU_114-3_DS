public class RectangleDemo {

    public static void printRectangleInfo(Rectangle rect, String name) {
        System.out.println("=== " + name + " ===");
        System.out.println(rect);
        System.out.printf("面積: %.2f%n", rect.calculateArea());
        System.out.printf("周長: %.2f%n", rect.calculatePerimeter());
        System.out.println("是否為正方形: " + (rect.isSquare() ? "是" : "否"));
        System.out.println();
    }

    public static void main(String[] args) {

        Rectangle r1 = new Rectangle(5.0, 3.0);
        Rectangle r2 = new Rectangle(4.5, 4.5);
        Rectangle r3 = new Rectangle(8.2, 2.0); 

        printRectangleInfo(r1, "矩形實例 1");
        printRectangleInfo(r2, "矩形實例 2");
        printRectangleInfo(r3, "矩形實例 3");

        System.out.println("=== 測試資料驗證 (Setter) ===");
        Rectangle testRect = new Rectangle(10, 10);
        System.out.println("嘗試將寬改為 -5.0: " + (testRect.setWidth(-5.0) ? "成功" : "失敗 (不合法的負數)"));
        System.out.println("當前矩形狀態: " + testRect);
    }
}