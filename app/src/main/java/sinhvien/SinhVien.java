package sinhvien;

import java.io.Serializable;

public class SinhVien implements Serializable {
    int MaSV;
    String HoVaTen;
    int Phai;
    String DiaChi;
    String NgaySinh;
    String DienThoai;
    int MaKhoa;


    public SinhVien() {

    }

    public SinhVien(int MaSV, String HoVaTen, int Phai, String DiaChi,String NgaySinh, String DienThoai, int MaKhoa) {
        this.MaSV = MaSV;
        this.HoVaTen = HoVaTen;
        this.Phai = Phai;
        this.DiaChi = DiaChi;
        this.NgaySinh = NgaySinh;
        this.DienThoai = DienThoai;
        this.MaKhoa = MaKhoa;
    }

    public void setDienThoai(String dienThoai) {
        DienThoai = dienThoai;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public void setMaKhoa(int maKhoa) {
        MaKhoa = maKhoa;
    }

    public void setPhai(int phai) {
        Phai = phai;
    }

    public int getMaKhoa() {
        return MaKhoa;
    }

    public int getMaSV() {
        return MaSV;
    }

    public int getPhai() {
        return Phai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getDienThoai() {
        return DienThoai;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }
}
