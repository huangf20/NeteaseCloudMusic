package com.example.mymusic.main.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mymusic.R;
import com.example.mymusic.main.bean.Student;

import java.util.List;

/**
 * Created by Administrator on 2019/11/22.
 */

public class StuAdapter extends BaseAdapter {
    List<Student> studentList;
    Context context;

    public StuAdapter(Context context, List<Student> students){
        this.context=context;
        this.studentList=students;
    }
    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int i) {
        return studentList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
          if(view==null){
              view=  LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
          }
        ImageView imageView=(ImageView)view.findViewById(R.id.image);
        TextView  tvName=(TextView)view.findViewById(R.id.name);
        TextView tvGrade=(TextView)view.findViewById(R.id.grade);
         //偶数为女生
        if(studentList.get(i).getId()%2==0){
            imageView.setImageResource(R.mipmap.girl);
        }else{
            imageView.setImageResource(R.mipmap.boy1);
        }
        tvName.setText("姓名  "+studentList.get(i).getName());
        tvGrade.setText("分数  "+studentList.get(i).getGrade());
        return view;
    }
}
