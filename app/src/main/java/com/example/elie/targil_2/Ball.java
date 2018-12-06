package com.example.elie.targil_2;

import android.graphics.RectF;

public class Ball {

    private RectF rect;


    public Ball(int ballX, int ballY){
        rect = new RectF(ballX-15, ballY - 15, ballX+ 15, ballY + 15);
    }

    public RectF getRect() {
        return rect;
    }

}
