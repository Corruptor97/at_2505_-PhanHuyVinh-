package bai_tap_Thanh_toan_nhan_vien;

public class HourlyEmployee extends Employee {

    private double wage;
    private int hours;

    public HourlyEmployee(String hoTen, String cccd, double wage, int hours) {
        super(hoTen, cccd);
        this.wage = wage;
        this.hours = hours;
    }

    public double getPaymentAmount() {
        return wage * hours;
    }

    public void hienThiThongTin() {
        System.out.println(
            "Hourly Employee | Ten: " + hoTen +
            " | CCCD: " + cccd +
            " | Luong: " + getPaymentAmount()
        );
    }
}