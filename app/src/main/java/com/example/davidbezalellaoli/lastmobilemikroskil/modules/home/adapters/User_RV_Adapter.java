package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;
import com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.fragments.Home;
import com.example.davidbezalellaoli.lastmobilemikroskil.utils.StringColorParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidbezalellaoli on 11/27/17.
 */

public class User_RV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> users;
    private int mode;
    private Context context;

    public User_RV_Adapter() {
        users  = new ArrayList<>();
    }


    public void setUsers (List<User> users) {
        this.users = users;
    }
    public void setMode (int mode){this.mode = mode;}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view=null;
        if(mode== Home.HOME_CODE) {
            _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_user_item, parent, false);
        }else if(mode== Home.DASHBOARD_CODE){
            _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_user_item_grid, parent, false);
        }
        this.context = parent.getContext();
        return new User_RV_ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        User_RV_ViewHolder _userRvViewHolder = (User_RV_ViewHolder) holder;
        final User _user = users.get(position);

        _userRvViewHolder.nim.setText(_user.nim);

        _userRvViewHolder.image.setText(_user.getNameInitial());
        StringColorParser colorParser = new StringColorParser(_user.name);
        GradientDrawable txtBg = (GradientDrawable) _userRvViewHolder.image.getBackground();

        try {
            txtBg.setColor(Color.parseColor(colorParser.getColorCode()));
        }catch (Exception e){colorParser.getColorCode();}
        _userRvViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        _userRvViewHolder.name.setText(_user.name);
        _userRvViewHolder.jurusan.setText(_user.getJurusan());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private class User_RV_ViewHolder extends RecyclerView.ViewHolder {

        private TextView image;
        private TextView nim, name, jurusan;

        public User_RV_ViewHolder(View itemView) {
            super(itemView);
            image = (TextView) itemView.findViewById(R.id.timelineuseritem_image);
            nim = (TextView) itemView.findViewById(R.id.timelineuseritem_nim);
            name = (TextView) itemView.findViewById(R.id.timelineuseritem_name);
            jurusan = (TextView) itemView.findViewById(R.id.timelineuseritem_jurusan);
        }
    }
}
