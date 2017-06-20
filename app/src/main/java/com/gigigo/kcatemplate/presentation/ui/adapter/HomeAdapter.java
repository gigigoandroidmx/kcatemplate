package com.gigigo.kcatemplate.presentation.ui.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.gigigo.kcatemplate.R;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.UserViewModel;

import gigigo.com.kmvp.IAction;
import gigigo.com.kmvp.KAdapter;
import gigigo.com.kmvp.KViewHolder;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeAdapter
        extends KAdapter<UserViewModel> {

    private final IAction<UserViewModel> actionCommand;

    public HomeAdapter(IAction<UserViewModel> actionCommand) {
        this.actionCommand = actionCommand;
    }

    @Override
    public KViewHolder<UserViewModel> onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getView(parent, R.layout.item_home);
        HomeViewHolder viewHolder = new HomeViewHolder(view);
        viewHolder.setActionCommand(actionCommand);
        return viewHolder;
    }
}
