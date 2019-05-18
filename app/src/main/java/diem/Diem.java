package diem;

import java.io.Serializable;

public class Diem implements Serializable {

    private int MaSV;
    private int MaMon;
    private float Diem;

    public Diem() {
    }

    public Diem(int maSV, int maMon, float diem) {
        MaSV = maSV;
        MaMon = maMon;
        Diem = diem;
    }

    public int getMaSV() {
        return MaSV;
    }

    public void setMaSV(int maSV) {
        MaSV = maSV;
    }

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public float getDiem() {
        return Diem;
    }

    public void setDiem(float diem) {
        Diem = diem;
    }
}
