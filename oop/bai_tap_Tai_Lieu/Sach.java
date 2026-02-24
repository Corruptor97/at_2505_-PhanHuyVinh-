package bai_tap_Tai_Lieu;
public class Sach extends TaiLieu {
    private String tenTacGia;
    private int soTrang;

    public Sach(String maTaiLieu, String tenNXB, int soBanPhatHanh, String tacGia, int soTrang) {
        super(maTaiLieu, tenNXB, soBanPhatHanh);
        this.tenTacGia = tacGia;
        this.soTrang = soTrang;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println(
            "Sach | maTaiLieu: " + maTaiLieu +
            ", tenNXB: " + tenNXB +
            ", So ban: " + soBanPhatHanh +
            ", Tac gia: " + tenTacGia +
            ", So trang: " + soTrang
        );
    }
}