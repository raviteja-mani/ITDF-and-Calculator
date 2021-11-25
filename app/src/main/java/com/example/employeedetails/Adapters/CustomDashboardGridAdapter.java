package com.example.employeedetails.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.employeedetails.ModalClasses.DashboardItem;
import com.example.employeedetails.R;

import java.util.ArrayList;

public class CustomDashboardGridAdapter extends ArrayAdapter<DashboardItem> {
    ArrayList<DashboardItem> dashArray;
    int resouseId;
    Context context;

    public CustomDashboardGridAdapter(ArrayList<DashboardItem> dashArray, int resouseId, Context context) {
        super(context,resouseId,dashArray);
        this.dashArray = dashArray;
        this.resouseId = resouseId;
        this.context = context;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;
        try {
            if (row == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                row = inflater.inflate(resouseId, parent, false);
                holder = new RecordHolder();
                holder.imageItem = (ImageView) row.findViewById(R.id.item_image);
                holder.txtCount = (TextView) row.findViewById(R.id.txtCount);
                row.setTag(holder);
            }
            else {
                holder = (RecordHolder) row.getTag();
            }
            DashboardItem item = dashArray.get(position);
            holder.imageItem.setImageBitmap(item.getImageBitmap());
        }
        catch (Exception ex){
            //todo
        }
        return row;
    }

    static class RecordHolder {
        TextView txtTitle;
        ImageView imageItem;
        TextView txtCount;
    }
}
