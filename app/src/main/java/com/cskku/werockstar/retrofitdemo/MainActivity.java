package com.cskku.werockstar.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cskku.werockstar.retrofitdemo.api.GithubAPI;
import com.cskku.werockstar.retrofitdemo.model.Github;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private String BASE_URL = "https://api.github.com";
    Button click;
    TextView tv;
    EditText edit_user;
    ProgressBar pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        click = (Button) findViewById(R.id.button);
        tv = (TextView) findViewById(R.id.tv);
        edit_user = (EditText) findViewById(R.id.edit);
        pbar = (ProgressBar) findViewById(R.id.pb);
        pbar.setVisibility(View.INVISIBLE);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edit_user.getText().toString();
                pbar.setVisibility(View.VISIBLE);
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(BASE_URL)
                        .build();

                GithubAPI git = adapter.create(GithubAPI.class);

                git.getFeed(user, new Callback<Github>() {
                    @Override
                    public void success(Github github, Response response) {
                        tv.setText("Github name : "+github.getName() + "\nWebsits : "+github.getBlog() + "");
                        pbar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        pbar.setVisibility(View.INVISIBLE);
                        Log.d("FAILURE" , error.getMessage());
                    }
                });
            }
        });
    }
}
