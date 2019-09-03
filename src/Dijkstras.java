import java.util.*;
import java.io.*;

public class Dijkstras{
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new FileReader("Dijkstras.txt"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("Dijkstras.out")));

		//init nodeArr
		int numNodes = Integer.parseInt(in.readLine());
		Node[] nodeArr = new Node[numNodes];
		for (int i = 0; i < numNodes; i++) {
			nodeArr[i] = new Node(i);
		}

		//init all nodes
		while (in.ready()) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			nodeArr[node1].addConnect(node2, len);
			nodeArr[node2].addConnect(node1, len);
		}

//		for (int i = 0; i < numNodes; i++) {
//			System.out.println(nodeArr[i]);
//		}

		//initialize distance array will store the shortest path to all nodes
		int[] dist = new int[numNodes];
		//init boolean array, tracks whether that node has been found already
		boolean[] boolArr = new boolean[numNodes];
		for (int i = 0; i < numNodes; i++) {
			dist[i] = Integer.MAX_VALUE;
			boolArr[i] = false;
		}
		int source = 0;
		dist[source] = 0; //distance from source to itself is always 0

//		System.out.println(printArr(dist));

		for (int i = 0; i < numNodes - 1; i++) {

			//find min index(node) not processed
			int minIndex = minDistance(dist, boolArr);

			//update all adjacent nodes with distances equal to path through this node
			Map<Integer, Integer> map = nodeArr[minIndex].getMap();
			Iterator it = map.keySet().iterator();

//			System.out.printf("%nInside for loop, minIndex = %d%n" +
//					"Processing...%n" +
//					"DistArr is now:%n%s%nminIndex = %d%n",minIndex,printArr(dist),minIndex);

			while (it.hasNext()) {
				//get the node this path goes to
				int pathEnd = (int) it.next();
				int newDist = dist[minIndex] + map.get(pathEnd);
				//update the distance of the adjacent node
				if (boolArr[pathEnd] == false && newDist < dist[pathEnd]) {
					dist[pathEnd] = newDist;
				}

//				System.out.printf("  Inside while loop, minIndex = %d, pathEnd = %d%n" +
//						"  Processing...%n" +
//						"  DistArr is now %s%n",minIndex,pathEnd,"");

			}

			//note this node as processed
			boolArr[minIndex] = true;
		}

		System.out.println(printArr(dist));

		out.close();
	}

	//finds minimum dist not processed
	private static int minDistance(int[] dist, boolean[] boolArr){
		int x = -1;
		int small = Integer.MAX_VALUE;
		for (int i = 0; i < dist.length; i++) {
			if(dist[i] < small && boolArr[i] == false){
				x = i;
				small = dist[x];
			}
		}
		return x;
	}

	private static String printArr(int[] arr){
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			str.append(String.format("Index %3d is %3d from source%n",i,arr[i]));
		}
		return str.toString();
	}
}

class Node{
	private int num;
	private Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	public Node(int num){
		this.num = num;
	}
	public boolean addConnect(int end, int length){
		if(map.containsKey(end)){
			map.put(end, Math.min(map.get(end),length));
			return map.get(end) == length;
		}
		map.put(end,length);
		return true;
	}
	public Map<Integer, Integer> getMap(){
		return map;
	}
	public String toString(){
		return String.format("Node %d, Map =%n%s%n",num,map.toString());
	}
}
/*
class Connect{
	public int end, length;
	public Connect(int end, int length){
		this.end = end;
		this.length = length;
	}
}
*/