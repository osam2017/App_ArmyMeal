package com.example.administrator.myproject6;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.webkit.WebHistoryItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-10-19.
 */

public class Dao {
    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;
        //SQLite 초기화
        database = context.openOrCreateDatabase("MyDATA.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try{
            String sql = "CREATE TABLE IF NOT EXISTS Articles  (ID integer primary key autoincrement,"
                    + "                                         Time text not null,"
                    + "                                         Menu text not null);";
            database.execSQL(sql);
        } catch (Exception e){
            Log.e("test", "CREATE TABLE FAILED! -" + e );
            e.printStackTrace();
        }
    }

    public void insertJsonData(String jsonData){

        // JSON으로 데이터를 파싱할 때 사용할 임시 변수
        String time;
        String menu;

        try{
            JSONArray jArr = new JSONArray(jsonData);

            for (int i = 0; i < jArr.length(); ++i) {
                JSONObject jObj = jArr.getJSONObject(i);

                time = jObj.getString("Time");
                menu = jObj.getString("Menu");

                Log.i("test", "Time: " + time + "menu:" + menu);

                String sql = "INSERT INTO Articles(Time, Menu)"
                        + " VALUES(" + time + ",'" + menu + "');";
                try{
                    database.execSQL(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (JSONException e){
            Log.e("test", "JSON ERROR! -" + e);
            e.printStackTrace();
        }
    }

    public ArrayList<Article> getArticleList() {

        ArrayList<Article> articleList = new ArrayList<Article>();

        String time;
        String menu;

        String sql = "SELECT * FROM Articles;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()){
            time = cursor.getString(1);
            menu = cursor.getString(2);
            articleList.add(new Article(time, menu));
        }
        cursor.close();
        return articleList;
    }

    public Article getArticleByTime(String time){

        Article article = null;
        String menu;

        String sql = "SELECT * FROM Articles WHERE Time = " + time + ";";
        Cursor cursor = database.rawQuery(sql, null);

        cursor.moveToNext();

        time = cursor.getString(1);
        menu = cursor.getString(2);

        cursor.close();
        return article;
    }

    //TestData
    public String getJsonTestData() {
    // '{', '}' 를 length함수로 읽는다.
        StringBuilder sb = new StringBuilder();
        sb.append("");

        sb.append("[");

        sb.append("      {");
        sb.append("         'Time':'조식',");
        sb.append("         'Menu':'흰쌀밥, 된장국, 삼치순살조림, 김치',");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'Time':'중식',");
        sb.append("         'Menu':'톳비빔밥, 호박감자국, 콩나물무침, 김치',");
        sb.append("      },");
        sb.append("      {");
        sb.append("         'Time':'석식',");
        sb.append("         'Menu':'현미밥, 계란국, 고등어순살조림, 김치',");
        sb.append("      }");

        sb.append("]");

        return sb.toString();
    }

}
