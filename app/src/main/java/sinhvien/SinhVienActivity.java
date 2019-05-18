package sinhvien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class SinhVienActivity extends AppCompatActivity {

    public String DATABASE_NAME = "QLTS.db";
    public String DB_PATH_SUFFIX = "/databases/";
    public SQLiteDatabase database = null;


    ListView listView;

    ArrayList<SinhVien> arrayList;
    SinhVienAdapter sinhVienAdapter;

    ArrayList<String> tenkhoa;
    ArrayAdapter<String> adapterKhoa;
    Spinner spinner;

    DBSinhVien db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinh_vien);

        addControls();
    }

    private void addControls() {
        db = new DBSinhVien(SinhVienActivity.this);
        listView = findViewById(R.id.listSV);
        arrayList = db.getAllStudent();
        sinhVienAdapter = new SinhVienAdapter(SinhVienActivity.this,
                R.layout.item_sinhvien,
                arrayList);
        listView.setAdapter(sinhVienAdapter);

        registerForContextMenu(listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId()==R.id.mnu_add){
            final SinhVien sinhVien = new SinhVien();

            AlertDialog.Builder builder = new AlertDialog.Builder(SinhVienActivity.this);

            builder.setTitle("Thêm Sinh Viên");
            builder.setCancelable(false);

            LayoutInflater inflater = getLayoutInflater().from(SinhVienActivity.this);
            View view = inflater.inflate(R.layout.layout_sinhvien, null);

            final EditText edtHoVaTen = (EditText)view.findViewById(R.id.edtHoVaTen);
            final EditText edtDiaChi = (EditText)view.findViewById(R.id.edtDiaChi);
            final EditText edtDienThoai = (EditText)view.findViewById(R.id.edtDienThoai);
            final EditText edtNgaySinh = (EditText)view.findViewById(R.id.edtNgaySinh);
            final EditText edtMaKhoa = (EditText)view.findViewById(R.id.edtMaKhoa);
            final RadioGroup rbg = (RadioGroup) view.findViewById(R.id.rdgtPhai);
            final ImageView img = (ImageView)view.findViewById(R.id.image);

            builder.setView(view);

            rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId){
                        case R.id.rdbFeMale:
                            sinhVien.setPhai(1);
                            img.setImageResource(R.drawable.girl);
                            break;
                        case R.id.rdbMale:
                            sinhVien.setPhai(0);
                            img.setImageResource(R.drawable.boy);
                            break;
                    }
                }
            });

            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sinhVien.setHoVaTen(edtHoVaTen.getText().toString());
                    sinhVien.setDiaChi(edtDiaChi.getText().toString());
                    sinhVien.setDienThoai(edtDienThoai.getText().toString());
                    sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                    sinhVien.setMaKhoa(parseInt(edtMaKhoa.getText().toString()));

                    db.insertStudent(sinhVien);
                    arrayList.add(sinhVien);
                    sinhVienAdapter.notifyDataSetChanged();
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.mnu_update:
                final SinhVien sinhVien = arrayList.get(info.position);

                AlertDialog.Builder builder = new AlertDialog.Builder(SinhVienActivity.this);

                builder.setTitle("Update sinhVien");
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(SinhVienActivity.this);
                View view = inflater.inflate(R.layout.layout_sinhvien, null);

                final EditText edtHoVaTen = (EditText)view.findViewById(R.id.edtHoVaTen);
                final EditText edtDiaChi = (EditText)view.findViewById(R.id.edtDiaChi);
                final EditText edtDienThoai = (EditText)view.findViewById(R.id.edtDienThoai);
                final EditText edtNgaySinh = (EditText)view.findViewById(R.id.edtNgaySinh);
                final EditText edtMaKhoa = (EditText)view.findViewById(R.id.edtMaKhoa);
                final RadioGroup rbg = (RadioGroup) view.findViewById(R.id.rdgtPhai);
                final RadioButton rdbMale = (RadioButton)view.findViewById(R.id.rdbMale);
                final RadioButton rdbFeMale = (RadioButton)view.findViewById(R.id.rdbFeMale);
                final ImageView img = (ImageView)view.findViewById(R.id.image);

                edtHoVaTen.setText(sinhVien.getHoVaTen());
                edtDiaChi.setText(sinhVien.getDiaChi());
                edtDienThoai.setText(sinhVien.getDienThoai());
                edtNgaySinh.setText(sinhVien.getNgaySinh());
                edtMaKhoa.setText(String.valueOf(sinhVien.getMaKhoa()));

                if(sinhVien.getPhai()==1){
                    rdbFeMale.setChecked(true);
                    img.setImageResource(R.drawable.girlion);
                } else {
                    rdbMale.setChecked(true);
                    img.setImageResource(R.drawable.boyicon);
                }

                builder.setView(view);

                rbg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        switch (checkedId){
                            case R.id.rdbFeMale:
                                sinhVien.setPhai(1);
                                img.setImageResource(R.drawable.girlion);
                                break;
                            case R.id.rdbMale:
                                sinhVien.setPhai(0);
                                img.setImageResource(R.drawable.boyicon);
                                break;
                        }
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sinhVien.setHoVaTen(edtHoVaTen.getText().toString());
                        sinhVien.setDiaChi(edtDiaChi.getText().toString());
                        sinhVien.setDienThoai(edtDienThoai.getText().toString());
                        sinhVien.setNgaySinh(edtNgaySinh.getText().toString());
                        sinhVien.setMaKhoa(parseInt(edtMaKhoa.getText().toString()));

                        db.updateStudent(sinhVien);
                        arrayList.set(info.position, sinhVien);
                        sinhVienAdapter.notifyDataSetChanged();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                break;
            case R.id.mnu_delete:
                final SinhVien sinhVien1 = arrayList.get(info.position);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(SinhVienActivity.this);

                builder1.setTitle("Delete sinhVien");
                builder1.setCancelable(false);
                builder1.setMessage("Are you sure delete this sinhVien?");

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteStudent(sinhVien1);
                        arrayList.remove(info.position);
                        sinhVienAdapter.notifyDataSetChanged();
                    }
                });
                builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialog1 = builder1.create();
                alertDialog1.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

}
