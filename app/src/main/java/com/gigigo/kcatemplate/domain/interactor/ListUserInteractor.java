package com.gigigo.kcatemplate.domain.interactor;


import com.gigigo.kcatemplate.data.entity.ListUsers;
import com.gigigo.kcatemplate.data.repository.UsersRepository;
import com.gigigo.kcatemplate.domain.base.Interactor;
import com.gigigo.kcatemplate.domain.mapper.UserEntityToUserMapper;
import com.gigigo.kcatemplate.domain.model.User;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class ListUserInteractor extends Interactor<List<User>> {

    private UsersRepository usersRepository;
    private UserEntityToUserMapper mapper;

    @Inject
    public ListUserInteractor(@Named("executor_thread") Scheduler executorThread,
                              @Named("ui_thread") Scheduler uiThread,
                              UsersRepository usersRepository,
                              UserEntityToUserMapper mapper) {
        super(executorThread, uiThread);
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    @Override
    protected Observable<List<User>> createObservable() {
        return usersRepository.users().map(new Function<ListUsers, List<User>>() {
            @Override
            public List<User> apply(@NonNull ListUsers listUsers) throws Exception {
                return mapper.map(listUsers.getUserList());
            }
        });
    }

    /*public ListUserInteractor() {}

    *
     * Defines the method to be invoked when

    @Override
    public void run() {
        int page = tryGetParamValueAs(Integer.class, 0);
        IApiService service = ServiceClient.createService(IApiService.class);
        ICall<ListUsers> call = service.getListUsers(page);

        *
         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}

        call.enqueue(new CallbackAdapter<ListUsers>() {
            @Override
            public void onSuccess(final ListUsers data) {
                EventBus.getDefault().post(new UsersListEvent(data));
            }

            @Override
            public void onError(final Throwable exception) {
                EventBus.getDefault().post(new ErrorEvent(exception));
            }
        });
    }*/
}
