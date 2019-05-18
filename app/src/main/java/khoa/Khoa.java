package khoa;

public class Khoa {
    private int id;
    private String tenkhoa;

    public Khoa() {
    }

    public Khoa(int id, String tenkhoa) {
        this.id = id;
        this.tenkhoa = tenkhoa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenkhoa() {
        return tenkhoa;
    }

    public void setTenkhoa(String tenkhoa) {
        this.tenkhoa = tenkhoa;
    }
}
