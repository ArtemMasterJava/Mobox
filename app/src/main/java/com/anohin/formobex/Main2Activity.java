package com.anohin.formobex;

import com.anohin.formobex.model.utilities.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Glide.with(this).load(Constants.PHOTO_URL + getIntent().getStringExtra("image"))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) findViewById(R.id.imageView));
    }
}
