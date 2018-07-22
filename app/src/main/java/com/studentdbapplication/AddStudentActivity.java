package com.studentdbapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddStudentActivity extends AppCompatActivity{

    DatabaseHandler dbHandler;
    EditText addName, addSurname, addAddress, addContact, addDOB, m1, m2, m3;
    Button addDB;
    RadioGroup genderGroup;
    RadioButton genButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        dbHandler = new DatabaseHandler(this);

        addName = findViewById(R.id.addName);
        addSurname = findViewById(R.id.addSurname);
        addAddress = findViewById(R.id.addAddress);
        addContact = findViewById(R.id.addContact);
        addDOB = findViewById(R.id.addDOB);
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        m3 = findViewById(R.id.m3);
        genderGroup = findViewById(R.id.genderRadio);
        addDB = findViewById(R.id.addDB);
        addDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewStudent();
            }
        });
    }

    private void addNewStudent() {
        try {
            String name =   addName.getText().toString().trim();
            String surname = addSurname.getText().toString().trim();
            String address = addAddress.getText().toString().trim();
            String contact = addContact.getText().toString().trim();
            String dob = addDOB.getText().toString().trim();
            String pic = "R.drawable.ic_account_box_black_24dp";

            int gen = genderGroup.getCheckedRadioButtonId();
            genButton  = findViewById(gen);
            String gender = genButton.getText().toString();

            String value1 = m1.getText().toString();
            int sub1 = Integer.parseInt(value1);
            String value2 = m2.getText().toString();
            int sub2 = Integer.parseInt(value2);
            String value3 = m3.getText().toString();
            int sub3 = Integer.parseInt(value3);

            int total = (sub1+sub2+sub3);
            String strTotal = total+"";

            double percentage = (total/3);
            String percent = percentage+"%";

            String remark = null;
            if(percentage>=75)
                remark = "Distinction";
            else if (percentage>=60)
                remark = "First Class";
            else if (percentage>=50)
                remark = "Second Class";
            else if (percentage>=40)
                remark = "Pass";
            else
                remark = "Fail";



            dbHandler = new DatabaseHandler(this);

            StudentData sd = new StudentData(name,surname,address,contact,dob,gender,value1,value2,value3,strTotal,percent,remark,pic);
            boolean isInserted = dbHandler.insertData(sd);

//        boolean isInserted = dbHandler.insertData(addName.getText().toString(), addSurname.getText().toString(), addAddress.getText().toString(), addContact.getText().toString(),
//                addDOB.getText().toString(),genButton.getText().toString(), m1.getText().toString(), m2.getText().toString(), m3.getText().toString(),strTotal, percent, remark, pic);

            if (isInserted == true)
                Toast.makeText(this,"Data Inserted",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"Data Not Inserted",Toast.LENGTH_SHORT).show();

            Intent addToList = new Intent(this,StudentDBActivity.class);
            startActivity(addToList);
        }
        catch (Exception e){
            if (addName == null)
                Toast.makeText(this,"Insert Student Name",Toast.LENGTH_SHORT).show();
            if (addSurname == null)
                Toast.makeText(this,"Insert Student Surname",Toast.LENGTH_SHORT).show();
            if (addAddress == null)
                Toast.makeText(this,"Insert Student Address",Toast.LENGTH_SHORT).show();
            if (addContact == null)
                Toast.makeText(this,"Insert Student Contact",Toast.LENGTH_SHORT).show();
            if (addDOB == null)
                Toast.makeText(this,"Insert Student Date of Birth",Toast.LENGTH_SHORT).show();
            if (m1 == null)
                Toast.makeText(this,"Insert Student Name",Toast.LENGTH_SHORT).show();
            if (m2 == null)
                Toast.makeText(this,"Insert Student Name",Toast.LENGTH_SHORT).show();
            if (m3 == null)
                Toast.makeText(this,"Insert Student Name",Toast.LENGTH_SHORT).show();
        }

    }
}
