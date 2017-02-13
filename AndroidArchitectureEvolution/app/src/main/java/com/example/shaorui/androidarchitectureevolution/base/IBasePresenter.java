package com.example.shaorui.androidarchitectureevolution.base;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IBasePresenter<T extends IBaseView> {
    /**
     * 生命周期管理
     * view（Fragment/Activity）attach到window时调用
     */
    void doViewAttach(T view);

    /**
     * 生命周期管理
     * view(Fragment/Activity)从window detatch的时候调用
     */
    void doViewDetach();
}
