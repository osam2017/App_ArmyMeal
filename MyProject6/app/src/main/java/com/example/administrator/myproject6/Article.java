package com.example.administrator.myproject6;

import android.widget.ArrayAdapter;

/**
 * Created by Administrator on 2017-10-19.
 */

public class Article {

    private String time;
    private String menu;

    public  Article(String time, String menu){
        this.time = time;
        this.menu = menu;
    }

    public String getTime() {
        return time;
    }

    public String getMenu() {
        return menu;
    }
}

