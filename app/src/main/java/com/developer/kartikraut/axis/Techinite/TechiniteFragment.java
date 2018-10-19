package com.developer.kartikraut.axis.Techinite;


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
public class TechiniteFragment extends Fragment {

    RecyclerView rv_Techie;
    TechieAdapter adapter;
    private ProgressDialog pDialog;
    List<Techie_Class> techie_classes;
    String url_req_technite="http://axisvnit.org/api/techie_nite/?format=json";


    public TechiniteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_techinite, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("Techie Nite");
        rv_Techie = (RecyclerView)view.findViewById(R.id.rv_techie);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_Techie.setLayoutManager(llm);

        return view;
    }

    private void initializeData() {
        showpDialog();
        techie_classes = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_req_technite, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    if(jsonArray.length()==0)
                    {
                        Toast.makeText(getContext(),"COMING SOON",Toast.LENGTH_SHORT).show();
                    }

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int id = jsonObject.getInt("id");
                        String name = jsonObject.getString("name");
                        String desc = jsonObject.getString("desc");
                        String image = jsonObject.getString("image");
                        image = "http://axisvnit.org"+image;
                        Boolean reg_closed = jsonObject.getBoolean("registrations_closed");
                        String date = jsonObject.getString("date");
                        String date1="";
                        if(id==1)
                        {
                            date1 = "24th February,2018";
                        }
                        else
                        {
                            date1 = "25th February,2018";
                        }
                        String time = date.substring(11,16);
                        String t1 = time.substring(0,2);
                        String time1;
                        if(Integer.parseInt(t1)>12)
                        {
                            int t11 = Integer.parseInt(t1)-12;
                            t1 = Integer.toString(t11);
                            time1 = t1 + time.substring(2) + " PM";
                        }
                        else
                        {
                            time1 = time + " AM";
                        }
                      //  Toast.makeText(getContext(),time1,Toast.LENGTH_SHORT).show();
                        String venue = jsonObject.getString("venue");
                        Techie_Class techie_class = new Techie_Class(id,name,desc,image,reg_closed,date1,time1,venue);
                        techie_classes.add(techie_class);
                    }
                    adapter = new TechieAdapter(getContext(),techie_classes);
                    rv_Techie.setAdapter(adapter);
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
        MySingleton.getInstance(getContext()).addToRequestque(stringRequest);
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
