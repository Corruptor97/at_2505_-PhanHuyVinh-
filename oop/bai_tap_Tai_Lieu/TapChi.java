package bai_tap_Tai_Lieu;

public class TapChi extends TaiLieu {
    private int soPhatHanh;
    private int thangPhatHanh;

    public TapChi(String maTaiLieu, String tenNXB, int soBanPhatHanh,
                  int soPhatHanh, int thangPhatHanh) {
        super(maTaiLieu, tenNXB, soBanPhatHanh);
        this.soPhatHanh = soPhatHanh;
        this.thangPhatHanh = thangPhatHanh;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Tap chi | Ma: " + maTaiLieu +
                ", NXB: " + tenNXB +
                ", So ban: " + soBanPhatHanh +
                ", So phat hanh: " + soPhatHanh +
                ", Thang: " + thangPhatHanh);
    }
}