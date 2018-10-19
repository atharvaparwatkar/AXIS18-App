package com.developer.kartikraut.axis.Summit;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Events.GoToLogin;
import com.developer.kartikraut.axis.Inaugration.InaugClass;
import com.developer.kartikraut.axis.Inaugration.SliderAdapter;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummitFragment extends Fragment {

    private ViewPager SlideViewPager;
    private LinearLayout DotsLayout;
    private ProgressDialog pDialog;
    private SliderAdapter sliderAdapter;
    CardView inaug_reg;
    String summit_info_link = "http://axisvnit.org/api/summit/?format=json";
    String summit_reg_link = "http://axisvnit.org/api/register/summit/";
    List<InaugClass> inaugList;
    private TextView[] mDots;
    int num_dots;
    Boolean login;
    String axisId;
    String registered_url_summit = "http://axisvnit.org/api/registered/summit/";
    boolean is_reg;

    private ImageView[] dots;



    public SummitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_summit, container, false);
        getActivity().setTitle("TNS");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        SlideViewPager = (ViewPager)view.findViewById(R.id.slideViewPager);
        DotsLayout = (LinearLayout)view.findViewById(R.id.dotsLayout);
        inaug_reg = (CardView)view.findViewById(R.id.summit_reg);

        Constants.instance(this.getContext());
        login = (Constants.instance().fetchValueBool("LOGIN"));

        is_reg = Constants.instance().fetchValueBool("TNS_REG");
        if(is_reg==true)
        {
            inaug_reg.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"You have already registered for the TNS",Toast.LENGTH_SHORT).show();
        }


        inaugList = new ArrayList<>();

        initializeData();

       // check_if_reg();

        SlideViewPager.addOnPageChangeListener(viewListener);

        inaug_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showpDialog();
                inaug_reg.setCardBackgroundColor(Color.parseColor("#004040"));
                if(login==false)
                {
                    hidepDialog();
                    inaug_reg.setCardBackgroundColor(Color.parseColor("#008080"));
                    Intent i = new Intent(getContext(), GoToLogin.class);
                    startActivity(i);
                }
                else {
                    axisId = Constants.instance().fetchValueString("AXISID");
                    register_inaug();
                }
            }
        });

        addDotstoIndicator(0);

        return view;
    }

  /*  private void check_if_reg() {
        showpDialog();
        String registered_url1 = registered_url_summit + axisId + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, registered_url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            is_reg = jsonObject.getBoolean("is_registered");
                            if(is_reg==true)
                            {
                                Toast.makeText(getContext(),"You have already registered for the TNS",Toast.LENGTH_SHORT).show();
                                inaug_reg.setVisibility(View.GONE);
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
                Toast.makeText(getContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });
        MySingleton.getInstance(getContext()).addToRequestque(stringRequest);
    }   */

    private void register_inaug() {
        StringRequest reg_request = new StringRequest(Request.Method.POST, summit_reg_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    String message = jsonObject.getString("message");
                    if(!error)
                    {
                        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                        inaug_reg.setVisibility(View.INVISIBLE);
                        Constants.instance().storeValueBool("TNS_REG",true);
                    }
                    else
                    {
                        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                        inaug_reg.setCardBackgroundColor(Color.parseColor("#004040"));
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
                hidepDialog();
                Toast.makeText(getContext(),
                        "PLEASE MAKE SURE YOUR DEVICE IS CONNECTED TO INTERNET", Toast.LENGTH_SHORT).show();
                inaug_reg.setCardBackgroundColor(Color.parseColor("#008080"));
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("axis_id",axisId);
                return params;
            }
        };
        reg_request.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getContext()).addToRequestque(reg_request);

    }

    private void initializeData() {
        showpDialog();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, summit_info_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray speakers = new JSONArray(response);
                    Constants.instance().storeValueInt("NUM_SPEAKERS1",speakers.length());
                    Boolean reg_closed=null;
                    if(speakers.length()==0)
                    {
                        inaug_reg.setVisibility(View.INVISIBLE);
                        Toast.makeText(getContext(),"COMING SOON",Toast.LENGTH_SHORT).show();
                    }
                    for(int i=0;i<speakers.length();i++)
                    {
                        JSONObject jsonObject = speakers.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("speaker_name");
                        String desc = jsonObject.getString("desc");
                        desc = html2text(desc);
                        String image = jsonObject.getString("image");
                        image = "http://axisvnit.org" + image;
                        reg_closed = jsonObject.getBoolean("registrations_closed");
                        InaugClass inaugClass = new InaugClass(id,name,desc,image,reg_closed);
                        inaugList.add(inaugClass);
                    }
                    sliderAdapter = new SliderAdapter(getContext(),inaugList);
                    SlideViewPager.setAdapter(sliderAdapter);
                    if(reg_closed!=null) {
                        if (reg_closed == true) {
                            Toast.makeText(getContext(), "REGISTRATIONS CLOSED", Toast.LENGTH_SHORT).show();
                            inaug_reg.setVisibility(View.INVISIBLE);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),
                        "PLEASE MAKE SURE YOUR DEVICE IS CONNECTED TO INTERNET", Toast.LENGTH_SHORT).show();
                hidepDialog();
            }
        });
        MySingleton.getInstance(getContext()).addToRequestque(stringRequest);
    }

    public void addDotstoIndicator(int position){
        num_dots = Constants.instance().fetchValueInt("NUM_SPEAKERS1");
        if(num_dots!=0)
        {
            dots = new ImageView[num_dots];
            mDots = new TextView[num_dots];
            DotsLayout.removeAllViews();


        /*    for(int i=0;i<num_dots;i++)
            {
                mDots[i] = new TextView(this.getContext());
                mDots[i].setText(Html.fromHtml("&#8226"));
                mDots[i].setTextSize(35);
                if(i==position) {
                    mDots[i].setTextColor(getResources().getColor(R.color.active_dots));
                }
                else
                {
                    mDots[position].setTextColor(getResources().getColor(R.color.inactive_dots));
                }
                DotsLayout.addView(mDots[i]);
            }   */

            for(int i=0;i<num_dots;i++)
            {
                dots[i] = new ImageView(getContext());
                if(i==position)
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.active_dots));
                }
                else
                {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.default_dots));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(8,0,8,0);

                DotsLayout.addView(dots[i]);
            }
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotstoIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private String html2text(String response) {
        return Jsoup.parse(response).text();
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
