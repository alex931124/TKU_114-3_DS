public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };
        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }
        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {

        if (record == null || record.trim().isEmpty()) {
            return false;
        }

        int pipeCount = 0;
        for (int i = 0; i < record.length(); i++) {
            if (record.charAt(i) == '|') {
                pipeCount++;
            }
        }
        if (pipeCount != 3) {
            return false;
        }

        String[] parts = record.split("\\|", -1);
        if (parts.length != 4) {
            return false;
        }

        String code = parts[0].trim();
        String name = parts[1].trim();
        if (code.isEmpty() || name.isEmpty()) {
            return false;
        }

        try {
            int quantity = Integer.parseInt(parts[2].trim());
            int price = Integer.parseInt(parts[3].trim());
            
            if (quantity <= 0 || price < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] parts = record.split("\\|", -1);
        int quantity = Integer.parseInt(parts[2].trim());
        int price = Integer.parseInt(parts[3].trim());

        return quantity * price;
    }

    public static int countValidRecords(String[] records) {
        if (records == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < records.length; i++) {
            if (isValidRecord(records[i])) {
                count++;
            }
        }
        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        if (records == null) {
            return 0;
        }

        int recordCheckpointBF8E = 0;
        for (int i = 0; i < records.length; i++) {
            if (isValidRecord(records[i])) {
                recordCheckpointBF8E += calculateRecordTotal(records[i]);
            }
        }
        return recordCheckpointBF8E;
    }
}