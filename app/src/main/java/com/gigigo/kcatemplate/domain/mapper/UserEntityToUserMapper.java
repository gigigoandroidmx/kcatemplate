package com.gigigo.kcatemplate.domain.mapper;

import com.gigigo.kcatemplate.data.entity.UserEntity;
import com.gigigo.kcatemplate.domain.model.User;

import javax.inject.Inject;

/**
 * Created by Omar on 6/20/17.
 */

public class UserEntityToUserMapper extends Mapper<UserEntity, User> {

    @Inject
    public UserEntityToUserMapper() {
    }

    @Override
    public User map(UserEntity value) {
        User user = new User();

        user.setId(value.getId());
        user.setFirstName(value.getFirstName());
        user.setLastName(value.getLastName());
        user.setAvatar(value.getAvatar());

        return user;
    }

    @Override
    public UserEntity reverseMap(User value) {
        return null;
    }
}
