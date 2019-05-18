package diem;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.canhdinh.qlts.R;

import java.util.ArrayList;
import java.util.List;

public class DiemAdapter extends ArrayAdapter<Diem> {
    Context context;
    int layout;
    ArrayList<Diem> arrList;

    public DiemAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<Diem> objects){
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.arrList=objects;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);

        TextView txtMaSV = convertView.findViewById(R.id.txtMaSV);
        TextView txtMMon = convertView.findViewById(R.id.txtMMon);
        TextView txtDiem = convertView.findViewById(R.id.txtDiem);

        txtMaSV.setText(String.valueOf(arrList.get(position).getMaSV()));
        txtMMon.setText(String.valueOf(arrList.get(position).getMaMon()));
        txtDiem.setText(String.valueOf(arrList.get(position).getDiem()));

        return convertView;
    }
}
