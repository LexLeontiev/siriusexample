package com.example.lexleontiev.siriusexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private GameFieldView grid;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.button);
        grid = findViewById(R.id.grid);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // пользуемся API из другой вьюхи, то о чем я вчера говорил
                grid.toggleCell(4,4);
                grid.toggleCell(4,5);
                grid.toggleCell(4,3);
                grid.toggleCell(3,4);
                grid.toggleCell(5,4);

                // тут приложение не упадет, потому что API с защитой от дурака, ну или просто от
                // уставшего и невнимательного программиста
                grid.toggleCell(8,8);
            }
        });

        // наслаждаемся API нашей вьюхи
        grid.setSideCount(7);
        grid.setSpaceBetweenCells(8);

        // итого в MainActivity минимум кода, осталась только бизнес логика, то есть кол-во ячеек
        // и кнопка с переключение ячеек, все остальное инкапсулировано в специализированные классы
    }
}
