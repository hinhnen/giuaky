package com.canhdinh.qlts;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import diem.DiemActivity;
import khoa.KhoaActivity;
import mon.MonActivity;
import sinhvien.SinhVienActivity;

public class MainActivity extends AppCompatActivity {

    public String DATABASE_NAME = "QLTS.db";
    public String DB_PATH_SUFFIX = "/databases/";
    public SQLiteDatabase database = null;

    CardView khoa,sinhvien,mon,diem;
    //ImageView imgkhoa,imgsinhvien,imgmon,imgdiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xuLySaoChepCSDL();

        setControl();

        khoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, KhoaActivity.class);
                khoa.getContext().startActivity(intent);
            }
        });
        sinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SinhVienActivity.class);
                sinhvien.getContext().startActivity(intent);
            }
        });
        mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonActivity.class);
                mon.getContext().startActivity(intent);
            }
        });
        diem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiemActivity.class);
                diem.getContext().startActivity(intent);
            }
        });

    }

    private void setControl() {
        khoa = findViewById(R.id.khoa);
        diem = findViewById(R.id.diem);
        mon = findViewById(R.id.mon);
        sinhvien = findViewById(R.id.sinhvien);

//        imgdiem = findViewById(R.id.imgdiem);
//        imgkhoa = findViewById(R.id.imgkhoa);
//        imgsinhvien = findViewById(R.id.imgsinhvien);
//        imgmon = findViewById(R.id.imgmon);
    }

    private void xuLySaoChepCSDL() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        if (!dbFile.exists()) { // nếu chưa tồn tại thì sao chép
            try {
                CopyDataBaseFromAsset();
                Toast.makeText(this, "sao chép thành công", Toast.LENGTH_LONG).show();
            } catch
            (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    private void CopyDataBaseFromAsset() {
        InputStream myInput;
        try {
            myInput = getAssets().open(DATABASE_NAME);
            String tenFile = layDuongDanLuuTru();
            // xem trong thư mục tồn tại chưa, nếu chưa thì tạo mới
            File duongDan = new File(getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if (!duongDan.exists()) {// hoi xem ton tai hay chua
                duongDan.mkdir(); // tao thu muc
            }
            OutputStream myOutput = new FileOutputStream(tenFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            // Close the streams
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            Log.e("Loi", e.toString());
        }
    }

    private String layDuongDanLuuTru() {
        //getApplicationInfo().dataDir tra ve thu muc goc cai dat : tra ve package
        return getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }
}
