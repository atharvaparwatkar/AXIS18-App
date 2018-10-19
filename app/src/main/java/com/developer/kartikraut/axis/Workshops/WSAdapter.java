package com.developer.kartikraut.axis.Workshops;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.R;

import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WSAdapter extends RecyclerView.Adapter<WSAdapter.ViewHolder> {

    List<WSClass> WSList;
    Context context;

    public WSAdapter(Context context, List<WSClass> WSList)
    {
        this.context=context;
        this.WSList=WSList;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public WSAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ws,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WSAdapter.ViewHolder holder, int position) {

        WSClass wsClassitem = WSList.get(position);

        holder.tvCategory.setText(wsClassitem.getTitle());

        Glide.with(getContext()).load(wsClassitem.getimageId())
                .thumbnail(0.5f)
                .crossFade()
                // .placeholder(R.mipmap.ic_axis_app_logo)
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivCategory);

    }

    @Override
    public int getItemCount() {
        return WSList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.ivWS)
        ImageView ivCategory;
        @BindView(R.id.tvWS)
        TextView tvCategory;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
          WSClass wsClass = WSList.get(getAdapterPosition());

          Intent i =new Intent(getContext(),WSActivity.class);
          i.putExtra("WS",wsClass);
          getContext().startActivity(i);
        }
    }
}
