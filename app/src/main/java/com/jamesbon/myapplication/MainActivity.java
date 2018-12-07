package com.jamesbon.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.jamesbon.pswedittext.PswEditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PswEditText editText1 = findViewById(R.id.pswEditText1);
        editText1.setItemCount(7)
                .setLineColor(Color.BLUE)
                .setLineWidth(10)
                .setPointColor(Color.RED)
                .setPointWidth(20)
                .setOnPswEditListener(new PswEditText.OnPswEditListener() {
                    @Override
                    public void onDone(CharSequence text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }).updateSetting();

        PswEditText editText2 = findViewById(R.id.pswEditText2);
        editText2.setItemCount(4)
                .setLineColor(Color.RED)
                .setLineWidth(5)
                .setPointColor(Color.GRAY)
                .setPointWidth(20)
                .setOnPswEditListener(new PswEditText.OnPswEditListener() {
                    @Override
                    public void onDone(CharSequence text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }).updateSetting();

        PswEditText editText3 = findViewById(R.id.pswEditText3);
        editText3.setPadding(10, 10, 10, 10);
        editText3.setItemCount(6)
                .setLineColor(Color.CYAN)
                .setLineWidth(2)
                .setPointColor(Color.GREEN)
                .setPointWidth(20)
                .setOnPswEditListener(new PswEditText.OnPswEditListener() {
                    @Override
                    public void onDone(CharSequence text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                }).updateSetting();

        PswEditText editText4 = findViewById(R.id.pswEditText4);
        editText4.setOnPswEditListener(new PswEditText.OnPswEditListener() {
                    @Override
                    public void onDone(CharSequence text) {
                        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
