package com.example.shaorui.androidarchitectureevolution.user.fragment;

import com.example.shaorui.androidarchitectureevolution.network.rxfunc.GetResponseDataFunc1;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;
import com.example.shaorui.androidarchitectureevolution.user.data.source.IUserInfoSource;
import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by shaorui on 17/2/13.
 */
public class UserInfoPresenter implements UserInfoContract.IPresenter {
    private UserInfoContract.IView mView;
    private LifecycleProvider<FragmentEvent> mLifecycleProvider;
    private IUserInfoSource mUserInfoRepository;

    public UserInfoPresenter(UserInfoContract.IView view, IUserInfoSource userInfoRepository) {
        mView = view;
        mView.setPresenter(this);
        mLifecycleProvider = mView.getLifecycleProvider();
        mUserInfoRepository = userInfoRepository;
    }

    @Override
    public void doGetUserInfo() {
        mView.onLoading("正在加载...");
        getUserInfo();
    }

    private void getUserInfo() {
        mUserInfoRepository.getUserInfo("userId")
                .map(new GetResponseDataFunc1<UserInfoBean>())
                .compose(mLifecycleProvider.<UserInfoBean>bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<UserInfoBean>() {
                    @Override
                    public void call(UserInfoBean userInfoBean) {
                        mView.onLoadSuccess(userInfoBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        mView.onLoadError(throwable.getMessage());
                    }
                });
    }
}
