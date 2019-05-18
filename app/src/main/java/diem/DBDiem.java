package diem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

import sinhvien.SinhVien;

public class DBDiem  {

    private static String DATABASE_NAME = "QLTS.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;

    Context context;

    public DBDiem(Context context){
        this.context = context;
    }

    public ArrayList<Diem> getAllDiem(){
        ArrayList<Diem> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Diem ";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int MaSV = cursor.getInt(0);
            int MaMon = cursor.getInt(1);
            float Diem = cursor.getFloat(2);
            arrayList.add(new Diem(MaSV, MaMon, Diem));
        }
        return arrayList;
    }


    public void insertDiem(Diem diem){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSV", diem.getMaSV());
        contentValues.put("MaMon", diem.getMaMon());
        contentValues.put("Diem", diem.getDiem());
//        if (0<=diem.getDiem() &&diem.getDiem())
        if(db.insert("Diem", null, contentValues)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDiem(Diem diem){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("MaSV", diem.getMaSV());
        contentValues.put("MaMon", diem.getMaMon());
        contentValues.put("Diem", diem.getDiem());
        if(db.update("Diem", contentValues, ("MaSV = " + diem.getMaSV()+" AND MaMon= "+diem.getMaMon()), null) > 0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteDiem(Diem diem){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        if(db.delete("Diem", "MaSV="+ diem.getMaSV()+" AND MaMon="+diem.getMaMon(), null)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
