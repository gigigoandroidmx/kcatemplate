package com.gigigo.kcatemplate.data.repository;

import com.gigigo.kcatemplate.data.entity.ListUsers;
import com.gigigo.kcatemplate.data.entity.SingleUser;
import com.gigigo.kcatemplate.data.service.IApiService;
import com.gigigo.kretrofitmanager.CallbackAdapter;
import com.gigigo.kretrofitmanager.ICall;
import com.gigigo.kretrofitmanager.ServiceClient;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Omar on 6/20/17.
 */

public class UsersRepositoryImpl implements UsersRepository {
    @Override
    public Observable<ListUsers> users() {
        return Observable.create(new ObservableOnSubscribe<ListUsers>() {
            @Override
            public void subscribe(final @NonNull ObservableEmitter<ListUsers> e) throws Exception {
                IApiService service = ServiceClient.createService(IApiService.class);
                ICall<ListUsers> call = service.getListUsers(0);

                call.enqueue(new CallbackAdapter<ListUsers>() {
                    @Override
                    public void onSuccess(ListUsers data) {
                        e.onNext(data);
                        e.onComplete();
                    }

                    @Override
                    public void onError(Throwable exception) {
                        e.onError(exception);
                    }
                });
            }
        });

//        return ServiceClient.createService(IApiService.class).getListUsers(0);
    }

    @Override
    public Observable<SingleUser> singleUser(final int id) {
        return Observable.create(new ObservableOnSubscribe<SingleUser>() {
            @Override
            public void subscribe(final @NonNull ObservableEmitter<SingleUser> e) throws Exception {

                IApiService service = ServiceClient.createService(IApiService.class);
                ICall<SingleUser> call = service.getSingleUser(id);

                call.enqueue(new CallbackAdapter<SingleUser>() {
                    @Override
                    public void onSuccess(SingleUser data) {
                        e.onNext(data);
                        e.onComplete();
                    }

                    @Override
                    public void onError(Throwable exception) {
                        e.onError(exception);
                    }
                });
            }
        });
    }
}
