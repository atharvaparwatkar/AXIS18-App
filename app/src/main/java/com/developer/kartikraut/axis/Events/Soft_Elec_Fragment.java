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
public class Soft_Elec_Fragment extends Fragment {

    RecyclerView rvSoft_Elec;
    EventsAdapter adapter;
    private ProgressDialog pDialog;

    private List<EventsClass> Soft_Elecs;

    public Soft_Elec_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_soft__elec_, container, false);
        ButterKnife.bind(this,view);
        rvSoft_Elec = (RecyclerView)view.findViewById(R.id.rvSoft_Elec);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rvSoft_Elec.setHasFixedSize(true);
        rvSoft_Elec.setLayoutManager(llm);

        getActivity().setTitle("SOFTWARE & ELECTRONICS");

        Soft_Elecs = new ArrayList<>();

        return view;
    }

    private void initializeData() {
    /*    Soft_Elecs.add(new CategoryClass("ANIMAZIONE", R.drawable.anim));
        Soft_Elecs.add(new CategoryClass("CRYPTOCRUX", R.drawable.crypto));
        Soft_Elecs.add(new CategoryClass("INSOMNIA", R.drawable.inso));
        Soft_Elecs.add(new CategoryClass("POSTEROLIC", R.drawable.poster));
        Soft_Elecs.add(new CategoryClass("ELECTROBLITZ",R.drawable.electroblitzmin));   */

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

                                    if(name.equals("animazione")){
                                        image = R.drawable.animmin;
                                        em1_name = "Srichandra";
                                        em1_phone = "+91-9849046579";
                                        em2_name = "Srilekha";
                                        em2_phone = "+91-9604307572";
                                        em3_name = "Sandeep";
                                        em3_phone = "+91-8121480507";
                                        EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                        Soft_Elecs.add(eventsClass);
                                    }
                                    else if(name.equals("cryptocrux")){
                                        image = R.drawable.cryptomin;
                                        em1_name = "Pragati";
                                        em1_phone = "+91-9930198202";
                                        em2_name = "Shreyas";
                                        em2_phone = "+91-9967585092";
                                        EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                        Soft_Elecs.add(eventsClass);
                                    }
                                    else if(name.equals("insomnia")){
                                        image = R.drawable.inso;
                                        em1_name = "Anish";
                                        em1_phone = "+91-9422442613";
                                        em2_name = "Apeksha";
                                        em2_phone = "+91-9545967237";
                                        EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                        Soft_Elecs.add(eventsClass);
                                    }
                                    else if(name.equals("posterolic")){
                                        image = R.drawable.postermin;
                                        em1_name = "Srichandra";
                                        em1_phone = "+91-9849046579";
                                        em2_name = "Srilekha";
                                        em2_phone = "+91-9604307572";
                                        em3_name = "Sandeep";
                                        em3_phone = "+91-9604307572";
                                        EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                        Soft_Elecs.add(eventsClass);
                                    }
                                    else if(name.equals("electroblitz")) {
                                        image = R.drawable.electroblitzmin;
                                        em1_name = "Sukanya";
                                        em1_phone = "+91-9168928481";
                                        em2_name = "Shubham";
                                        em2_phone = "+91-9422817708";
                                        EventsClass eventsClass = new EventsClass(id,max_team_mem,category_id,image,name,slug,description,rules,ps,em1_name,em2_name,em3_name,em4_name,em5_name,em6_name,em1_phone,em2_phone,em3_phone,em4_phone,em5_phone,em6_phone,date,last_date);
                                        Soft_Elecs.add(eventsClass);
                                    }

                                }

                                adapter = new EventsAdapter(getContext(),Soft_Elecs);
                                rvSoft_Elec.setAdapter(adapter);

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
