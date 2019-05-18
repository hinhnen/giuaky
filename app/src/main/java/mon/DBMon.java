package mon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class DBMon {
    private static String DATABASE_NAME = "QLTS.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;

    Context context;

    public DBMon(Context context) {
        this.context = context;
        processSQLite();
    }

        private void processSQLite(){
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try{
                copyDatabaseFromAsset();
                Toast.makeText(context,"coppy success !", Toast.LENGTH_SHORT).show();
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void copyDatabaseFromAsset(){
        try{
            InputStream dataInputStream = context.getAssets().open(DATABASE_NAME);

            String outputStream = getPathDataSystem();
            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!file.exists()){
                file.mkdir();
            }

            OutputStream dataOutPutStream = new FileOutputStream(outputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = dataInputStream.read(buffer)) > 0){
                dataOutPutStream.write(buffer, 0, length);
            }
            dataOutPutStream.flush();
            dataOutPutStream.close();
            dataInputStream.close();
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getPathDataSystem(){
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public ArrayList<Mon> getAllMon(){
        ArrayList<Mon> arrayList = new ArrayList<>();
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        String sql = "SELECT * FROM Mon";

        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()){
            int MaMon = cursor.getInt(0);
            String TenMon = cursor.getString(1);
            int SoTiet = cursor.getInt(2);
            arrayList.add(new Mon(MaMon,TenMon,SoTiet));
        }
        return arrayList;
    }


    public void insertMon(Mon mon){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("TenMon", mon.getTenMon());
        contentValues.put("SoTiet", mon.getSoTiet());
        if(db.insert("Mon", null, contentValues)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }



    public void updateMon(Mon mon){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("TenMon", mon.getTenMon());
        contentValues.put("SoTiet", mon.getSoTiet());
        if(db.update("Mon", contentValues, "MaMon = " + mon.getMaMon(), null) > 0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteMon(Mon mon){
        db = context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);

        if(db.delete("Mon", "MaMon="+ mon.getMaMon(), null)>0){
            Toast.makeText(context, "success !", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }
    }
}
