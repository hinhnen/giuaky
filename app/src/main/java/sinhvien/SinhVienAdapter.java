package sinhvien;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.canhdinh.qlts.R;

import java.util.ArrayList;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    Context context;
    int layout;
    ArrayList<SinhVien> arrList;

    public SinhVienAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<SinhVien> objects){
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.arrList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layout, null);

        TextView txtMaSV = convertView.findViewById(R.id.txtMaSV);
        ImageView img = (ImageView)convertView.findViewById(R.id.image);
        TextView txtHoVaTen = (TextView)convertView.findViewById(R.id.txtHoVaTen);
        TextView txtDiaChi = (TextView)convertView.findViewById(R.id.txtDiaChi);
        TextView txtDienThoai = (TextView)convertView.findViewById(R.id.txtDienThoai);
        TextView txtNgaySinh = (TextView)convertView.findViewById(R.id.txtNgaySinh);
        TextView txtKhoa = (TextView)convertView.findViewById(R.id.txtKhoa);

        if(arrList.get(position).getPhai()==1){
            img.setImageResource(R.drawable.girl);
        } else {
            img.setImageResource(R.drawable.boy);
        }
        txtMaSV.setText(String.valueOf(arrList.get(position).getMaSV()));
        txtHoVaTen.setText(arrList.get(position).getHoVaTen());
        txtDiaChi.setText(arrList.get(position).getDiaChi());
        txtDienThoai.setText(arrList.get(position).getDienThoai());
        txtNgaySinh.setText(arrList.get(position).getNgaySinh());
        txtKhoa.setText(String.valueOf(arrList.get(position).getMaKhoa()));
        return convertView;
    }
}
