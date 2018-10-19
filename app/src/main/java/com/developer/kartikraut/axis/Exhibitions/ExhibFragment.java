package com.developer.kartikraut.axis.Exhibitions;


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
import com.developer.kartikraut.axis.Talks.TalkAdapter;
import com.developer.kartikraut.axis.Talks.TalkClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExhibFragment extends Fragment {

    RecyclerView rv_Exhib;
    exhib_adapter adapter;
    List<TalkClass> exhibClasses;

    String req_url = "http://axisvnit.org/api/exhibitions?format=json";
    private ProgressDialog pDialog;

    public ExhibFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exhib, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("EXHIBITIONS");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        rv_Exhib = (RecyclerView)view.findViewById(R.id.rv_Exhib);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        rv_Exhib.setLayoutManager(llm);

        exhibClasses = new ArrayList<>();

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
                            JSONArray exhibs = new JSONArray(response);

                            if(exhibs.length()==0)
                            {
                                Toast.makeText(getContext(),"Exhibitions will be updated soon",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                for (int i = 0; i < exhibs.length(); i++) {
                                    JSONObject jsonexhib = exhibs.getJSONObject(i);
                                    int id = jsonexhib.getInt("id");
                                    String name = jsonexhib.getString("name");
                                    String desc = jsonexhib.getString("description");
                                    desc = html2text(desc);
                                    String venue = jsonexhib.getString("venue");
                                    String date = jsonexhib.getString("date");
                                    String time = jsonexhib.getString("time");
                                    String image = jsonexhib.getString("image");
                                    image = "http://axisvnit.org" + image;
                                    String link = "";

                                    TalkClass talk = new TalkClass(id, name, desc, venue, date, time, image, link,3);
                                    exhibClasses.add(talk);
                                }

                                adapter = new exhib_adapter(getContext(), exhibClasses);
                                rv_Exhib.setAdapter(adapter);

                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                hidepDialog();
                Toast.makeText(getContext(),
                        "PLEASE MAKE SURE YOUR DEVICE IS CONNECTED TO INTERNET", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(getContext()).addToRequestque(jsonArrayRequest);
        hidepDialog();
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
