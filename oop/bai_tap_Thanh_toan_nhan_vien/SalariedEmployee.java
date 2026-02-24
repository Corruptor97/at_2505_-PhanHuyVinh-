package bai_tap_Thanh_toan_nhan_vien;

public class SalariedEmployee extends Employee {

    private double weeklySalary;

    public SalariedEmployee(String hoTen, String cccd, double weeklySalary) {
        super(hoTen, cccd);
        this.weeklySalary = weeklySalary;
    }

    public double getPaymentAmount() {
        return weeklySalary;
    }

    public void hienThiThongTin() {
        System.out.println(
            "Salaried Employee | Ten: " + hoTen +
            " | CCCD: " + cccd +
            " | Luong tuan: " + getPaymentAmount()
        );
    }
}