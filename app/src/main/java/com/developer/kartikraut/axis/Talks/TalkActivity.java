package com.developer.kartikraut.axis.Talks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.developer.kartikraut.axis.Events.EventsActivity;
import com.developer.kartikraut.axis.Events.GoToLogin;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

public class TalkActivity extends AppCompatActivity {

    TalkClass talkClass;

    ImageView talk_backd;

    TextView talk_desc,talk_venue,talk_date,talk_time,talk_field_date,talk_field_time,talk_is_reg;
    CardView reg_talk;
    private ProgressDialog pDialog;
    String id,axisId;
    String talk_reg_link = "http://axisvnit.org/api/register/talk/";
    String guest_reg_link = "http://axisvnit.org/api/register/guest_lecture/";
    String registered_url_talk = "http://axisvnit.org/api/registered/talks/";
    String registered_url_guest = "http://axisvnit.org/api/registered/guest_lectures/";
    Boolean login;
    final boolean[] is_reg = new boolean[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talk);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Constants.instance(this.getApplicationContext());
        login = (Constants.instance().fetchValueBool("LOGIN"));
        axisId = Constants.instance().fetchValueString("AXISID");

        talk_backd = (ImageView)findViewById(R.id.talk_backd);
        talk_desc = (TextView)findViewById(R.id.talk_desc);
        talk_venue = (TextView)findViewById(R.id.talk_venue);
        talk_date = (TextView)findViewById(R.id.talk_date);
        talk_time = (TextView)findViewById(R.id.talk_time);
        talk_field_date = (TextView)findViewById(R.id.talk_field_date);
        talk_field_time = (TextView)findViewById(R.id.talk_field_time);
        reg_talk = (CardView)findViewById(R.id.reg_talk);
        talk_is_reg = (TextView)findViewById(R.id.talk_is_reg);

        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            talkClass = (TalkClass) extras.getSerializable("talk");
            this.setTitle(talkClass.getName());
            id = Integer.toString(talkClass.getId());
            talk_desc.setText(talkClass.getDescription());
            talk_venue.setText(talkClass.getVenue());
            String date = talkClass.getDate();
            String time = talkClass.getTime();
            if ((date.equals("")) || (time.equals(""))) {
                talk_date.setVisibility(View.INVISIBLE);
                talk_time.setVisibility(View.INVISIBLE);
                talk_field_time.setVisibility(View.INVISIBLE);
                talk_field_date.setVisibility(View.INVISIBLE);
            } else {
                talk_date.setText(date);
                talk_time.setText(time);
            }
            Glide.with(this).load(talkClass.getImageurl())
                    .into(talk_backd);

            final int is_talk = talkClass.getIs_talk();
            if (is_talk == 3) {
                reg_talk.setVisibility(View.GONE);
            }
            else {
                String date_event = talkClass.getDate();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date_today = new Date();
                Date date_of_event = null;
                try {
                    date_of_event = dateFormat.parse(String.valueOf(date_event));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (date_today.compareTo(date_of_event) > 0) {
                    reg_talk.setVisibility(View.INVISIBLE);
                    talk_is_reg.setVisibility(View.VISIBLE);
                    talk_is_reg.setText("Registrations Closed");
                }
                else
                {
                       is_talk_registered();
                       is_guest_registered();
                }

                is_talk_registered();
                is_guest_registered();

                reg_talk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reg_talk.setCardBackgroundColor(Color.parseColor("#004040"));
                        if (login == false) {
                            reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                            Intent i = new Intent(TalkActivity.this, GoToLogin.class);
                            startActivity(i);
                        }
                        else {
                            if (is_talk == 1) {
                                talk_registration();
                            }
                            else if (is_talk == 2) {
                                guest_registration();
                            }
                        }
                    }
                });

            }
        }
    }

    private void is_guest_registered() {
        showpDialog();
        String registered_url1 = registered_url_guest + axisId + "-" + id + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, registered_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            is_reg[0] = jsonObject.getBoolean("is_registered");
                            if(is_reg[0]==true)
                            {
                                Toast.makeText(getApplicationContext(),"You have already registered for the Guest Lecture",Toast.LENGTH_SHORT).show();
                                reg_talk.setVisibility(View.GONE);
                                talk_is_reg.setVisibility(View.VISIBLE);
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
        MySingleton.getInstance(TalkActivity.this).addToRequestque(stringRequest);
    }

    private void is_talk_registered() {
        showpDialog();
        String registered_url1 = registered_url_talk + axisId + "-" + id + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, registered_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            is_reg[0] = jsonObject.getBoolean("is_registered");
                            if(is_reg[0]==true)
                            {
                                Toast.makeText(getApplicationContext(),"You have already registered for the talk",Toast.LENGTH_SHORT).show();
                                reg_talk.setVisibility(View.GONE);
                                talk_is_reg.setVisibility(View.VISIBLE);
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
        MySingleton.getInstance(TalkActivity.this).addToRequestque(stringRequest);
    }


    private void talk_registration() {
        showpDialog();
        StringRequest talk_req = new StringRequest(Request.Method.POST, talk_reg_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    String message = jsonObject.getString("message");
                    if(!error)
                    {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        reg_talk.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                } catch (JSONException e) {
                    reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                    e.printStackTrace();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("axis_id",axisId);
                params.put("talk_id",id);
                return params;
            }
        };
        talk_req.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(TalkActivity.this).addToRequestque(talk_req);
    }

    private void guest_registration() {
        showpDialog();
        StringRequest guest_req = new StringRequest(Request.Method.POST, guest_reg_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    String message = jsonObject.getString("message");
                    if(!error)
                    {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        reg_talk.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                        reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                } catch (JSONException e) {
                    reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                reg_talk.setCardBackgroundColor(Color.parseColor("#008080"));
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("axis_id",axisId);
                params.put("guest_lecture_id",id);
                return params;
            }
        };
        guest_req.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(TalkActivity.this).addToRequestque(guest_req);
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
