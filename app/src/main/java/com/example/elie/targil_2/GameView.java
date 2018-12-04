package com.example.elie.targil_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;


public class GameView extends View {

    private final int ROWS = 3;
    private final int COLS = 7;
    private Paint paddlePen;
    private Paint ballPen;
    private Paint textPen;

    private int screenWidth;
    private int screenHeight;

    private Brick b1;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paddlePen = new Paint();
        ballPen = new Paint();
        textPen = new Paint();

        screenWidth = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getWidth();
        screenHeight = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getHeight();
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.background));
    }
}
