package com.example.elie.targil_2;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    private GameView game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = (GameView)findViewById(R.id.gameview);
        game.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
