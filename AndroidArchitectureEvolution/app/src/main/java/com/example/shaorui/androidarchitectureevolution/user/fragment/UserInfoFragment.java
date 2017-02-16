package com.example.shaorui.androidarchitectureevolution.user.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.shaorui.androidarchitectureevolution.R;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.data.source.UserInfoRepository;

/**
 * created by shaorui on 17/02/13
 */
public class UserInfoFragment extends Fragment implements UserInfoContract.IView {

    private UserInfoContract.IPresenter mPresenter;

    private View mContentContainer;
    private TextView mNameTv;
    private TextView mAgeTv;
    private TextView mDescTv;

    private ProgressBar mProgressBar;

    public static UserInfoFragment newInstance() {
        UserInfoFragment fragment = new UserInfoFragment();
        new UserInfoPresenter(fragment, new UserInfoRepository());
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mPresenter.doViewAttach(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_user_info, container, false);
        initView(contentView);
        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.doGetUserInfo();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.doViewDetach();
    }

    private void startLoading() {
        mContentContainer.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void stopLoading() {
        mContentContainer.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
    }

    private void displayUserInfo(UserInfoBean response) {
        mNameTv.setText(response.getName());
        mAgeTv.setText(String.valueOf(response.getAge()));
        mDescTv.setText(response.getDesc());
    }

    private void initView(View containerView) {
        mContentContainer = containerView.findViewById(R.id.container);

        mNameTv = (TextView) containerView.findViewById(R.id.name);
        mAgeTv = (TextView) containerView.findViewById(R.id.age);
        mDescTv = (TextView) containerView.findViewById(R.id.desc);

        mProgressBar = (ProgressBar) containerView.findViewById(R.id.progress_bar);
    }


    @Override
    public void onLoading(String msg) {
        startLoading();
    }

    @Override
    public void onLoadError(String errorMsg) {
        stopLoading();
    }

    @Override
    public void onLoadSuccess(UserInfoBean data) {
        stopLoading();
        displayUserInfo(data);
    }

    @Override
    public void setPresenter(UserInfoContract.IPresenter presenter) {
        mPresenter = presenter;
    }
}
