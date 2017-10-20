package com.example.administrator.myproject6;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView mainListView;
    private ArrayList<Article> articleList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner
        String[] str = getResources().getStringArray(R.array.location);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, str);
        Spinner spi = (Spinner) findViewById(R.id.spinner1);
        spi.setAdapter(adapter);
        spi.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        print(view, position);
                    }

                    private void print (View v, int position){
                        Spinner sp = (Spinner) findViewById(R.id.spinner1);
                        TextView tx = (TextView) findViewById(R.id.textView1);
                        String res = "";
                        if (sp.getSelectedItemPosition() > 0) {
                            res = (String) sp.getAdapter().getItem(sp.getSelectedItemPosition());
                        }
                        if (res != "") {
                            tx.setText(res);
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
        View.OnClickListener listener = new View.OnClickListener() {

            //메인 우상버튼 -> 식약청 홈페이지 이동
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.foodpoison:
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://mfds.go.kr")));
                        break;
                }
            }
        };
        TextView foodpoison = (TextView) findViewById(R.id.foodpoison);
        foodpoison.setOnClickListener(listener);

    }

    protected void listView() {
        Dao dao = new Dao(getApplicationContext());
        String testJsonData = dao.getJsonTestData();
        dao.insertJsonData(testJsonData);

        articleList = dao.getArticleList();

        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.eachmeal , articleList);
    }
}
