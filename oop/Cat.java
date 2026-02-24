
public class Cat {
    private String name;
    private int age;
    

public Cat() {
    name = "Unknown";
    age = 0;

    }

public static void main(String[] args){

    Cat newCat = new Cat();
    System.out.println(newCat.name);
    System.out.println(newCat.age);

    }
}