package com.example.product1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class HoaDon extends AppCompatActivity {
    private TextView tvTenKH, tvTenSP, tvGia, tvKhuyenMai, tvDC, tvTongTien, tvSDT,tvDate;
    private Button btnLuu;
    private SqliteDatabase database1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);


        getSupportActionBar().setTitle("Hóa đơn");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Anhxa();
        database1 = new SqliteDatabase(this);

        Intent it = getIntent();
        Bundle bundle = it.getBundleExtra("data");
        String b = bundle.getString("namesp");
        //String c = bundle.getString("gia");
        int c = bundle.getInt("gia");
        //final String d = bundle.getString("khuyenmai");
        int d = bundle.getInt("khuyenmai");
        String a = bundle.getString("tenkh");
        String e = bundle.getString("sdt");
        String f = bundle.getString("diachi");
        String g = bundle.getString("Date");
        //String e = bundle.getString("DC");
        //String namekh = it.getStringExtra("namKH");
        //int tong =
        //double tong = (90.0/100.0);
        int tong = (c-d);

        System.out.println(c);
        System.out.println(d);
        System.out.println(tong);


        tvTenSP.setText(b);
       // tvTenKH.setText(a);
        tvGia.setText(String.valueOf(c));
        tvKhuyenMai.setText(String.valueOf(d));
        //tvDC.setText(e);
        tvTenKH.setText(a);
        tvSDT.setText(e);
        tvDC.setText(f);
        //tvTongTien.setText(c);
        tvTongTien.setText(String.valueOf(tong));
        tvDate.setText(g);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenkhach = tvTenKH.getText().toString();
                String tensanpham = tvTenSP.getText().toString();
                String date = tvDate.getText().toString();
                int tongtien = Integer.parseInt(tvTongTien.getText().toString());
                //int giamgia = Integer.parseInt(tvGia.getText().toString());
                //int tong = tongtien * (giamgia/100);
                History tt = new History(tenkhach, tensanpham, date, tongtien);
                database1.addHistory(tt);
                Intent it = new Intent(HoaDon.this, LichSu.class);
                startActivity(it);
            }
        });


    }

    public void Anhxa(){
        tvTenKH = (TextView) findViewById(R.id.txtTenKH);
        tvTenSP = (TextView) findViewById(R.id.txtTenSP);
        tvGia = (TextView) findViewById(R.id.txtGia);
        tvKhuyenMai = (TextView) findViewById(R.id.tvKhuyenMai);
        tvDC = (TextView) findViewById(R.id.txtDC);
        tvTongTien = (TextView) findViewById(R.id.txtTongTien);
        tvSDT = (TextView) findViewById(R.id.txtPhone);
        tvDate = (TextView) findViewById(R.id.txtDate);
        btnLuu = (Button) findViewById(R.id.btnLuu);
    }
}