package com.developer.kartikraut.axis.LoginSignup;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity implements OnItemSelectedListener {


    CardView reg_bn;
    EditText FName, LName, Email,College, Contact, Password, ConPassword;
    String fname, lname, email,clg, contact, year,password, conpassword;
    TextView tv_go_to_login;
    String reg_url="http://axisvnit.org/api/signup/";
    AlertDialog.Builder builder;
    private ProgressDialog pDialog;
    Dialog login_dialog;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        reg_bn = (CardView) findViewById(R.id.bn_reg);
        FName = (EditText)findViewById(R.id.reg_name);
        LName = (EditText)findViewById(R.id.reg_lastname);
        Email = (EditText)findViewById(R.id.reg_email);
        College = (EditText)findViewById(R.id.reg_clg);
        Contact = (EditText)findViewById(R.id.reg_contact);
        Password = (EditText)findViewById(R.id.reg_password);
        ConPassword = (EditText)findViewById(R.id.reg_con_password);
        spinner = (Spinner)findViewById(R.id.reg_year);
        tv_go_to_login = (TextView)findViewById(R.id.tv_go_to_login);
        builder = new AlertDialog.Builder(Signup.this);
        spinner.setOnItemSelectedListener(this);

        login_dialog = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim));

        final List<String> years = new ArrayList<String>();
        years.add("Select Year");
        years.add("1st");
        years.add("2nd");
        years.add("3rd");
        years.add("4th");
        years.add("None");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,years);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);

        reg_bn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                reg_bn.setCardBackgroundColor(Color.parseColor("#004040"));
                fname = FName.getText().toString();
                lname = LName.getText().toString();
                email = Email.getText().toString();
                clg = College.getText().toString();
                contact = Contact.getText().toString();
                year = spinner.getSelectedItem().toString();
                password = Password.getText().toString();
                conpassword = ConPassword.getText().toString();


                if(fname.equals("")||(lname.equals(""))||(email.equals(""))||(clg.equals(""))||(contact.equals(""))||(year.equals("Select Year"))||(password.equals(""))||(conpassword.equals("")))
                {
                    builder.setTitle("Something went wrong.....");
                    builder.setMessage("Please fill all the details.....");
                    displayAlert("input_error");
                    reg_bn.setCardBackgroundColor(Color.parseColor("#008080"));
                }
                else
                {
                     if(!(password.equals(conpassword))) {
                        builder.setTitle("Something went wrong.....");
                        builder.setMessage("Your passwords are not matching.....");
                        displayAlert("input_error");
                         reg_bn.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                    else {
                         showpDialog();
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
                                                 Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                                 showPopup();
                                                 reg_bn.setVisibility(View.INVISIBLE);
                                                 tv_go_to_login.setVisibility(View.VISIBLE);
                                                 tv_go_to_login.setOnClickListener(new View.OnClickListener() {
                                                     @Override
                                                     public void onClick(View v) {
                                                         Intent i = new Intent(Signup.this,Login.class);
                                                         startActivity(i);
                                                         finish();
                                                     }
                                                 });
                                             }
                                             else
                                             {
                                                 Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                                             }
                                         }
                                         catch (JSONException e) {
                                             e.printStackTrace();
                                         }
                                         hidepDialog();
                                     }
                                 }, new Response.ErrorListener() {

                                     @Override
                                    public void onErrorResponse(VolleyError error) {
                                         Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                                         reg_bn.setCardBackgroundColor(Color.parseColor("#008080"));
                                         hidepDialog();
                                     }
                         }){
                             @Override
                             protected Map<String, String> getParams() throws AuthFailureError {
                                 Map<String, String> params = new HashMap<String, String>();
                                 params.put("f_name",fname);
                                 params.put("l_name",lname);
                                 params.put("email",email);
                                 params.put("clg_name",clg);
                                 params.put("contact_no",contact);
                                 params.put("year",year);
                                 params.put("password1",password);
                                 params.put("password2",conpassword);
                                 return params;
                             }
                         };
                         strObjReq.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                         MySingleton.getInstance(Signup.this).addToRequestque(strObjReq);
                     }
                }
            }
        });

    }

    private void displayAlert(final String code) {
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which){

                if(code.equals("input_error"))
                {
                    Password.setText("");
                    ConPassword.setText("");
                }
                else if(code.equals("reg_success"))
                {
                    Toast.makeText(getApplicationContext(),"REGISTRATION SUCCESSFUL",Toast.LENGTH_SHORT).show();
                }
                else if(code.equals("reg_failed"))
                {
                    FName.setText("");
                    LName.setText("");
                    Email.setText("");
                    Contact.setText("");
                    Password.setText("");
                    ConPassword.setText("");
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showPopup() {
        TextView dialog_close;
        final CardView login_btn;
        login_dialog.setContentView(R.layout.after_signup);
        dialog_close = (TextView)login_dialog.findViewById(R.id.dialog_close);
        login_btn = (CardView)login_dialog.findViewById(R.id.bn_gotologin);
        dialog_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_dialog.dismiss();
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login_dialog.dismiss();
                Intent i = new Intent(Signup.this,Login.class);
                startActivity(i);
            }
        });

        login_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        login_dialog.show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

        private void showpDialog() {
            if (!pDialog.isShowing())
                pDialog.show();
        }

        private void hidepDialog() {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
}
