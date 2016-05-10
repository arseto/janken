package com.arseto.janken;

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

    private int randomize() {
        int max = 3;
        int min = 1;
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start_btn = (Button) findViewById(R.id.start_btn);

        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int random = randomize();
                ImageView img = (ImageView) findViewById(R.id.hand_iv);
                int draw = 0;
                switch (random) {
                    case 1:
                        draw = R.drawable.rock;
                        break;
                    case 2:
                        draw = R.drawable.paper;
                        break;
                    case 3:
                        draw = R.drawable.scissors;
                        break;
                    default:
                        draw = R.drawable.rock;
                        break;
                }
                Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), draw);
                img.setImageDrawable(drawable);
            }
        });
    }
}
