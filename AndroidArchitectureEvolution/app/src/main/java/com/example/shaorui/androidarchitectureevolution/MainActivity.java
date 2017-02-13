package com.example.shaorui.androidarchitectureevolution;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shaorui.androidarchitectureevolution.bean.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.request.OkHttpClientManager;

public class MainActivity extends AppCompatActivity {
    private UserInfoBean mUserInfoBean;

    private View mContentContainer;
    private TextView mNameTv;
    private TextView mAgeTv;
    private TextView mDescTv;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        loadData();

    }

    private void loadData() {
        showProgressOrContent(true);
        OkHttpClientManager.getInstance().execute(UserInfoBean.MOCK_URL, new OkHttpClientManager.ResultCallback<UserInfoBean>() {
            @Override
            public void onError(Exception e) {
                mProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onResponse(UserInfoBean response) {
                mUserInfoBean = response;
                showProgressOrContent(false);
                displayUserInfo(response);
            }
        });
    }

    private void showProgressOrContent(boolean loading) {
        if (loading) {
            mContentContainer.setVisibility(View.GONE);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            mContentContainer.setVisibility(View.VISIBLE);
            mProgressBar.setVisibility(View.GONE);
        }
    }

    private void displayUserInfo(UserInfoBean response) {
        mNameTv.setText(response.getName());
        mAgeTv.setText(String.valueOf(response.getAge()));
        mDescTv.setText(response.getDesc());
    }

    private void initView() {
        mContentContainer = findViewById(R.id.container);

        mNameTv = (TextView) findViewById(R.id.name);
        mAgeTv = (TextView) findViewById(R.id.age);
        mDescTv = (TextView) findViewById(R.id.desc);

        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}
