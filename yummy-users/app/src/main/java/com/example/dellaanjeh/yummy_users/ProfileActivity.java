package com.example.dellaanjeh.yummy_users;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ivProfilePic = (ImageView) findViewById(R.id.ivProfilePic);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvName = (TextView) findViewById(R.id.tvName);
        tvNameValue = (TextView) findViewById(R.id.tvNameValue);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitleValue = (TextView) findViewById(R.id.tvTitleValue);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvEmailValue = (TextView) findViewById(R.id.tvEmailValue);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("EXTRA_USERNAME");
        }

        tvUsername.setText(username);
    }

    public void onClick(View v) {
        if (v == btnBack) {
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

}
