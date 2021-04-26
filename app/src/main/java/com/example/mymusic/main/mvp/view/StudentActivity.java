package com.example.mymusic.main.mvp.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mymusic.R;
import com.example.mymusic.main.bean.Student;
import com.example.mymusic.main.util.ConstantsDaily;
import com.example.mymusic.main.util.StuDBOpenHelper;


public class StudentActivity extends Activity implements View.OnClickListener {
    private EditText etName,etGrade;
    private ImageView imageView;
    private Button btnChange,btnDelete,btnAdd;
    private int id;
    private StuDBOpenHelper handler;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student);
        initView();
        intent=getIntent();
        String request=intent.getStringExtra("request");
        switch (request){
            case "Add":
                btnChange.setVisibility(View.GONE);
                btnDelete.setVisibility(View.GONE);
                btnAdd.setVisibility(View.VISIBLE);
                break;
            case "Look":
                Bundle bundle=intent.getExtras();
                Student s=(Student)bundle.getSerializable("stu");
                id=s.getId();
                etName.setText(s.getName());
                etGrade.setText(s.getGrade());
                if(s.getId()%2==0){
                    imageView.setImageResource(R.mipmap.girl);
                }else{
                    imageView.setImageResource(R.mipmap.boy1);
                }
                break;
        }
        btnAdd.setOnClickListener(this);
        btnChange.setOnClickListener(this);
        btnDelete.setOnClickListener(this);


    }
    private void initView(){
        etName= (EditText) findViewById(R.id.student_name);
        etGrade= (EditText) findViewById(R.id.student_grade);
        btnChange= (Button) findViewById(R.id.btn_change);
        btnDelete= (Button) findViewById(R.id.btn_delete);
        btnAdd= (Button) findViewById(R.id.btn_add_student);
        imageView= (ImageView) findViewById(R.id.student_image);
        handler=new StuDBOpenHelper(this);
    }

    @Override
    public void onClick(View view) {
        Student s=new Student();
        switch (view.getId()){
            case R.id.btn_add_student:
                s.setName(etName.getText().toString().trim());
                s.setGrade(etGrade.getText().toString().trim());
                handler.addStu(s);
                setResult(RESULT_OK,intent);
                finish();
                break;
            case R.id.btn_change:
                s.setName(etName.getText().toString().trim());
                s.setGrade(etGrade.getText().toString().trim());
                //修改需要使用Id,所以需要传递Id
                s.setId(id);
                handler.updateStu(s);
                setResult(ConstantsDaily.MODIFY_BACK,intent);
                finish();
                break;
            case R.id.btn_delete:
                handler.deleteStu(id);
                setResult(ConstantsDaily.DELETE_BACK,intent);
                finish();
                break;
        }
    }
}
