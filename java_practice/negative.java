public class negative {
    public static void main(String[] args) {
        int[] bugSeverities = {1, 2, 3, 5, 4, 2};
        int number = 5; 

        boolean found = false;

        for (int i = 0; i < bugSeverities.length; i++) {
            if (bugSeverities[i] == number) {
                System.out.println("position " + i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Không tìm thấy");
        }
    }
}