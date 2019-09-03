import java.util.*;
import java.io.*;

public class BinarySearch {
    static int n;
    static int numDigits;
    public static void main(String[] args) throws Exception {
        n = 50;
        numDigits = (n + "").length();
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i = 0; i < n; i++) {
            int ran = (int)(Math.random()*3);
            if (ran != 0){
                ((LinkedList<Integer>) q).add(i);
            }
        }

        System.out.printf("q is %s%n", q.toString());

        int[] arr = new int[q.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = q.poll();
        }

        for (int i = -1; i < n + 1; i++) {
            System.out.printf("%nArray: %s%n", printArr2(arr));
            System.out.printf("Array: %s%n", printArr(arr));
            System.out.printf("Binary search of i = %d,   Is equal to: %d%n", i, binarySearch(arr,i, 3, arr.length-3));
        }
    }

    /*
    Will search the increasing array, arr for val
    Will return the index of val, or the negative index of where val should be placed if it doesn't exist
    Will return -1 if the index was not found and should be placed at the beginning, since -0 is still 0;
     */
    public static int binarySearch(int[] arr, int val, int startIn, int endIn){
        int mid;
        int start = startIn;
        int end = endIn;
        while(start <= end){

            mid = (start + end)/2;                          //Initialize mid

            int dirTest = val - (arr[mid]);

            if(dirTest < 0){                                //if val is smaller
                end = mid - 1;                                  //change range, go to the left

                if(mid - 1 >= startIn && val - (arr[mid-1]) > 0){     //if val is in between mid and mid - 1
                    return -mid;
                }
                else if(mid == startIn){                            //if val is smaller, but we are at beginning of the array
                   if(mid != 0)
                        return -mid;
                    else                                                //Edge case since -0 is 0
                        return -1;
                }
            }
            else if(dirTest > 0){                           //if val is bigger
                start = mid + 1;                                //change range, go to the right

                if(mid + 1 < endIn && val - (arr[mid+1]) < 0){        //if val is in between mid and mid + 1
                    return -mid - 1;
                }
                else if(mid == endIn - 1){                          //if val is bigger, but we are at end of the array
                    return -mid - 1;
                }
            }
            else if(dirTest == 0){
                return mid;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static String printArr(int[] arr){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            str.append(String.format("%" + numDigits + "d ", arr[i]));
        }
        return str.toString();
    }
    public static String printArr2(int[] arr){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            str.append(String.format("%" + numDigits + "d ", i));
        }
        return str.toString();
    }
}
