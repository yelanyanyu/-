import org.junit.jupiter.api.Test;

import java.lang.management.MemoryType;
import java.util.Arrays;

/**
 * @author yelanyanyu@zjxu.edu.cn
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class AsFarFromLandAsPossible_1911 {
    public static void main(String[] args) {

    }

    //for test
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    //for test
    private static int[][] getRandomMatrix(int maxCol, int maxRow, int maxValue, int minValue) {
        int[][] grid = new int[maxRow][maxCol];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = getRandomNum(1, 0);
            }
        }
        return grid;
    }

    //for test
    private static int getRandomNum(int maxValue, int minValue) {
        return (int) (Math.random() * (maxValue - minValue + 1) + minValue);
    }

    public static int[] getAllLandPos(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[] pos = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    pos[i] = j;
                }
            }
        }
        return pos;
    }

    //for test
    @Test
    public void Test01() {
        printMatrix(getRandomMatrix(3, 3, 1, 0));
    }

    @Test
    public void Test02() {

    }

    public int maxDistance(int[][] grid) {
        int max = 0;
        int curD = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    curD = getMinLandDistance(grid, i, j);
                    if (curD > max) {
                        max = curD;
                    }
                }
            }
        }
        return max;
    }

    /**
     * 返回离(i,j)最近岛屿的距离,d最小
     *
     * @param grid
     * @param i
     * @param j
     * @return
     */
    public int getMinLandDistance(int[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (i < 0 || j < 0 || i == rows || j == cols) {
            return -1;
        }
        if (grid[i][j] == 1) {
            return 0;
        }
        //遍历方式
        int[] pos = new int[rows * cols];
        int max = 0;
        int curD = 0;
        for (int k = 0; k < grid.length; k++) {
            for (int l = 0; l < grid[0].length; l++) {
                if (grid[k][l] == 0) {  //为海洋
                    for (int m = 0; m < pos.length; m++) {
                        curD = Math.abs(m - k) + Math.abs(pos[m] - l);
                        // TODO: 2022/10/1 补充找最大值逻辑
                    }
                }
            }
        }
    }

    /**
     * 在(x,y)上，已经距离原位置(i,j)的距离d，返回
     *
     * @param grid
     * @param i
     * @param j
     * @param x
     * @param y
     * @return
     */
//    public int process(int[][] grid, int i, int j, int x, int y) {
//        int rows = grid.length;
//        int cols = grid[0].length;
//        if (x < 0 || y < 0 || x == rows || y == cols) {
//            return 0;
//        }
//        if (x == i && y == j) {
//            return 0;
//        }
//        if (grid[x][y] == 1) {
//            return Math.abs(x - i) + Math.abs(y - j);
//        }
//        int p1 = process(grid, i, j, x + 1, y);
//        int p2 = process(grid, i, j, x - 1, y);
//        int p3 = process(grid, i, j, x, y + 1);
//        int p4 = process(grid, i, j, x, y - 1);
//        return Math.min(Math.min(p1, p2), Math.min(p3, p4));
//    }
}
