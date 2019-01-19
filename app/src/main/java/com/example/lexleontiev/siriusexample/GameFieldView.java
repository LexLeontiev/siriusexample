package com.example.lexleontiev.siriusexample;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;

public class GameFieldView extends GridLayout {

    private Button[] buttons;

    public GameFieldView(Context context) {
        super(context);
        init();
    }

    public GameFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public GameFieldView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setAlignmentMode(GridLayout.ALIGN_BOUNDS);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int width = getWidth();
                int size = getRowCount();
                int cellSize = width / size;
                int space = 10;
                int margin = space / 2;
                for (int i = 0; i < size * size; i++) {
                    Button cellView = new Button(getContext());
                    cellView.setText("lol");
                    cellView.setGravity(Gravity.CENTER);
                    cellView.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.height = cellSize - space;
                    layoutParams.width = cellSize - space;
                    layoutParams.setMargins(margin,margin,margin,margin);
//                    buttons[i] = cellView;
                    addView(cellView, layoutParams);
                }
            }
        });
    }

    @Override
    public void setRowCount(int rowCount) {
        // переопределяем этот метод, чтобы нельзя было задать кол-во строк
    }

    @Override
    public void setColumnCount(int columnCount) {
        // переопределяем этот метод, чтобы нельзя было задать кол-во столбцов
    }

    public void setSideSize(int sideSize) {
        // вводим метод, который позволит создавать только квадратные поля
        super.setColumnCount(sideSize);
        super.setRowCount(sideSize);
//        buttons = new Button[sideSize*sideSize];
    }


}
