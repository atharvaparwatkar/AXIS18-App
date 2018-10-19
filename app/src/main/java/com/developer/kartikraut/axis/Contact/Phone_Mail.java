package com.developer.kartikraut.axis.Contact;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developer.kartikraut.axis.Axis_Home.Home;
import com.developer.kartikraut.axis.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class Phone_Mail extends Activity {

    ImageView ivCall,ivMail;
    CircleImageView contact_photo;
    View view_Screen;
    RelativeLayout rl_phone_mail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__mail);

        rl_phone_mail = (RelativeLayout)findViewById(R.id.rl_phone_mail);
        ivCall = (ImageView)findViewById(R.id.ivCall);
        ivMail = (ImageView)findViewById(R.id.ivMail);
        view_Screen = (View)findViewById(R.id.view_Screen);

        Bundle extras = getIntent().getExtras();
        final String mobile_no = extras.getString("MobileNo");
        final String mailId = extras.getString("MailId");
        int ImageId = extras.getInt("ImageId");

        ivCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialer(mobile_no);
            }
        });

        ivMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail(mailId);
            }
        });

        contact_photo = (CircleImageView)findViewById(R.id.civ_Photo);
        Glide.with(getApplicationContext()).load(ImageId)
                .thumbnail(0.5f)
                .crossFade()
                // .placeholder(R.mipmap.ic_axis_app_logo)
                .error(R.mipmap.ic_axis_app_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(contact_photo);

        contact_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        view_Screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void sendMail(String mailId) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", mailId, null));
        intent.putExtra(Intent.EXTRA_SUBJECT,"");
        intent.putExtra(Intent.EXTRA_TEXT,"");
        startActivity(Intent.createChooser(intent, "Send email..."));
    }

    private void openDialer(String ph_no) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + ph_no));
        startActivity(intent);
    }
}
