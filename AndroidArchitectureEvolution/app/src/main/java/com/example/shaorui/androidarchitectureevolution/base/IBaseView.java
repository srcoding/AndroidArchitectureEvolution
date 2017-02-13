package com.example.shaorui.androidarchitectureevolution.base;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IBaseView<T extends IBasePresenter> {
    void setPresenter(T presenter);
}
