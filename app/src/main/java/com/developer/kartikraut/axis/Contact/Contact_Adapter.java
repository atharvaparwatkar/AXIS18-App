package com.developer.kartikraut.axis.Contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.Axis_Home.Home;
import com.developer.kartikraut.axis.LoginSignup.Login;
import com.developer.kartikraut.axis.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Contact_Adapter extends RecyclerView.Adapter<Contact_Adapter.ContactViewHolder> {
    private ArrayList<ContactClass> contactClassList;
    Context context;

    public Contact_Adapter(Context context, ArrayList<ContactClass> contactList){
        contactClassList = contactList;
        this.context = context;
    }

    private Context getContext(){
        return context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact,parent,false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
            ContactClass currentItem = contactClassList.get(position);

            holder.tv_post1.setText(currentItem.getPost1());
            holder.tv_Name.setText(currentItem.getName());

        Glide.with(getContext()).load(currentItem.getImageId())
                .thumbnail(0.5f)
                .crossFade()
               // .placeholder(R.mipmap.ic_axis_app_logo)
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(250,250)
                .into(holder.Con_photo);

     /*   Picasso.with(getContext())
                .load(currentItem.getImageId())
                .into(holder.Con_photo); */

    }

    @Override
    public int getItemCount() {
        return contactClassList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.ivContactPhoto)
        de.hdodenhof.circleimageview.CircleImageView Con_photo;

        @BindView(R.id.tv_post1)
        TextView tv_post1;

        @BindView(R.id.tv_name)
        TextView tv_Name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ContactClass itemSelected = contactClassList.get(getAdapterPosition());
           // Toast.makeText(getContext(),itemSelected.getName(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getContext(),Phone_Mail.class);
            intent.putExtra("MobileNo",itemSelected.getPhone_no());
            intent.putExtra("MailId",itemSelected.getMailId());
            intent.putExtra("ImageId",itemSelected.getImageId());
            getContext().startActivity(intent);
        }
    }




}
