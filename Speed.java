public class Speed{
    public static void main(String[] args){
System.out.println("enter hours ");
    double hours = Double.parseDouble(args[0]);
System.out.println("enter minutes ");   
    double minutes = Double.parseDouble(args[1]);
System.out.println("enter seconds ");   
    double seconds = Double.parseDouble(args[2]);
 System.out.println("enter distance ");   
    double distance = Double.parseDouble(args[3]);

    double SumofSeconds = (hours * 3600) + (minutes * 60) + seconds;

    double speedMperS = distance / SumofSeconds;
    System.out.println("Your speed in meters/second is " + speedMperS);
    double speedKmPerHrs = (distance / 1000.0) / (SumofSeconds / 3600);
    System.out.println("Your speed in km/h " + speedKmPerHrs);
    double speedMilesPerHrs = (distance / 1609.344) / (SumofSeconds / 3600);
    System.out.println("Your speed in miles/h " + speedMilesPerHrs);
   
    
}

}