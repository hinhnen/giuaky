package mon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

public class MonAdapter extends ArrayAdapter<Mon> {
    Context context;
    int resource;
    ArrayList<Mon> objects;
    public MonAdapter(Context context, int resource,ArrayList<Mon> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,null);

        TextView txtMaMon = convertView.findViewById(R.id.txtMaMon);
        TextView txtTenMon = convertView.findViewById(R.id.txtTenMon);
        TextView txtSoTiet = convertView.findViewById(R.id.txtSoTiet);

        txtMaMon.setText(String.valueOf(objects.get(position).getMaMon()));
        txtTenMon.setText(objects.get(position).getTenMon());
        txtSoTiet.setText(String.valueOf(objects.get(position).getSoTiet()));

        return convertView;
    }
}
