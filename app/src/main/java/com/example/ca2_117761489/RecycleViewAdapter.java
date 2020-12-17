package com.example.ca2_117761489;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {


    //code adapted from https://www.youtube.com/watch?v=FFCpjZkqfb0

    List<StaffModel> staffList;
    Context context;

    public RecycleViewAdapter(List<StaffModel> staffList, Context context) {
        this.staffList = staffList;
        this.context = context;
    }

    //Declare parent layout to use click listener
    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvIDDetail;
        TextView tvNameDetail;
        TextView tvAgeDetail;
        TextView tvActiveDetail;
        ConstraintLayout parentLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIDDetail = itemView.findViewById(R.id.tvIDDetail);
            tvNameDetail = itemView.findViewById(R.id.tvNameDetail);
            tvAgeDetail = itemView.findViewById(R.id.tvAgeDetail);
            tvActiveDetail = itemView.findViewById(R.id.tvActiveDetail);
            parentLayout = itemView.findViewById(R.id.onerow);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    //Setting values based on list position
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvIDDetail.setText(String.valueOf(staffList.get(position).getId()));
        holder.tvNameDetail.setText(staffList.get(position).getName());
        holder.tvAgeDetail.setText(String.valueOf(staffList.get(position).getAge()));
        holder.tvActiveDetail.setText(staffList.get(position).isCurrent() == true ? "Yes":"No");
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sends user to EditActivity
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", staffList.get(position).getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return staffList.size();
    }
}
