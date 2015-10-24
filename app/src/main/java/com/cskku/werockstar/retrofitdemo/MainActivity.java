package com.cskku.werockstar.retrofitdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cskku.werockstar.retrofitdemo.adapter.GithubAdapter;
import com.cskku.werockstar.retrofitdemo.api.GithubAPI;
import com.cskku.werockstar.retrofitdemo.model.Github;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://api.github.com";
    Button btnSearch;
    EditText edtUser;
    RecyclerView recyclerUserList;
    ProgressBar progressBar;
    TextView txtError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearch = (Button) findViewById(R.id.btnSearch);
        edtUser = (EditText) findViewById(R.id.editUser);
        recyclerUserList = (RecyclerView) findViewById(R.id.gitList);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtError = (TextView) findViewById(R.id.txtError);

        progressBar.setVisibility(View.INVISIBLE);
        txtError.setVisibility(View.INVISIBLE);

        recyclerUserList.setHasFixedSize(true);
        recyclerUserList.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isNetworkConnected()) {
                    progressBar.setVisibility(View.VISIBLE);
                    String user = edtUser.getText().toString();

                    RestAdapter adapter = new RestAdapter.Builder()
                            .setEndpoint(BASE_URL)
                            .build();

                    GithubAPI git = adapter.create(GithubAPI.class);

                    git.getFeed(user, new Callback<Github>() {
                        @Override
                        public void success(Github githubs, Response response) {
                            progressBar.setVisibility(View.INVISIBLE);
                            txtError.setVisibility(View.INVISIBLE);

                            List<Github> list = new ArrayList<Github>();
                            list.add(githubs);

                            GithubAdapter githubAdapter = new GithubAdapter(list);
                            recyclerUserList.setAdapter(githubAdapter);
                            edtUser.setText("");
                        }

                        @Override
                        public void failure(RetrofitError error) {

                            progressBar.setVisibility(View.INVISIBLE);
                            txtError.setVisibility(View.VISIBLE);
                            Log.d("FAILURE", error.getMessage());
                        }
                    });
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this)
                            .setMessage("Please connect internet")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                    builder.show();
                }
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
