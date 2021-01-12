public class B extends A
{
    int a1=20;
     public void display() 
    {
       System.out.println("Calss B");
    }
     public static void main(String args[]) 
  {
    A obj1= new B();  
    obj1.display();
    System.out.println("Calss main" +obj1.a1);
  }
     
     public String method1(int i)
     {
     System.out.println("B class method1 first");
     return "hi";
     }
     public void method1()
     {
     System.out.println("B class method1 second");
     }

}


