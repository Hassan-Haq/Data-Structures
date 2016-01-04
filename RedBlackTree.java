// Hassan Haq
// 10061306
public class RedBlackTree {
	//Assigning RED and BLACk as boolean values, this way we assign a value or true or false to each node.
	public static boolean RED = true;
	public static boolean BLACK = false;
	
	//initializing the root node
	static Node root;

	//insert Algorithm
	//The insert function called from the main function
	public static void insert(int val){
		//calling the insert actual insert function with root as the return point
		root = insert(root, val);
		//reassiging the color black to the root node in case it changes
		root.colour = BLACK;
		
	}
	public static Node insert (Node curr, int val){
		//if the node provided is null then we return a new node with containing the value and the color red
		if (curr == null){
			return new Node(val, RED);
		}
		else{
			//Simple recursive insert where we recurse through the binary tree and find the null pointer
			//if val is less the val of the current node recurse into the left subtree
			if (val < curr.val){
				curr.LC = insert(curr.LC, val);
			}
			//if val is greater than the val of the current node recurse inot the right subtree
			else if (val > curr.val){
				curr.RC = insert(curr.RC, val);
			}
			
			//if neither of the above then insert val into the current node
			//prevents multiple nodes whihc hold the same value
			else {
				curr.val = val;
			}
		}
		//The Balancing of the tree
		//if sibling nodes are different colors then rotate left
		if (isRed (curr.RC) && !isRed(curr.LC)){
			curr = rotateL(curr);
		}
		//if parent and child node are both red then rotate right 
		if (isRed (curr.LC) && isRed(curr.LC.LC)){
			curr = rotateR(curr);
		}
		//flipping colors if the children are both red
		if (isRed (curr.LC) && isRed(curr.RC)){
			changeColours(curr);
		}

		return curr;
	}
	
	
	//search Algorithm
	public static void search(int x){
		//since the colour for each node is a boolean value we create a string and assign it a RED or BLACK depending on the
		//boolean value of the node
		String color;
		Node curr = root;
		//returns null if the assigned tree is null
		if (root == null){
			System.out.println("null");
			return; 
		}
		if(curr.colour == true){
			color = "RED";
		}
		else{
			color = "BLACK";
		}
		//instead of returning a list containing the path I took the option of printing out each value as we visited the node
		//simpler in the long run espically if you need to print the colors of the nodes as well
		while (curr.val != x){
			//repeating the code inside the loop so that it is done for every node we visit. For the sake of creating the path
			if(curr.colour == true){
				color = "RED";
			}
			else{
				color = "BLACK";
			}
			//if the currnet node contains the value then we print the value followed by the color
			if (curr.val == x){
				System.out.print(curr.val + " " + color + ", ");
				return;
			}
			//if the current node isn't the value then it prints out the value and jumps to the Left or Right node
			else if (curr.val < x){
				System.out.print(curr.val + " " + color + ", ");
				curr = curr.RC;
			}
			else{
				System.out.print(curr.val + " " + color +  ", ");
				curr = curr.LC;
			}
		}
		//if node.colour value is true assigns RED else assigns BLACK

		//when it finds the node it jumps out of the while loop and this prints the final value followed by a period
		System.out.println (curr.val + " " + color +  ".");
		return;
	}
	//maxDepth function. Simple and straight forward recursion
	//enter the function with the root having a depth value of 1 and then recurse adding the values of each child at each level
	public static int maxDepth(Node root, int depth){
		if (root == null){
			return 0;
		}
		return depth + maxDepth(root.LC, depth +1) + maxDepth(root.RC, depth +1);	
		
	}
	//Rotate Right function, reassigning the pointer values to rotate a subtree of the tree to the right 
	public static Node rotateR(Node curr){
		Node n = curr.LC;
		curr.LC = n.RC;
		n.RC = curr;
		n.colour = n.RC.colour;
		n.RC.colour = RED;
		return n;
	}
	
	//Rotate Left same as above by to the left
	public static Node rotateL(Node curr){
		Node n = curr.RC;
		curr.RC = n.LC;
		n.LC = curr;
		n.colour = n.LC.colour;
		n.LC.colour = RED;
		return n;
	}
	//Flip the Colours of the nodes
	//flipping the boolean values for the nodes
	public static void changeColours(Node curr){
		curr.colour = !curr.colour;
		curr.LC.colour = !curr.LC.colour;
		curr.RC.colour = !curr.RC.colour;
	}
	//Confirms if node is RED
	public static boolean isRed(Node curr){
		if (curr == null){
			return false;
		}
			return (curr.colour);

	}
	/*The main function is set up so that you can enter any array into the 
	 * insertArr array and the insert, maxDepth and search functions will 
	 * be automatically run. For testing purposes I've used the array 
	 * that was provided in Part 1 and Part 2 of the assignment*/
	public static void main (String [] args){

		//using a for loop to feed the array values to the insert function
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
//Node inner class used to define every node as having a value, a colour and a pointer to another node that is LC and RC
class Node{
	int val;
	boolean colour;
	Node LC;
	Node RC;
	
	//Node class constructor 
	Node (int val, boolean colour){
		this.val = val;
		this.colour = colour;
	}
	
}
//9ai15@queensu.ca
