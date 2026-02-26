public class Break {
    public static void main(String[] args) {

    int number = Integer.parseInt(args[0]);

    String str = Integer.toString(number);

        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i) + " ");
        }
    }
}
