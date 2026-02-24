package bai_tap_Tai_Lieu;

public class Bao extends TaiLieu {
    private String ngayPhatHanh;

    public Bao(String maTaiLieu, String tenNXB, int soBanPhatHanh, String ngayPhatHanh) {
        super(maTaiLieu, tenNXB, soBanPhatHanh);
        this.ngayPhatHanh = ngayPhatHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Bao | Ma: " + maTaiLieu +
                ", NXB: " + tenNXB +
                ", So ban: " + soBanPhatHanh +
                ", Ngay phat hanh: " + ngayPhatHanh);
    }
}