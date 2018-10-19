package com.developer.kartikraut.axis.Workshops;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Events.GoToLogin;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class WSActivity extends AppCompatActivity {

    WSClass wsClass;
    private ProgressDialog pDialog;
    TextView ws_desc,ws_paylink,ws_is_reg;
    ImageView ws_backd;
    Boolean reg_closed,login;
    String axisId,wsId;
    CardView btn_ws_reg;
    String ws_reg_link = "http://axisvnit.org/api/register/workshop/";
    String ws_registered_link = "http://axisvnit.org/api/registered/workshops/";
    final boolean[] is_reg = new boolean[1];
    String pay_link;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ws);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Constants.instance(this.getApplicationContext());
        login = (Constants.instance().fetchValueBool("LOGIN"));

        ws_backd = (ImageView)findViewById(R.id.ws_backd);
        ws_desc = (TextView)findViewById(R.id.ws_desc);
        ws_paylink = (TextView)findViewById(R.id.ws_paylink);
        btn_ws_reg = (CardView)findViewById(R.id.btn_ws_reg);
        ws_is_reg = (TextView)findViewById(R.id.tv_ws_is_reg);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            wsClass = (WSClass) extras.getSerializable("WS");
            setTitle(wsClass.getTitle());

            axisId = Constants.instance().fetchValueString("AXISID");
            wsId = Integer.toString(wsClass.getId());

            ws_desc.setText(wsClass.getDescription());
            pay_link = wsClass.getPayment_link();
        //    ws_paylink.setText(wsClass.getPayment_link());
            reg_closed = wsClass.getReg_closed();

            if(reg_closed==false)
            {
                if(login==true) {
                    is_ws_reg();
                }
                btn_ws_reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        btn_ws_reg.setCardBackgroundColor(Color.parseColor("#004040"));
                        if(login==false)
                        {
                            Intent intent = new Intent(WSActivity.this, GoToLogin.class);
                            startActivity(intent);
                            btn_ws_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                        }
                        else
                        {
                            reg_ws();
                        }
                    }
                });
            }
            else
            {
                btn_ws_reg.setVisibility(View.INVISIBLE);
                ws_is_reg.setVisibility(View.INVISIBLE);
                ws_paylink.setText("Registrations Closed");
                ws_paylink.setVisibility(View.VISIBLE);
            }

            Glide.with(this).load(wsClass.getimageId())
                    .into(ws_backd);
        }

        if(login==false){
            if(reg_closed==false) {
                ws_paylink.setText("Please login and register for the workshop to pay.");
            }
            else
            {
                ws_paylink.setText("Registrations Closed");
            }
        }
    /*    else {
            if(is_ws_reg();)
            ws_paylink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (reg_closed == false) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(wsClass.getPayment_link()));
                        startActivity(i);
                    }
                }
            });
        }   */

    }

    private void is_ws_reg() {
        showpDialog();
        String registered_url1 = ws_registered_link + axisId + "-" + wsId + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, registered_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            is_reg[0] = jsonObject.getBoolean("is_registered");
                            if(is_reg[0]==true)
                            {
                                Toast.makeText(getApplicationContext(),"You have already registered for the Workshop",Toast.LENGTH_SHORT).show();
                                ws_paylink.setText(pay_link);
                                ws_paylink.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (reg_closed == false) {
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setData(Uri.parse(wsClass.getPayment_link()));
                                            startActivity(i);
                                        }
                                    }
                                });
                                btn_ws_reg.setVisibility(View.GONE);
                                ws_is_reg.setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                ws_paylink.setText("Please register to pay");
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
                hidepDialog();
            }
        });
        MySingleton.getInstance(WSActivity.this).addToRequestque(stringRequest);
    }

    private void reg_ws() {
        showpDialog();
        StringRequest ws_reg_request = new StringRequest(Request.Method.POST, ws_reg_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error_create = jsonObject.getBoolean("error");
                    String ws_msg = jsonObject.getString("message");
                    if(!error_create)
                    {
                        Toast.makeText(getApplicationContext(),ws_msg,Toast.LENGTH_SHORT).show();
                        btn_ws_reg.setVisibility(View.INVISIBLE);
                        ws_paylink.setText(pay_link);
                        ws_paylink.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (reg_closed == false) {
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(wsClass.getPayment_link()));
                                    startActivity(i);
                                }
                            }
                        });
                     //   ws_is_reg.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),ws_msg,Toast.LENGTH_SHORT).show();
                        btn_ws_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                } catch (JSONException e) {
                    btn_ws_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_ws_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("workshop_id",wsId);
                params.put("axis_id",axisId);
                return params;
            }
        };
        ws_reg_request.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(WSActivity.this).addToRequestque(ws_reg_request);
        hidepDialog();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
