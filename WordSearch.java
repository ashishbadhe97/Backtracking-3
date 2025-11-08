// 79. Word Search
// https://leetcode.com/problems/word-search/description/

// Solution => DFS + Backtracking

/**
 * Time Complexity: O(m * n * 3 ^ l) since we iterate over enitre matrix we have m * n 
 *  and at each dfs we take 3 decisions and we go till we find the word, i.e depth is l
 * 
 * Space Complexity: O(l) where l is length of the word. Since in worst case we would have l call in call stack
 */

class Solution {
    int m;
    int n;
    int[][] dirs;
    boolean found;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        found = false;

        dirs = new int[][] { {1,0}, {-1,0}, {0,1}, {0,-1}};

        // start new dfs for every matching first char
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                char ch = word.charAt(0);

                if(board[i][j] == ch){
                    // mark it visited
                    board[i][j] = '#';
                    dfs(board, i, j, 1, word);
                    // backtrack
                    board[i][j] = ch;
                }
            }
        }

        return found;
    }

    private void dfs(char[][] board, int i, int j, int idx, String word){
        // base condition
        if(idx >= word.length()){
            found = true;
            return;
        }

        // logic

        // dfs on all 4 directions
        for(int[] dir: dirs){
            int r = i + dir[0];
            int c = j + dir[1];

            char ch = word.charAt(idx);

            if(r >= 0 && c >= 0 && r < m && c < n && ch == board[r][c]){
                // action
                board[r][c] = '#';
                // recurse
                dfs(board, r, c, idx + 1, word);
                // backtrack
                board[r][c] = ch;
            }
        }
    }
}