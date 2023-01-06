package com.fadiyah.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvUsers;
    private FloatingActionButton fabAddUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvUsers = findViewById(R.id.rv_users);
        fabAddUser = findViewById(R.id.fab_add_user);

        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        rvUsers.setLayoutManager(llm);

        fabAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        new GetAllUsers(MainActivity.this).execute();
    }

    public static class GetAllUsers extends AsyncTask<Void, Void, List<User>> {
        private WeakReference<Context> c;

        public GetAllUsers(Context c) {
            this.c = new WeakReference<>(c);
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            UserDatabase ud = UserDatabase.getAppDatabase(c.get());
            return ud.userDao().getAllUsers();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            RecyclerView rv = ((Activity) c.get()).findViewById(R.id.rv_users);

            UserAdapter ua = new UserAdapter(c.get(), users);
            rv.setAdapter(ua);
        }
    }
}
