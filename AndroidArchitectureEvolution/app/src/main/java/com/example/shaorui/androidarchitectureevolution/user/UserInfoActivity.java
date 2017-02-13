package com.example.shaorui.androidarchitectureevolution.user;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.shaorui.androidarchitectureevolution.R;
import com.example.shaorui.androidarchitectureevolution.user.fragment.UserInfoFragment;
import com.example.shaorui.androidarchitectureevolution.utils.ActivityUtil;

public class UserInfoActivity extends AppCompatActivity {

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, UserInfoActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infos);

        initFragment();
    }

    private void initFragment() {
        ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                UserInfoFragment.newInstance(),
                R.id.fragment_container);
    }
}
