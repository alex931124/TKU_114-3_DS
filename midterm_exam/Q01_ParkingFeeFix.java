public class Q01_ParkingFeeFix {
    public static void main(String[] args) {
        int[] testMinutes = {-20, 30, 31, 60, 61, 120, 121, 420};
        for (int minutes : testMinutes) {
            int fee = calculateFee(minutes);
            System.out.println("停車 " + minutes + " 分鐘，費用：" + fee + " 元");
        }
    }

    public static int calculateFee(int minutes) {
        if (minutes < 0) {
            return -1;
        }

        if (minutes <= 30) {
            return 0;
        }
        
        int fee = 0;
        
        if (minutes <= 120) {
            int units = (minutes - 31) / 30 + 1;
            fee = units * 20;
        } 
        
        else {
            int units = (minutes - 121) / 60 + 1;
            fee = 60 + (units * 30);
        }

        if (fee > 180) {
            fee = 180;
        }
        
        return fee;
    }
}