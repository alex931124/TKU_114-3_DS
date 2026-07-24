/**
 * PlaylistNode.java
 * 課後作業二：播放清單 - 節點類別
 * 
 * 保存歌曲代碼 (songId)、歌曲名稱 (title) 與指向下一個節點的參考 (next)
 */
public class PlaylistNode {
    String songId;
    String title;
    PlaylistNode next;

    public PlaylistNode(String songId, String title) {
        this.songId = songId;
        this.title = title;
        this.next = null;
    }
}