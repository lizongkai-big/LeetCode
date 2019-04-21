package Ordered;

// 精进
public class _79__Word_Search {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                /* correspond to find()
                if(board[i][j] == word.charAt(0)) {
                    board[i][j] = '_';
                    if(find(board, word, 1, i, j)) return true;
                    board[i][j] = word.charAt(0);
                }
                */
                if (exist(board, w, 0, i, j)) return true;
            }
        }
        return false;
    }

    // Obviously, the for check phase is very similar, so why don't merge them?
    // The problem is r+1, r-1, c+1, c-1 are different.
    // 因此，把下标的判断放在下一层（函数开始），就有了exist()函数
    public boolean find(char[][] board, String word, int inx, int r, int c) {
        if (inx == word.length()) return true;
        int m = board.length, n = board[0].length;
        if (r + 1 < m && board[r + 1][c] == word.charAt(inx)) {
            board[r + 1][c] = '_';
            if (find(board, word, inx + 1, r + 1, c)) return true;
            board[r + 1][c] = word.charAt(inx);
        }
        if (r - 1 >= 0 && board[r - 1][c] == word.charAt(inx)) {
            board[r - 1][c] = '_';
            if (find(board, word, inx + 1, r - 1, c)) return true;
            board[r - 1][c] = word.charAt(inx);
        }
        if (c + 1 < n && board[r][c + 1] == word.charAt(inx)) {
            board[r][c + 1] = '_';
            if (find(board, word, inx + 1, r, c + 1)) return true;
            board[r][c + 1] = word.charAt(inx);
        }
        if (c - 1 >= 0 && board[r][c - 1] == word.charAt(inx)) {
            board[r][c - 1] = '_';
            if (find(board, word, inx + 1, r, c - 1)) return true;
            board[r][c - 1] = word.charAt(inx);
        }
        return false;
    }

    private boolean exist(char[][] board, char[] word, int inx, int r, int c) {
        if (inx == word.length) return true;
        if (c < 0 || r < 0 || r == board.length || c == board[0].length) return false;
        if (board[r][c] != word[inx]) return false;
        board[r][c] ^= 256;
        boolean exist = exist(board, word, inx + 1, r, c + 1)
                || exist(board, word, inx + 1, r, c - 1)
                || exist(board, word, inx + 1, r + 1, c)
                || exist(board, word, inx + 1, r - 1, c);
        board[r][c] ^= 256;
        return exist;
    }
}
