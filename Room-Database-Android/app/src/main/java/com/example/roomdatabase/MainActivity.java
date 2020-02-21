package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomdatabase.Room.DatabaseSingletonClient;
import com.example.roomdatabase.Room.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private MyCustomAdapter mAdapter;
    private RecyclerView entriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
    }


    private void initUi() {
        firstNameEditText = (EditText) findViewById(R.id.firstName);
        lastNameEditText = (EditText) findViewById(R.id.lastName);
    }

    public void addEntryClick(View view) {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();

        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)) {

            User userObj = new User();
            userObj.setFirstName(firstName);
            userObj.setLastName(lastName);

            //insert
            DatabaseSingletonClient
                    .getInstance(getApplicationContext())
                    .getAppDatabase()
                    .userDao()
                    .insert(userObj);
        }

        Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();

    }

    public void viewEntriesClick(View view) {

        //getAll
        List<User> usersList = DatabaseSingletonClient
                .getInstance(getApplicationContext())
                .getAppDatabase()
                .userDao()
                .getAll();

        setUpRecyclerView(usersList);
    }

    private void setUpRecyclerView(List<User> usersList) {
        entriesRecyclerView = (RecyclerView) findViewById(R.id.entriesRecyclerView);
        mAdapter = new MyCustomAdapter(this, usersList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        entriesRecyclerView.setLayoutManager(mLayoutManager);
        entriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        entriesRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}
