package com.anohin.formobex;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.anohin.formobex.model.utilities.Constants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Main2Activity extends AppCompatActivity {
    TextView mTextView;
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main2);

        mTextView = (TextView) findViewById(R.id.infoTextView);
        mTextView.setText(getIntent().getStringExtra("info"));

        Glide.with(this).load(Constants.PHOTO_URL + getIntent().getStringExtra("image"))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into((ImageView) findViewById(R.id.imageView));
    }
}
