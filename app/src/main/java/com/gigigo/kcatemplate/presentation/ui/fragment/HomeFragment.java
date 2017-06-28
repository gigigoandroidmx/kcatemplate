package com.gigigo.kcatemplate.presentation.ui.fragment;


import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.gigigo.kcatemplate.R;
import com.gigigo.kcatemplate.data.repository.UsersRepositoryImpl;
import com.gigigo.kcatemplate.domain.interactor.ListUserInteractor;
import com.gigigo.kcatemplate.domain.interactor.SingleUserInteractor;
import com.gigigo.kcatemplate.domain.mapper.UserEntityToUserMapper;
import com.gigigo.kcatemplate.presentation.App;
import com.gigigo.kcatemplate.presentation.presenter.HomePresenter;
import com.gigigo.kcatemplate.presentation.ui.adapter.HomeAdapter;
import com.gigigo.kcatemplate.presentation.ui.base.KFragmentBase;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.UserViewModel;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.mapper.UserViewModelToUserMapper;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import gigigo.com.kmvp.IAction;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */

public class HomeFragment extends KFragmentBase<HomePresenter.IViewHome, HomePresenter>
        implements HomePresenter.IViewHome {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.textview_userdetail)
    TextView textviewUserDetail;

    private HomeAdapter adapter;

    @Inject
    public HomePresenter homePresenter;

    @Override
    public void onResume() {
        super.onResume();
        presenter.setParams(1);
        presenter.getUserList();

        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    //region KFragment members

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL,
                false));


        adapter = new HomeAdapter(new IAction<UserViewModel>() {
            @Override
            public void invoke(UserViewModel argument) {
                if (null == argument) return;

                presenter.setParams(argument.getId());
                presenter.getSingleUser();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected HomePresenter createPresenter() {
        /*ListUserInteractor listUserInteractor = new ListUserInteractor(Schedulers.newThread(),
                AndroidSchedulers.mainThread(), new UsersRepositoryImpl(), new UserEntityToUserMapper());

        SingleUserInteractor singleUserInteractor = new SingleUserInteractor(Schedulers.newThread(),
                AndroidSchedulers.mainThread(), new UsersRepositoryImpl(), new UserEntityToUserMapper());

        return new HomePresenter(listUserInteractor, singleUserInteractor, new UserViewModelToUserMapper());*/

        return homePresenter;
    }

    //endregion

    //region IViewHome members

    @Override
    public void showListUsers(List<UserViewModel> listUsers) {
        if (null == listUsers) return;
        adapter.set(listUsers);
    }

    @Override
    public void showSingleUser(final UserViewModel user) {
        if (null == user) return;

        String data = "Id: " + String.valueOf(user.getId()) + "\n" +
                "Full Name: " + user.getFullName();

        textviewUserDetail.setText(data);
    }

    @Override
    public void showError(Throwable exception) {
        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading(boolean active) {
        showProgressIndicator(active);
    }

    //endregion

    private void initializeDagger() {
        App app = (App) getActivity().getApplication();
        app.getMainComponent().inject(this);
    }
}
