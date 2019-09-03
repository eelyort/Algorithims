/*
ID: troylee1
LANG: JAVA
TASK: ride
*/

import java.util.*;
import java.io.*;

public class ride {
    public static void main(String[] args) throws Exception {
        BufferedReader s = new BufferedReader(new FileReader("ride.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));

        while(s.ready()){
            String str = s.readLine();
            String str2 = s.readLine();
            int num = 1;
            int num2 = 1;
            for (int i = 0; i < str.length(); i++) {
                num*=(int)str.charAt(i)-64;
            }
            for (int i = 0; i < str2.length(); i++) {
                num2*=(int)str2.charAt(i)-64;
            }
            if(num%47==num2%47)
                out.println("GO");
            else
                out.println("STAY");
        }

        out.close();
    }
}
