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

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import static com.developer.kartikraut.axis.Events.Auto_Robo_Fragment.urlJsonArry;

/**
 * A simple {@link Fragment} subclass.
 */
public class Construction_Fragment extends Fragment {

    RecyclerView rvConstruction;
    EventsAdapter adapter;
    private ProgressDialog pDialog;

    private List<EventsClass> Consts;


    public Construction_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_construction_, container, false);
        ButterKnife.bind(this,view);

        rvConstruction = (RecyclerView)view.findViewById(R.id.rvConstruction);
        getActivity().setTitle("CONSTRUCTION & DESIGN");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvConstruction.setHasFixedSize(true);
        rvConstruction.setLayoutManager(llm);

        Consts = new ArrayList<>();

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
                                if(name.equals("devise")){
                                    image = R.drawable.devise;
                                    em1_name = "Aditya";
                                    em1_phone = "+91-9665345145";
                                    em2_name = "Shashank";
                                    em2_phone = "+91-7771048056";
                                    em3_name = "Khushboo";
                                    em3_phone = "+91-7769839907";
                                    em4_name = "Mansi";
                                    em4_phone = "+91-8275248592";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Consts.add(eventsClass);
                                }
                                else if(name.equals("aqua skylark")){
                                    image = R.drawable.aqua;
                                    em1_name = "Vamshi";
                                    em1_phone = "+91-9492405651";
                                    em2_name = "Moderesa";
                                    em2_phone = "+91-7030524635";
                                    em3_name = " Jinka";
                                    em3_phone = "+91-7981095257";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Consts.add(eventsClass);
                                }
                                else if(name.equals("crepido")){
                                    image = R.drawable.crepido;
                                    em1_name = "Pranavi";
                                    em1_phone = "+91-8686355234";
                                    em2_name = "Akshar";
                                    em2_phone = "+91-9978514443";
                                    em3_name = "Rohit";
                                    em3_phone = "+91-7875415243";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Consts.add(eventsClass);
                                }
                                else if(name.equals("paradeigma")){
                                    image = R.drawable.para;
                                    em1_name = "Haider";
                                    em1_phone = "+91-9527547252";
                                    em2_name = "Mounika";
                                    em2_phone = "+91-8500079894";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Consts.add(eventsClass);
                                }
                                else if(name.equals("turboflux")) {
                                    max_team_mem=3;
                                    image = R.drawable.turboflux;
                                    em1_name = "Angunoori";
                                    em1_phone = "+91-7382101928";
                                    em2_name = "Yelmelwar";
                                    em2_phone = "+91-9494097899";
                                    em3_name = "M Sai";
                                    em3_phone = "+91-9145539018";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Consts.add(eventsClass);
                                }

                            }

                            adapter = new EventsAdapter(getContext(),Consts);
                            rvConstruction.setAdapter(adapter);

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

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}
