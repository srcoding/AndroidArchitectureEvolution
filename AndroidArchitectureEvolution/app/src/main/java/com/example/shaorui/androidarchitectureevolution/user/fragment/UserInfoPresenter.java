package com.example.shaorui.androidarchitectureevolution.user.fragment;

import com.example.shaorui.androidarchitectureevolution.network.request.RetrofitManager;
import com.example.shaorui.androidarchitectureevolution.network.response.MyResponse;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.data.source.IUserInfoSource;

import retrofit2.Call;

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
        mUserInfoRepository.getUserInfo("userId", new RetrofitManager.ResultCallback<MyResponse<UserInfoBean>>() {
            @Override
            public void onFailure(Call<MyResponse<UserInfoBean>> call, Throwable t) {
                mView.onLoadError(t.getMessage());
            }

            @Override
            public void onResponse(MyResponse<UserInfoBean> response) {
                mView.onLoadSuccess(response.getData());
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
