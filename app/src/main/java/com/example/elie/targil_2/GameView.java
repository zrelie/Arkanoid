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
    private Paint brickPen;
    private int Score;
    private int Lives;

    private Brick b1;
    private int screenWidth = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getWidth();
    private int screenHeight = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getHeight();
    private  BrickCollection TheBricksCollection;
    private  Paddle paddle;
    private  Ball ball;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TheBricksCollection = new BrickCollection(context, attrs, screenWidth,screenHeight);
        paddle = new Paddle(screenWidth, screenHeight);
        ball = new Ball(screenWidth, screenHeight);
        paddlePen = new Paint();
        ballPen = new Paint();
        textPen = new Paint();
        brickPen = new Paint();
        Score = 0;
        Lives = 3;
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.background));

        brickPen.setColor(getResources().getColor(R.color.bricksColor));
        textPen.setColor(getResources().getColor(R.color.colorPrimary));
        textPen.setTextSize(70);
        paddlePen.setColor(getResources().getColor(R.color.colorPrimaryDark));
        ballPen.setColor(getResources().getColor(R.color.ball));

        canvas.drawRoundRect(ball.getRect(),50, 50, ballPen);
        canvas.drawRect(paddle.getRect(), paddlePen);
        canvas.drawText("Score: " +  Integer.toString(Score),0,80,textPen);
        canvas.drawText("Lives: " +  Integer.toString(Lives),screenWidth-250,80,textPen);

        for(int i = 0; i < TheBricksCollection.getROWS(); i++)
            for(int j = 0; j < TheBricksCollection.getCOLS(); j++)
                canvas.drawRect(TheBricksCollection.getMyBricks()[i][j].getRect(),brickPen);
    }
}
