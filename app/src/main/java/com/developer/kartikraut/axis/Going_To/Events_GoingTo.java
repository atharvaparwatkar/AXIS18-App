package com.developer.kartikraut.axis.Going_To;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Events.CategoryClass;
import com.developer.kartikraut.axis.MySingleton;
import com.developer.kartikraut.axis.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnItemLongClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class Events_GoingTo extends Fragment {

    ArrayList<GoingToClass> events_going;
    ArrayList<GoingToClass> events;
    RecyclerView rv_goingTo;
    GoingToAdapter adapter;
    private ProgressDialog pDialog;
    Boolean login;
    String axisId;
    List<Integer> reg_events;

    public Events_GoingTo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events__going_to,container,false);
        ButterKnife.bind(this,view);

        Constants.instance(getContext());
        login = Constants.instance().fetchValueBool("LOGIN");
        rv_goingTo = (RecyclerView) view.findViewById(R.id.rv_EventGoingTo);
        axisId = Constants.instance().fetchValueString("AXISID");

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);

        events = new ArrayList<>();
        events.add(new GoingToClass(1,"Devise","Construction and Design"));
        events.add(new GoingToClass(2,"Dexter","School Events"));
        events.add(new GoingToClass(3,"Crepido","Construction and Design"));
        events.add(new GoingToClass(4,"Turboflux","Construction and Design"));
        events.add(new GoingToClass(5,"Aqua Skylark","Construction and Design"));
        events.add(new GoingToClass(6,"Paradeigma","Construction and Design"));
        events.add(new GoingToClass(7,"Junior Scientist","School Events"));
        events.add(new GoingToClass(8,"Robowars","Automation and Robotics"));
        events.add(new GoingToClass(9,"Mechatryst","Automation and Robotics"));
        events.add(new GoingToClass(10,"Aquahunt","Automation and Robotics"));
        events.add(new GoingToClass(11,"Robo Cup","Automation and Robotics"));
        events.add(new GoingToClass(12,"AutoBot","Automation and Robotics"));
        events.add(new GoingToClass(13,"Robo Terraformer","Automation and Robotics"));
        events.add(new GoingToClass(14,"221B Baker Street","Management and Others"));
        events.add(new GoingToClass(15,"Freak-O-Matix","Management and Others"));
        events.add(new GoingToClass(16,"Wall Street","Management and Others"));
        events.add(new GoingToClass(17,"Informals","Management and Others"));
        events.add(new GoingToClass(18,"Gamesutra","Management and Others"));
        events.add(new GoingToClass(19,"Brainstorm","AXIS-Igniting Minds"));
        events.add(new GoingToClass(20,"Who’s The Boss","Management and Others"));
        events.add(new GoingToClass(21,"Laser Litt","Management and Others"));
        events.add(new GoingToClass(22,"Techno.docx","AXIS-Igniting Minds"));
        events.add(new GoingToClass(23,"Kartavya","AXIS-Igniting Minds"));
        events.add(new GoingToClass(24,"Posterolic","Software and Electronics"));
        events.add(new GoingToClass(25,"Animazione","Software and Electronics"));
        events.add(new GoingToClass(26,"Cryptocrux","Software and Electronics"));
        events.add(new GoingToClass(27,"Insomnia","Software and Electronics"));
        events.add(new GoingToClass(28,"Electroblitz","Software and Electronics"));
        events.add(new GoingToClass(29,"Vidarbha Innovation Challenge","AXIS-Igniting Minds"));


        if(login==false){
            rv_goingTo.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(),"Please Login to see the registered events",Toast.LENGTH_SHORT).show();
        }
        else {
            initializeData();
            LinearLayoutManager llm = new LinearLayoutManager(this.getContext());
            rv_goingTo.setLayoutManager(llm);
         /*   if (events_going.size() == 0) {
                Toast.makeText(getContext(), "No Registered Events", Toast.LENGTH_SHORT).show();
            }  */
        }
        return view;
    }

    private void initializeData() {
        showpDialog();
        events_going = new ArrayList<>();
        String reg_events_url = "http://axisvnit.org/api/all_registered/events/" + axisId + "/";
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
                                GoingToClass goingToClass = new GoingToClass(events.get(numbers[i]-1).getEvent_id(),events.get(numbers[i]-1).getEvent_name(),events.get(numbers[i]-1).getCategory_name());
                                events_going.add(goingToClass);
                            }
                            adapter = new GoingToAdapter(getContext(),events_going);
                            rv_goingTo.setAdapter(adapter);
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
