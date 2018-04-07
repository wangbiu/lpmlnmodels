package cn.edu.seu.kse.lpmln.lpmln.misc;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoSuchTest {
    @Test
    public void noSuchTest() {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        int a = in.nextInt();
        int x = in.nextInt();
        int b = in.nextInt();
        int y = in.nextInt();
        int count = 0;
        int[][] table = new int[a][b];
        table[0][0] = 0;
        for (int i = 0; i < a; i++) {
            if (i == 0) {
                table[i][0] = 0;
            }
            table[i][0] = table[i-1][0] + x;
            if (table[i][0] == k) {
                count += a - i;
                break;
            }
            for (int j = 0; j < b; j++) {
                table[i][j] = table[i - 1][j] + y;
                if (table[i][j] == k) {
                    count += b - j;
                    break;
                }
            }
        }
        System.out.println(count % 1000000007);
    }
}
