package com.developer.kartikraut.axis.Techinite;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.developer.kartikraut.axis.Events.GoToLogin;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;
import com.developer.kartikraut.axis.Workshops.WSActivity;
import com.developer.kartikraut.axis.Workshops.WSClass;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TechiActivity extends AppCompatActivity {

    Techie_Class techie_class;
    int id;
    private ProgressDialog pDialog;
    ImageView techi_backd;
    TextView techi_desc,tv_techi_is_reg,speaker_link,talk_time,talk_date,talk_venue;
    CardView btn_techi_reg;
    Boolean reg_closed,login,is_reg_techi;
    String axisId,techi_Id;
    String techi_reg_link=" http://axisvnit.org/api/register/techie_nite/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_techi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        techi_backd = (ImageView)findViewById(R.id.techi_backd);
        techi_desc = (TextView)findViewById(R.id.techi_desc);
        tv_techi_is_reg = (TextView)findViewById(R.id.tv_techi_is_reg);
        btn_techi_reg = (CardView)findViewById(R.id.btn_techi_reg);
        speaker_link = (TextView)findViewById(R.id.speakers_link);
        talk_date = (TextView)findViewById(R.id.talk_date);
        talk_time = (TextView)findViewById(R.id.talk_time);
        talk_venue = (TextView)findViewById(R.id.talk_venue);

        Constants.instance(getApplicationContext());
        login = Constants.instance().fetchValueBool("LOGIN");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            techie_class = (Techie_Class) extras.getSerializable("TECHI");
            setTitle(techie_class.getName().toUpperCase());

            if(techie_class.getId()==1)
            {
                is_reg_techi = Constants.instance(getApplicationContext()).fetchValueBool("TECHI1");
            }
            else
            {
                is_reg_techi = Constants.instance(getApplicationContext()).fetchValueBool("TECHI2");
            }

            id = techie_class.getId();
            axisId = Constants.instance().fetchValueString("AXISID");
            techi_Id = Integer.toString(techie_class.getId());

            String description = techie_class.getDesc();

            final String link = extractUrls(description);

            if(link=="no_link")
            {
                speaker_link.setVisibility(View.INVISIBLE);
            }
            else
            {
                speaker_link.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(link));
                        startActivity(i);
                    }
                });
            }
            description = html2text(description);

            techi_desc.setText(description);

            talk_venue.setText(techie_class.getVenue());
            talk_time.setText(techie_class.getTime());
            talk_date.setText(techie_class.getDate());

            reg_closed = techie_class.getReg_closed();

            if(reg_closed==false)
            {
                if(login==true) {

                      if(is_reg_techi==true)
                      {
                          Toast.makeText(getApplicationContext(),"You have already registered for " + techie_class.getName(),Toast.LENGTH_SHORT).show();
                          btn_techi_reg.setVisibility(View.INVISIBLE);
                          tv_techi_is_reg.setVisibility(View.VISIBLE);
                      }

                }

                btn_techi_reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showpDialog();
                        btn_techi_reg.setCardBackgroundColor(Color.parseColor("#004040"));
                        if(login==false)
                        {
                            hidepDialog();
                            Intent intent = new Intent(TechiActivity.this, GoToLogin.class);
                            startActivity(intent);
                            btn_techi_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                        }
                        else
                        {
                            reg_techi();
                        }
                    }
                });
            }
            else
            {
                btn_techi_reg.setVisibility(View.INVISIBLE);
                tv_techi_is_reg.setText("Registrations Closed");
                tv_techi_is_reg.setVisibility(View.VISIBLE);
            }

            Glide.with(this).load(techie_class.getImage())
                    .into(techi_backd);
        }

    }

    private void reg_techi() {

        StringRequest ws_reg_request = new StringRequest(Request.Method.POST, techi_reg_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    String techi_msg = jsonObject.getString("message");
                    if(!error)
                    {
                        Toast.makeText(getApplicationContext(),techi_msg,Toast.LENGTH_SHORT).show();
                        btn_techi_reg.setVisibility(View.INVISIBLE);
                     //   tv_techi_is_reg.setVisibility(View.VISIBLE);
                        if(id==1)
                        {
                            Constants.instance().storeValueBool("TECHI1",true);
                        }
                        else
                        {
                            Constants.instance().storeValueBool("TECHI2",true);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),techi_msg,Toast.LENGTH_SHORT).show();
                        btn_techi_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                    }
                } catch (JSONException e) {
                    btn_techi_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                    e.printStackTrace();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                btn_techi_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                Toast.makeText(getApplicationContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("tc_id",techi_Id);
                params.put("axis_id",axisId);
                return params;
            }
        };

        MySingleton.getInstance(TechiActivity.this).addToRequestque(ws_reg_request);
    }

    private String html2text(String response) {
        return Jsoup.parse(response).text();
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

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
