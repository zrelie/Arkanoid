package com.example.elie.targil_2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.Random;


public class GameView extends View {

    private final int LIVES_AT_BEGINNING = 3;
    private final int SCORE_AT_BEGINNING = 0;
    private final int MOVEMENT = 50;
    private final int BALL_SPEED = 20;

    private enum State {GET_READY, PLAYING, GAME_OVER};

    private Paint paddlePen;
    private Paint ballPen;
    private Paint textPen;
    private Paint brickPen;
    private int Score;
    private int Lives;

    private int died_bricks;

    private boolean new_game;
    private boolean won;

    private int paddle_place;
    private boolean paddle_is_moving;

    private int screenWidth = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getWidth();
    private int screenHeight = (((Activity)getContext()).getWindowManager().getDefaultDisplay()).getHeight();
    private  BrickCollection TheBricksCollection;
    private  Paddle paddle;
    private  Ball ball;
    private State state;
    private int ballX;
    private int ballY;
    private int firstX;
    private double firstY;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);

        new_game = true;
        state = State.GET_READY;
        init_game();
    }


    public void init_game(){

        if (new_game)
        {
            TheBricksCollection = new BrickCollection(screenWidth,screenHeight);
            Score = SCORE_AT_BEGINNING;
            Lives = LIVES_AT_BEGINNING;

            won = false;
            new_game = false;
            died_bricks = 0;
        }


        paddle_place = screenWidth/2;

        ballX = screenWidth/2;
        ballY = screenHeight - 115;

        firstX = 0;
        firstY = 0;

        paddlePen = new Paint();
        ballPen = new Paint();
        textPen = new Paint();
        brickPen = new Paint();


        Random rand = new Random();
        do {
            firstX = rand.nextInt(((BALL_SPEED) - (-BALL_SPEED)) + 1) - (BALL_SPEED);
        } while (Math.abs(firstX) > BALL_SPEED-5 || Math.abs(firstX) == BALL_SPEED);

        firstY = (BALL_SPEED - Math.abs(firstX));

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.background));

        paddle = new Paddle(screenHeight, paddle_place);
        ball = new Ball(ballX, ballY);

        paddlePen.setColor(getResources().getColor(R.color.colorPrimaryDark));
        canvas.drawRect(paddle.getRect(), paddlePen);


        ballPen.setColor(getResources().getColor(R.color.ball));
        canvas.drawRoundRect(ball.getRect(),50, 50, ballPen);


        brickPen.setColor(getResources().getColor(R.color.bricksColor));
        for(int i = 0; i < TheBricksCollection.getROWS(); i++)
            for(int j = 0; j < TheBricksCollection.getCOLS(); j++)
                if(TheBricksCollection.getMyBricks()[i][j].getVisibility())
                    canvas.drawRect(TheBricksCollection.getMyBricks()[i][j].getRect(),brickPen);


        textPen.setColor(getResources().getColor(R.color.colorPrimary));
        textPen.setTextSize(60);
        canvas.drawText("Score: " +  Integer.toString(Score),0,80,textPen);
        canvas.drawText("Lives: " +  Integer.toString(Lives),screenWidth-250,80,textPen);
        if (state == State.GET_READY) {
            canvas.drawText("Click to PLAY!", (screenWidth / 2) - 180, (screenHeight / 2) + 50, textPen);
        }
        else if (state == State.GAME_OVER)
        {
            if (won)
                canvas.drawText("CONGRATULATIONS - Click to start a new game!",(screenWidth/2)-600,(screenHeight/2)+50,textPen);
            else
                canvas.drawText("GAME OVER - Click to start a new game!",(screenWidth/2)-500,(screenHeight/2)+50,textPen);
        }
        else if (state == State.PLAYING)
        {
            for (int i = 0 ; i < TheBricksCollection.getROWS() ; i++)
                for (int j = 0 ; j < TheBricksCollection.getCOLS() ; j++)
                    if(TheBricksCollection.getMyBricks()[i][j].getVisibility())
                    {
                        if (RectF.intersects(ball.getRect(), TheBricksCollection.getMyBricks()[i][j].getRect())) {
                            firstY *= (-1);
                            TheBricksCollection.getMyBricks()[i][j].setInvisible();
                            died_bricks++;
                            Score += (Lives*5);
                            if (died_bricks == TheBricksCollection.getCOLS()*TheBricksCollection.getROWS())
                            {
                                Log.e("mylog", "finish");
                                state = State.GAME_OVER;
                                won = true;
                                new_game = false;
                                init_game();
                                return;
                            }
                        }
                    }

            if (RectF.intersects(ball.getRect(), paddle.getRect()))
            {
                ballY = ballY - ((int)(firstY)+20);
                firstY *= (-1);
            }

            if (ball.getRect().left <= 0)
            {
                firstX *= (-1);
            }

            if (ball.getRect().right >= screenWidth)
            {
                firstX *= (-1);
            }

            if (ball.getRect().top <= 0)
            {
                firstY *= (-1);
            }

            if (ball.getRect().bottom >= screenHeight)
            {
                Lives -= 1;

                if (Lives == 0)
                {
                    state = State.GAME_OVER;
                    won = false;
                    new_game = false;
                }
                else
                {
                    state = State.GET_READY;
                    new_game = false;
                }


                init_game();
                return;
            }


            keep_going(firstX,firstY);

            invalidate();
        }
    }


    public void move_left(){
        if ((paddle_place - MOVEMENT) - 200 > 0)
            paddle_place -= MOVEMENT;

        else
            paddle_place = 200;

        invalidate();
    }

    public void move_right(){
        if ((paddle_place + MOVEMENT) + 200 < screenWidth)
            paddle_place += MOVEMENT;

        else
            paddle_place = screenWidth - 200;

        invalidate();
    }

    public void keep_going(int x, double y){
        ballX += x;
        ballY -= y;
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (state == State.GET_READY)
        {
            state = State.PLAYING;
            new_game = false;
        }
        else if (state == State.GAME_OVER)
        {
            state = State.GET_READY;
            new_game = true;
            init_game();
            return true;
        }

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                paddle_is_moving = true;
                if (event.getX() > screenWidth / 2)
                    new Thread() {
                        @Override
                        public void run() {
                            // Still moving object until an ACTION_UP is done.
                            while (paddle_is_moving) {
                                move_right();

                                // Some sleep time
                                try {
                                    Thread.sleep(50L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                    //move_right();
                else
                    new Thread() {
                        @Override
                        public void run() {
                            // Still moving object until an ACTION_UP is done.
                            while (paddle_is_moving) {
                                move_left();

                                // Some sleep time
                                try {
                                    Thread.sleep(50L);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();
                //move_left();
                break;

            case MotionEvent.ACTION_MOVE:

                break;

            case MotionEvent.ACTION_UP:
                paddle_is_moving = false;
                break;

        }
        return true;
    }

}
