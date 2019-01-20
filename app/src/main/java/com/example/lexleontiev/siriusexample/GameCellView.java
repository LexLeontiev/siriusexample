package com.example.lexleontiev.siriusexample;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

public class GameCellView extends AppCompatTextView {

    private boolean ifSelected = false;

    public GameCellView(Context context) {
        super(context);
        init();
    }

    public GameCellView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameCellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        setText("LOL");
        setGravity(Gravity.CENTER);
        colorBackground();
    }

    public void toggle() {
        ifSelected = !ifSelected;
        colorBackground();
    }

    private void colorBackground() {
        if (ifSelected) setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
        else setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
    }
}
