public class Book {
   private String title;
   private String author;
   private double price;


   public Book() {
        title = "ABC";
        author = "Joe";
        price = 2;

    }

   public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.price = 6;
       
    }

   public Book(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static void main(String[] args) {
        Book book1 = new Book();
        Book book2 = new Book("DEF", "Jeff");
        Book book3 = new Book("FDF", "John", 5);
        System.out.println("book 1");
        System.out.println(book1.title);
        System.out.println(book1.author);
        System.out.println(book1.price);
        System.out.println("book 2");
        System.out.println(book2.title);
        System.out.println(book2.author);
        System.out.println(book2.price);
        System.out.println("book 3");
        System.out.println(book3.title);
        System.out.println(book3.author);
        System.out.println(book3.price);


    }
}