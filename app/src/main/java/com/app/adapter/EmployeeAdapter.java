package com.app.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.model.EmployeeData;
import com.app.projectone.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder>{
    private List<EmployeeData> employeeDataList;
    Activity activity;

    // RecyclerView recyclerView;
    public EmployeeAdapter(Activity activity, List<EmployeeData> employeeDataList) {
        this.employeeDataList = employeeDataList;
        this.activity = activity;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_employee_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EmployeeData myListData = employeeDataList.get(position);

        holder.tv_emp_name.setText(myListData.getEmp_name());
        holder.tv_femp_age.setText("Age: "+myListData.getEmp_age());
        holder.tv_femp_salary.setText("Salary: "+myListData.getEmp_salary());

        //holder.img_emp_image.setImageResource(employeeDataList[position].getImgId());

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .priority(Priority.HIGH);

        Glide.with(activity)
                .load(myListData.getEmp_image())
                .apply(options)
                .into(holder.img_emp_image);

    }


    @Override
    public int getItemCount() {
        return employeeDataList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView img_emp_image;
        public TextView tv_emp_name, tv_femp_age, tv_femp_salary;

        public ViewHolder(View itemView) {
            super(itemView);
            this.img_emp_image = (ImageView) itemView.findViewById(R.id.img_emp_image);
            this.tv_emp_name = (TextView) itemView.findViewById(R.id.tv_emp_name);
            this.tv_femp_age = (TextView) itemView.findViewById(R.id.tv_femp_age);
            this.tv_femp_salary = (TextView) itemView.findViewById(R.id.tv_femp_salary);
        }
    }
}