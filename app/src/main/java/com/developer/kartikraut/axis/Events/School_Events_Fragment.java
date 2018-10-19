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
public class School_Events_Fragment extends Fragment {

    RecyclerView rvSchool;

    private List<EventsClass> Schools;
    EventsAdapter adapter;

    private ProgressDialog pDialog;

    public School_Events_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school__events_, container, false);
        ButterKnife.bind(this,view);
        rvSchool = (RecyclerView)view.findViewById(R.id.rvSchool);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvSchool.setHasFixedSize(true);
        rvSchool.setLayoutManager(llm);

        Schools = new ArrayList<>();

        getActivity().setTitle("SCHOOL EVENTS");

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
                                if(slug.equals("junior-scientist")){
                                    image = R.drawable.js;
                                    em1_name = "Shyamli";
                                    em1_phone = "+91-7588740817";
                                    em2_name = "Shubham";
                                    em2_phone = "+91-9545764277";
                                    em3_name = "Vaibhav";
                                    em3_phone = "+91-7028438413";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Schools.add(eventsClass);
                                }
                                else if(name.equals("dexter")){
                                    image = R.drawable.dexter;
                                    em1_name = "Sumit";
                                    em1_phone = "+91-9664181239";
                                    em2_name = "Shashank";
                                    em2_phone = "+91-9757235823";
                                    em3_name = "Priyanka";
                                    em3_phone = "+91-7030152313";
                                    em4_name = "Vaibhav";
                                    em4_phone = "+91-9922383872";
                                    em5_name = "Vaishnavi";
                                    em5_phone = "+91-8275534607";
                                    em6_name = "Sanket";
                                    em6_phone = "+91-7775938359";
                                    EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                    Schools.add(eventsClass);
                                }
                            }

                            adapter = new EventsAdapter(getContext(),Schools);
                            rvSchool.setAdapter(adapter);

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
