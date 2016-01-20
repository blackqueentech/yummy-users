package com.example.dellaanjeh.yummy_users;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dellaanjeh on 1/19/16.
 */
public class UserListAdapter extends ArrayAdapter<UserList> {
    private int resource;
    private LayoutInflater inflater;
    private Context context;
    private List<UserList> users;

    public UserListAdapter(Context c, int resourceId, List<UserList> users) {
        super(c, resourceId, users);
        resource = resourceId;
        inflater = LayoutInflater.from(c);
        context = c;
        this.users = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View itemView;
        if (convertView != null) {
            itemView = convertView;
        } else {
            itemView = inflater.inflate(resource, null);
        }
        final UserList usersList = getItem(position);
        TextView tvUsername = (TextView) itemView.findViewById(R.id.tvUsername);
        tvUsername.setText(usersList.getUsername());
        TextView tvName = (TextView) itemView.findViewById(R.id.tvName);
        tvName.setText(usersList.getName());
        return itemView;
    }
}
