import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = scanner.nextLine();

        System.out.print("請輸入 Java 成績：");
        double javaScore = scanner.nextDouble();

        System.out.print("請輸入 English 成績：");
        double englishScore = scanner.nextDouble();

        System.out.print("請輸入 Math 成績：");
        double mathScore = scanner.nextDouble();
        scanner.nextLine();

        double average = (javaScore + englishScore + mathScore) / 3.0;
        String status;
        String grade;

        if (average >= 60) {
            status = "及格";
        } else {
            status = "不及格";
        }

        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int option = -1;
        while (option != 0) {
            System.out.println();
            System.out.println("=== 成績查詢選單 ===");
            System.out.println("1. 顯示平均分數");
            System.out.println("2. 顯示及格狀態");
            System.out.println("3. 顯示等第");
            System.out.println("0. 離開");
            System.out.print("請選擇：");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("姓名：" + name);
                    System.out.printf("平均分數：%.2f%n", average);
                    break;
                case 2:
                    System.out.println("姓名：" + name);
                    System.out.println("及格狀態：" + status);
                    break;
                case 3:
                    System.out.println("姓名：" + name);
                    System.out.println("等第：" + grade);
                    break;
                case 0:
                    System.out.println("謝謝使用！");
                    break;
                default:
                    System.out.println("無效的選項");
            }
        }

        scanner.close();
    }
}