package com.developer.kartikraut.axis.Going_To;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.Constants;
import com.developer.kartikraut.axis.Events.CategoryClass;
import com.developer.kartikraut.axis.Events.CategoryRecyclerViewAdapter;
import com.developer.kartikraut.axis.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoingToAdapter extends RecyclerView.Adapter<GoingToAdapter.ViewHolder> {

    List<GoingToClass> goingTo_List;
    Context context;
    AlertDialog.Builder builder;

    public GoingToAdapter(Context context, ArrayList<GoingToClass> goingTo_List){
        this.context=context;
        this.goingTo_List=goingTo_List;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public GoingToAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_goingto,parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GoingToAdapter.ViewHolder holder, int position) {

        GoingToClass goingToClass = goingTo_List.get(position);

        holder.tvEventName.setText(goingToClass.getEvent_name());
        holder.tvCategoryName.setText(goingToClass.getCategory_name());
    }

    @Override
    public int getItemCount() {
        return goingTo_List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.tvCategoryName)
        TextView tvCategoryName;
        @BindView(R.id.tvEventName)
        TextView tvEventName;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
