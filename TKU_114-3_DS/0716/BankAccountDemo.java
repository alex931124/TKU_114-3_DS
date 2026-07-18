public class BankAccountDemo {
    public static void main(String[] args) {

        BankAccount account1 = new BankAccount("A001", "Amy", 5000);
        BankAccount account2 = new BankAccount("A002", "Ben", 1000);

        System.out.println("=== 初始帳戶狀態 ===");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println();

        System.out.println("=== 測試存款功能 ===");
        System.out.println("Amy 存入 500 元: " + (account1.deposit(500) ? "成功" : "失敗"));
        System.out.println("Amy 嘗試存入 -100 元: " + (account1.deposit(-100) ? "成功" : "失敗 (金額不合法)"));
        System.out.println("當前 Amy 餘額: " + account1.getBalance());
        System.out.println();

        System.out.println("=== 測試提款功能 ===");
        System.out.println("Ben 提出 300 元: " + (account2.withdraw(300) ? "成功" : "失敗"));
        System.out.println("Ben 嘗試超額提款 2000 元: " + (account2.withdraw(2000) ? "成功" : "失敗 (餘額不足)"));
        System.out.println("當前 Ben 餘額: " + account2.getBalance());
        System.out.println();

        System.out.println("=== 測試成功轉帳 ===");
        System.out.println("Amy 轉帳 2000 元給 Ben: " + (account1.transferTo(account2, 2000) ? "成功" : "失敗"));
        System.out.println("Amy 狀態: " + account1);
        System.out.println("Ben 狀態: " + account2);
        System.out.println();

        System.out.println("=== 測試失敗轉帳（金額不足） ===");
        System.out.println("Ben 嘗試轉帳 5000 元給 Amy: " + (account2.transferTo(account1, 5000) ? "成功" : "失敗"));
        System.out.println("【檢查】失敗後兩者餘額是否未變：");
        System.out.println("Amy 狀態: " + account1);
        System.out.println("Ben 狀態: " + account2);
        System.out.println();

        System.out.println("=== 測試失敗轉帳（轉給自己） ===");
        System.out.println("Amy 嘗試轉帳 500 元給自己: " + (account1.transferTo(account1, 500) ? "成功" : "失敗"));
    }
}