
enum DaysOfWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

public class TestDaysOfWeek {
    public static void main(String[] args) {
        DaysOfWeek day = DaysOfWeek.SATURDAY;


                if (day == DaysOfWeek.SATURDAY || day == DaysOfWeek.SUNDAY) {
            System.out.println(day + " is a Weekend");
        } else {
            System.out.println(day + " is a Weekday");
        }
    }
}
    
