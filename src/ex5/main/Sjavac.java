package ex5.main;

import ex5.a.VerifyDocument;

public class Sjavac {
    public static void main(String[] args) {
        int result = VerifyDocument.Verify("src/ex5/text.txt");
        System.out.println(result);
    }
}