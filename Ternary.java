public class Ternary {
    public static void main(String[] args) {

        Integer x = 128;
        Integer y = 128;
        System.out.println( (x==y) + " "+y );
        System.out.println(x == y ? "A" : "B");

        x = 10;
        y = 10;
        System.out.println(x == y ? "A" : "B");
        System.out.println(10 == 10 ? "A" : "B");
    }
}