package com.gigigo.kcatemplate.presentation.ui.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gigigo.kcatemplate.R;
import com.gigigo.kcatemplate.presentation.ui.viewmodel.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import gigigo.com.kmvp.KViewHolder;

/**
 * @author Juan Godínez Vera - 5/23/2017.
 */
public class HomeViewHolder
        extends KViewHolder<UserViewModel> {

    @BindView(R.id.imageview_avatar)
    ImageView imageviewAvatar;

    @BindView(R.id.textview_firstname)
    TextView textViewFirstname;

    public HomeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewModel item) {
        super.onBindViewHolder(item);

        textViewFirstname.setText(item.getFullName());

        Glide.with(getContext())
                .load(item.getAvatar())
                .into(imageviewAvatar);
    }
}
