package cn.edu.seu.kse.lpmln.lpmln.misc;

import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NoSuchTest {
    @Test
    public void noSuchTest() {
        String a = "asdfadsf";
        String b = a;
        System.out.println(a);
        b += "aaaa";
        System.out.println(b);
        System.out.println(a);
        System.out.println(b.substring(1));
    }
}
