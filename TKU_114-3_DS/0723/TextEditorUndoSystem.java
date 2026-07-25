import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {
    private String currentText;
    private Deque<String> history;

    public TextEditorUndoSystem() {
        this.currentText = "";
        this.history = new ArrayDeque<>();
    }

    public void append(String str) {
        if (str == null || str.isEmpty()) return;
        saveState();
        currentText += str;
        System.out.println("[操作] 新增文字: \"" + str + "\"");
    }

    public void delete(int count) {
        if (count <= 0 || currentText.isEmpty()) return;
        saveState();
        int actualDelete = Math.min(count, currentText.length());
        String deletedStr = currentText.substring(currentText.length() - actualDelete);
        currentText = currentText.substring(0, currentText.length() - actualDelete);
        System.out.println("[操作] 刪除末尾 " + actualDelete + " 個字元 (刪除了: \"" + deletedStr + "\")");
    }

    public boolean undo() {
        if (history.isEmpty()) {
            System.out.println("[Undo 失敗] 沒有可復原的歷史紀錄！");
            return false;
        }
        currentText = history.pop();
        System.out.println("[Undo 成功] 已恢復至上一狀態");
        return true;
    }

    private void saveState() {
        history.push(currentText);
    }

    public void printEditorState() {
        System.out.println("----------------------------------------");
        System.out.println("【當前編輯器內容】: \"" + currentText + "\"");
        System.out.println("【可 Undo 次數】: " + history.size());
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== 文字編輯 Undo 系統 (TextEditorUndoSystem) 測試 ===");
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("\n--- [測試 1] 初始狀態與空 Undo 測試 ---");
        editor.printEditorState();
        editor.undo();

        System.out.println("\n--- [測試 2] 連續新增文字 ---");
        editor.append("Hello");
        editor.append(" World");
        editor.append("!");
        editor.printEditorState();

        System.out.println("\n--- [測試 3] 執行刪除字元 ---");
        editor.delete(1);
        editor.delete(6);
        editor.printEditorState();

        System.out.println("\n--- [測試 4] 連續 Undo 三次測試 ---");
        System.out.println(">> 第 1 次 Undo:");
        editor.undo(); 
        editor.printEditorState();

        System.out.println(">> 第 2 次 Undo:");
        editor.undo(); 
        editor.printEditorState();

        System.out.println(">> 第 3 次 Undo:");
        editor.undo(); 
        editor.printEditorState();

        System.out.println("\n--- [測試 5] 繼續 Undo 至初始狀態 ---");
        editor.undo(); 
        editor.undo(); 
        editor.printEditorState();
        editor.undo(); 
    }
}