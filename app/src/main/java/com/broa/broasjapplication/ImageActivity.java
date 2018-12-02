package com.broa.broasjapplication;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class ImageActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        imageView=findViewById(R.id.ImageBroa);
        button=findViewById(R.id.btn_Imagreview);


    }

    public void ImageView(View view) {
        imageView.setImageResource(R.drawable.book1);

    }
}
