package com.soal.ukg.plpg.pretest.sertifikasi;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class baruadapter extends BaseAdapter
{
    private ArrayList<String> listCountry;
    private ArrayList<String> listdetail;
    private ArrayList<Integer> listFlag;
    private Activity activity;



    public baruadapter(Activity activity, ArrayList<String> listCountry, ArrayList<String> listdetail, ArrayList<Integer> listFlag) {
        super();
        this.listCountry = listCountry;
        this.listdetail = listdetail;
        this.listFlag = listFlag;
        this.activity = activity;




    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listCountry.size();
    }

    @Override
    public String getItem(int position) {
        // TODO Auto-generated method stub
        return listCountry.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static class ViewHolder
    {
        public ImageView imgViewFlag;
        public TextView txtViewTitle;
        public TextView txtViewTitle2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder view;
        LayoutInflater inflator = activity.getLayoutInflater();

        if(convertView==null)
        {
            view = new ViewHolder();
            convertView = inflator.inflate(R.layout.baru_item, null);

            view.txtViewTitle = (TextView) convertView.findViewById(R.id.textView1);
            view.txtViewTitle2 = (TextView) convertView.findViewById(R.id.textView2);
            view.imgViewFlag = (ImageView) convertView.findViewById(R.id.imageView1);

            convertView.setTag(view);
        }
        else
        {
            view = (ViewHolder) convertView.getTag();
        }

        view.txtViewTitle.setText(listCountry.get(position));
        view.txtViewTitle2.setText(listdetail.get(position));
        view.imgViewFlag.setImageResource(listFlag.get(position));

        return convertView;
    }
}