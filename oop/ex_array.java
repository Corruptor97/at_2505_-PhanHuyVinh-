import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ex_array {
      public static void main(String[] args) {
        ArrayList<String> colors = new ArrayList<>();
        colors.add("Blue");
        colors.add("green");
        colors.add(0,"Red");

        ArrayList<String> colors2 = new ArrayList<>();
        colors2.add("Blue");
        colors2.add("green");
        colors2.add(0,"Red");

        colors.addAll(colors2);
        for (String color : colors) {
        System.out.println(color);
    }
        HashMap<Integer, String> map = new HashMap<>();

   
        map.put(1, "Red");
        map.put(2, "Green");
        
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
