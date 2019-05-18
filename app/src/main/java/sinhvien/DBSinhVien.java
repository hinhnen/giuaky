package sinhvien;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import khoa.Khoa;

public class DBSinhVien {

    private static String DATABASE_NAME = "QLTS.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;

    Context context;

    public DBSinhVien(Context context){
        this.context = context;
       // processSQLite();
    }

//    private void processSQLite(){
//        File dbFile = context.getDatabasePath(DATABASE_NAME);
//        if(!dbFile.exists()){
//            try{
//                copyDatabaseFromAsset();
//                Toast.makeText(context,"coppy success !", Toast.LENGTH_SHORT).show();
//            } catch (Exception ex){
//                ex.printStackTrace();
//            }
//        }
//    }
//
//    private void copyDatabaseFromAsset(){
//        try{
//            InputStream dataInputStream = context.getAssets().open(DATABASE_NAME);
//
//            String outputStream = getPathDataSystem();
//            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
//            if(!file.exists()){
//                file.mkdir();
//            }
//
//            OutputStream dataOutPutStream = new FileOutputStream(outputStream);
//
//            byte[] buffer = new byte[1024];
//            int length;
//            while ((length = dataInputStream.read(buffer)) > 0){
//                dataOutPutStream.write(buffer, 0, length);
//            }
//            dataOutPutStream.flush();
//            dataOutPutStream.close();
//            dataInputStream.close();
//        } catch (Exception ex){
//            ex.printStackTrace();
//        }
//    }
//
//    private String getPathDataSystem(){
//        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
//    }

    public ArrayList<SinhVien> getAllStudent(){
        ArrayList<SinhVien> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM SinhVien ";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int MaSV = cursor.getInt(0);
            String HoVaTen = cursor.getString(1);
            int phai = cursor.getInt(2);
            String NgaySinh = cursor.getString(3);
            String DiaChi = cursor.getString(4);
            String DienThoai = cursor.getString(5);
            int MaKhoa = cursor.getInt(6);

            arrayList.add(new SinhVien(MaSV, HoVaTen, phai, NgaySinh, DiaChi, DienThoai, MaKhoa));
        }
        return arrayList;
    }

    public ArrayList<Khoa> getTenKhoa(){
        ArrayList<Khoa> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Khoa";
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int makhoa = cursor.getInt(0);
            String tenkhoa = cursor.getString(1);
            arrayList.add(new Khoa(makhoa,tenkhoa));
        }
        return arrayList;
    }

    public void insertStudent(SinhVien sinhVien){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("HoVaTen", sinhVien.getHoVaTen());
        contentValues.put("phai", sinhVien.getPhai());
        contentValues.put("NgaySinh", sinhVien.getNgaySinh());
        contentValues.put("DiaChi", sinhVien.getDiaChi());
        contentValues.put("DienThoai", sinhVien.getDienThoai());
        contentValues.put("MaKhoa", sinhVien.getMaKhoa());

        if(db.insert("SinhVien", null, contentValues)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }



    public void updateStudent(SinhVien sinhVien){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("HoVaTen", sinhVien.getHoVaTen());
        contentValues.put("Phai", sinhVien.getPhai());
        contentValues.put("NgaySinh", sinhVien.getNgaySinh());
        contentValues.put("DiaChi", sinhVien.getDiaChi());
        contentValues.put("DienThoai", sinhVien.getDienThoai());
        contentValues.put("MaKhoa", sinhVien.getMaKhoa());

        if(db.update("SinhVien", contentValues, "MaSV = " + sinhVien.getMaSV(), null) > 0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteStudent(SinhVien sinhVien){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        if(db.delete("SinhVien", "MaSV="+ sinhVien.getMaSV(), null)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
