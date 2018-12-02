package com.broa.broasjapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class TabHostActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabhost);

        TabHost host=findViewById(R.id.tabhost);
        host.setup();

        TabHost.TabSpec tab1=host.newTabSpec("publice");
        TabHost.TabSpec tab2=host.newTabSpec("private");
        TabHost.TabSpec tab3=host.newTabSpec("protected");

        tab1.setContent(R.id.tab1);
        tab2.setContent(R.id.tab2);
        tab3.setContent(R.id.tab3);


        tab1.setIndicator("جامد");
        tab2.setIndicator("مایع");
        tab3.setIndicator("گاز");


        host.addTab(tab1);
        host.addTab(tab2);
        host.addTab(tab3);

    }
}
