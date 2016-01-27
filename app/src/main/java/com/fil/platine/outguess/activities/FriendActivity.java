package com.fil.platine.outguess.activities;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.fil.platine.outguess.R;
import com.fil.platine.outguess.adapters.FriendsAdapter;

import java.util.ArrayList;

/**
 * Created by Chris on 25/01/2016.
 */
public class FriendActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendchoice);

        ArrayList<String> friends = new ArrayList<String>();
        friends.add("Rémi");
        friends.add("Chris");
        friends.add("François");
        friends.add("Alexis");
        friends.add("Lucas");

        FriendsAdapter adapter = new FriendsAdapter(this, R.drawable.ic_friendlist, friends);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        Toast.makeText(this, "Défi envoyé", Toast.LENGTH_SHORT).show();
    }
}
