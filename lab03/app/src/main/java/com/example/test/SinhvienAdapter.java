package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SinhvienAdapter extends BaseAdapter {
    private Context context;
    private List<Sinhvien> listSinhvien;
    public SinhvienAdapter(Context context,List<Sinhvien> listSinhvien)
    {
        this.context=context;
        this.listSinhvien=listSinhvien;
    }

    @Override
    public int getCount() {
        return listSinhvien.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null)
        {
            viewHolder=new ViewHolder();

            //LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView=LayoutInflater.from(context).inflate(R.layout.item_lv,null,false);

            viewHolder.tvMssv=convertView.findViewById(R.id.tv_mssv);
            viewHolder.ivGioitinh=convertView.findViewById(R.id.iv_gioitinh);
            viewHolder.tvName=convertView.findViewById(R.id.tv_user);
            viewHolder.tvPhone=convertView.findViewById(R.id.tvphone);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        Sinhvien sinhvien=listSinhvien.get(position);
        viewHolder.tvMssv.setText(sinhvien.getID());
        viewHolder.tvName.setText(sinhvien.getName());
        viewHolder.tvPhone.setText(sinhvien.getPhone());
        if(sinhvien.getGioitinh()==0)
        {
            viewHolder.ivGioitinh.setVisibility(View.VISIBLE);
            viewHolder.ivGioitinh.setImageResource(R.drawable.ic_man);
        }else
        {
            viewHolder.ivGioitinh.setVisibility(View.VISIBLE);
            viewHolder.ivGioitinh.setImageResource(R.drawable.ic_female);
        }
        return convertView;
    }
    class ViewHolder
    {
        ImageView ivGioitinh;
        TextView tvMssv,tvName,tvPhone;
    }
}
