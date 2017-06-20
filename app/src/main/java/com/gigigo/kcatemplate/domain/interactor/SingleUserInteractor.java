package com.gigigo.kcatemplate.domain.interactor;

import com.gigigo.kcatemplate.data.entity.SingleUser;
import com.gigigo.kcatemplate.data.repository.UsersRepository;
import com.gigigo.kcatemplate.domain.base.Interactor;
import com.gigigo.kcatemplate.domain.mapper.UserEntityToUserMapper;
import com.gigigo.kcatemplate.domain.model.User;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @author Juan Godínez Vera - 6/2/2017.
 */
public class SingleUserInteractor extends Interactor<User> {

    private UsersRepository usersRepository;
    private UserEntityToUserMapper mapper;
    private int id;

    public SingleUserInteractor(Scheduler executorThread, Scheduler uiThread,
                                UsersRepository usersRepository, UserEntityToUserMapper mapper) {
        super(executorThread, uiThread);
        this.usersRepository = usersRepository;
        this.mapper = mapper;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    @Override
    protected Observable<User> createObservable() {
        return usersRepository.singleUser(this.id).map(new Function<SingleUser, User>() {
            @Override
            public User apply(@NonNull SingleUser singleUser) throws Exception {
                return mapper.map(singleUser.getData());
            }
        });
    }


    /**
     * Defines the method to be invoked when the use case is executed
     */
//    @Override
//    public void run() {
//        int userId = tryGetParamValueAs(Integer.class, 0);
//        IApiService service = ServiceClient.createService(IApiService.class);
//        ICall<SinlgeUser> call = service.getSingleUser(userId);
//
//        /**
//         * implements {@link CallbackAdapter} or {@link ICallbackAdapter}
//         */
//        call.enqueue(new CallbackAdapter<SinlgeUser>() {
//            @Override
//            public void onSuccess(final SinlgeUser data) {
//                EventBus.getDefault().post(new SingleUserEvent(data));
//            }
//
//            @Override
//            public void onError(final Throwable exception) {
//                EventBus.getDefault().post(new ErrorEvent(exception));
//            }
//        });
//    }
}
