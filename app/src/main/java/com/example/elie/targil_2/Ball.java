package com.example.elie.targil_2;

import android.graphics.RectF;

public class Ball {

    private RectF rect;
    private int width_in_move;
    private final int move = 50;

    public Ball(int width, int height){
        width_in_move = (width/2);
        rect = new RectF(width_in_move-15, height - 130, width_in_move+ 15, height - 100);
    }

    public RectF getRect() {
        return rect;
    }

}
