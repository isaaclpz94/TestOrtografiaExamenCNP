package com.example.administrador.testortografiaexamencnp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Administrador on 27/01/2017.
 */

public class MyCustomTextView extends TextView {

    public MyCustomTextView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/Orbitron.ttf"));
    }
}
