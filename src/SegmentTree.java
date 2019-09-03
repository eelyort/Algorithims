public class SegmentTree {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[]{1, 3, 5, 7, 9, 11};
        int[] segTree = constructSegTree(arr);

        System.out.println(printArr(arr));
        System.out.println(printArr(segTree));

        int a = 0;
        int b = arr.length;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
        a = 0;
        b = 1;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
        a = 0;
        b = 2;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
        a = 0;
        b = 3;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
        a = 2;
        b = 3;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
        a = 2;
        b = 4;
        System.out.println(segTreeQuery(segTree, a, b, arr.length));
    }
    public static String printArr(int[] arr){
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i] + " ");
        }
        return str.toString();
    }

    /*------------------------------------------------------------------------------------------------------------------
    Code for segment tree, does same thing as Binary Index Tree but can work with more operations
    O(log n) for both query and update
    Stored as an array of length 2n-1
    Since there can be empty nodes, let Integer.minValue be the dummy
    To get left child of node at index i: index = i*2 + 1
    To get right child of node at index i: index = i*2 + 2
    Currently using operation: -
    */
    //construct and return array representation from an input array
    public static int[] constructSegTree(int[] arr){

        //Remember log_2(n) is equal to log_10(n) / log_10(2)
        int size = 2 * (int)Math.pow(2, (int)Math.ceil(Math.log(arr.length) / Math.log(2)) ) - 1;
        int[] ans = new int[size];

        //Initialize seg tree array with dummies
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Integer.MIN_VALUE;
        }

        //fill seg tree with values from input array
        for (int i = 0; i < arr.length; i++) {
    //            System.out.printf("Starting update call with targetIndex = %d, val = %d%n", i, arr[i]);
            segTreeUpdate(ans, i, arr[i], arr.length);
        }

        return ans;
    }

    //construct and return array representation given a length
    public static int[] constructSegTree(int n){

        //Remember log_2(n) is equal to log_10(n) / log_10(2)
        int size = 2 * (int)Math.pow(2, (int)Math.ceil(Math.log(n) / Math.log(2)) ) - 1;
        int[] ans = new int[size];

        //Initialize seg tree array with dummies
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Integer.MIN_VALUE;
        }

        return ans;
    }

    //Method to update a given index
    public static void segTreeUpdate(int[] arr, int targetIndex, int val, int length){

        //Check for invalid inputs
        if(targetIndex < 0 || targetIndex >= length){
            System.out.println("SegTreeUpdate: Invalid input");
            return;
        }

        segTreeUpdate2(arr, 0, 0, length - 1, targetIndex, val);
    }

    //Internal recursive update method
    public static void segTreeUpdate2(int[] arr, int currNode, int rangeBeg, int rangeEnd, int targetIndex, int val){

        //Exit Condition
        if(targetIndex < rangeBeg || targetIndex > rangeEnd)
            return;

    //        System.out.printf("  Update at currNode = %d, rangeBeg = %d, rangeEnd = %d%n", currNode, rangeBeg, rangeEnd);

        //if current node is empty(dummy), change to 0 first
        if (arr[currNode] == Integer.MIN_VALUE)
            arr[currNode] = 0;

        //operate val to current node
        arr[currNode] -= val;
        if(rangeBeg != rangeEnd){                                   //Checks if there is still further to go down
            //Find the center of the current range
            int mid = (rangeBeg + rangeEnd)/2;
            segTreeUpdate2(arr, currNode*2 + 1, rangeBeg, mid, targetIndex, val);                   //Go left
            segTreeUpdate2(arr, currNode*2 + 2, mid + 1, rangeEnd, targetIndex, val);      //Go Right
        }
    }

    //Method to query a given range [targetBeg, targetEnd]
    public static int segTreeQuery(int[] arr, int targetBeg, int targetEnd, int length){

        //Check for invalid inputs
        if(targetBeg > targetEnd || targetBeg < 0 || targetEnd >= length){
            System.out.println("SegTreeQuery: Invalid input");
            return 0;
        }

        System.out.printf("Calculating query between %d to %d%n", targetBeg, targetEnd);
        return segTreeQuery2(arr, 0, 0, length - 1, targetBeg, targetEnd, "");
    }

    //Internal recursive method to query a certain range
    public static int segTreeQuery2(int[] arr, int currNode, int rangeBeg, int rangeEnd, int targetBeg, int targetEnd, String space){

    //        System.out.printf("%s  Query at currNode = %d, rangeBeg = %d, rangeEnd = %d, targetBeg = %d, targetEnd = %d%n", space, currNode, rangeBeg, rangeEnd, targetBeg, targetEnd);

        //Exit conditions
        if(targetBeg > rangeEnd || targetEnd < rangeBeg){   //If current range is completely outside target range
    //            System.out.printf("%s   Exiting due to being outside range%n", space);
            return 0;
        }
        if(rangeBeg >= targetBeg && rangeEnd <= targetEnd){   //If current range is inside target range
    //            System.out.printf("%s   Exiting due to being inside range%n", space);
            return arr[currNode];
        }

        //Find the center of the current range
        int mid = (rangeBeg + rangeEnd)/2;
        //Operate left and right nodes
        return segTreeQuery2(arr, currNode*2 + 1, rangeBeg, mid, targetBeg, targetEnd, space + " ") +                //Go Left
                segTreeQuery2(arr, currNode*2 + 2, mid + 1, rangeEnd, targetBeg, targetEnd, space + " ");    //Go Right
    }
    //------------------------------------------------------------------------------------------------------------------

//    /*------------------------------------------------------------------------------------------------------------------
//    Code for segment tree, does same thing as Binary Index Tree but can work with more operations
//    O(log n) for both query and update
//    Stored as an array of length 2n-1
//    Since there can be empty nodes, let Integer.minValue be the dummy
//    To get left child of node at index i: index = i*2 + 1
//    To get right child of node at index i: index = i*2 + 2
//    */
//    //construct and return array representation from an input array
//    public static int[] constructSegTree(int[] arr){
//
//        //Remember log_2(n) is equal to log_10(n) / log_10(2)
//        int size = 2 * (int)Math.pow(2, (int)Math.ceil(Math.log(arr.length) / Math.log(2)) ) - 1;
//        int[] ans = new int[size];
//
//        //Initialize seg tree array with dummies
//        for (int i = 0; i < ans.length; i++) {
//            ans[i] = Integer.MIN_VALUE;
//        }
//
//        //fill seg tree with values from input array
//        for (int i = 0; i < arr.length; i++) {
////            System.out.printf("Starting update call with targetIndex = %d, val = %d%n", i, arr[i]);
//            segTreeUpdate(ans, i, arr[i], arr.length);
//        }
//
//        return ans;
//    }
//
//    //construct and return array representation given a length
//    public static int[] constructSegTree(int n){
//
//        //Remember log_2(n) is equal to log_10(n) / log_10(2)
//        int size = 2 * (int)Math.pow(2, (int)Math.ceil(Math.log(n) / Math.log(2)) ) - 1;
//        int[] ans = new int[size];
//
//        //Initialize seg tree array with dummies
//        for (int i = 0; i < ans.length; i++) {
//            ans[i] = Integer.MIN_VALUE;
//        }
//
//        return ans;
//    }
//
//    //Method to update a given index
//    public static void segTreeUpdate(int[] arr, int targetIndex, int val, int length){
//
//        //Check for invalid inputs
//        if(targetIndex < 0 || targetIndex >= length){
//            System.out.println("SegTreeUpdate: Invalid input");
//            return;
//        }
//
//        segTreeUpdate2(arr, 0, 0, length - 1, targetIndex, val);
//    }
//
//    //Internal recursive update method
//    public static void segTreeUpdate2(int[] arr, int currNode, int rangeBeg, int rangeEnd, int targetIndex, int val){
//
//        //Exit Condition
//        if(targetIndex < rangeBeg || targetIndex > rangeEnd)
//            return;
//
////        System.out.printf("  Update at currNode = %d, rangeBeg = %d, rangeEnd = %d%n", currNode, rangeBeg, rangeEnd);
//
//        //if current node is empty(dummy), change to 0 first
//        if (arr[currNode] == Integer.MIN_VALUE)
//            arr[currNode] = 0;
//
//        //add val to current node
//        arr[currNode] += val;
//        if(rangeBeg != rangeEnd){                                   //Checks if there is still further to go down
//            //Find the center of the current range
//            int mid = (rangeBeg + rangeEnd)/2;
//            segTreeUpdate2(arr, currNode*2 + 1, rangeBeg, mid, targetIndex, val);                   //Go left
//            segTreeUpdate2(arr, currNode*2 + 2, mid + 1, rangeEnd, targetIndex, val);      //Go Right
//        }
//    }
//
//    //Method to query a given range [targetBeg, targetEnd]
//    public static int segTreeQuery(int[] arr, int targetBeg, int targetEnd, int length){
//
//        //Check for invalid inputs
//        if(targetBeg > targetEnd || targetBeg < 0 || targetEnd >= length){
//            System.out.println("SegTreeQuery: Invalid input");
//            return 0;
//        }
//
//        System.out.printf("Calculating query between %d to %d%n", targetBeg, targetEnd);
//        return segTreeQuery2(arr, 0, 0, length - 1, targetBeg, targetEnd, "");
//    }
//
//    //Internal recursive method to query a certain range
//    public static int segTreeQuery2(int[] arr, int currNode, int rangeBeg, int rangeEnd, int targetBeg, int targetEnd, String space){
//
////        System.out.printf("%s  Query at currNode = %d, rangeBeg = %d, rangeEnd = %d, targetBeg = %d, targetEnd = %d%n", space, currNode, rangeBeg, rangeEnd, targetBeg, targetEnd);
//
//        //Exit conditions
//        if(targetBeg > rangeEnd || targetEnd < rangeBeg){   //If current range is completely outside target range
////            System.out.printf("%s   Exiting due to being outside range%n", space);
//            return 0;
//        }
//        if(rangeBeg >= targetBeg && rangeEnd <= targetEnd){   //If current range is inside target range
////            System.out.printf("%s   Exiting due to being inside range%n", space);
//            return arr[currNode];
//        }
//
//        //Find the center of the current range
//        int mid = (rangeBeg + rangeEnd)/2;
//        return segTreeQuery2(arr, currNode*2 + 1, rangeBeg, mid, targetBeg, targetEnd, space + " ") +                //Go Left
//                segTreeQuery2(arr, currNode*2 + 2, mid + 1, rangeEnd, targetBeg, targetEnd, space + " ");    //Go Right
//    }
//    //------------------------------------------------------------------------------------------------------------------
}
