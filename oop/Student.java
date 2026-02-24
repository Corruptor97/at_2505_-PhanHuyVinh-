package oop;
public class Student {
    private String name;
    private String className;
    private double toan;
    private double ly;
    private double hoa;
    private double diemTB;


public void setDiem (double toan, double ly, double hoa){
    this.toan = toan;
    this.ly = ly;
    this.hoa = hoa;

}
public Student (String name, String className){
    this.name = name;
    this.className= className;
} 
public void tinhDiemTrungBinh (){
    this.diemTB = (toan + ly + hoa)/3;
}
public void xepLoai (){
    if (diemTB >= 8){
        System.out.println("gioi");

    }
    else if(diemTB >= 6){
        System.out.println("kha");
    }
    else{
        System.out.println("TB");
    }
}
}