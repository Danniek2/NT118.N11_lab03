package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.view.View;


import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvUser;
    private SinhvienAdapter sinhvienAdapter;
    private List<Sinhvien> listSinhvien;
    private DbAdapter adapter;
    RadioButton radioButton,radioButton2;
    RadioGroup radioGroup;
    private Button button,button2,button3,button4;
    private EditText edt_name;
    private EditText edt_phone;
    private EditText edt_mssv;
    private TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        lvUser = (ListView) findViewById(R.id.lv);
        button=(Button) findViewById(R.id.btnThem);
        button2=(Button) findViewById(R.id.btnSua);
        button3=(Button) findViewById(R.id.btnXoaall);
        button4=(Button) findViewById(R.id.btnClear);
        edt_phone=(EditText) findViewById(R.id.edtSdt);
        edt_mssv=(EditText) findViewById(R.id.edtMssv);
        edt_name=(EditText) findViewById(R.id.edtTen);
        //textView1=(TextView) findViewById(R.id.tv_mssv);
        textView2=(TextView) findViewById(R.id.testtt);
        radioButton=(RadioButton) findViewById(R.id.rdNam);
        radioButton2=(RadioButton) findViewById(R.id.rdNu);
        radioGroup=(RadioGroup) findViewById(R.id.radio);
        adapter=new DbAdapter(MainActivity.this);
        adapter.open();
        listSinhvien=new ArrayList<Sinhvien>();
        listSinhvien=adapter.all();
//
        sinhvienAdapter = new SinhvienAdapter(getApplicationContext(),listSinhvien);
        lvUser.setAdapter(sinhvienAdapter);

//
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //adapter.deleteAllUsers();
                if(edt_name.getText()+"" == "" ||edt_mssv.getText()+"" == "" || edt_phone.getText()+"" == "" ) {
                    Toast.makeText(MainActivity.this,"Nhap thong tin day du",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //textView2.setText("Nhap thong tin day du");
                    adapter.createUser(edt_name.getText() + "", edt_phone.getText() + "", edt_mssv.getText() + "", check());
                    getdata();
                    edt_name.setText("");
                    edt_mssv.setText("");
                    edt_phone.setText("");
                    radioButton.setChecked(true);
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.updateStudent(edt_name.getText()+"",edt_phone.getText()+"",edt_mssv.getText()+"",check());
                getdata();
                edt_name.setText("");
                edt_mssv.setText("");
                edt_phone.setText("");
                radioButton.setChecked(true);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.deleteAllUsers();
                getdata();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_name.setText("");
                edt_mssv.setText("");
                edt_phone.setText("");
                radioButton.setChecked(true);
            }
        });
        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this,String.valueOf(i),Toast.LENGTH_SHORT).show();
                edt_name.setText(listSinhvien.get(i).getName());
                edt_mssv.setText(listSinhvien.get(i).getID());
                edt_phone.setText(listSinhvien.get(i).getPhone());
                if(listSinhvien.get(i).getGioitinh()==0)
                {
                    radioButton.setChecked(true);
                    radioButton2.setChecked(false);
                }
                else
                {
                    radioButton.setChecked(false);
                    radioButton2.setChecked(true);
                }
            }
        });
        lvUser.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                adapter.deleteUser(listSinhvien.get(arg2).getID());
                getdata();
                return false;
            }
        });
    }

    void getdata()
    {
        listSinhvien=new ArrayList<Sinhvien>();
        listSinhvien=adapter.all();
//
        sinhvienAdapter = new SinhvienAdapter(getApplicationContext(),listSinhvien);
        lvUser.setAdapter(sinhvienAdapter);
    }
    int check()
    {
        int checkradio=radioGroup.getCheckedRadioButtonId();
        int check=0;
        if(checkradio==R.id.rdNam)
        {
            check=0;
        }
        else
        {
            check=1;
        }
        return check;
    }
    void update()
    {

    }
}