import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Restaurant {     // Resturent_Management_System_Java_Code
     
	public static void main(String[] args){
        full();
     }
     public static void full(){

         System.out.println("\t\t\t\t\t\t-----------------------------------");
         System.out.println("\t\t\t\t\t\t\t YARA RESTAURANT");
         System.out.println("\t\t\t\t\t\t===================================");
         System.out.println("\n\n\t\t\t1.Admin");
         System.out.println("\t\t\t2.Waiter");
         System.out.println("\t\t\t3.Customer");

         char c;
         Scanner sc=new Scanner(System.in);
         System.out.print("\n\t\t\tPlease Press(1/2/3) :");
         c=sc.nextLine().charAt(0);
         if(c=='1'){
            admin obj=new admin(); //for Admin;
            obj.cni();
         }
         else if(c=='2'){
            waiter obj2=new waiter(); //for Waiter;
            obj2.cid();
         }
         else if(c=='3'){
             coustomer obj2=new coustomer (); //for Customer;
             obj2.cid();
         }
         else
           full();
           Scanner bc=new Scanner(System.in);
           System.out.println("\n\n\t\tPress m to return main menu or press any other key to exit");
           int b=bc.nextLine().charAt(0);
           if(b=='m'){
           full();
           }
          
     }
     
     }
    


class admin {
    static ArrayList<String > tables = new ArrayList<String>();
    static ArrayList<String> waiterlist = new ArrayList<String>();
  public static void cni(){
      System.out.println("\n\n\t\t\t1.Need to add table");
      System.out.println("\t\t\t2.Assign waiter to table");
      System.out.println("\t\t\t3.Exit");

      char c;
      Scanner sc=new Scanner(System.in);
      System.out.print("\n\t\t\tPlease Press(1/2) :");
      c=sc.nextLine().charAt(0);
      if(c=='1'){
          System.out.print("Please Add the table ");
          Scanner scc=new Scanner(System.in);
          String name=scc.nextLine();
          System.out.println("Table  "+ name +" is add succesfully");

      }
      else if(c=='2'){
          System.out.print("Please Enter  the waiter  name  ");
          Scanner scc=new Scanner(System.in);
          String name=scc.nextLine();
          System.out.print("Please Enter  the table number to which waiter need to assign ");
          String number=scc.nextLine();
          System.out.println("waiter "+name+" is assigned to the table "+ number);
      }

      else{
          System.out.println("please give the correct optin");
      }
  }


}
class waiter {
    public static void cid() {
        int bill=0;
        System.out.println("Place the order from the below the list");
        HashMap<String, Integer> menu = new HashMap<String, Integer>();
        menu.put("Daryabadi Biryani", 100);
        menu.put("Beef", 100);
        menu.put("Vegetable Biryani", 100);
        menu.put("Burger", 100);
        menu.put("Mutton Rezala", 100);
        menu.put("Fish Tikka", 100);
        menu.put("Mutton Fry", 100);
        menu.put("Chicken Biryani", 100);
        menu.put("Cold Drinks", 100);
        for (String i : menu.keySet()) {
            System.out.println("Item: " + i +"                    "+ "Price: " + menu.get(i));
        }
        System.out.println("Enter the Item Name :" );
        Scanner sc=new Scanner(System.in);

        String  s=sc.nextLine();
        System.out.println("Selected Item Name : " + s);
        if(s !=null) {
        //bill=bill+menu.get(s);
        if(menu.containsKey(s)){
            bill=bill+menu.get(s);
          	  }else {
          		  System.out.println("Item name not found please reorder other item");  
          	  }
        }
        int c=0;
      do{
          System.out.println("Enter 1 to add the item name : " +"" +"Enter 2 bill: ");

try {
          c=sc.nextInt();
}catch(InputMismatchException e){
	System.out.println("Enter 1 to add the item name :");
}
          if(c==1) {
              System.out.println("Enter the item name :");
              Scanner scc=new Scanner(System.in);
              String  ss=scc.nextLine();
              System.out.println("Selected Item Name :" + ss);
              if(menu.get(ss)!=null) {
            	  if(menu.containsKey(ss)){
              bill=bill+menu.get(ss);
            	  }else {
            		  System.out.println("Item name not found please reorder other item");  
            	  }
              }
          }

      }
      while (c==1);
        System.out.println("to see the bill for the items press 1");
        try {     
        int k= sc.nextInt();
        if(k==1){
            System.out.println("the bill for the items is "+bill);
        }
    }catch(InputMismatchException e){
    	System.out.println("to see the bill for the items press 1");
    }
       
    }
}
class  coustomer {
    public static void cid(){
        System.out.println("\n\n\t\t\t1.See Menu");
        System.out.println("\t\t\t2.Book a table");


        char c;
        Scanner sc=new Scanner(System.in);
        System.out.print("\n\t\t\tPlease Press(1/2) :");
        c=sc.nextLine().charAt(0);
        if(c=='1'){
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Product Name|| Product Price|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Daryabadi Biryani|| 100");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Beef|| 239|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Vegetable Biryani|| 110|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Burger|| 80|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Mutton Rezala|| 399|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Fish Tikka|| 299|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Mutton Fry|| 299|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Chicken Biryani|| 199|");
            System.out.println("\t\t------------------------------");
            System.out.println("\t\t|Cold Drinks|| 59|");
            System.out.println("\t\t------------------------------");
        }
        else if(c=='2'){
            System.out.println("please enter the table number to book the table ");
            Scanner scc=new Scanner(System.in);
            String  ss=scc.nextLine();
           System.out.println("your table is selected  "+ss);
        }
    }
}
