package mon;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class MonActivity extends AppCompatActivity {

    public String DATABASE_NAME = "QLTS.db";
    public String DB_PATH_SUFFIX = "/databases/";
    public SQLiteDatabase database = null;
    DBMon db;


    ListView listView;
    ArrayList<Mon> arrayList;
    MonAdapter monAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon);
        
        addControls();
    }

    private void addControls() {
        db = new DBMon(MonActivity.this);

        listView =findViewById(R.id.listMon);
        arrayList =db.getAllMon();
        monAdapter =new MonAdapter(MonActivity.this,
                R.layout.item_mon,
                arrayList);
        listView.setAdapter(monAdapter);
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
            final Mon mon = new Mon();

            AlertDialog.Builder builder = new AlertDialog.Builder(MonActivity.this);

            builder.setTitle("Thêm Môn");
            builder.setCancelable(false);

            LayoutInflater inflater = getLayoutInflater().from(MonActivity.this);
            View view = inflater.inflate(R.layout.layout_monhoc, null);
            final EditText editTenMon = view.findViewById(R.id.editTenMon);
            final EditText editSoTiet = view.findViewById(R.id.editSoTiet);
            builder.setView(view);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mon.setTenMon(editTenMon.getText().toString());
                    mon.setSoTiet(Integer.parseInt(String.valueOf(editSoTiet.getText().toString())));
                    db.insertMon(mon);
                    arrayList.add(mon);
                    monAdapter.notifyDataSetChanged();
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
                final Mon mon = arrayList.get(info.position);

                AlertDialog.Builder builder = new AlertDialog.Builder(MonActivity.this);

                builder.setTitle("Update Mon");
                builder.setCancelable(false);

                LayoutInflater inflater = LayoutInflater.from(MonActivity.this);
                View view = inflater.inflate(R.layout.layout_monhoc, null);

                final EditText edtTenMon = view.findViewById(R.id.editTenMon);
                final EditText edtSoTiet = view.findViewById(R.id.editSoTiet);

                edtTenMon.setText(mon.getTenMon());
                edtSoTiet.setText(String.valueOf(mon.getSoTiet()));

                builder.setView(view);

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mon.setTenMon(edtTenMon.getText().toString());
                        mon.setSoTiet(Integer.parseInt(edtSoTiet.getText().toString()));
                        db.updateMon(mon);
                        arrayList.set(info.position, mon);
                        monAdapter.notifyDataSetChanged();
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
                final Mon mon1 = arrayList.get(info.position);

                AlertDialog.Builder builder1 = new AlertDialog.Builder(MonActivity.this);

                builder1.setTitle("Delete Mon");
                builder1.setCancelable(false);
                builder1.setMessage("Are you sure delete this Mon?");

                builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.deleteMon(mon1);
                        arrayList.remove(info.position);
                        monAdapter.notifyDataSetChanged();
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
