public class Student2 {
    private int ID;
    private String name;
    private String grade;

    
    public Student2() {
        this(0234, "Jeff", "5");
    }

    public Student2(int ID, String name, String grade) {
        this.ID = ID;
        this.name = name;
        this.grade = grade;
    }

    public static void main(String[] args) {
        Student2 std1 = new Student2(); 
        Student2 std2 = new Student2(48574, "Joe", "6");

        System.out.println("Student 1");
        System.out.println(std1.ID);
        System.out.println(std1.name);
        System.out.println(std1.grade);

        System.out.println("Student 2");
        System.out.println(std2.ID);
        System.out.println(std2.name);
        System.out.println(std2.grade);
    }
}    

