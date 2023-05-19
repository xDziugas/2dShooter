package com.example.a2dshooter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a2dshooter.utils.Constants;

import java.io.Serializable;

/**
 * Starting screen activity, contains UI elements
 */

public class StartScreenActivity extends AppCompatActivity implements View.OnClickListener, Serializable {

    private SeekBar seekBar;
    private TextView tvDiff;
    final String[] diff = {"EASY", "MEDIUM", "HARD"};

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
        tvDiff = findViewById(R.id.TV_diff);

        seekBar = findViewById(R.id.SB_difficulty);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvDiff.setText(diff[seekBar.getProgress() - 1]);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnStart.setOnClickListener(this);
        tvDiff.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start) {
            Constants.gameMode = 1.0;

            if (seekBar.getProgress() == 1)
                Constants.gameMode = 1.0;
            else if (seekBar.getProgress() == 2)
                Constants.gameMode = 1.5;
            else if (seekBar.getProgress() == 3)
                Constants.gameMode = 2;

            Game game = new Game(this);
            setContentView(game); //end activity?
            //finish();
        }
        if (v.getId() == R.id.TV_diff) {
            if (seekBar.getProgress() + 1 <= seekBar.getMax()) {
                tvDiff.setText(diff[seekBar.getProgress()]);
                seekBar.setProgress(seekBar.getProgress() + 1);
            } else {
                tvDiff.setText(diff[0]);
                seekBar.setProgress(1);
            }
        }
    }
}