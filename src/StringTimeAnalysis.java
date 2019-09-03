import java.util.*;
import java.io.*;

public class StringTimeAnalysis {
    public static void main(String[] args) throws Exception {
        long time = 0;

        int n = 2;
        int size = (int)Math.pow(10,n);

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(i);
        }

        System.out.println("Hi");

        String ans = "";

        ans = "";
        time = System.nanoTime();
        ans = String.format("String is %s and %s",str,str);
//        System.out.println(ans);
        System.out.printf("Dummy , %24s: %-12d, time = %d%n","String.format",System.nanoTime()-time,time);

        ans = "";
        time = System.nanoTime();
        ans = String.format("String is %s and %s",str,str);
//        System.out.println(ans);
        System.out.printf("Time 1, %24s: %-12d%n","String.format",System.nanoTime()-time,time);

        ans = "";
        time = System.nanoTime();
        ans = "String is " + str + " and " + str;
//        System.out.println(ans);
        System.out.printf("Time 2, %24s: %-12d%n","Concatanation",System.nanoTime()-time, time);

        time = System.nanoTime();
        StringBuilder ans2 = new StringBuilder();
        ans2.append("String is ");
        ans2.append(str);
        ans2.append(" and ");
        ans2.append(str);
//        System.out.println(ans2);
        System.out.printf("Time 3, %24s: %-12d%n","String Builder",System.nanoTime()-time,time);
    }
}
