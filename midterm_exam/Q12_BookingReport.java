public class Q12_BookingReport {
    public static void main(String[] args) {
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };
        System.out.println("已確認筆數：" + countConfirmed(bookings));
        System.out.println("已確認收入：" + calculateConfirmedRevenue(bookings));
        Q12_Booking found = findById(bookings, "b003");
        System.out.println("搜尋結果：" + found);
        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約：" + largest);
    }

    public static int countConfirmed(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < bookings.length; i++) {

            if (bookings[i] != null && bookings[i].isConfirmed()) {
                count++;
            }
        }
        return count;
    }

    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        if (bookings == null) {
            return 0.0;
        }

        double totalRevenue = 0.0;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && bookings[i].isConfirmed()) {
                totalRevenue += bookings[i].getTotalPrice();
            }
        }
        return totalRevenue;
    }

    public static Q12_Booking findById(Q12_Booking[] bookings, String id) {
        if (bookings == null || id == null) {
            return null;
        }

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && bookings[i].getId() != null) {

                if (bookings[i].getId().equalsIgnoreCase(id)) {
                    return bookings[i];
                }
            }
        }
        return null;
    }

    public static Q12_Booking findLargestConfirmed(Q12_Booking[] bookings) {
        if (bookings == null || bookings.length == 0) {
            return null;
        }

        Q12_Booking largest = null;

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null && bookings[i].isConfirmed()) {
                if (largest == null) {
                    largest = bookings[i];
                } 

                else if (bookings[i].getTotalPrice() > largest.getTotalPrice()) {
                    largest = bookings[i];
                }
            }
        }

        return largest;
    }
}