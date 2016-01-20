package com.example.dellaanjeh.yummy_users;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView ivProfilePic;
    TextView tvUsername;
    TextView tvName;
    TextView tvNameValue;
    TextView tvTitle;
    TextView tvTitleValue;
    TextView tvEmail;
    TextView tvEmailValue;
    Button btnBack;
    String username;
    String name;
    String email;
    String imageUrl;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        Typeface pacifico = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
        tvUsername.setTypeface(pacifico);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNameValue = (TextView) findViewById(R.id.tvNameValue);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitleValue = (TextView) findViewById(R.id.tvTitleValue);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmailValue = (TextView) findViewById(R.id.tvEmailValue);
        btnBack = (Button) findViewById(R.id.btnBack);
//        btnBack.setButtonColor(getResources().getColor(R.color.color_concrete));
//        disabledBtn.setShadowColor(getResources().getColor(R.color.color_asbestos));
//        disabledBtn.setShadowEnabled(true);
//        disabledBtn.setShadowHeight(5);
//        disabledBtn.setCornerRadius(5);
        btnBack.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("EXTRA_USERNAME");
            name = extras.getString("EXTRA_NAME");
            email = extras.getString("EXTRA_EMAIL");
            imageUrl = extras.getString("EXTRA_IMAGEURL");
            title = extras.getString("EXTRA_TITLE");
        }

        tvUsername.setText(username);
        Picasso.with(ProfileActivity.this).load(imageUrl).into(ivProfilePic);
        tvNameValue.setText(name);
        tvEmailValue.setText(email);
        tvTitleValue.setText(title);
    }

    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
