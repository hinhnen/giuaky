package khoa;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.canhdinh.qlts.R;

import java.util.List;

public class KhoaAdapter extends ArrayAdapter<Khoa> {

    Activity context;
    int resource;
    List<Khoa> objects;

    public KhoaAdapter(Activity context, int resource, List<Khoa> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
        this.resource = resource;
    }

    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
        LayoutInflater inflater =this.context.getLayoutInflater();
        View row =inflater.inflate(this.resource,null);
        TextView ma = row.findViewById(R.id.ma);
        TextView khoa = row.findViewById(R.id.khoa);

        ma.setText(objects.get(position).getId());
        khoa.setText(objects.get(position).getTenkhoa());
        return row;
    }
}
