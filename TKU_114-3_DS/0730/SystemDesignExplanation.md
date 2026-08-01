
# 系統資料結構與演算法設計說明

## 1. 完整報名資料保存 (`ArrayList`)

- **應用位置**: `EventRegistrationSystem.java` - `mainList`
- **選擇原因**: 需要頻繁依照索引存取資料，且需要經常將資料轉為陣列以進行排序。
- **未採用 Queue/Stack 的原因**: Queue 與 Stack 限制了隨機存取能力，無法遍歷查詢重複編號。

## 2. 候補順序維護 (`Queue` / `ArrayDeque`)

- **應用位置**: `EventRegistrationSystem.java` - `waitQueue`
- **選擇原因**: 候補者遵從「先到先得 (FIFO)」原則，`Queue.offer()` 與 `poll()` 能保證順序公平。
- **未採用 Stack 的原因**: Stack 為 LIFO，會導致後候補的人優先遞補，破壞規則。

## 3. 取消復原機制 (`Stack` / `ArrayDeque`)

- **應用位置**: `EventRegistrationSystem.java` - `cancelStack`
- **選擇原因**: 復原功能 (Undo) 屬於「後進先出 (LIFO)」，最晚被取消的紀錄必須最先被復原。
- **未採用 Queue 的原因**: Queue 只能取得最舊的紀錄，無法滿足即時復原前一次操作的需求。

## 4. 大量資料編號排序 (`Merge Sort`)

- **應用位置**: `RegistrationAlgorithms.java` - `mergeSortById()`
- **选择原因**: 擁有穩定的 $O(N \log N)$ 時間複雜度，且具備穩定性 (Stability)，適用於資料量較大的系統。
- **未採用 Selection Sort 的原因**: Selection Sort 時間複雜度為 $O(N^2)$，資料量大時效能極差。

## 5. 精確編號快速查詢 (`Binary Search`)

- **應用位置**: `RegistrationAlgorithms.java` - `binarySearchById()`
- **選擇原因**: 對於已排序的資料，查詢時間複雜度僅為 $O(\log N)$，效能極高。
- **未採用 Sequential Search 的原因**: Sequential Search 需要 $O(N)$ 時間，在頻繁查詢時開銷較大。

## 6. 依姓名模糊/多筆查詢 (`Sequential Search`)

- **應用位置**: `RegistrationAlgorithms.java` - `sequentialSearchByName()`
- **選擇原因**: 姓名可能有重複，且資料並非依姓名排序，必須全表掃描以收集所有符合的結果。
- **未採用 Binary Search 的原因**: Binary Search 必須要求資料已排序，且主要適用於唯一鍵值的精確查詢。
