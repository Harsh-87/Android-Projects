package com.example.projects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class Bottle_game extends AppCompatActivity {
    ImageView bottle;
    Boolean spinning = false;
    Random ran = new Random();
    int lastdir = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottle_game);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        bottle = (ImageView) findViewById(R.id.bottle);
        bottle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spin_bottle();
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    public void spin_bottle() {

        if (!spinning) {
            int newdir = ran.nextInt(6000);
            float pivotx = bottle.getWidth() / 2;
            float pivoty = bottle.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastdir, newdir, pivotx, pivoty);
            rotate.setDuration(2000);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            lastdir = newdir;
            bottle.startAnimation(rotate);
        }

    }
}
