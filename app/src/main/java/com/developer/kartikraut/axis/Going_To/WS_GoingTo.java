package com.developer.kartikraut.axis.Going_To;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class WS_GoingTo extends Fragment {

    ArrayList<GoingToClass> ws_going;
    ArrayList<GoingToClass> wss;
    RecyclerView rv_goingTo_ws;
    GoingToAdapter adapter;
    private ProgressDialog pDialog;
    Boolean login;
    String axisId;
    List<Integer> reg_ws;
    String get_ws_link = "http://axisvnit.org/api/workshops/?format=json";

    public WS_GoingTo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ws__going_to,container,false);
        ButterKnife.bind(this,view);

        Constants.instance(getContext());
        login = Constants.instance().fetchValueBool("LOGIN");
        rv_goingTo_ws = (RecyclerView) view.findViewById(R.id.rv_WSGoingTo);
        axisId = Constants.instance().fetchValueString("AXISID");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        wss = new ArrayList<>();
        wss.add(new GoingToClass(1,"Ethical Hacking",""));
        wss.add(new GoingToClass(2,"Articulated Robotic Arm",""));
        wss.add(new GoingToClass(3,"Tall Building Design",""));
        wss.add(new GoingToClass(4,"Artificial Intelligence and Machine Learning",""));


        if(login==false)
        {
            Toast.makeText(getContext(),"Please Login to see the registered events",Toast.LENGTH_SHORT).show();
        }
        else
        {
            get_reg_ws();
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            rv_goingTo_ws.setLayoutManager(llm);

        }

        return view;
    }

    private void get_reg_ws() {
        showpDialog();
        ws_going = new ArrayList<>();
        String reg_events_url = "http://axisvnit.org/api/all_registered/workshops/" + axisId + "/";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, reg_events_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("id_list");
                    if(jsonArray!=null)
                    {
                        int[] numbers = new int[jsonArray.length()];

                        for(int i=0;i<jsonArray.length();i++)
                        {
                            numbers[i] = jsonArray.optInt(i);
                            GoingToClass goingToClass = new GoingToClass(wss.get(numbers[i]-1).getEvent_id(),wss.get(numbers[i]-1).getEvent_name(),"");
                            ws_going.add(goingToClass);
                        }
                        adapter = new GoingToAdapter(getContext(),ws_going);
                        rv_goingTo_ws.setAdapter(adapter);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Please make sure your device is connected to internet",Toast.LENGTH_SHORT).show();
            }
        });
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(20000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(getContext()).addToRequestque(stringRequest);
        hidepDialog();
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
