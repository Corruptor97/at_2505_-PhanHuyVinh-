public class Dog {
    private String name;
    private String color;

public Dog(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public static void main(String[] args) {
        Dog newDog = new Dog("ABC", "red");

        System.out.println(newDog.name);
        System.out.println(newDog.color);
    }

}
