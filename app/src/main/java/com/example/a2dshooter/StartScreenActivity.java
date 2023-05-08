package com.example.a2dshooter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener {

    private ToggleButton tbButton1;
    private ToggleButton tbButton2;
    private ToggleButton tbButton3;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Button btnStart = findViewById(R.id.btn_start);
        tbButton1 = findViewById(R.id.tv_button1);
        tbButton2 = findViewById(R.id.tb_button2);
        tbButton3 = findViewById(R.id.tb_button3);

        tbButton1.setChecked(true);

        btnStart.setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                String gameMode = "EASY";

                if(tbButton1.isChecked())
                    gameMode = "EASY";
                else if(tbButton2.isChecked())
                    gameMode = "MEDIUM";
                else if(tbButton3.isChecked())
                    gameMode = "HARD";

                intent.putExtra("GAME_MODE", gameMode);

                Game game = new Game(this);
                setContentView(game); //end activity?
                break;
            case R.id.tv_button1:
                tbButton1.setChecked(true);
                tbButton2.setChecked(false);
                tbButton3.setChecked(false);
                break;
            case R.id.tb_button2:
                tbButton1.setChecked(false);
                tbButton2.setChecked(true);
                tbButton3.setChecked(false);
                break;
            case R.id.tb_button3:
                tbButton1.setChecked(false);
                tbButton2.setChecked(false);
                tbButton3.setChecked(true);
                break;
        }
    }
}