import java.util.*;
import java.io.*;

public class test2 {
    public static void main(String[] args) throws Exception {
        BufferedReader s = new BufferedReader(new FileReader("hi.txt"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
        StringTokenizer st = new StringTokenizer("");
        while(s.ready()){
            System.out.println(s.readLine());
        }
    }
}
