package com.developer.kartikraut.axis.Events;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

import com.developer.kartikraut.axis.LoginSignup.Login;
import com.developer.kartikraut.axis.LoginSignup.Signup;
import com.developer.kartikraut.axis.R;

public class GoToLogin extends Activity {

    CardView log_button;
    CardView sign_button;
    View view_Screen1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_login);

        log_button = (CardView) findViewById(R.id.log_button);
        sign_button = (CardView) findViewById(R.id.sign_button);
        view_Screen1 = (View)findViewById(R.id.view_Screen1);

        view_Screen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        log_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoToLogin.this, Login.class);
                startActivity(intent);
            }
        });

        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GoToLogin.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}
