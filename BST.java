public class BST {

  class Node {
    int key;
    Node left, right;

    public Node(int data){
      key = data;
      left = right = null;
    }
  }

  Node root;

  //constructor
  BST() {root = null;}

  void deleteKey (int key) { root = deleteRec(root, key);}
  //recursive delete function
  Node deleteRec(Node root, int key){
    if (root == null) return root;

    //rescurse down tree to find the node w/ the key
    if (key < root.key)
      root.left = deleteRec(root.left, key);
    else if (key > root.key)
      root.right = deleteRec(root.right, key);

    //found the key, node needs to be deleted so we return it's successor
    else {
      if(root.left == null)
        return root.right;
      else if (root.right == null)
        return root.left;

      //node w/ 2 children get the next inorder successor (smallest in right tree)
      System.out.print(root.key + "key before aloc");
      root.key = minValue(root.right);
      System.out.print(root.key + "key after aloc");
      //delete that successor as it's now the parent
      System.out.print(root.right + "right before aloc");
      root.right = deleteRec(root.right, root.key);
      System.out.print(root.right + "right after aloc");
    }
    return root;
  }

  int minValue(Node root) {
    int minv = root.key;
    while(root.left !=null){
      minv = root.left.key;
      root = root.left;
    }
    return minv;
  }

  void insert(int key) { root = insertRec(root, key);}

  Node insertRec(Node root, int key){
    //empty tree, create new node
    if(root == null){
      root = new Node(key);
      return root;
    }

    //recurse down tree
    if(key < root.key)
      root.left = insertRec(root.left, key);
    else if(key > root.key)
      root.right = insertRec(root.right, key);

    return root;
  }

  void inorder() { inorderRec(root);}

  void inorderRec(Node root){
    if(root != null){
      inorderRec(root.left);
      System.out.print(root.key + " ");
      inorderRec(root.right);
    }
  }

}
