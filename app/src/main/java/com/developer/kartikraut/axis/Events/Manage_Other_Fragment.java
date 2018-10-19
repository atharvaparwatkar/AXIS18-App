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
public class Manage_Other_Fragment extends Fragment {

    RecyclerView rvManage;
    EventsAdapter adapter;
    private ProgressDialog pDialog;

    private List<EventsClass> Manages;


    public Manage_Other_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_manage__other_, container, false);
        ButterKnife.bind(this,view);
        rvManage = (RecyclerView)view.findViewById(R.id.rvManage);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvManage.setHasFixedSize(true);
        rvManage.setLayoutManager(llm);

        Manages = new ArrayList<>();

        getActivity().setTitle("MANAGEMENT AND OTHER");

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
                                if(name.equals("wall street")){
                                    image = R.drawable.wall;
                                    em1_name = "Anish";
                                    em1_phone = "+91-7028813480";
                                    em2_name = "Aman";
                                    em2_phone = "+91-7775094904";
                                    em3_name = "Rutuja";
                                    em3_phone = "+91-7219196604";
                                    em4_name = "Mahendra";
                                    em4_phone = "+91-9493704738";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(name.equals("informals")){
                                    image = R.drawable.informals;
                                    em1_name = "Undeti";
                                    em1_phone = "+91-9561481427";
                                    em2_name = "Sri Chandana";
                                    em2_phone = "+91-8806989777";
                                    em3_name = "Maneesh";
                                    em3_phone = "+91-8551997168";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(slug.equals("whos-the-boss")){
                                    name = "WHO'S THE BOSS";
                                    image = R.drawable.boss;
                                    em1_name = "Adarsh";
                                    em1_phone = "+91-8291108103";
                                    em2_name = "Ilesh";
                                    em2_phone = "+91-9960549425";
                                    em3_name = "Apurva";
                                    em3_phone = "+91-7038501676";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(slug.equals("221b-baker-street")){
                                    image = R.drawable.baker;
                                    em1_name = "Hrushikesh";
                                    em1_phone = "+91-7715987343";
                                    em2_name = "Samay";
                                    em2_phone = "+91-8237877685";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(name.equals("freak-o-matix")) {
                                    image = R.drawable.freako;
                                    em1_name = "Srujitha";
                                    em1_phone = "+91-7032094547";
                                    em2_name = "Nikhil";
                                    em2_phone = "+91-9422817708";
                                    em3_name = "Gayathri";
                                    em3_phone = "+91-7032534966";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(name.equals("gamesutra")) {
                                    image = R.drawable.gamesutra;
                                    em1_name = "Abhijeet";
                                    em1_phone = "+91-9769959343";
                                    em2_name = "Amuthan";
                                    em2_phone = "+91-99969728121";
                                    em3_name = "Mohit";
                                    em3_phone = "+91-9822940514";
                                    em4_name = "Nikhil";
                                    em4_phone = "+91-8975754046";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                                else if(slug.equals("laser-litt")){
                                    image = R.drawable.laserlittmin;
                                    em1_name = "Avdhoot";
                                    em1_phone = "+91-9422202349";
                                    em2_name = "Prakhar";
                                    em2_phone = "+91-8551961945";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Manages.add(eventsClass);
                                }
                            }

                            adapter = new EventsAdapter(getContext(),Manages);
                            rvManage.setAdapter(adapter);

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
