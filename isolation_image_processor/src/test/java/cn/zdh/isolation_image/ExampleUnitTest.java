package cn.zdh.isolation_image;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);

        String string="测试";
        System.out.print("-------"+string);
//        get(string);

    }

    public void get(Object o) {
        String typeName = o.getClass().getTypeName();
        System.out.print("-------"+typeName);
    }
}