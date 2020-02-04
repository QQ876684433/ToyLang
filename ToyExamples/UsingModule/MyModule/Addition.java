package UsingModule.MyModule;

public class Addition {

    public static void main(String[] args) {
        System.out.println(new Addition().add(1, 2));
    }

    public int add(int x, int y) {
        return x + y;
    }
}