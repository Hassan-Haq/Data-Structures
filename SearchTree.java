//Hassan Haq
// 10061306
public class SearchTree {
	
	static Node root;
	//Standard insert function. Done using iteration
	//The insert function is set up so that no two nodes have the same value
	public static void insert (int val){
		Node newNode = new Node(val);
		//if root is null then insert new node
		if (root == null){
			root = newNode;
		}
		else{
			//simple assignment for current node and it's parent node
			Node curr = root;
			Node parent;
			while (true){
				//insert left child
				parent = curr;
				if (val < curr.val){
					curr = curr.LC;
					if (curr == null){
						parent.LC = newNode;
						return;
					}
				}
				//if a repeat value is given re-insert it into the same position
				else if (curr.val == val){
					curr.val = val;
					return;
				}
				//insert Right child
				else {
					curr = curr.RC;
					if (curr == null){
						parent.RC = newNode;
						return;
					}
				}
			}
		}
	}
	//Standard Search function done using iteration
	/*In this function I decided to print the values as they were found
	 * instead of returning a list of ints containing the path that was taken.
	 * This was done mainly because when we search for values in the RB Tree
	 * it is easier to print colors this way and I wanted to remain consistent
	 * throughout my code*/
	public static void search(int x){
		//if null tree is sent in return null
		if (root == null){
			System.out.println("null");
			return; 
		}
		Node curr = root;
		
		while (curr.val != x){
			//if the value is found print the value and return
			if (curr.val == x){
				System.out.print(curr.val + ", ");
				return;
			}
			//if the value isn't found print the value of the node and jump to
			//right/left child.
			else if (curr.val < x){
				System.out.print(curr.val + ", ");
				curr = curr.RC;
			}
			else{
				System.out.print(curr.val + ", ");
				curr = curr.LC;
			}
		}
		//when the value is found it exits the while loop so we print the found 
		//value as part of the path
		System.out.println (curr.val + ".");
		return;
	}
	//Total Depth function done recursively 
	//the depth of the root node is provided and from that the children know thier depth
	public static int maxDepth(Node root, int depth){
		if (root == null){
			return 0;
		}
		//recursive call to add the depth values of all the node
		return depth + maxDepth(root.LC, depth +1) + maxDepth(root.RC, depth +1);	
		
	}
	/*The main function is set up so that you can enter any array into the 
	 * insertArr array and the insert, maxDepth and search functions will 
	 * be automatically run. For testing purposes I've used the array 
	 * that was provided in Part 1 and Part 2 of the assignment*/
	public static void main (String [] args){
		//call insert with a for loop of value (6, 10, 20, 8, 3)
		int [] insertArr = {6,10,20,8,3};
		for(int i =0; i < insertArr.length; i++){
			insert(insertArr[i]);
		}
		
		//call the function maxDepth(root);
		int depth = 1;
		int totalDepth = maxDepth(root, depth);
		System.out.println(totalDepth + " is the max depth of the binary tree");
		
		//search and print the path for every value in the tree
		for(int j = 0; j < insertArr.length; j++){
			System.out.print ("The path for the value was: ");
			search(insertArr[j]);
		}
		
	}

}
//Inner class to create the Nodes for the BST.
//Every Node has a pointer to it's Right and Left Child and a value.
class Node{
	int val;

	
	Node LC;
	Node RC;
	//Constructor
	Node (int val){
		this.val = val;
	}
	

}