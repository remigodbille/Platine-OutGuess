package com.fil.platine.outguess.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fil.platine.outguess.R;

import java.util.ArrayList;

/**
 * Created by Chris on 25/01/2016.
 */
public class FriendsAdapter extends ArrayAdapter<String> {

    private ArrayList<String> friends;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        String friendItem = friends.get(position);

        // Si la ListView est créé pour la première fois
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.onefriend, null);

            TextView textView = (TextView) convertView.findViewById(R.id.label);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.icon);

            viewHolder = new ViewHolder(textView, imageView);
            convertView.setTag(viewHolder);

        } else {
            // Sinon on récupère son tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // On affiche le nom des amis
        viewHolder.getText().setText(friendItem);

        return convertView;
    }

    public FriendsAdapter(Context context, int resource, ArrayList<String> friends) {
        super(context, resource, friends);
        this.friends = friends;
    }
}

