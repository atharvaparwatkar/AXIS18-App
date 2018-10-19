package com.developer.kartikraut.axis.GuestLectures;


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

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class GuestFragment extends Fragment {

    RecyclerView rv_guest;
    TalkAdapter adapter;
    List<TalkClass> lectures;
    Boolean Connected=false;
    String req_url = "http://axisvnit.org/api/guest_lectures?format=json";
    String reg_url;
    private ProgressDialog pDialog;


    public GuestFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_guest, container, false);
        ButterKnife.bind(this,view);
        getActivity().setTitle("GUEST LECTURES");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        rv_guest = (RecyclerView)view.findViewById(R.id.rv_guest);

        LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
        rv_guest.setLayoutManager(llm);

        lectures = new ArrayList<>();

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
                            JSONArray lecs = new JSONArray(response);

                            for(int i=0;i<lecs.length();i++)
                            {
                                JSONObject jsonlec = lecs.getJSONObject(i);
                                int id = jsonlec.getInt("id");
                                String name = jsonlec.getString("speaker_name");
                                String about_speaker = jsonlec.getString("about_speaker");
                                String about_lec = jsonlec.getString("about_lecture");
                                String desc = "ABOUT SPEAKER: " + about_speaker+ "\n\n" + "ABOUT LECTURE: " + about_lec;
                                desc = html2text(desc);
                                String venue = jsonlec.getString("venue");
                                String date = jsonlec.getString("date");
                                String time = jsonlec.getString("time");
                                String image = jsonlec.getString("speaker_image");
                                image = "http://axisvnit.org"+image;
                                String link = "";
                                Boolean isValid = jsonlec.getBoolean("isvalid");

                                TalkClass talk = new TalkClass(id,name,desc,venue,date,time,image,link,2);
                                lectures.add(talk);
                            }

                            adapter = new TalkAdapter(getContext(),lectures);
                            rv_guest.setAdapter(adapter);

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
