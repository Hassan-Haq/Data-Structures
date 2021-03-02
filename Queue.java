public class Queue {

  Stack stack1;
  Stack stack2;

  static void push(Stack stac, int d){
    stac.push(d);
  }

  static int pop(Stack stac){
    if(stac.isEmpty()){
      System.out.println("Stack Underflow");
      System.exit(0);
    }
    return stac.pop();
  }

  static void enQueue(Queue q, int d){
    push(q.stack1, d);
  }

  static int deQueue(Queue q){
    int x;
    if(q.stack1.isEmpty() && q.stack2.isEmpty()){
      System.out.println("Queue is empty");
      System.exit(0);
    }

    if(q.stack2.isEmpty()){
      while(!q.stack1.isEmpty()){
        x = q.stack1.pop();
        push(q.stack2, x);
      }
    }
    x = pop(q.stack2);
    return x;
  }
}
