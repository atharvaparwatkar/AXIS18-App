package com.developer.kartikraut.axis.Schedule;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class ThirdDayFragment extends Fragment {

    RecyclerView rv_day3;
    TextView event_name,event_time,event_venue;
    private ProgressDialog pDialog;
    List<ScheduleClass> scheduleClasses;
    ScheduleAdapter adapter;

    String day3_events_url = "http://axisvnit.org/api/schedule/3/?format=json";


    public ThirdDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third_day, container, false);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        rv_day3 = (RecyclerView)view.findViewById(R.id.rv_day3);

        initializeData();

        if (scheduleClasses == null) {
            Toast.makeText(getContext(), "Schedule will be updated soon", Toast.LENGTH_SHORT).show();
        }
        else {
                LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
                rv_day3.setLayoutManager(llm);
        }
        return view;
    }

    private void initializeData() {
        showpDialog();
        scheduleClasses = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, day3_events_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray day1_events = new JSONArray(response);
                    String name,time,venue;

                    if(day1_events.length()==0)
                    {
                        Toast.makeText(getContext(),"Schedule will be updated soon",Toast.LENGTH_SHORT);
                    }
                    else {
                        for (int i = 0; i < day1_events.length(); i++) {
                            JSONObject jsonObject = day1_events.getJSONObject(i);
                            name = jsonObject.getString("name");
                            time = jsonObject.getString("date");
                            String t1 = time.substring(11, 16);
                            int t2 = Integer.parseInt(t1.substring(0, 2));
                            String t3;

                            if (t2 > 12) {
                                t2 = t2 - 12;
                                t3 = Integer.toString(t2) + t1.substring(2) + " PM";
                            } else if (t2 == 12) {
                                t3 = Integer.toString(t2) + t1.substring(2) + " PM";
                            } else {
                                t3 = t1 + " AM";
                            }
                            venue = jsonObject.getString("venue");
                            scheduleClasses.add(new ScheduleClass(name, t3, venue));
                        }

                        adapter = new ScheduleAdapter(getContext(), scheduleClasses);
                        rv_day3.setAdapter(adapter);
                    }
                    if (scheduleClasses.size()==0)
                    {
                        Toast.makeText(getContext(),"Schedule will be updated soon",Toast.LENGTH_SHORT);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(getContext(),"PLEASE MAKE SURE YOUR DEVICE IS CONNECTED TO INTERNET",Toast.LENGTH_SHORT).show();
            }
        });
        MySingleton.getInstance(getContext()).addToRequestque(stringRequest);
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
