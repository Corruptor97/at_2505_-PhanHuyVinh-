package bai_tap_Thanh_toan_nhan_vien;

 public abstract class Employee implements IPayable {

    protected String hoTen;
    protected String cccd;

    public Employee(String hoTen, String cccd) {
        this.hoTen = hoTen;
        this.cccd = cccd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getCccd() {
        return cccd;
    }

    public abstract void hienThiThongTin();
}   
    

