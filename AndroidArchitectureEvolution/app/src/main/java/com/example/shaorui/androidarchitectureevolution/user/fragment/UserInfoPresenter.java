package com.example.shaorui.androidarchitectureevolution.user.fragment;

import com.example.shaorui.androidarchitectureevolution.request.OkHttpClientManager;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.data.source.IUserInfoSource;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoPresenter implements UserInfoContract.IPresenter<UserInfoContract.IView> {
    private UserInfoContract.IView mView;
    private IUserInfoSource mUserInfoRepository;

    public UserInfoPresenter(IUserInfoSource userInfoRepository) {
        mUserInfoRepository = userInfoRepository;
    }

    @Override
    public void doGetUserInfo() {
        mView.onLoading("正在加载...");
        getUserInfo();
    }

    private void getUserInfo() {
        mUserInfoRepository.getUserInfo("http://com.meizu.com/mock/userInfo", new OkHttpClientManager.ResultCallback<UserInfoBean>() {
            @Override
            public void onError(Exception e) {
                mView.onLoadError(e.getMessage());
            }

            @Override
            public void onResponse(UserInfoBean response) {
                mView.onLoadSuccess(response);
            }
        });
    }

    @Override
    public void doViewAttach(UserInfoContract.IView view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void doViewDetach() {
        mView = null;
    }
}
