package com.gigigo.kcatemplate.presentation.ui.viewmodel;

/**
 * Created by Omar on 6/20/17.
 */

public class UserViewModel {
    private int id;
    private String fullName;
    private String avatar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
