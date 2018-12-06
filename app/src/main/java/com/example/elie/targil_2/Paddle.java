package com.example.elie.targil_2;

import android.graphics.RectF;

public class Paddle {

    private RectF rect;


    public Paddle(int height, int centerX){
        rect = new RectF(centerX-200, height - 100, centerX+ 200, height - 80);
    }


    public RectF getRect() {
        return rect;
    }

}
