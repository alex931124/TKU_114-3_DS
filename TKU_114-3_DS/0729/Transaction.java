public class Transaction {
    private String id;
    private String account;
    private int amount;
    private int seq;

    public Transaction(String id, String account, int amount, int seq) {
        this.id = id;
        this.account = account;
        this.amount = amount;
        this.seq = seq;
    }

    public int getAmount() { return amount; }
    public int getSeq() { return seq; }

    @Override
    public String toString() {
        return String.format("單號: %s | 帳號: %s | 金額: %6d | 時間序號: %d", id, account, amount, seq);
    }
}