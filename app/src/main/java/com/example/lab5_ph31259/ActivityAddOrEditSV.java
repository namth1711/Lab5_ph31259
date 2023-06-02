package com.example.lab5_ph31259;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityAddOrEditSV extends AppCompatActivity {
    Spinner sp ;
    TextView edtName, edtAddress;
    Button btSubmit;

    public static final String KEY_COSO = "coso";
    public static final String KEY_TEN_SV = "ten";
    public static final String KEY_DIA_CHI = "diachi";

    ArrayList<School> lst = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = findViewById(R.id.sp);
        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        btSubmit = findViewById(R.id.btSubmit);

        lst.add(new School(R.drawable.hn,"FPoly Hà Nội"));
        lst.add(new School(R.drawable.dn,"FPoly Đà Nẵng"));
        lst.add(new School(R.drawable.tn,"FPoly Tây Nguyên"));
        lst.add(new School(R.drawable.logo,"FPoly Hồ Chí Minh"));
        lst.add(new School(R.drawable.ct,"FPoly Cần Thơ"));

        SinhVienModel svModel = (SinhVienModel) getIntent().getSerializableExtra(ActivityBai2.KEY_SV_MODEL);

        SchoolAdapter adapter = new SchoolAdapter(lst, ActivityAddOrEditSV.this);
        sp.setAdapter(adapter);

        if (svModel != null) { // sua sinh vien
            edtName.setText(svModel.hoTen);
            edtAddress.setText(svModel.diaChi);

            int position = lst.indexOf(svModel.coSo);
            sp.setSelection(position);
        }

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index= sp.getSelectedItemPosition();
                String cs=lst.get(index).ten;

                String name = edtName.getText().toString();
                String adr = edtAddress.getText().toString();

                if (name.trim().equals("")) {
                    Toast.makeText(ActivityAddOrEditSV.this, "Tên SV không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (adr.trim().equals("")) {
                    Toast.makeText(ActivityAddOrEditSV.this, "Địa chỉ không được để trống!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent();
                    Bundle b = new Bundle();

                    b.putString(KEY_COSO,cs);
                    //Log.d("coso", cs);

                    b.putString(KEY_TEN_SV,name);
                    b.putString(KEY_DIA_CHI,adr);
                    i.putExtras(b);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        });
    }
}