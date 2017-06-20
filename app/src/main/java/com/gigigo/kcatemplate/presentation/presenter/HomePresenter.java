package com.gigigo.kcatemplate.presentation.presenter;

import com.gigigo.kcatemplate.domain.interactor.DefaultObserver;
import com.gigigo.kcatemplate.domain.interactor.ListUserInteractor;
import com.gigigo.kcatemplate.domain.interactor.SingleUserInteractor;
import com.gigigo.kcatemplate.domain.model.User;
import com.gigigo.kcatemplate.presentation.ui.base.IViewBase;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.UserViewModel;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.mapper.UserViewModelToUserMapper;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomePresenter
        extends Presenter<HomePresenter.IViewHome> {

    private final ListUserInteractor listUserInteractor;
    private final SingleUserInteractor singleUserInteractor;
    private final UserViewModelToUserMapper mapper;

    public HomePresenter(ListUserInteractor listUserInteractor,
                         SingleUserInteractor singleUserInteractor,
                         @NonNull UserViewModelToUserMapper mapper) {
        this.listUserInteractor = listUserInteractor;
        this.singleUserInteractor = singleUserInteractor;
        this.mapper = mapper;
    }

    public void getUserList() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
//        listUserInteractor.setParams(getParams());
        listUserInteractor.execute(new UsersListObserver());
    }

    public void getSingleUser() {
        if(!isViewAttached()) return;

        getView().showLoading(true);
//        singleUserInteractor.setParams(getParams());

        singleUserInteractor.setUserId(Integer.valueOf(getParams().get(0).toString()));
        singleUserInteractor.execute(new SingleUserObserver());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.listUserInteractor.dispose();
        this.singleUserInteractor.dispose();
    }

    @Override
    public void handleError(Throwable exception) {
        super.handleError(exception);
        getView().showError(exception);
    }

    public interface IViewHome extends IViewBase {
        void showListUsers(List<UserViewModel> listUsers);
        void showSingleUser(UserViewModel user);
    }

    public final class UsersListObserver extends DefaultObserver<List<User>> {
        @Override
        public void onComplete() {
            getView().showLoading(false);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            super.onError(e);
            handleError(e);
        }

        @Override
        public void onNext(@NonNull List<User> users) {
            super.onNext(users);
            getView().showListUsers(mapper.reverseMap(users));
        }
    }

    public final class SingleUserObserver extends DefaultObserver<User> {
        @Override
        public void onComplete() {
            getView().showLoading(false);
        }

        @Override
        public void onError(@NonNull Throwable e) {
            super.onError(e);
            handleError(e);
        }

        @Override
        public void onNext(@NonNull User user) {
            super.onNext(user);
            getView().showSingleUser(mapper.reverseMap(user));
        }
    }
}
