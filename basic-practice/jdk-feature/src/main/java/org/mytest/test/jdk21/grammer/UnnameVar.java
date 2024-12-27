package org.mytest.test.jdk21.grammer;

import java.util.ArrayList;

public class UnnameVar {
    public static void main(String[] args) {

        try {
            var _ = new ArrayList<>();
            // 无法使用_
            // System.out.println(_);
        } catch (Exception _) {
        }
    }
}
