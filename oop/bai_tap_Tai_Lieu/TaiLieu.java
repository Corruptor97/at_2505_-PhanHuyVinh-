package bai_tap_Tai_Lieu;
public abstract class TaiLieu {
    protected String maTaiLieu;
    protected String tenNXB;
    protected int soBanPhatHanh;

    public TaiLieu(String maTaiLieu, String tenNXB, int soBanPhatHanh) {
        this.maTaiLieu = maTaiLieu;
        this.tenNXB = tenNXB;
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public abstract void hienThiThongTin();
}