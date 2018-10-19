package com.developer.kartikraut.axis.LoginSignup;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.Axis_Home.Home;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    TextView reg_text;
    CardView bn_login;
    EditText EmailId,Password;
    String emailId,password;
    AlertDialog.Builder builder;
    Intent intent;
    String reg_url="http://axisvnit.org/api/login/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Constants.instance(this.getApplicationContext());
        builder = new AlertDialog.Builder(Login.this);
        bn_login = (CardView)findViewById(R.id.bn_login);
        EmailId = (EditText)findViewById(R.id.login_name);
        Password = (EditText)findViewById(R.id.login_password);
        bn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bn_login.setCardBackgroundColor(Color.parseColor("#004040"));

            //    Toast.makeText(getApplicationContext(),emailId,Toast.LENGTH_SHORT).show();
            //    Toast.makeText(getApplicationContext(),password,Toast.LENGTH_SHORT).show();

                emailId = EmailId.getText().toString();
                password = Password.getText().toString();

                if (emailId.equals("") || (password.equals(""))) {
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid username and password...");
                    bn_login.setCardBackgroundColor(Color.parseColor("#008080"));
                }
                else
                {
                    StringRequest strObjReq = new StringRequest(Request.Method.POST, reg_url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jObj = new JSONObject(response);
                                        boolean error = jObj.getBoolean("error");
                                        String message = jObj.getString("message");
                                        if(!error)
                                        {
                                            String name = jObj.getString("f_name");
                                            String axisId = jObj.getString("axis_id");
                                            String email = jObj.getString("email");
                                            Constants.instance(Login.this);
                                            Constants.instance().storeValueBool("LOGIN",true);
                                            Constants.instance().storeValueString("USER_NAME",name);
                                            Constants.instance().storeValueString("AXISID",axisId);
                                            Constants.instance().storeValueString("EMAIL",email);
                                            intent = new Intent(Login.this, Home.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                        else
                                        {
                                            builder.setTitle("Wrong Credentials");
                                            displayAlert(message);
                                            bn_login.setCardBackgroundColor(Color.parseColor("#008080"));
                                        }
                                    }
                                    catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                            bn_login.setCardBackgroundColor(Color.parseColor("#008080"));
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String,String> params = new HashMap<String,String>();
                            params.put("email",emailId);
                            params.put("password",password);
                            return params;
                        }
                    };
                    MySingleton.getInstance(Login.this).addToRequestque(strObjReq);
                }
            }
        });

        reg_text = (TextView)findViewById(R.id.reg_text);
        reg_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
    }

    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Password.setText("");
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
