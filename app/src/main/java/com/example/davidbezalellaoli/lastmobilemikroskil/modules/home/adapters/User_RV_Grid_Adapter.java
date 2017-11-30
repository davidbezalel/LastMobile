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
 * Created by Chris Shinta on 30/11/2017.
 */

public class User_RV_Grid_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<User> users;
    private Context context;

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User_RV_Grid_Adapter() {
        users  = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View _view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timeline_user_item_grid, parent, false);
        this.context = parent.getContext();
        return new User_RV_Grid_Adapter.User_RV_Grid_ViewHolder(_view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,final int position) {
        User_RV_Grid_Adapter.User_RV_Grid_ViewHolder _userRvViewHolder = (User_RV_Grid_Adapter.User_RV_Grid_ViewHolder) holder;
        final User _user = users.get(position);
        _userRvViewHolder.nim.setText(_user.nim);
        _userRvViewHolder.name.setText(_user.name);
        _userRvViewHolder.jurusan.setText(_user.getJurusan());
        _userRvViewHolder.image.setImageResource(context.getResources().getIdentifier(_user.getProfile_image(),"drawable",context.getPackageName()));
        _userRvViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    private class User_RV_Grid_ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView nim, name, jurusan;

        public User_RV_Grid_ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.timelineuseritem_image);
            nim = (TextView) itemView.findViewById(R.id.timelineuseritem_nim);
            name = (TextView) itemView.findViewById(R.id.timelineuseritem_name);
            jurusan =(TextView) itemView.findViewById(R.id.timelineuseritem_jurusan);
        }
    }
}
