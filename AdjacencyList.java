import java.util.Random;


public class AdjacencyList {
	public WeightedEdge[] aList;

	public AdjacencyList(int num) {
		aList = new WeightedEdge[num];
	}

	public boolean isInArray(int[] arr, int val) {
		for (int i=0; i<arr.length; i++) {
			if (arr[i] == val) {
				return true;
			}
		}
		return false;
	}
	
	public void generateRandom() {
		Random rand = new Random();
		int x,s,w;
		int len = aList.length;
		for (int i=2; i<len; i++) {
			
			x=rand.nextInt(i-1);
			x++;
			int[] picked = new int[x];
			for(int j=0; j<picked.length; j++) {
				s = rand.nextInt(i-1);
				s++;
				while (isInArray(picked, s)) {
					s = rand.nextInt(i-1);
					s++;
				}
				picked[j]=s;
				w = rand.nextInt(91);
				w += 10;
				//System.out.printf("i: %d, s: %d, w: %d\n", i, s, w);
				insertEdge(i, s, w);
				insertEdge(s, i, w);
			}
			
		}
	}
	
	public void insertEdge(int v1, int v2, int w) {
		WeightedEdge nd = new WeightedEdge(v2, w);
		if (v1 <= aList.length) {
			if (aList[v1] == null) {
				aList[v1] = nd;
			} else {
				WeightedEdge temp = aList[v1];
				nd.setNext(temp);
				aList[v1] = nd;
			}
		}
	}

	public int search (int v1, int v2) {
		if (v1 <= aList.length && v2 <= aList.length) {
			if (aList[v1] == null) {
				return -1;
			}
			else {
				WeightedEdge current = aList[v1];
				while (current.vertex != v2 && current.next != null) {
					current = current.next;
				}
				if (current.vertex == v2) {
					return current.weight;
				} else {
					return -1;
				}
			}
		}
		return -1;
	}

	public class WeightedEdge {
		public int vertex;
		public int weight;
		public WeightedEdge next = null;

		public WeightedEdge (int v1, int w) {
			vertex = v1;
			weight = w;
		}

		public WeightedEdge setNext (WeightedEdge edg) {
			next = edg;
			return next;
		}

	}

}
