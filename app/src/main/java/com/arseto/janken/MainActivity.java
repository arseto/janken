package com.arseto.janken;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand = new Random();

    private final int ROCK = 1;
    private final int PAPER = 2;
    private final int SCISSORS = 3;

    private final int WIN = 1;
    private final int LOSE = 2;
    private final int DRAW = 3;

    private void setImage(int imageViewId, int value) {
        ImageView img = (ImageView) findViewById(imageViewId);
        assert img != null;
        int draw = 0;
        switch (value) {
            case ROCK:
                draw = R.drawable.rock;
                break;
            case PAPER:
                draw = R.drawable.paper;
                break;
            case SCISSORS:
                draw = R.drawable.scissors;
                break;
            default:
                draw = R.drawable.rock;
                break;
        }
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), draw);
        img.setImageDrawable(drawable);
    }

    private int randomizeOpponent() {
        int max = 3;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    private int calculateResult(int opponent, int yourChoice) {
        int result = yourChoice - opponent;
        int state;

        switch (result) {
            case -2:
                state = WIN;
                break;
            case -1:
                state = LOSE;
                break;
            case 0:
                state = DRAW;
                break;
            case 1:
                state = WIN;
                break;
            case 2:
                state = LOSE;
                break;
            default:
                state = DRAW;
                break;
        }

        return state;
    }

    final int[] yourChoice = {0};

    private void showChoiceDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Make your choice")
                .setItems(new String[] {"Rock", "Paper", "Scissors"}, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                yourChoice[0] = ROCK;
                                break;
                            case 1:
                                yourChoice[0] = PAPER;
                                break;
                            case 2:
                                yourChoice[0] = SCISSORS;
                                break;
                            default:
                                yourChoice[0] = ROCK;
                        }
                        setImage(R.id.you_iv, yourChoice[0]);
                    }
                });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

    private void showResultAlert(int state) {
        String message;
        switch (state) {
            case DRAW:
                message = "Draw";
                break;
            case WIN:
                message = "You Win!";
                break;
            case LOSE:
                message = "You Lose...";
                break;
            default:
                message = "Draw";
        }

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Result");
        alertBuilder.setMessage(message);
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();
    }

    private View.OnClickListener getStartBtnOnClickListener() {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int opponent = randomizeOpponent();
                setImage(R.id.opponent_iv, opponent);

                int state = calculateResult(opponent, yourChoice[0]);
                showResultAlert(state);

            }
        };
    }

    private View.OnClickListener getYouIvOnClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChoiceDialog();
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_btn = (Button) findViewById(R.id.start_btn);
        assert start_btn != null;
        start_btn.setOnClickListener(getStartBtnOnClickListener());

        ImageView you_iv = (ImageView) findViewById(R.id.you_iv);
        assert you_iv != null;
        you_iv.setOnClickListener(getYouIvOnClickListener());
    }

}
