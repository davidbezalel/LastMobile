package com.example.davidbezalellaoli.lastmobilemikroskil.modules.home.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidbezalellaoli.lastmobilemikroskil.R;
import com.example.davidbezalellaoli.lastmobilemikroskil.models.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidbezalellaoli on 11/27/17.
 */

public class User_RV_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> users;
    private Context context;
    private int type;

    public User_RV_Adapter() {
        users  = new ArrayList<>();
    }

    public void setUsers (List<User> users) {
        this.users = users;
    }
    public void setType(int type) {this.type = type; }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(this.type == 0) {
            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_user_item, parent, false);
            this.context = parent.getContext();
            return new User_RV_ViewHolder(_view);
        }
        else {
            View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_user_item_grid, parent, false);
            this.context = parent.getContext();
            return new User_RV_ViewHolder(_view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        User_RV_ViewHolder _userRvViewHolder = (User_RV_ViewHolder) holder;
        final User _user = users.get(position);

        _userRvViewHolder.nim.setText(_user.nim);
        _userRvViewHolder.image.setImageResource(_user.imagePofil);
        _userRvViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        _userRvViewHolder.jurusan.setText(_user.getProgramStudi(_user.nim));
        _userRvViewHolder.name.setText(_user.name);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    private class User_RV_ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView nim, name, jurusan;

        public User_RV_ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.timelineuseritem_image);
            nim = (TextView) itemView.findViewById(R.id.timelineuseritem_nim);
            jurusan = (TextView) itemView.findViewById(R.id.timelineuseritem_jurusan);
            name = (TextView) itemView.findViewById(R.id.timelineuseritem_name);
        }
    }
}
