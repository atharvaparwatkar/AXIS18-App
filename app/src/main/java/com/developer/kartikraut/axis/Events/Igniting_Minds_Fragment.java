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
public class Igniting_Minds_Fragment extends Fragment {

    RecyclerView rvIgnite;

    private List<EventsClass> Ignites;
    EventsAdapter adapter;

    private ProgressDialog pDialog;

    public Igniting_Minds_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_igniting__minds_, container, false);
        ButterKnife.bind(this,view);
        rvIgnite = (RecyclerView)view.findViewById(R.id.rvIgnite);
        getActivity().setTitle("AXIS IGNITING MINDS");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvIgnite.setHasFixedSize(true);
        rvIgnite.setLayoutManager(llm);

        Ignites = new ArrayList<>();

        return view;
    }

    private void initializeData() {
     /*   Ignites.add(new CategoryClass("KARTAVYA", R.drawable.kartavya1));
        Ignites.add(new CategoryClass("TECHNO.DOCX", R.drawable.technodocx1));   */

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
                                if(name.equals("kartavya")){
                                    image = R.drawable.kartavya1;
                                    em1_name = "Kamil";
                                    em1_phone = "+91-9158811213";
                                    em2_name = "Anusha";
                                    em2_phone = "+91-9177985424";
                                    em3_name = "Ankit";
                                    em3_phone = "+91-8554858450";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Ignites.add(eventsClass);
                                }
                                else if(name.equals("techno.docx")){
                                    image = R.drawable.technodocx1min;
                                    em1_name = "Anudeep";
                                    em1_phone = "+91-9420713836";
                                    em2_name = "Vaibhav";
                                    em2_phone = "+91-9594215011";
                                    em3_name = " Sahithi";
                                    em3_phone = "+91-9177300538";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Ignites.add(eventsClass);
                                }
                                else if(name.equals("brainstorm")){
                                    image = R.drawable.brainstormmin;
                                    em1_name = "Anushree";
                                    em1_phone = "+91-7841956932";
                                    em2_name = "Shubham";
                                    em2_phone = "+91-8087806702";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Ignites.add(eventsClass);
                                }
                                else if(slug.equals("vidarbha-innovation-challenge")) {
                                    image = R.drawable.vidarbhainnovationchallengemin;
                                    em1_name = "Ashwarya";
                                    em1_phone = "+91-9828458618";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Ignites.add(eventsClass);
                                }

                            }

                            adapter = new EventsAdapter(getContext(),Ignites);
                            rvIgnite.setAdapter(adapter);

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
