public class Contestant {
    private String id;
    private String name;
    private int score;
    private int seconds;

    public Contestant(String id, String name, int score, int seconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.seconds = seconds;
    }

    public int getScore() { return score; }
    public int getSeconds() { return seconds; }

    @Override
    public String toString() {
        return id + " " + name + " | 分數: " + score + ", 完成時間: " + seconds + " 秒";
    }
}