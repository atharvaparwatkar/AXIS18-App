package com.developer.kartikraut.axis.Talks;


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
import com.android.volley.toolbox.JsonArrayRequest;
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
public class TalkFragment extends Fragment {

    RecyclerView rv_talks;
    TalkAdapter adapter;
    List<TalkClass> talkClasses;
    String req_url = "http://axisvnit.org/api/talks?format=json";
    String reg_url;
    private ProgressDialog pDialog;

    public TalkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_talk, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("TALKS");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        rv_talks = (RecyclerView)view.findViewById(R.id.rv_talks);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv_talks.setLayoutManager(llm);

        talkClasses = new ArrayList<>();

        getData();

        return view;
    }

    private void getData() {

        showpDialog();
        final StringRequest jsonArrayRequest = new StringRequest(Request.Method.GET, req_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray talks = new JSONArray(response);

                            for(int i=0;i<talks.length();i++)
                            {
                                JSONObject jsontalk = talks.getJSONObject(i);
                                int id = jsontalk.getInt("id");
                                String name = jsontalk.getString("name");
                                String desc = jsontalk.getString("description");
                            //    desc = desc.replaceAll("\\<.*?>","");
                                desc = desc.replace("‘","'");
                                desc = html2text(desc);
                                String venue = jsontalk.getString("venue");
                                String date = jsontalk.getString("date");
                                String time = jsontalk.getString("time");
                                String image = jsontalk.getString("image");
                                image = "http://axisvnit.org"+image;
                                String link = jsontalk.getString("link");

                                TalkClass talk = new TalkClass(id,name,desc,venue,date,time,image,link,1);
                                talkClasses.add(talk);
                            }

                            adapter = new TalkAdapter(getContext(),talkClasses);
                            rv_talks.setAdapter(adapter);
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
