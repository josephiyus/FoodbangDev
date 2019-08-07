package id.foodbang.common;

import android.support.v7.app.AppCompatActivity;

import java.util.Map;

public abstract class FoodbangAppCompatActivity extends AppCompatActivity {
    public abstract void parentProcess(Map<String,Object> param);
}
