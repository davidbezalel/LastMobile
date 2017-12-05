package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidbezalellaoli on 12/5/17.
 */

public class Home2UserVpAdapter extends PagerAdapter {

    private static final float PAGE_WIDTH = 0.8f;

    List<User> users;

    public Home2UserVpAdapter() {
        users = new ArrayList<>();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View _view = LayoutInflater.from(container.getContext()).inflate(R.layout.timeline_user_item, null, false);

        final User _user = users.get(position);

        TextView _nim = (TextView) _view.findViewById(R.id.timelineuseritem_nim);
        _nim.setText(_user.nim);

        container.addView(_view);
        return _view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public void setUsers (List<User> users) {
        this.users = users;
    }

    @Override
    public float getPageWidth(int position) {
        return PAGE_WIDTH;
    }
}
