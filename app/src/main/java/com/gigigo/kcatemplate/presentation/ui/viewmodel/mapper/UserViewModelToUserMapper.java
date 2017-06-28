package com.gigigo.kcatemplate.presentation.ui.viewmodel.mapper;

import com.gigigo.kcatemplate.domain.mapper.Mapper;
import com.gigigo.kcatemplate.domain.model.User;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.UserViewModel;

import javax.inject.Inject;

/**
 * Created by Omar on 6/20/17.
 */

public class UserViewModelToUserMapper extends Mapper<UserViewModel, User> {

    @Inject
    public UserViewModelToUserMapper() {
    }

    @Override
    public User map(UserViewModel value) {
        User user = new User();

        user.setId(value.getId());
        user.setFirstName(value.getFullName());
        user.setAvatar(value.getAvatar());

        return user;
    }

    @Override
    public UserViewModel reverseMap(User value) {
        UserViewModel userViewModel = new UserViewModel();

        userViewModel.setId(value.getId());
        userViewModel.setFullName(value.getFirstName() + " " + value.getLastName());
        userViewModel.setAvatar(value.getAvatar());

        return userViewModel;
    }
}
