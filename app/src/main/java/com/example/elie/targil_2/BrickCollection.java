package com.example.elie.targil_2;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BrickCollection {

    private final int ROWS = 5; //5
    private final int COLS = 1; //7

    private Brick[][] MyBricks;

    public BrickCollection(int screen_W, int screen_H) {
        MyBricks = new Brick[ROWS][COLS];
        for(int i = 0; i < ROWS; i++){
            for(int j = 0; j <COLS;j++)
                MyBricks[i][j] = new Brick(i,j,screen_W/COLS, (screen_H/4)/ROWS);
        }

    }

    public int getROWS() {
        return ROWS;
    }

    public int getCOLS() {
        return COLS;
    }

    public Brick[][] getMyBricks() {
        return MyBricks;
    }

}
