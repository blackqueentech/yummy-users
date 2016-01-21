package com.example.dellaanjeh.yummy_users;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvUsers;
    private List<UserList> userList;
    private ArrayAdapter<UserList> adapter;
    private  List<String> userListInfo;
    private String urlJson = "https://api.slack.com/api/users.list?token=xoxp-5048173296-5048487710-18650790535-1cc8644082";
    private ProgressDialog pDialog;
    private static String TAG = MainActivity.class.getSimpleName();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList = new ArrayList<UserList>();
        userListInfo = new LinkedList<String>();
        adapter = new UserListAdapter(MainActivity.this, R.layout.list_view, userList);
        lvUsers = (ListView) findViewById(R.id.lvUsers);
        lvUsers.setAdapter(adapter);
        lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                UserList user = adapter.getItem(position);
                intent.putExtra("EXTRA_USERNAME", user.getUsername());
                intent.putExtra("EXTRA_NAME", user.getName());
                intent.putExtra("EXTRA_EMAIL", user.getEmail());
                intent.putExtra("EXTRA_IMAGEURL", user.getImageUrl());
                intent.putExtra("EXTRA_TITLE", user.getTitle());
                startActivity(intent);
            }
        });
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        sharedPreferences = this.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        for(int i = 0; i <= sharedPreferences.getAll().size(); i++) {
            userListInfo.add(i, sharedPreferences.getString("", ));
        }

        userList.addAll(userListInfo);
        adapter.notifyDataSetChanged();

        if (!sharedPreferences.contains("initialized")) {
            makeJsonObjectRequest();
        } else {
            onResume();
        }
        // TODO: only have this run when shared preferences is uninitialized
        makeJsonObjectRequest();

    }

    @Override
    protected void onPause() {
        super.onPause();
        editor = sharedPreferences.edit();
        editor.putBoolean("initialized", true);
        // adds user information to shared preferences
        for (int i = 0; i < adapter.getCount(); i++) {
            UserList user = adapter.getItem(i);
            editor.putString("Username", user.getUsername());
            editor.putString("Name", user.getName());
            editor.putString("Email", user.getEmail());
            editor.putString("ImageURL", user.getImageUrl());
            editor.putString("Title", user.getTitle());
        }
        editor.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // pulls user information from shared preferences
        for (int i = 1; i < sharedPreferences.getAll().size(); i = i+5) {
            sharedPreferences.getString("Username", user.getUsername());
            sharedPreferences.getString("Name", user.getName());
            sharedPreferences.getString("Email", user.getEmail());
            sharedPreferences.getString("ImageURL", user.getImageUrl());
            sharedPreferences.getString("Title", user.getTitle());
            adapter.add(new UserList(user.getUsername(), user.getName(),
                    user.getEmail(), user.getImageUrl(), user.getTitle()));
        }
    }

    private void makeJsonObjectRequest() {

        showpDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJson, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());

                try {
                    // Parsing json object response
                    // response will be a json object
                    JSONObject team = new JSONObject(response.toString());
                    JSONArray members = team.getJSONArray("members");

                    for (int i = 0; i < members.length(); i++) {
                        JSONObject user = members.getJSONObject(i);
                        JSONObject profile = user.getJSONObject("profile");
                        String username = user.getString("name");
                        if (!username.equals("slackbot")) {
                            String name = profile.getString("real_name");
                            String email = profile.getString("email");
                            String imageUrl = profile.getString("image_192");
                            String title = profile.getString("title");
                            adapter.add(new UserList(username, name, email, imageUrl, title));
                        }
                    }
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        mRequestQueue.add(jsonObjReq);
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
