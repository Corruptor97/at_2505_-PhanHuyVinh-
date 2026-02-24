package bai_tap_Tai_Lieu;

public class QuanLySach {

    private TaiLieu[] danhSach = new TaiLieu[100];
    private int count = 0;

    // Thêm tài liệu
    public void themTaiLieu(TaiLieu tl) {
        if (count < danhSach.length) {
            danhSach[count] = tl;
            count++;
        }
    }

    
    public void xoaTheoMa(String ma) {
        for (int i = 0; i < count; i++) {
            if (danhSach[i].getMaTaiLieu().equalsIgnoreCase(ma)) {
                for (int j = i; j < count - 1; j++) {
                    danhSach[j] = danhSach[j + 1];
                }
                danhSach[count - 1] = null;
                count--;
                break;
            }
        }
    }

   
    public void hienThiTatCa() {
        for (int i = 0; i < count; i++) {
            danhSach[i].hienThiThongTin();
        }
    }

   
    public void timTheoLoai(String loai) {
        for (int i = 0; i < count; i++) {
            TaiLieu tl = danhSach[i];

            if (loai.equalsIgnoreCase("sach") && tl instanceof Sach ||
                loai.equalsIgnoreCase("tapchi") && tl instanceof TapChi ||
                loai.equalsIgnoreCase("bao") && tl instanceof Bao) {
                tl.hienThiThongTin();
            }
        }
    }

    public static void main(String[] args) {
        QuanLySach ql = new QuanLySach();

        ql.themTaiLieu(new Sach("JKD", "NXB Tre", 1000, "Nguyen Nhat Anh", 300));
        ql.themTaiLieu(new TapChi("TC01", "NXB GD", 500, 12, 8));
        ql.themTaiLieu(new Bao("B01", "Thanh Nien", 2000, "01/02/2026"));

        System.out.println("Tat ca tai lieu");
        ql.hienThiTatCa();

        System.out.println("\n Tim Sach");
        ql.timTheoLoai("sach");

        System.out.println("\n Xoa ma ");
        ql.xoaTheoMa("JKD");
        ql.hienThiTatCa();
    }
}