package com.studentdbapplication;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class StudentDBActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    DatabaseHandler dbHandler;
    StudentCardAdapter adapter;
    private String filter = "";
    FloatingActionButton addNewStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_db);

        addNewStudent = findViewById(R.id.add_floating);
        addNewStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoAdd = new Intent(StudentDBActivity.this, AddStudentActivity.class);
                startActivity(gotoAdd);
            }
        });

        recyclerView = findViewById(R.id.Student_recycler);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        
        listRecyclerView(filter);
    }

    private void listRecyclerView(String filter) {
        dbHandler = new DatabaseHandler(this);

        adapter = new StudentCardAdapter(dbHandler.studentList(filter),this,recyclerView);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
