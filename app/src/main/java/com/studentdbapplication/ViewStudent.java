package com.studentdbapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewStudent extends AppCompatActivity {

    DatabaseHandler dbHandler;
    TextView viewName, viewDetails, viewStudentAddress;
    ImageButton viewCall, viewSms;
    Button viewAddressMap;
    int no;
    int receivedViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_student);

        String[] perms = {"android.permission.CALL_PHONE","android.permission.SEND_SMS"};
        int permsRequestCode = 200;
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED &&
                    checkSelfPermission(android.Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_GRANTED) {
            }
            else
            {
                requestPermissions(perms, permsRequestCode);
            }
        }

        viewName = findViewById(R.id.nameView);
        viewDetails = findViewById(R.id.detailsView);
        viewStudentAddress = findViewById(R.id.studentAddress);
        viewCall = findViewById(R.id.callView);
        viewSms = findViewById(R.id.smsView);
        viewAddressMap = findViewById(R.id.addressMapButton);

        dbHandler = new DatabaseHandler(this);
        receivedViewId = getIntent().getIntExtra("ID",1);

        final StudentData selectedForView = dbHandler.getStudent(receivedViewId);
        viewName.setText(selectedForView.getName()+" "+selectedForView.getSurname());
        viewDetails.setText("ID : "+receivedViewId+"\n\nCONTACT NO : "+selectedForView.getContact_no()+"\n\nDATE OF BIRTH : "+selectedForView.getDob()+"\n\nGENDER : "+selectedForView.getGender()+"\n\nMARKS 1 : "+selectedForView.getM1()+"\n\nMARKS 2 : "+selectedForView.getM2()+"\n\nMARKS 3 : "+selectedForView.getM3()+"\n\nTOTAL : "+selectedForView.getStrTotal()+"\n\nPERCENTAGE : "+selectedForView.getPercent()+"\n\nREMARK : "+selectedForView.getRemark());
        viewStudentAddress.setText(selectedForView.getAddress());

        viewCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialContactPhone(selectedForView.getContact_no());
            }
        });

        viewSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSms(selectedForView.getContact_no());
            }
        });
        viewAddressMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(),"Show Address on Google MAP...",Toast.LENGTH_LONG).show();
//                Uri gmmIntentUri = Uri.parse("google.navigation:q=Kothrud,+Pune");
//                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//                mapIntent.setPackage("com.google.android.apps.maps");
//                startActivity(mapIntent);

                Uri mapUri = Uri.parse("geo:0,0?q=" + Uri.encode(selectedForView.getAddress()));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, mapUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    private void sendSms(String no) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", no, null)));
    }

    private void dialContactPhone(String contactNo) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel",contactNo,null)));
    }
}
