package com.developer.kartikraut.axis.Workshops;


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
import com.developer.kartikraut.axis.Events.CategoryClass;
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
public class WSFragment extends Fragment {

    @BindView(R.id.rv_WS)
    RecyclerView rv_WS;

    WSAdapter wsAdapter;
    private ProgressDialog pDialog;

    private List<WSClass> WSClassList;

    String req_url = "http://axisvnit.org/api/workshops?format=json";

    public WSFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_w, container, false);
        ButterKnife.bind(this,view);
        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());

        rv_WS.setHasFixedSize(true);
        rv_WS.setLayoutManager(llm);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        initializeData();

        getActivity().setTitle("WORKSHOPS");
        return view;
    }

    private void initializeData()
    {
        showpDialog();
        WSClassList = new ArrayList<>();
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
                                desc = html2text(desc);
                                String image = jsontalk.getString("image");
                                image = "http://axisvnit.org" + image;
                                Boolean reg_closed = jsontalk.getBoolean("registrations_closed");
                                String pay_link = jsontalk.getString("payment_link");

                                if((reg_closed==true)||(((pay_link.equals("null")))&&(reg_closed==true)))
                                {
                                    pay_link = "Registrations Closed";

                                }

                                WSClass ws = new WSClass(id,name,image,desc,pay_link,reg_closed);
                                WSClassList.add(ws);
                            }

                            wsAdapter = new WSAdapter(getContext(),WSClassList);
                            rv_WS.setAdapter(wsAdapter);

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(),
                                    "PLEASE MAKE SURE YOUR DEVICE IS CONNECTED TO INTERNET", Toast.LENGTH_SHORT).show();

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
