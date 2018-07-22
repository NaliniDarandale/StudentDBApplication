package com.studentdbapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class StudentCardAdapter extends RecyclerView.Adapter<StudentCardAdapter.ViewHolder>{
    private List<StudentData> mStudentList;
    private Context mContext;
    private RecyclerView mRecyclerView;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView sstudent_photo;
        public TextView sname;
        public TextView ssurname;
        public TextView saddress;
        public TextView smarks;
        public Button sedit;
        public Button sdelete;

        public View layout;

        public ViewHolder(View v){
            super(v);
            layout = v;
            sstudent_photo = v.findViewById(R.id.student_photo);
            sname = v.findViewById(R.id.name);
            ssurname = v.findViewById(R.id.surname);
            saddress = v.findViewById(R.id.address);
            smarks = v.findViewById(R.id.marks);
            sedit = v.findViewById(R.id.edit);
            sdelete = v.findViewById(R.id.delete);
        }
    }

    public void add(int position, StudentData studentData) {
        mStudentList.add(position, studentData);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mStudentList.remove(position);
        notifyItemRemoved(position);
    }

    public StudentCardAdapter(List<StudentData> myDataset, Context context, RecyclerView recyclerView) {
        mStudentList = myDataset;
        mContext = context;
        mRecyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View convertView = inflater.inflate(R.layout.student_card, parent, false);
        ViewHolder VH = new ViewHolder(convertView);
        return VH;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
         final StudentData studentData = mStudentList.get(position);
        holder.sstudent_photo.setImageResource(R.drawable.ic_account_box_black_24dp);
        holder.sname.setText(studentData.getName());
        holder.ssurname.setText(studentData.getSurname());
        holder.saddress.setText(studentData.getAddress());
        holder.smarks.setText(studentData.getPercent());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToView(studentData.getId());
            }
        });
        holder.sedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToUpdate(studentData.getId()); //,StudentData.getId(position)
                //Toast.makeText(mContext, "Position = "+position, Toast.LENGTH_SHORT).show();
            }
        });
        holder.sdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("Do You Really Want To Delete this Student's Profile..?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int temint=studentData.getId();
                                Log.i("value",String.valueOf(temint));
                                deleteStudent(studentData.getId());
                                mStudentList.remove(position);
                                //mStudentList.remove(StudentData.getId(position));
                                mRecyclerView.removeViewAt(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mStudentList.size());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                builder.setTitle("Confirm Delete");
                builder.show();

            }
        });
    }

    private void goToView(int id) {
        Intent toView = new Intent(mContext, ViewStudent.class);
        toView.putExtra("ID",id);
        mContext.startActivity(toView);
    }

    private void deleteStudent(int studId) {
        DatabaseHandler dbHandler = new DatabaseHandler(mContext);
        dbHandler.deleteData(studId, mContext);

        notifyDataSetChanged();
    }

    private void goToUpdate(int id) {
        //final StudentData studentData = mStudentList.get(position);
        Intent toEdit = new Intent(mContext,UpdateStudentActivity.class);
        toEdit.putExtra("ID", id);  //id
        mContext.startActivity(toEdit);
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return mStudentList.size();
    }

}
