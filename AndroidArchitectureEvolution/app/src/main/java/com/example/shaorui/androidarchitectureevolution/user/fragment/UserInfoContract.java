package com.example.shaorui.androidarchitectureevolution.user.fragment;

import com.example.shaorui.androidarchitectureevolution.base.IBasePresenter;
import com.example.shaorui.androidarchitectureevolution.base.IBaseView;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

/**
 * Created by shaorui on 17/2/13.
 */
public interface UserInfoContract {

    interface IView extends IBaseView<IPresenter> {
        void onLoading(String msg);
        void onLoadError(String errorMsg);
        void onLoadSuccess(UserInfoBean data);
    }

    interface IPresenter<T extends IBaseView> extends IBasePresenter<T> {
        void doGetUserInfo();
    }
}
