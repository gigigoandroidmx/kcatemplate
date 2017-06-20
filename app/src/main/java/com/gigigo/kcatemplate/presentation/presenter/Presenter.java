package com.gigigo.kcatemplate.presentation.presenter;

import gigigo.com.kmvp.IView;
import gigigo.com.kmvp.KPresenter;

/**
 * Created by Omar on 6/6/17.
 */

public abstract class Presenter<T extends IView> extends KPresenter<T> {

    public void onResume() { }

    public void onPause() { }

    public void onDestroy() { }

}
