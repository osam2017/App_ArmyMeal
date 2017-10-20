package com.example.administrator.myproject6;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Article> {
    private Context context;
    private int layoutResourceId;
    private ArrayList<Article> articleData;

    public CustomAdapter(Context context, int layoutResourceId, ArrayList<Article> articleData) {
        super(context, layoutResourceId, articleData);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.articleData = articleData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if(row == null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
        }


        TextView tvTitle = (TextView) row.findViewById(R.id.TimeText);
        TextView tvContent = (TextView) row.findViewById(R.id.MenuText);

        tvTitle.setText(articleData.get(position).getTime());
        tvContent.setText(articleData.get(position).getMenu());

        return row;
    }

}
