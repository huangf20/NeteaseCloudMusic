package com.example.mymusic.main.mvp.view.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.mymusic.R;
import com.example.mymusic.base.BaseFragment;
import com.example.mymusic.main.adapter.StuAdapter;
import com.example.mymusic.main.bean.Student;
import com.example.mymusic.main.mvp.contract.DailyContract;
import com.example.mymusic.main.mvp.presenter.DailyPresent;
import com.example.mymusic.main.mvp.view.StudentActivity;
import com.example.mymusic.main.util.ConstantsDaily;
import com.example.mymusic.main.util.StuDBOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by huangf20
 * on 2021/4/26
 */
public class DailyFragment extends BaseFragment<DailyPresent> implements DailyContract.View {
    View mView;
    private Button addBtn,searchBtn;
    private ListView lv;
    StuDBOpenHelper dbHandler;
    StuAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        final List<Student> stus=dbHandler.getAllStudent();
        adapter=new StuAdapter(getContext(),stus);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getContext(),
                        StudentActivity.class);
                Student s=stus.get(i);
                Bundle bundle=new Bundle();
                bundle.putSerializable("stu",s);
                // 有两种方式进入了学生详细页面，所以标识是从增加按钮，还是单击列表项
                intent.putExtra("request","Look");
                intent.putExtras(bundle);
                startActivityForResult(intent, ConstantsDaily.LOOK_REQUEST);
            }
        });
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_daily,container,false);
        mView=view;
        init();
        return view;
    }

    public DailyFragment() {
        setFragmentTitle("听歌日记");
    }


    private void init(){
        addBtn=(Button)mView.findViewById(R.id.btn_add);
        searchBtn=(Button)mView.findViewById(R.id.btn_search);
        lv=(ListView)mView.findViewById(R.id.stduent_list);
        dbHandler=new StuDBOpenHelper(getContext());
        addBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
    }
    protected void initData() {

    }

    @Override
    public DailyPresent onCreatePresenter() {
        return new DailyPresent(this);
    }

    @Override
    protected void initVariables(Bundle bundle) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                Intent intent=new Intent(getContext(),
                        StudentActivity.class);
                // 有两种方式进入了学生详细页面，所以标识是从增加按钮，还是单击列表项
                intent.putExtra("request","Add");
                startActivityForResult(intent, ConstantsDaily.ADD_REQUEST);
                break;
            case R.id.btn_search:
                final AlertDialog dialog=new AlertDialog.Builder(getContext()).create();
                LinearLayout line = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_search,null);
                dialog.setView(line);
                Button btnsearch=(Button)line.findViewById(R.id.btn_search_dialog);
                final EditText searchname=(EditText)line.findViewById(R.id.search_name);
                final ListView list=(ListView)line.findViewById(R.id.search_result) ;
                btnsearch.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name=searchname.getText().toString().trim();
                        final Student s=dbHandler.findByName(name);
                        List<Student>results=new ArrayList<>();
                        if(s!=null){
                            results.add(s);
                            StuAdapter adapter=new StuAdapter(getContext(),results);
                            list.setAdapter(adapter);
                            list.setVisibility(View.VISIBLE);
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent=new Intent(getContext(),
                                            StudentActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putSerializable("stu",s);
                                    // 有两种方式进入了学生详细页面，所以标识是从增加按钮，还是单击列表项
                                    intent.putExtra("request","Look");
                                    intent.putExtras(bundle);
                                    startActivityForResult(intent, ConstantsDaily.LOOK_REQUEST);
                                }
                            });
                        }else{
                            dialog.dismiss();
                        }


                    }
                });
                dialog.show();
                break;
        }
    }
    public void updata(){
        adapter.notifyDataSetChanged();
    }

}
