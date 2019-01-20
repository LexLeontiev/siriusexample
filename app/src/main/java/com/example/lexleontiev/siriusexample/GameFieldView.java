package com.example.lexleontiev.siriusexample;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

public class GameFieldView extends GridLayout {

    public String TAG = this.getClass().getSimpleName();

    private List<GameCellView> cells = new ArrayList<>();
    private int spaceBetweenCells = 0;

    // обязательно нужно реализовать как минимум 3 конструктора для кастомных вьюх

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

    private void init() {
        setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        // добавляем наше ячейки только когда рассчиталась основная разметка, так как до этого
        // момента ширина и высота GridLayout может быть еще неизвестна, а без нее мы не сможем
        // рассчитать размер ячеек
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // проверяем, не добавлены ли уже кнопки, если уже добавлены - ничего не делаем
                // проверяем, потому что метод может вызываться несколько раз подряд, и если не
                // проверить - произойдет наложение вьюх
                if (!cells.isEmpty()) return;

                // рассчитываем размер ячеек
                int size = getRowCount();
                int cellSize = getWidth() / size;

                for (int i = 0; i < size * size; i++) {

                    final GameCellView cellView = new GameCellView(getContext());
                    cellView.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            cellView.toggle();
                        }
                    });

                    // устанавливаем параметры разметки ячейки
                    GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
                    layoutParams.height = cellSize - spaceBetweenCells;
                    layoutParams.width = cellSize - spaceBetweenCells;

                    // добавляем оступы
                    int margin = spaceBetweenCells / 2;
                    layoutParams.setMargins(margin,margin,margin,margin);

                    addView(cellView, layoutParams);
                    cells.add(cellView);

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

    /**
     * API нашей вьюхи, через которое мы можем, в данном случае, установаить размер сетки и оступы
     **/

    // вводим метод, который позволит создавать только квадратные поля
    public void setSideCount(int sideCount) {
        super.setColumnCount(sideCount);
        super.setRowCount(sideCount);
        cells.clear();
    }

    // устанавливаем расстояние между ячейками
    public void setSpaceBetweenCells(int space) {
        spaceBetweenCells = space;
    }

    // переключаем ячейку в другой состояние
    public void toggleCell(int column, int row) {

        // проверяем корректность входных параметров - потому что нашей API может пользоваться кто
        // угодно, и надо максимально снизить вероятность падения приложения из за неграмотного
        // использования API (аля полезный совет на будущее)
        if (column < 0 || column >= getColumnCount()) {
            Log.d(TAG, "Неверно указан номер колонки!");
            return;
        }

        if (row < 0 || column >= getRowCount()) {
            Log.d(TAG, "Неверно указан номер строки!");
            return;
        }

        int index = (row - 1) * getColumnCount() + column - 1;
        if (index < cells.size()) {
            cells.get(index).toggle();
        }
    }

}
