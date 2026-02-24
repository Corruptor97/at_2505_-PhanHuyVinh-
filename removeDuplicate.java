
public class removeDuplicate {
    public static void main(String[] args) {
        String S = "feredssagg";
        String new_S = "";

        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);

            if (new_S.indexOf(ch) == -1) {
                new_S += ch;
            }
        }

        System.out.println(new_S);
    }
}

