package khoa;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

public class KhoaActivity extends AppCompatActivity {

    public String DATABASE_NAME = "QLTS.db";
    public String DB_PATH_SUFFIX = "/databases/";
    public SQLiteDatabase database = null;

    EditText tenkhoa;
    TextView maKhoa;
    Button themkhoa, suakhoa;

    ListView listkhoa;
    ArrayList<String> dsKhoa;
    ArrayAdapter<String> adapterKhoa;

    String ma = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khoa);

        //xuLySaoChepCSDL();
        setControls();
        setEvents();
        showAllKhoa();

    }

    private void setEvents() {
        themkhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themKhoa();
            }
        });

        suakhoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                suaKhoa();
            }
        });

        listkhoa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Vị trí id nên không được thay đổi
                if ((position-1)%2 !=0){
                    Toast.makeText(KhoaActivity.this, "Vị Trí Không Được Cập Nhật", Toast.LENGTH_SHORT).show();
                }
                else {
                    ma= dsKhoa.get(position-1); // lấy mã khoa
                    tenkhoa.setText(dsKhoa.get(position)); // gán tên khoa vào edittext
                }
            }
        });

        listkhoa.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if ((position%2==0)){
                    ma = dsKhoa.get(position);
                    xoaKhoa();
                }
                else {
                    tenkhoa.setText(dsKhoa.get(position)); // gán tên khoa vào edittext
                }
                return false;
            }
        });
    }

    private void setControls() {
       // maKhoa = findViewById(R.id.maKhoa);
        tenkhoa = findViewById(R.id.tenKhoa);
        themkhoa = findViewById(R.id.btnthemkhoa);
        suakhoa = findViewById(R.id.btnsuakhoa);
        //xoakhoa = findViewById(R.id.btnxoakhoa);

        listkhoa = findViewById(R.id.listkhoa);
        dsKhoa = new ArrayList<>();
        adapterKhoa = new ArrayAdapter(
                KhoaActivity.this,
                android.R.layout.simple_list_item_1,
                dsKhoa
        );
        listkhoa.setAdapter(adapterKhoa);
    }

    //them khoa
    private void themKhoa() {
        ContentValues values = new ContentValues();
        values.put("TenKhoa", tenkhoa.getText().toString());
        long r = database.insert("khoa", null, values);
        Toast.makeText(KhoaActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
        showAllKhoa();
        adapterKhoa.notifyDataSetChanged();
    }

    private void suaKhoa() {
        ContentValues row = new ContentValues();
        row.put("TenKhoa", tenkhoa.getText().toString());
        database.update("khoa", row, "MaKhoa=?", new String[]{ma.toString()});
        Toast.makeText(KhoaActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
        showAllKhoa();
        adapterKhoa.notifyDataSetChanged();
    }

    private void xoaKhoa(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác Nhận");
        builder.setMessage("Bạn chắc chắn muốn xóa Mã Khoa là: "+ma);
        builder.setIcon(android.R.drawable.ic_input_delete);
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ContentValues row = new ContentValues();
                database.delete("khoa","MaKhoa=?",new String[]{ma.toString()});
                Toast.makeText(KhoaActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                showAllKhoa();
                adapterKhoa.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAllKhoa() {
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor cursor = database.rawQuery("SELECT * FROM Khoa", null);
        dsKhoa.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            dsKhoa.add(String.valueOf(id));
            dsKhoa.add(ten);
        }
        cursor.close();
    }

}
