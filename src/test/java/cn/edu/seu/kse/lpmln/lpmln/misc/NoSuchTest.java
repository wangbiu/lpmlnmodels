package cn.edu.seu.kse.lpmln.lpmln.misc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NoSuchTest {
    @Test
    public void noSuchTest() {
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            a.add(i);
        }

        StringBuilder sb = new StringBuilder();
        a.forEach(i -> sb.append(i).append(" "));

        System.out.println(sb.toString());
    }
}
