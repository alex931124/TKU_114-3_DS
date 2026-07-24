public class PlaylistSystem {
    public static void main(String[] args) {
        System.out.println("=== 播放清單系統 (PlaylistSystem) 測試開始 ===");
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        System.out.println("\n--- [測試 1] 初始空清單檢查 ---");
        playlist.printPlaylist();
        playlist.removeById("S001");

        System.out.println("\n--- [測試 2] 新增歌曲至播放清單 ---");
        playlist.addLast("S001", "晴天");
        playlist.addLast("S002", "七里香");
        playlist.addLast("S003", "稻香");
        playlist.addLast("S004", "告白氣球");
        playlist.printPlaylist();

        System.out.println("\n--- [測試 3] 測試代碼重複防護 ---");
        playlist.addLast("S002", "重複的七里香");

        System.out.println("\n--- [測試 4] 搜尋歌曲 ---");
        PlaylistNode found = playlist.searchById("S003");
        if (found != null) {
            System.out.println("搜尋成功 -> 找到歌曲: [" + found.songId + "] " + found.title);
        } else {
            System.out.println("搜尋失敗 -> 找不到歌曲！");
        }

        PlaylistNode notFound = playlist.searchById("S999");
        System.out.println("搜尋不存在代碼 S999 -> " + (notFound != null ? "找到" : "找不到歌曲"));

        System.out.println("\n--- [測試 5] 刪除中間歌曲 (S002) ---");
        playlist.removeById("S002");
        playlist.printPlaylist();

        System.out.println("\n--- [測試 6] 刪除第一首歌曲 (S001) ---");
        playlist.removeById("S001");
        playlist.printPlaylist();

        System.out.println("\n--- [測試 7] 刪除最後一首歌曲 (S004) ---");
        playlist.removeById("S004");
        playlist.printPlaylist();

        System.out.println("\n--- [測試 8] 清空清單 (刪除 S003) ---");
        playlist.removeById("S003");
        playlist.printPlaylist();
    }
}