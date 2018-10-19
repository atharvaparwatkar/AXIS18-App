package com.developer.kartikraut.axis.Events;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventsActivity extends AppCompatActivity {

    TextView em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone;
    TextView desc,rules,ps,rules_link,ps_link,tv_is_reg;
    ImageView img_bkgd;
    Boolean login;
    String team_name,team_password;
    String join_name,join_password,date,last_date;
    CardView event_reg_btn;
    String single_event_url = "http://axisvnit.org/api/register/event/single/";
    String team_event_url = "http://axisvnit.org/api/register/event/team/";
    String registered_url = "http://axisvnit.org/api/registered/events/";
    TextView setA,setB,setC,setD,setE,top50;
    TextView devB,devC,devD;
    TextView event_rule_link2,event_rule_link3;

    private ProgressDialog pDialog;

    Dialog dialog_create_join,dialog_create,dialog_join;
    AlertDialog.Builder builder;

    String axisId;
    String eventId;
    final boolean[] is_reg = new boolean[1];

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Constants.instance(this.getApplicationContext());
        login = (Constants.instance().fetchValueBool("LOGIN"));

        builder = new AlertDialog.Builder(this);
        dialog_create_join = new Dialog(this);
        dialog_create = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim));
        dialog_join = new Dialog(new ContextThemeWrapper(this,R.style.DialogSlideAnim));

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        img_bkgd = (ImageView)findViewById(R.id.event_backd);
        desc = (TextView)findViewById(R.id.event_det);
        rules = (TextView)findViewById(R.id.event_rules);
        ps = (TextView)findViewById(R.id.event_ps);
        rules_link = (TextView)findViewById(R.id.event_rule_link);
        ps_link = (TextView)findViewById(R.id.event_ps_link);
        em1_name = (TextView)findViewById(R.id.em1_name);
        em2_name = (TextView)findViewById(R.id.em2_name);
        em3_name = (TextView)findViewById(R.id.em3_name);
        em4_name = (TextView)findViewById(R.id.em4_name);
        em5_name = (TextView)findViewById(R.id.em5_name);
        em6_name = (TextView)findViewById(R.id.em6_name);
        em1_phone = (TextView)findViewById(R.id.em1_phone);
        em2_phone = (TextView)findViewById(R.id.em2_phone);
        em3_phone = (TextView)findViewById(R.id.em3_phone);
        em4_phone = (TextView)findViewById(R.id.em4_phone);
        em5_phone = (TextView)findViewById(R.id.em5_phone);
        em6_phone = (TextView)findViewById(R.id.em6_phone);
        event_reg_btn = (CardView)findViewById(R.id.event_reg_btn);
        tv_is_reg = (TextView)findViewById(R.id.tv_is_reg);
        setA = (TextView)findViewById(R.id.dex_setA_link);
        setB = (TextView)findViewById(R.id.dex_setB_link);
        setC = (TextView)findViewById(R.id.dex_setC_link);
        setD = (TextView)findViewById(R.id.dex_setD_link);
        setE = (TextView)findViewById(R.id.dex_setE_link);
        top50 = (TextView)findViewById(R.id.dex_top_link);
        devB = (TextView)findViewById(R.id.devB);
        devC = (TextView)findViewById(R.id.devC);
        devD = (TextView)findViewById(R.id.devD);
        event_rule_link2 = (TextView)findViewById(R.id.event_rule_link2);
        event_rule_link3 = (TextView)findViewById(R.id.event_rule_link3);



        Bundle extras  = getIntent().getExtras();
        if(extras!=null)
        {
            final EventsClass eventObj = (EventsClass) extras.getSerializable("eventObj");

            axisId = Constants.instance().fetchValueString("AXISID");
            eventId = Integer.toString(eventObj.getId());

            setTitle(eventObj.getName().toUpperCase());

            String desc1 = eventObj.getDescription();

            if(eventObj.getName().toLowerCase().equals("dexter"))
            {
                setA.setVisibility(View.VISIBLE);
                setB.setVisibility(View.VISIBLE);
                setC.setVisibility(View.VISIBLE);
                setD.setVisibility(View.VISIBLE);
                setE.setVisibility(View.VISIBLE);
                top50.setVisibility(View.VISIBLE);

                final String setA_link = extractUrlsArray(desc1,0);

                setA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setA.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(setA_link));
                        startActivity(intent);
                    }
                });

                final String setB_link = extractUrlsArray(desc1,1);
                setB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setB.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(setB_link));
                        startActivity(intent);
                    }
                });

                final String setC_link = extractUrlsArray(desc1,2);
                setC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setC.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(setC_link));
                        startActivity(intent);
                    }
                });

                final String setD_link = extractUrlsArray(desc1,3);
                setD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setD.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(setD_link));
                        startActivity(intent);
                    }
                });

                final String setE_link = extractUrlsArray(desc1,4);
                setE.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setE.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(setE_link));
                        startActivity(intent);
                    }
                });

                final String toppers_link = extractUrlsArray(desc1,5);
                top50.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        top50.setTextColor(Color.parseColor("#ffffff"));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(toppers_link));
                        startActivity(intent);
                    }
                });

            }

            desc1 = html2text(desc1);
            desc.setText(desc1);

            String rules1 = eventObj.getRules();
            if((eventObj.getName().toLowerCase().equals("turboflux"))||(eventObj.getSlug().equals("electroblitz"))||(eventObj.getSlug().equals("freak-o-matix"))||(eventObj.getSlug().equals("221b-baker-street")))
            {
                event_rule_link2.setText("Test");
                final String rule1 = extractUrlsArray(rules1,0);
                if(extractUrlsArray(rules1,1).equals("no_link")) {
                    event_rule_link2.setVisibility(View.INVISIBLE);
                }
                else {
                    event_rule_link2.setVisibility(View.VISIBLE);
                    final String test = extractUrlsArray(rules1, 1);
                    event_rule_link2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            event_rule_link2.setTextColor(Color.parseColor("#ffffff"));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(test));
                            startActivity(i);
                        }
                    });
                }

                rules_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rules_link.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(rule1));

                        startActivity(i);

                    }
                });

                rules1 = html2text(rules1);
                rules.setText(rules1);
            }
            else {
                final String rules1_link = extractUrls(rules1);
                rules1 = html2text(rules1);
                rules.setText(rules1);
                if (rules1_link.equals("no_link")) {
                    rules_link.setVisibility(View.INVISIBLE);
                } else {
                    rules_link.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            rules_link.setTextColor(Color.parseColor("#ffffff"));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(rules1_link));
                            startActivity(i);
                        }
                    });
                }
            }

            String ps1 = eventObj.getPs();
            if(eventObj.getName().toLowerCase().equals("devise"))
            {

                devB.setVisibility(View.VISIBLE);
                devC.setVisibility(View.VISIBLE);
                devD.setVisibility(View.VISIBLE);

                ps_link.setText("AR1");
                devB.setText("AR2");
                devC.setText("DS1");
                devD.setText("DS2");

                final String dev1 = extractUrlsArray(ps1,0);
                final String dev2 = extractUrlsArray(ps1,1);
                final String dev3 = extractUrlsArray(ps1,2);
                final String dev4 = extractUrlsArray(ps1,3);

                ps_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ps_link.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev1));
                        startActivity(i);
                    }
                });

                devB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        devB.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev2));
                        startActivity(i);
                    }
                });

                devC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        devC.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev3));
                        startActivity(i);
                    }
                });

                devD.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        devD.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev4));
                        startActivity(i);
                    }
                });

                ps1 = html2text(ps1);
                ps.setText(ps1);

            }
            else if(eventObj.getSlug().equals("robo-terraformer"))
            {
                devB.setVisibility(View.VISIBLE);
                ps_link.setText("Problem Statement");
                devB.setText("Arena Video");
                final String dev1 = extractUrlsArray(ps1,0);
                final String dev2 = extractUrlsArray(ps1,1);
                ps_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ps_link.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev1));
                        startActivity(i);
                    }
                });

                devB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        devB.setTextColor(Color.parseColor("#ffffff"));
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(dev2));
                        startActivity(i);
                    }
                });
                ps1 = html2text(ps1);
                ps.setText(ps1);
            }
            else {
                final String ps1_link = extractUrls(ps1);
                ps1 = html2text(ps1);
                ps.setText(ps1);
                if (ps1_link.equals("no_link")) {
                    ps_link.setVisibility(View.INVISIBLE);
                } else {
                    ps_link.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ps_link.setTextColor(Color.parseColor("#ffffff"));
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(ps1_link));
                            startActivity(i);
                        }
                    });
                }
            }

            String date_event = eventObj.getLast_date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date_today = new Date();
            Date date_of_event = null;
            try {
                date_of_event = dateFormat.parse(String.valueOf(date_event));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(date_today.compareTo(date_of_event)>0)
            {
                event_reg_btn.setVisibility(View.INVISIBLE);
                tv_is_reg.setVisibility(View.VISIBLE);
                tv_is_reg.setText("Registrations Closed");
            }
            else
            {
                if(login==true)
                {
                    is_reg_event();
                }
            }


            if(!(eventObj.getEm1_name().equals(""))&&(!(eventObj.getEm1_phone().equals(""))))
            {
                em1_name.setText(eventObj.getEm1_name());
                em1_phone.setText(eventObj.getEm1_phone());
                em1_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm1_phone());
                    }
                });
            }
            else if((eventObj.getEm1_name().equals(""))&&((eventObj.getEm1_phone().equals(""))))
            {
                em1_name.setVisibility(View.INVISIBLE);
                em1_phone.setVisibility(View.INVISIBLE);
            }


            if(!(eventObj.getEm2_name().equals(""))&&(!(eventObj.getEm2_phone().equals(""))))
            {
                em2_name.setText(eventObj.getEm2_name());
                em2_phone.setText(eventObj.getEm2_phone());
                em2_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm2_phone());
                    }
                });
            }
            else if((eventObj.getEm2_name().equals(""))&&((eventObj.getEm2_phone().equals(""))))
            {
                em2_name.setVisibility(View.INVISIBLE);
                em2_phone.setVisibility(View.INVISIBLE);
            }

            if(!(eventObj.getEm3_name().equals(""))&&(!(eventObj.getEm3_phone().equals(""))))
            {
                em3_name.setText(eventObj.getEm3_name());
                em3_phone.setText(eventObj.getEm3_phone());
                em3_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm3_phone());
                    }
                });
            }
            else if((eventObj.getEm3_name().equals(""))&&((eventObj.getEm3_phone().equals(""))))
            {
                em3_name.setVisibility(View.INVISIBLE);
                em3_phone.setVisibility(View.INVISIBLE);
            }

            if(!(eventObj.getEm4_name().equals(""))&&(!(eventObj.getEm4_phone().equals(""))))
            {
                em4_name.setText(eventObj.getEm4_name());
                em4_phone.setText(eventObj.getEm4_phone());
                em4_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm4_phone());
                    }
                });
            }
            else if((eventObj.getEm4_name().equals(""))&&((eventObj.getEm4_phone().equals(""))))
            {
                em4_name.setVisibility(View.INVISIBLE);
                em4_phone.setVisibility(View.INVISIBLE);
            }

            if(!(eventObj.getEm5_name().equals(""))&&(!(eventObj.getEm5_phone().equals(""))))
            {
                em5_name.setText(eventObj.getEm5_name());
                em5_phone.setText(eventObj.getEm5_phone());
                em5_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm5_phone());
                    }
                });
            }
            else if((eventObj.getEm5_name().equals(""))&&((eventObj.getEm5_phone().equals(""))))
            {
                em5_name.setVisibility(View.INVISIBLE);
                em5_phone.setVisibility(View.INVISIBLE);
            }

            if(!(eventObj.getEm6_name().equals(""))&&(!(eventObj.getEm6_phone().equals(""))))
            {
                em6_name.setText(eventObj.getEm6_name());
                em6_phone.setText(eventObj.getEm6_phone());
                em6_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openDialer(eventObj.getEm6_phone());
                    }
                });
            }
            else if((eventObj.getEm6_name().equals(""))&&((eventObj.getEm6_phone().equals(""))))
            {
                em6_name.setVisibility(View.INVISIBLE);
                em6_phone.setVisibility(View.INVISIBLE);
            }

            Glide.with(this).load(eventObj.getImage())
                    .into(img_bkgd);



            if((eventObj.getSlug().equals("junior-scientist"))||(eventObj.getName().equals("dexter"))||(eventObj.getName().equals("informals")))
            {
                event_reg_btn.setVisibility(View.INVISIBLE);
            }

            event_reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    event_reg_btn.setCardBackgroundColor(Color.parseColor("#004040"));
                    if(login==false)
                    {
                        Intent intent = new Intent(EventsActivity.this, GoToLogin.class);
                        startActivity(intent);
                        event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                    else
                    {
                        if(is_reg[0]==true)
                        {
                            Toast.makeText(getApplicationContext(),"You have already registered for the event",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(eventObj.getMax_team_mem()==1)
                            {
                                showpDialog();
                                StringRequest singleEventRequest = new StringRequest(Request.Method.POST, single_event_url,
                                        new Response.Listener<String>() {
                                            @Override
                                            public void onResponse(String response) {
                                                try {
                                                    JSONObject jsonObject = new JSONObject(response);
                                                    Boolean event_error = jsonObject.getBoolean("error");
                                                    String event_msg = jsonObject.getString("message");
                                                    if(event_error==false)
                                                    {
                                                        Toast.makeText(getApplicationContext(),event_msg,Toast.LENGTH_SHORT).show();
                                                        event_reg_btn.setVisibility(View.GONE);
                                                    }
                                                    else
                                                    {
                                                        Toast.makeText(getApplicationContext(),event_msg,Toast.LENGTH_SHORT).show();
                                                        event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
                                                    }

                                                } catch (JSONException e) {
                                                    event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
                                                    e.printStackTrace();
                                                }
                                                hidepDialog();
                                            }
                                        }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
                                        Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                                        hidepDialog();
                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        Map<String, String> params = new HashMap<String, String>();
                                        params.put("axis_id",axisId);
                                        params.put("event_id",eventId);
                                        return params;
                                    }
                                };
                                singleEventRequest.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                                MySingleton.getInstance(EventsActivity.this).addToRequestque(singleEventRequest);
                            }
                            else {
                                hidepDialog();
                                showPopup1();
                            }
                        }
                    }
                }
            });
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please make sure your device is connected to the internet",Toast.LENGTH_LONG).show();
            event_reg_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),"Please make sure your device is connected to the internet",Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    private void is_reg_event() {
        showpDialog();
        String registered_url1 = registered_url + axisId + "-" + eventId + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, registered_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            is_reg[0] = jsonObject.getBoolean("is_registered");
                            if(is_reg[0]==true)
                            {
                                Toast.makeText(getApplicationContext(),"You have already registered for the event",Toast.LENGTH_SHORT).show();
                                event_reg_btn.setVisibility(View.GONE);
                                tv_is_reg.setVisibility(View.VISIBLE);
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
        MySingleton.getInstance(EventsActivity.this).addToRequestque(stringRequest);
    }

    public static String extractUrls(String text)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);
        String retval;
        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        if(containedUrls.size()!=0) {
            retval = containedUrls.get(0);
        }
        else
        {
            retval = "no_link";
        }
        return retval;
    }

    public String extractUrlsArray(String text,int position)
    {
        List<String> containedUrls = new ArrayList<String>();
        String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
        Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);
        String retval;
        while (urlMatcher.find())
        {
            containedUrls.add(text.substring(urlMatcher.start(0),
                    urlMatcher.end(0)));
        }

        if(position>=containedUrls.size())
        {
            return "no_link";
        }
        else {
            return containedUrls.get(position);
        }
    }

    private String html2text(String response) {
        return Jsoup.parse(response).text();
    }

    private void openDialer(String ph_no) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ph_no));
        startActivity(intent);
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

    public void displayAlert(String message)
    {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showPopup1() {
        TextView txtclose;
        final CardView create_btn,join_btn;
        dialog_create_join.setContentView(R.layout.create_join_team_popup);
        txtclose = (TextView)dialog_create_join.findViewById(R.id.txtclose);
        create_btn = (CardView)dialog_create_join.findViewById(R.id.create_btn);
        join_btn = (CardView)dialog_create_join.findViewById(R.id.join_btn);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_create_join.dismiss();
                event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
            }
        });

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup2();
                create_btn.setCardBackgroundColor(Color.parseColor("#004040"));
                dialog_create_join.dismiss();
            }
        });

        join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup3();
                join_btn.setCardBackgroundColor(Color.parseColor("#004040"));
                dialog_create_join.dismiss();
            }
        });

        dialog_create_join.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_create_join.show();

    }

    private void showPopup2() {
        TextView txtclose1;
        final EditText create_team_name,create_team_password;
        final CardView create_btn1;
        dialog_create.setContentView(R.layout.create_popup);
        txtclose1 = (TextView)dialog_create.findViewById(R.id.txtclose1);
        create_team_name = (EditText)dialog_create.findViewById(R.id.create_team_name);
        create_team_password = (EditText)dialog_create.findViewById(R.id.create_team_password);
        create_btn1 = (CardView)dialog_create.findViewById(R.id.create_btn1);
        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_create.dismiss();
                event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
            }
        });

        create_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpDialog();
                create_btn1.setCardBackgroundColor(Color.parseColor("#004040"));
                team_name = create_team_name.getText().toString();
                team_password = create_team_password.getText().toString();
                if((team_name.equals(""))||(team_password.equals("")))
                {
                    hidepDialog();
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid team name and password...");
                    create_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                }
                else {

                    StringRequest team_stringRequest = new StringRequest(Request.Method.POST, team_event_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Boolean error_create = jsonObject.getBoolean("error");
                                String msg_create = jsonObject.getString("message");
                                if(!error_create)
                                {
                                    dialog_create.dismiss();
                                    Toast.makeText(getApplicationContext(),msg_create,Toast.LENGTH_SHORT).show();
                                    event_reg_btn.setVisibility(View.INVISIBLE);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),msg_create,Toast.LENGTH_SHORT).show();
                                    create_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                                }
                            } catch (JSONException e) {
                                create_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                                e.printStackTrace();
                            }
                            hidepDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            create_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                            Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                            hidepDialog();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            String type = "create";
                            params.put("type",type);
                            params.put("team_name",team_name);
                            params.put("team_password",team_password);
                            params.put("event_id",eventId);
                            params.put("axis_id",axisId);
                            return params;
                        }
                    };
                    team_stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton.getInstance(EventsActivity.this).addToRequestque(team_stringRequest);

                }
            }
        });
        dialog_create.getWindow().setGravity(Gravity.BOTTOM);
        dialog_create.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_create.show();

    }

    private void showPopup3() {
        TextView txtclose1;
        final EditText create_team_name,create_team_password;
        final CardView join_btn1;
        dialog_join.setContentView(R.layout.join_popup);
        txtclose1 = (TextView)dialog_join.findViewById(R.id.txtclose1);
        create_team_name = (EditText)dialog_join.findViewById(R.id.create_team_name);
        create_team_password = (EditText)dialog_join.findViewById(R.id.create_team_password);
        join_btn1 = (CardView)dialog_join.findViewById(R.id.join_btn1);
        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_join.dismiss();
                event_reg_btn.setCardBackgroundColor(Color.parseColor("#008080"));
            }
        });

        join_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpDialog();
                join_btn1.setCardBackgroundColor(Color.parseColor("#004040"));
                join_name = create_team_name.getText().toString();
                join_password = create_team_password.getText().toString();
                if((join_name.equals(""))||(join_password.equals("")))
                {
                    hidepDialog();
                    builder.setTitle("Something went wrong");
                    displayAlert("Enter a valid team name and password...");
                    join_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                }
                else {
                    StringRequest team_joinstringRequest = new StringRequest(Request.Method.POST, team_event_url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                Boolean error_create = jsonObject.getBoolean("error");
                                String msg_create = jsonObject.getString("message");
                                if(!error_create)
                                {
                                    dialog_join.dismiss();
                                    Toast.makeText(getApplicationContext(),msg_create,Toast.LENGTH_SHORT).show();
                                    event_reg_btn.setVisibility(View.INVISIBLE);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),msg_create,Toast.LENGTH_SHORT).show();
                                    join_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                                }
                            } catch (JSONException e) {
                                join_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                                e.printStackTrace();
                            }
                            hidepDialog();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            join_btn1.setCardBackgroundColor(Color.parseColor("#008080"));
                            Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                            hidepDialog();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<String, String>();
                            String type = "join";
                            params.put("type",type);
                            params.put("team_name",join_name);
                            params.put("team_password",join_password);
                            params.put("event_id",eventId);
                            params.put("axis_id",axisId);
                            return params;
                        }
                    };
                    team_joinstringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    MySingleton.getInstance(EventsActivity.this).addToRequestque(team_joinstringRequest);
                }
            }
        });
        dialog_join.getWindow().setGravity(Gravity.BOTTOM);
        dialog_join.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog_join.show();
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
