public class PasserBy {
    public static void swap(int x, int y) {
        int tmp = x;
        x = y;
        y = tmp;
    }
    public static void main(String[] args) {
        int i = 1, j = 2;
        swap(i, j);
        System.out.println(i + " " + j);
    }
}