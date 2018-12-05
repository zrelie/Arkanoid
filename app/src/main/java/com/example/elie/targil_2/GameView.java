package com.example.elie.targil_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class GameView extends View {

    private final int LIVES_AT_BEGINNING = 3;
    private final int SCORE_AT_BEGINNING = 0;

    private enum State {GET_READY, PLAYING, GAME_OVER};

    private Paint paddlePen;
    private Paint ballPen;
    private Paint textPen;
    private Paint brickPen;
    private int Score;
    private int Lives;
    private final int MOVEMENT = 50;
    private int paddle_place;

    private int screenWidth = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getWidth();
    private int screenHeight = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getHeight();
    private  BrickCollection TheBricksCollection;
    private  Paddle paddle;
    private  Ball ball;
    private State state;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paddle_place = screenWidth/2;
        state = State.GET_READY;
        TheBricksCollection = new BrickCollection(context, attrs, screenWidth,screenHeight);
        ball = new Ball(screenWidth, screenHeight);

        paddlePen = new Paint();
        ballPen = new Paint();
        textPen = new Paint();
        brickPen = new Paint();

        Score = SCORE_AT_BEGINNING;
        Lives = LIVES_AT_BEGINNING;

    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        paddle = new Paddle(screenWidth, screenHeight, paddle_place);

        canvas.drawColor(getResources().getColor(R.color.background));


        textPen.setColor(getResources().getColor(R.color.colorPrimary));
        textPen.setTextSize(60);
        canvas.drawText("Score: " +  Integer.toString(Score),0,80,textPen);
        canvas.drawText("Lives: " +  Integer.toString(Lives),screenWidth-250,80,textPen);
        if (state == State.GET_READY)
            canvas.drawText("Click to PLAY!",(screenWidth/2)-180,(screenHeight/2)+50,textPen);


        paddlePen.setColor(getResources().getColor(R.color.colorPrimaryDark));
        canvas.drawRect(paddle.getRect(), paddlePen);


        ballPen.setColor(getResources().getColor(R.color.ball));
        canvas.drawRoundRect(ball.getRect(),50, 50, ballPen);


        brickPen.setColor(getResources().getColor(R.color.bricksColor));
        for(int i = 0; i < TheBricksCollection.getROWS(); i++)
            for(int j = 0; j < TheBricksCollection.getCOLS(); j++)
                if(TheBricksCollection.getMyBricks()[i][j].getVisibility())
                    canvas.drawRect(TheBricksCollection.getMyBricks()[i][j].getRect(),brickPen);

    }

//l
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        while ((event.getAction() == MotionEvent.ACTION_DOWN) && (event.getX() > screenWidth / 2))
                move_right();

        while ((event.getAction() == MotionEvent.ACTION_DOWN) && (event.getX() < screenWidth / 2))
            move_left();
        return true;
    }

    public void move_left(){
        if((paddle_place - MOVEMENT) - 200 > 0)
            paddle_place -= MOVEMENT;

        else
            paddle_place = 200;

        invalidate();
    }

    public void move_right(){
        if((paddle_place + MOVEMENT) + 200 < screenWidth)
             paddle_place += MOVEMENT;

        else
            paddle_place = screenWidth - 200;

        invalidate();
    }

}
