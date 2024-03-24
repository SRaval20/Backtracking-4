// Time Complexity:  O((H*W)*((H*W)P(n)))
// Space Complexity: O(n/k)
// where P is permutation,
//       H, W are Height and Width,
//       n is number of buildings

public class Main {
    
    public static void main(String[] args) {
        BuildingPlacement obj = new BuildingPlacement();
        System.out.println(obj.findMinDistance(4, 4, 3));
    }

    public static class BuildingPlacement {
        
        int minDistance = Integer.MAX_VALUE;                                                 // this will be our result
        
        private int findMinDistance(int H, int W, int n) {                    
            int[][] grid = new int[H][W];
            for(int i=0; i<H; i++) {   
                Arrays.fill(grid[i], -1);                                                    // filling up array with value -1
            }
            backtrack(grid, 0, 0, H, W, n);                                                  // calling backtrack
            return minDistance;                                                              // return result
        }

        private void backtrack(int[][] grid, int r, int c, int H, int W, int n) {
            // base  
            if(n == 0) {                                                                     // if all buildings placed
                bfs(grid, H, W);                                                             // call BFS
                return;
            }
            
            // logic
            if(c == W) {                                                                     // if we reach end of the row
                c = 0;                                                                       // go to first column of next row
                r++;
            }
            
            for(int i=r; i<H; i++) {                                                         // starting from given row and cloumn placement
                for(int j=c; j<W; j++) {
                    // action
                    grid[i][j] = 0;                                                          // make it 0
                    
                    // recurse
                    backtrack(grid, r, c+1, H, W, n-1);                                      // recursion for next building
                    
                    // backtrack
                    grid[i][j] = -1;                                                         // back to -1
                }
            }
        }

        private void bfs(int[][] grid, int H, int W) {                                       // BFS
            
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[H][W];
            int[][] dirs = new int[][] {
                {0, -1}, {0, 1}, {1, 0}, {-1, 0}
            };
            int dist = 0;
            
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    if(grid[i][j] == 0) { 
                        queue.add(new int[]{i, j});                                           // add buildings in queue and make it visited
                        visited[i][j] = true;
                    }
                }
            }
            
            while(!queue.isEmpty()) {                                                         // visited all grid placements
                int size = queue.size();
                while(size-- > 0) {
                    int[] cur = queue.poll();
                    for(int[] dir : dirs) {
                        int nr = cur[0] + dir[0];
                        int nc = cur[1] + dir[1];
                        if(nr >=0 && nr < H && nc >= 0 && nc < W && !visited[nr][nc]) {
                            queue.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;                                                                       // maintain distance
            }
            minDistance = Math.min(minDistance, dist-1);                                      // update result
        }
    }
    
}
