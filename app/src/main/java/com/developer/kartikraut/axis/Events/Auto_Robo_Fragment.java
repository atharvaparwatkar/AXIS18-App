package com.developer.kartikraut.axis.Events;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class Auto_Robo_Fragment extends Fragment {

    RecyclerView rvAuto_Robo;
    EventsAdapter adapter;

   // private List<CategoryClass> Auto_Robos;

    private List<EventsClass> Auto_Robos;
    public static String urlJsonArry = "http://axisvnit.org/api/events?format=json";
    private ProgressDialog pDialog;
    public Auto_Robo_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auto__robo_, container, false);
        ButterKnife.bind(this,view);
        rvAuto_Robo = (RecyclerView)view.findViewById(R.id.rvAuto_Robo);
        getActivity().setTitle("AUTOMATION & ROBOTICS");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvAuto_Robo.setHasFixedSize(true);
        rvAuto_Robo.setLayoutManager(llm);

        Auto_Robos = new ArrayList<>();

        return view;
    }

    private void initializeData() {
        showpDialog();
        final StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, urlJsonArry,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray events = new JSONArray(response);
                            int id,max_team_mem,category_id,image;
                            String name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone;
                            String date,last_date;

                            for(int i=0;i<events.length();i++)
                            {

                                em1_name="";
                                em1_phone="";
                                em2_name="";
                                em2_phone="";
                                em3_name="";
                                em3_phone="";
                                em4_name="";
                                em4_phone="";
                                em5_name="";
                                em5_phone="";
                                em6_name="";
                                em6_phone="";

                                JSONObject temp = events.getJSONObject(i);
                                name = temp.getString("name").toLowerCase();
                                id = temp.getInt("id");
                                max_team_mem = temp.getInt("max_team_mem");
                                category_id = temp.getInt("category");
                                slug = temp.getString("slug");
                                description = temp.getString("description");
                                rules = temp.getString("rules");
                                ps = temp.getString("prob_stmt");
                                date = temp.getString("date");
                                last_date = temp.getString("last_date_reg");

                                if(name.equals("robowars")){
                                    image = R.drawable.robo;
                                    em1_name = "Saurabh";
                                    em1_phone = "+91-9594328719";
                                    em2_name = "Divyansh";
                                    em2_phone = "+91-9145539281";
                                    em3_name = "Yash";
                                    em3_phone = "+91-8108485382";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(name.equals("mechatryst")){
                                    image = R.drawable.mecha;
                                    em1_name = "Mohammed";
                                    em1_phone = "+91-8830423995";
                                    em2_name = "Harender";
                                    em2_phone = "+91-7620795792";
                                    em3_name = "Mohd Sami";
                                    em3_phone = "+91-9561484281";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(name.equals("aquahunt")){
                                    image = R.drawable.aquahuntmin;
                                    em1_name = "Amey";
                                    em1_phone = "+91-8879710910";
                                    em2_name = "Shubham";
                                    em2_phone = "+91-9637328065";
                                    em3_name = "Pritam";
                                    em3_phone = "+91-7038232710";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(name.equals("autobot")){
                                    image = R.drawable.autobot;
                                    em1_name = "Chaitanya";
                                    em1_phone = "+91-9167313246";
                                    em2_name = "Pushkar";
                                    em2_phone = "+91-7756881415";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(name.equals("robo terraformer")){
                                    image = R.drawable.manromin;
                                    em1_name = "Sai";
                                    em1_phone = "+91-7262000543";
                                    em2_name = "Ankit";
                                    em2_phone = "+91-9576455154";
                                    em3_name = "Rohit";
                                    em3_phone = "+91-8412961951";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(slug.equals("robo-cup")) {
                                    image = R.drawable.robocup234min2;
                                    em1_name = "Om";
                                    em1_phone = "+91-7385659671";
                                    em2_name = "Prasad";
                                    em2_phone = "+91-9156484926";
                                    em3_name = "Himanshu";
                                    em3_phone = "+91-9411287469";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }
                                else if(slug.equals("electro-gt")) {
                                    image = R.drawable.electrogt;
                                    em1_name = "Mohammed";
                                    em1_phone = "+91-8830423995";
                                    em2_name = "Harender";
                                    em2_phone = "+91-7620795792";
                                    em3_name = "Mohd Sami";
                                    em3_phone = "+91-9561484281";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Auto_Robos.add(eventsClass);
                                }

                            }

                            adapter = new EventsAdapter(getContext(),Auto_Robos);
                            rvAuto_Robo.setAdapter(adapter);

                        }
                        catch (JSONException e) {
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

        MySingleton.getInstance(getContext()).addToRequestque(jsonArrayRequest);
    }

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
