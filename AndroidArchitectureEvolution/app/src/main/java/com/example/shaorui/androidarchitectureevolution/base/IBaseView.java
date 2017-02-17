package com.example.shaorui.androidarchitectureevolution.base;

import com.trello.rxlifecycle.LifecycleProvider;
import com.trello.rxlifecycle.android.FragmentEvent;

/**
 * Created by shaorui on 17/2/13.
 */
public interface IBaseView<T extends IBasePresenter> {
    /**
     * RxJava生命周期管理
     * @return
     */
    LifecycleProvider<FragmentEvent> getLifecycleProvider();

    /**
     * 由于Presenter的创建交给了Activity，因此我们不能通过new的方式直接给Fragment创建Presenter实例
     * 但是我们可以在Presenter中的构造方法中调用{@code #setPresnter(T presenter)}方法来隐式创建
     * 并且在View的实现方法里这样做：mPresenter = presenter;
     * @param presenter
     */
    void setPresenter(T presenter);
}
