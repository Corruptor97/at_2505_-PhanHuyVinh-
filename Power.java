public class Power {
    public static void main(String[] args) {
      
        System.out.print("Input a number: ");
        double number = Double.parseDouble(args[0]);

        double square = number*number;
        double cube = number*number*number;
        double fourthpower = number*number*number*number;
        System.out.println("Square " + square);
        System.out.println("Cube " + cube);
        System.out.println("fourthpower " + fourthpower);
    }
}