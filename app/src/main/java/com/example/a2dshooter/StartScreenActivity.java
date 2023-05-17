package com.example.a2dshooter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2dshooter.utils.Constants;

import java.io.Serializable;

/**
 * Starting screen activity, contains UI elements
 */

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private ToggleButton tbButton1;
    private ToggleButton tbButton2;
    private ToggleButton tbButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        Window window = getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        Button btnStart = findViewById(R.id.btn_start);
        tbButton1 = findViewById(R.id.tb_button1);
        tbButton2 = findViewById(R.id.tb_button2);
        tbButton3 = findViewById(R.id.tb_button3);

        tbButton1.setChecked(true);

        btnStart.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                Constants.gameMode = 1.0;

                if (tbButton1.isChecked())
                    Constants.gameMode = 1.0;
                else if (tbButton2.isChecked())
                    Constants.gameMode = 1.5;
                else if (tbButton3.isChecked())
                    Constants.gameMode = 2;

                Game game = new Game(this);
                setContentView(game); //end activity?
                //finish();

                break;
            case R.id.tb_button1:
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