package com.studentdbapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateStudentActivity extends AppCompatActivity {

    DatabaseHandler dbHandler;
    EditText updateName, updateSurname, updateAddress, updateContact, updateDOB, updatem1, updatem2, updatem3;
    Button updateDB;
    RadioGroup updategenderGroup;
    RadioButton updategenButton;
    int receivedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        dbHandler = new DatabaseHandler(this);

        receivedId = getIntent().getIntExtra("ID",1);

//        try{
//            receivedId = getIntent().getIntExtra("ID",1);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        updateName = findViewById(R.id.updateName);
        updateSurname = findViewById(R.id.updateSurname);
        updateAddress = findViewById(R.id.updateAddress);
        updateContact = findViewById(R.id.updateContact);
        updateDOB = findViewById(R.id.updateDOB);
        updatem1 = findViewById(R.id.updatem1);
        updatem2 = findViewById(R.id.updatem2);
        updatem3 = findViewById(R.id.updatem3);
        updategenderGroup = findViewById(R.id.updategenderRadio);
        updateDB = findViewById(R.id.updateDB);
        updateDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudent();
            }
        });

        StudentData selectedStudent =   dbHandler.getStudent(receivedId);
        updateName.setText(selectedStudent.getName());
        updateSurname.setText(selectedStudent.getSurname());
        updateAddress.setText(selectedStudent.getAddress());
        updateContact.setText(selectedStudent.getContact_no());
        updateDOB.setText(selectedStudent.getDob());
        int getgen = updategenderGroup.getCheckedRadioButtonId();
        updategenButton = findViewById(getgen);
        updatem1.setText(selectedStudent.getM1());
        updatem2.setText(selectedStudent.getM2());
        updatem3.setText(selectedStudent.getM3());
    }

    private void updateStudent() {
        try{
            String name =   updateName.getText().toString().trim();
            String surname = updateSurname.getText().toString().trim();
            String address = updateAddress.getText().toString().trim();
            String contact = updateContact.getText().toString().trim();
            String dob = updateDOB.getText().toString().trim();
            String pic = "R.drawable.ic_account_box_black_24dp";

            int gen = updategenderGroup.getCheckedRadioButtonId();
            updategenButton  = findViewById(gen);
            String gender = updategenButton.getText().toString();

            String value1 = updatem1.getText().toString();
            int sub1 = Integer.parseInt(value1);
            String value2 = updatem2.getText().toString();
            int sub2 = Integer.parseInt(value2);
            String value3 = updatem3.getText().toString();
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

            StudentData updatedStudent = new StudentData(name,surname,address,contact,dob,gender,value1,value2,value3,strTotal,percent,remark,pic);
            dbHandler.updateData(receivedId, this, updatedStudent);
            startActivity(new Intent(this,StudentDBActivity.class));
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Select Gender",Toast.LENGTH_LONG).show();
        }

    }
}
