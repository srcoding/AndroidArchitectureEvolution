package com.example.shaorui.androidarchitectureevolution.user.fragment;

import com.example.shaorui.androidarchitectureevolution.base.IBasePresenter;
import com.example.shaorui.androidarchitectureevolution.base.IBaseView;
import com.example.shaorui.androidarchitectureevolution.user.data.UserInfoBean;

/**
 * Created by shaorui on 17/2/13.
 */
public interface UserInfoContract {

    interface IView extends IBaseView<IPresenter> {
        /**
         * 开始加载
         * @param msg
         */
        void onLoading(String msg);

        /**
         * 加载出错
         * @param errorMsg
         */
        void onLoadError(String errorMsg);

        /**
         * 加载成功
         * @param data
         */
        void onLoadSuccess(UserInfoBean data);
    }

    interface IPresenter extends IBasePresenter {
        /**
         * 获取用户信息
         */
        void doGetUserInfo();
    }
}
