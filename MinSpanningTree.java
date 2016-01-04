import java.util.Random;


public class MinSpanningTree {
	public AdjacencyList list;
	public MSTNode[] nodes;
	public MSTNode[] marked;
	public int count=0;
	
	public MinSpanningTree(int num) {
		list = new AdjacencyList(num+1);
		nodes = new MSTNode[num+1];
		marked = new MSTNode[num+1];
	}
	
	public void setAdjacencyList(AdjacencyList l) {
		list = l;
	}

	public MSTNode[] getMarked() {
		int count=0;
		for(int i=1; i<nodes.length; i++) {
			if (nodes[i].marked == 1) {
				count++;
			}
		}
		int ind = 0;
		MSTNode[] marked = new MSTNode[count];
		for(int i=1; i<nodes.length; i++) {
			if (nodes[i].marked == 1) {
				marked[ind] = nodes[i];
				ind++;
			}
		}
		
		return marked;
	}
	

	public MSTNode[] getNext() {
		MSTNode min;
		MSTNode parent;
		MSTNode[] result = new MSTNode[2];
		
		int currentMin;
		int nextMin;
		parent = marked[1];
		min = marked[1].getMinUnmarked();
		int k = 1;
		
		while(min == null && k<marked.length) {
			parent = marked[k];
			min = marked[k].getMinUnmarked();
			k++;
		}
		for (int i=k; i<= count; i++) {
			currentMin = list.search(parent.value, min.value);
			MSTNode minUnmarked = marked[i].getMinUnmarked();
			//System.out.printf("minUnmarked[i]: %d\n", count);
			if(minUnmarked != null) { 
				nextMin = list.search(marked[i].value, marked[i].getMinUnmarked().value);
				if (nextMin < currentMin) {
					min = marked[i].getMinUnmarked();
					parent = marked[i];
				}
			}
		}
		result[0] = min;
		result[1] = parent;
		
		return result;
	}
	
	
	public int startSearching() {
		Random rand = new Random();
		int totalWeight = 0;
		int start = rand.nextInt(nodes.length-1);
		start++;
		nodes[start].mark();
		count++;
		marked[count] = nodes[start];
		
		System.out.printf("Minimum Spanning Tree: starting vertex is %d\n", start);
		//if everything is marked, then the tree is complete;
		while(count<nodes.length-1) {
			MSTNode[] next = getNext();
			int weight = list.search(next[1].value, next[0].value);
			next[0].mark();
			count++;
			marked[count] = next[0];
			System.out.printf("Vertex %d is the parent of vertx %d, with a weight of %d\n", next[1].value, next[0].value, weight);
			totalWeight += weight;
		}
		
		System.out.printf("The weight of the minimum spanning tree is: %d\n", totalWeight);
		return totalWeight;
		
	}
	
	public int startSearching(int start) {
		int totalWeight = 0;
		nodes[start].mark();
		count++;
		marked[count] = nodes[start];
		
		//System.out.printf("Minimum Spanning Tree: starting vertex is %d\n", start);
		//if everything is marked, then the tree is complete;
		while(count<nodes.length-1) {
			MSTNode[] next = getNext();
			int weight = list.search(next[1].value, next[0].value);
			next[0].mark();
			count++;
			marked[count] = next[0];
			//System.out.printf("Vertex %d is the parent of vertx %d, with a weight of %d\n", next[1].value, next[0].value, weight);
			totalWeight += weight;
		}
		
		//System.out.printf("The weight of the minimum spanning tree is: %d\n", totalWeight);
		return totalWeight;
	}
	
	public class MSTNode {
		public int value;
		public int marked;
		
		public MSTNode(int v) {
			value = v;
			marked = 0;
		}
		
		public void mark() {
			marked = 1;
		}
		
		public MSTNode getMinUnmarked() {
			MSTNode[] unmarked = getUnmarked();
			if (unmarked.length == 0) {
				return null;
			}
			MSTNode min = unmarked[0];
			for(int i=0; i<unmarked.length; i++) {
				if(list.search(value, unmarked[i].value) < list.search(value, min.value)) {
					min = unmarked[i];
				}
			}
			return min;
		}
		
		public MSTNode[] getUnmarked() {
			int count = 0;
			
		    AdjacencyList.WeightedEdge current = list.aList[value];
		    while(current.next != null) {
		    	if (nodes[current.vertex].marked == 0 && current.weight != 0) {
		    		count++;
		    		current=current.next;
		    	} else {
		    		current=current.next;
		    	}
		    }
	    	if (nodes[current.vertex].marked == 0 && current.weight != 0) {
	    		count++;
	    	}
	  
		    MSTNode[] unmarked = new MSTNode[count];
		    int i=0;
		    current = list.aList[value];
		    while(i<count) {
		    	//System.out.println(current.vertex);
		    	if (nodes[current.vertex].marked == 0 && current.weight != 0) {
		    		unmarked[i] = nodes[current.vertex];
		    		if (current.next != null) {
		    			current = current.next;
		    		}
		    		i++;
		    	} else {
		    		current = current.next;
		    	}
		    }
		    return unmarked;
		}
	}
}
