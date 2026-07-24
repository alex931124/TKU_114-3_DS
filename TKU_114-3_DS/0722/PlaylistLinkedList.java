/**
 * PlaylistLinkedList.java
 * 課後作業二：播放清單 - 單向鏈結串列封裝類別
 */
public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // 1. 依代碼搜尋歌曲節點
    public PlaylistNode searchById(String songId) {
        PlaylistNode current = head;
        while (current != null) {
            if (current.songId.equalsIgnoreCase(songId)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 2. 尾端新增歌曲 (檢查歌曲代碼不可重複)
    public boolean addLast(String songId, String title) {
        // 檢查代碼是否已存在
        if (searchById(songId) != null) {
            System.out.println("[新增失敗] 歌曲代碼 [" + songId + "] 已存在，無法重複加入！");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(songId, title);
        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[新增成功] 已加入歌曲: [" + songId + "] " + title);
        return true;
    }

    // 3. 依代碼刪除歌曲 (正確處理第一首、最後一首與找不到資料)
    public boolean removeById(String songId) {
        if (head == null) {
            System.out.println("[刪除失敗] 播放清單為空！");
            return false;
        }

        // 刪除第一首 (head)
        if (head.songId.equalsIgnoreCase(songId)) {
            System.out.println("[刪除成功] 已移除歌曲: [" + head.songId + "] " + head.title);
            head = head.next;
            size--;
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;

        while (current != null) {
            if (current.songId.equalsIgnoreCase(songId)) {
                System.out.println("[刪除成功] 已移除歌曲: [" + current.songId + "] " + current.title);
                previous.next = current.next; // 跨過 current 節點 (包含刪除最後一首的情境)
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("[刪除失敗] 找不到歌曲代碼: " + songId);
        return false;
    }

    // 4. 印出完整播放順序
    public void printPlaylist() {
        System.out.println("========================================");
        System.out.println("【播放清單順序】 (共 " + size + " 首歌曲)");
        if (head == null) {
            System.out.println(" (目前清單為空)");
            System.out.println("========================================");
            return;
        }

        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            System.out.println(" " + index + ". [" + current.songId + "] " + current.title);
            current = current.next;
            index++;
        }
        System.out.println("========================================");
    }
}