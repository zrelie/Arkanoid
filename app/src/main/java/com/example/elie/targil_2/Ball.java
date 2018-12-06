package com.example.elie.targil_2;

import android.graphics.RectF;

public class Ball {

    private RectF rect;
    private int width_in_move;
    private final int move = 50;

    public Ball(int width, int height, int ballX, int ballY){
        //width_in_move = (width/2);
        rect = new RectF(ballX-15, ballY - 15, ballX+ 15, ballY + 15);
    }

    public RectF getRect() {
        return rect;
    }

}
