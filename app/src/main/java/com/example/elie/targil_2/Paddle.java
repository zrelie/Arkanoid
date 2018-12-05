package com.example.elie.targil_2;

import android.graphics.RectF;

public class Paddle {

    private RectF rect;
    private int width_in_move;
    private final int move = 50;



    public Paddle(int width, int height, int size){
        width_in_move = size;
        rect = new RectF(width_in_move-200, height - 100, width_in_move+ 200, height - 70);
    }


    public RectF getRect() {
        return rect;
    }

}
