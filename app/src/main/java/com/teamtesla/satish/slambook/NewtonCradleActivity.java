package com.teamtesla.satish.slambook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.victor.loading.newton.NewtonCradleLoading;

public class NewtonCradleActivity extends AppCompatActivity {

    private NewtonCradleLoading newtonCradleLoading;
//    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newton_cradle);
        newtonCradleLoading = (NewtonCradleLoading) findViewById(R.id.newton_cradle_loading);

        if (newtonCradleLoading.isStart()) {

                    newtonCradleLoading.stop();
                } else {

                    newtonCradleLoading.start();
                }


//        button = (Button) findViewById(R.id.button);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (newtonCradleLoading.isStart()) {
//                    button.setText("start");
//                    newtonCradleLoading.stop();
//                } else {
//                    button.setText("stop");
//                    newtonCradleLoading.start();
//                }
//            }
//        });
    }
}
