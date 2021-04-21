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

public class Chitiet extends AppCompatActivity {
    private TextView tvnameSP, tvPrice, tvNameCus, tvAddress, tvPhone;
    private Button btnThoat, btnGiaohang;
    private EditText edtTenKH, edtSDT, edtDC, edtDate;

    // date
    DateFormat fmtDateAndTime = DateFormat.getDateTimeInstance();
    TextView lblDateAndTime;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
    private void updateLabel() {
        lblDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }
    //date
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show);
        lblDateAndTime = (TextView) findViewById(R.id.txtDate);
        Button btnDate = (Button) findViewById(R.id.btnDate);
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Chitiet.this, d,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        updateLabel();
        getSupportActionBar().setTitle("Chi tiết sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent it = getIntent();
        Bundle bl= it.getBundleExtra("MyPackage");
        Anhxa();
        if(bl != null) {
            tvnameSP.setText(bl.getString("Name"));
            tvNameCus.setText(bl.getString("NameCus"));
            tvAddress.setText(bl.getString("Address"));
            int a = bl.getInt("Price");
            int b = bl.getInt("Phone");
            tvPrice.setText(String.valueOf(a));
            tvPhone.setText(String.valueOf(b));
        }

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnGiaohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Chitiet.this, HoaDon.class);
                Bundle bundle = new Bundle();
                //String a = tvNameCus.getText().toString();
                String b = tvnameSP.getText().toString();
                //String c = tvPrice.getText().toString();
                int c = Integer.parseInt(tvPrice.getText().toString());
                //String d = tvPhone.getText().toString();
                int d = Integer.parseInt(tvPhone.getText().toString());
                //edt
                String a = edtTenKH.getText().toString();
                String e = edtSDT.getText().toString();
                String f = edtDC.getText().toString();
                String g = edtDate.getText().toString();
                //String e = tvAddress.getText().toString();
                //bundle.putString("namekh",a);
                bundle.putString("namesp",b);
                //bundle.putString("gia",c);
                bundle.putInt("gia",c);
                //bundle.putString("khuyenmai",d);
                bundle.putInt("khuyenmai",d);
                bundle.putString("tenkh",a);
                bundle.putString("sdt",e);
                bundle.putString("diachi",f);
                bundle.putString("Date",g);

               // bundle.putString("DC",e);

                it.putExtra("data",bundle);

                startActivity(it);
            }
        });

    }

    public void Anhxa(){
        tvnameSP = (TextView) findViewById(R.id.txtnameSP);
        tvPrice = (TextView) findViewById(R.id.txtPrice);
        tvNameCus = (TextView) findViewById(R.id.txtNameCus);
        tvAddress = (TextView) findViewById(R.id.txtnTPDD);
        tvPhone = (TextView) findViewById(R.id.txtPhone);
        btnThoat = (Button) findViewById(R.id.btnThoat);
        btnGiaohang = (Button) findViewById(R.id.btnGiaohang);
        edtTenKH = (EditText) findViewById(R.id.edtTenKH);
        edtSDT = (EditText) findViewById(R.id.edtSDT);
        edtDC = (EditText) findViewById(R.id.edtDC);
        edtDate = (EditText) findViewById(R.id.txtDate);

    }

}