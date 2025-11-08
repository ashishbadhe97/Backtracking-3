// 51. N-Queens
// https://leetcode.com/problems/n-queens/description/

// Solution => DFS + Backtracking

/**
 * Time Complexity: O(n * n!) since at every row we have options to place queen in factorial pattern i.e, n then n - 2, then n - 4 and so on
 * Also, for checking safety we check above, left diagnl and right diagnl elements which is n 
 * 
 * Space Complexity: O(n) since in worst case we would have n calls till we reach last row to place queen
 */

class Solution {

    List<List<String>> result;

    public List<List<String>> solveNQueens(int n) {

        result = new ArrayList<>();

        boolean[][] board = new boolean[n][n];

        // start dfs from first element
        dfs(board, 0);

        return result;
    }

    private void dfs(boolean[][] board, int r) {

        // base condition 
        if (r >= board.length) { // i.e we placed all queens

            List<String> pattern = new ArrayList<>();

            for (int i = 0; i < board.length; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == true) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }

                pattern.add(sb.toString());
            }

            result.add(pattern);
            return;
        }

        // dfs for every col in a row
        for (int c = 0; c < board[0].length; c++) {
            if (isValid(board, r, c)) {
                // action
                board[r][c] = true;
                // recurse
                dfs(board, r + 1);
                // backtrack
                board[r][c] = false;
            }
        }
    }

    private boolean isValid(boolean[][] board, int r, int c) {
        // check above
        int row = r;
        int col = c;
        while (row >= 0) {
            if (board[row][col] == true) {
                return false;
            }
            row--;
        }

        // check diagonally right
        row = r;
        col = c;
        while (row >= 0 && col < board[0].length) {
            if (board[row][col] == true) {
                return false;
            }
            row--;
            col++;
        }

        // check diagonally left
        row = r;
        col = c;
        while (row >= 0 && col >= 0) {
            if (board[row][col] == true) {
                return false;
            }
            row--;
            col--;
        }

        return true;
    }
}