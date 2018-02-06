package com.teamtesla.satish.slambook;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DialogBox extends AppCompatActivity {

    Button call_btn, sms_btn;
    private String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_box);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            mobile = (String) b.get("mobile");
        }
        initialise();
    }

    private void initialise() {
        call_btn = (Button) findViewById(R.id.call_button);
        sms_btn = (Button) findViewById(R.id.sms_button);
        call_btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (ActivityCompat.checkSelfPermission(DialogBox.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(new Intent(Intent.ACTION_CALL, Uri.fromParts("tel:", mobile, null)));
           }
        });
        sms_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", mobile, null)));
            }
        });
    }
}
