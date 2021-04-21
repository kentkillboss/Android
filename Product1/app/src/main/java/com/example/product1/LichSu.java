package com.example.product1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LichSu extends AppCompatActivity {
    private  HistoryAdapter historyAdapter;
    private List<History> historyList = new ArrayList<>();
    private ListView lv;
    private SqliteDatabase db;
    private History thang;
    private String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        lv = (ListView) findViewById(R.id.lvLS);
        db = new SqliteDatabase(this);
        historyList = new ArrayList<History>();
        historyList = db.getall();
        historyAdapter = new HistoryAdapter(getApplicationContext(), historyList);
        lv.setAdapter(historyAdapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int i, long id) {
                try {
                    thang = historyList.get(i);
                    System.out.println("db.deleteHistory1(thang) = " + db.deleteHistory1(thang));
                    historyList.remove(thang);
                    historyAdapter.notifyDataSetChanged();
                }catch (Exception e){
                    System.out.println(e);
                }
                return false;
            }
        });


    }


}