package bai_tap_Thanh_toan_nhan_vien;

public class PayrollSystem {

    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        int count = 0;

        employees[count++] = new SalariedEmployee(
                "Nguyen Van A", "0123456789", 8000000);

        employees[count++] = new HourlyEmployee(
                "Tran Thi B", "9876543210", 50000, 160);

        employees[count++] = new SalariedEmployee(
                "Le Van C", "111222333", 10000000);

        System.out.println("=== BANG LUONG NHAN VIEN ===");

        for (int i = 0; i < count; i++) {
            employees[i].hienThiThongTin();
        }
    }
}