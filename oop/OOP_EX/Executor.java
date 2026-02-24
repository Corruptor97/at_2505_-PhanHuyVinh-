package OOP_EX;

public class Executor {
    
    public static void main(String[] args) {

        Shape rect = new Rectangle(5, 3);
        Shape circle = new Circle(4);

        System.out.println("dien tich tam giac: " + rect.getArea());
        System.out.println("chu vi tam giac: " + rect.getPerimeter());

        System.out.println("dien tich hinh vuong: " + circle.getArea());
        System.out.println("Chu vi hinh vuong: " + circle.getPerimeter());
    }
}