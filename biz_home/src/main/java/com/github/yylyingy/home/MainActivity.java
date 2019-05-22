package com.github.yylyingy.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.yylyingy.common.arouter.HomePage;
import com.github.yylyingy.common.mvp.base.BaseActivity;

@Route(path = HomePage.HOME_PAGE,name = "主页")
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
