
class LinkedList {

  Node head;

  //Node class
  static class Node {
    int data;
    Node next;
    Node(int d) {
      data = d;
      next = null;

    }
  }

  //traversal
  public void printList(){
    Node n = head;

    while(n != null) {
      System.out.printf(n.data +  " ");
      n = n.next;
    }
  }

  //Insert
  public void push(int d){
    Node n = new Node(d);
    n.next = head;
    head = n;
  }

  //insert after given node
  public void insertAfter(Node prev, int d) {
    if (prev == null) return;

    Node n = new Node(d);
    n. next = prev.next;
    prev.next = n;
  }

  //insert at the end of the list
  public void append(int d) {
    Node n = new Node(d);

    if(head == null) {
      head = new Node(d);
      return;
    }

    n.next = null;
    Node last = head;

    while(last.next != null){
      last = last.next;
    }

    last.next = n;
    return;
  }

  //delete a node w/ a certain value
  public void delete(int key){
    Node temp = head;
    Node prev = null;

    if(temp != null && temp.data == key){
      head = temp.next;
      return;
    }

    while(temp != null && temp.data != key){
      prev = temp;
      temp = temp.next;
    }
    if(temp == null) return;
    prev.next = temp.next;
  }

  public int getCount(Node n){
    if(n == null) return 0;
    return 1 + getCount(n.next);
  }

  public boolean search(Node head, int key){
    if(head == null) return false;
    if(head.data == key)  return true;
    return search(head.next, key);
  }


}
