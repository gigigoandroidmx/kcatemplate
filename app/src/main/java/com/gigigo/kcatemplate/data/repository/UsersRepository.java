package com.gigigo.kcatemplate.data.repository;

import com.gigigo.kcatemplate.data.entity.ListUsers;
import com.gigigo.kcatemplate.data.entity.SingleUser;

import io.reactivex.Observable;

/**
 * Created by Omar on 6/20/17.
 */

public interface UsersRepository {
    Observable<ListUsers> users();
    Observable<SingleUser> singleUser(int id);
}
