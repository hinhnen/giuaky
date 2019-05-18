package mon;

import java.io.Serializable;

public class Mon implements Serializable {
    private  int MaMon;
    private String TenMon;
    private int SoTiet;

    public Mon()  {
    }

    public Mon(int maMon, String tenMon, int soTiet) {
        MaMon = maMon;
        TenMon = tenMon;
        SoTiet = soTiet;
    }

    public int getMaMon() {
        return MaMon;
    }

    public void setMaMon(int maMon) {
        MaMon = maMon;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public int getSoTiet() {
        return SoTiet;
    }

    public void setSoTiet(int soTiet) {
        SoTiet = soTiet;
    }
}
