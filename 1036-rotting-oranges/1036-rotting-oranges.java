class Solution {
    public int orangesRotting(int[][] grid) {
        Queue<int[]> queue = new LinkedList<>();
        int fresh = 0;
        for(int i = 0;i<grid.length;i++) {
            for(int j = 0;j<grid[0].length;j++) {
                if(grid[i][j] == 2) queue.offer(new int[] {i , j});
                else if(grid[i][j] == 1) fresh++;
            }
        }

        if (fresh == 0) return 0;

        int count = 0;
        int[][] dirs = { {0,1} , {-1,0} , {1,0}, {0,-1}};

        while(!queue.isEmpty()) {
            ++count;
            int size = queue.size();
            for(int i =0;i<size;i++) {
                int[] points = queue.poll();
                for(int[] dir: dirs) {
                    int x = points[0] + dir[0];
                    int y = points[1] + dir[1];

                    if(x <0 || y < 0 || x >= grid.length || y >= grid[0]. length || grid[x][y] == 2 || grid[x][y] == 0) continue;

                    grid[x][y] = 2;
                    queue.offer(new int[] {x,y});
                    fresh--;
                }
            }
        }
                return fresh == 0 ? count-1 : -1;
    }
}