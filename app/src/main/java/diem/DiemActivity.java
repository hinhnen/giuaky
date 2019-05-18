package diem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

import mon.Mon;
import mon.MonActivity;

public class DiemActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Diem> arrayList;
    DiemAdapter diemAdapter;

    DBDiem db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem);
        
        addControl();
    }

    private void addControl() {
        db = new  DBDiem(DiemActivity.this);
        listView = findViewById(R.id.listdiem);
        arrayList = db.getAllDiem();
        diemAdapter = new DiemAdapter(DiemActivity.this,
                R.layout.item_diem,
                arrayList);
        listView.setAdapter(diemAdapter);
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
            final Diem mon = new Diem();

            AlertDialog.Builder builder = new AlertDialog.Builder(DiemActivity.this);

            builder.setTitle("Thêm Điểm");
            builder.setCancelable(false);

            LayoutInflater inflater = getLayoutInflater().from(DiemActivity.this);
            View view = inflater.inflate(R.layout.layout_diem, null);
            final EditText editSV = view.findViewById(R.id.maSV);
            final EditText editMaMon = view.findViewById(R.id.maMH);
            final EditText editDiem = view.findViewById(R.id.diem);
            builder.setView(view);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mon.setMaSV(Integer.parseInt(editSV.getText().toString()));
                    mon.setMaMon(Integer.parseInt(editMaMon.getText().toString()));
                    mon.setDiem(Float.parseFloat(editDiem.getText().toString()));
                    db.insertDiem(mon);
                    arrayList.add(mon);
                    diemAdapter.notifyDataSetChanged();
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
                final Diem diem = arrayList.get(info.position);

                AlertDialog.Builder builder = new AlertDialog.Builder(DiemActivity.this);

                builder.setTitle("Cập Nhật Điểm");
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(DiemActivity.this);
                View view = inflater.inflate(R.layout.layout_diem, null);

                final EditText editSV = view.findViewById(R.id.maSV);
                final EditText editMaMon = view.findViewById(R.id.maMH);
                final EditText editDiem = view.findViewById(R.id.diem);

                editSV.setText(String.valueOf(diem.getMaSV()));
                editMaMon.setText(String.valueOf(diem.getMaMon()));
                editDiem.setText(String.valueOf(diem.getDiem()));

                builder.setView(view);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        diem.setMaSV(Integer.parseInt(editSV.getText().toString()));
                        diem.setMaMon(Integer.parseInt(editMaMon.getText().toString()));
                        diem.setDiem(Float.parseFloat(editDiem.getText().toString()));
                        db.updateDiem(diem);
                        arrayList.set(info.position, diem);
                        diemAdapter.notifyDataSetChanged();
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
                final Diem d = arrayList.get(info.position);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(DiemActivity.this);

                builder1.setTitle("Xóa Điểm");
                builder1.setCancelable(false);
                builder1.setMessage("Are you sure delete this Diem?");

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteDiem(d);
                        arrayList.remove(info.position);
                        diemAdapter.notifyDataSetChanged();
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
