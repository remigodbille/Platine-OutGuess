package com.fil.platine.outguess.adapters;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Chris on 25/01/2016.
 */


public class ViewHolder {
    TextView text;
    ImageView img;

    public ViewHolder(TextView text) {
        this.text = text;
    }

    public ViewHolder(TextView text, ImageView img) {
        this.text = text;
        this.img = img;
    }

    public TextView getText() {
        return text;
    }

    public void setText(TextView text) {
        this.text = text;
    }

}
