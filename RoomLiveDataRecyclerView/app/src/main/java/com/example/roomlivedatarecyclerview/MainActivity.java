package com.example.roomlivedatarecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomlivedatarecyclerview.Room.DatabaseSingletonClient;
import com.example.roomlivedatarecyclerview.Room.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    UserViewModel userViewModel;
    MyCustomAdapter mAdapter;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private RecyclerView entriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllData().observe(this, users -> mAdapter.setData(users));

        mAdapter = new MyCustomAdapter(this, userViewModel.getAllData().getValue());
        initUi();
        setUpRecyclerView();
    }
    private void setUpRecyclerView() {
        entriesRecyclerView = findViewById(R.id.entriesRecyclerView);
        entriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        entriesRecyclerView.setHasFixedSize(true);
        entriesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        entriesRecyclerView.setAdapter(mAdapter);
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

            //add Entry
            userViewModel.saveUserData(userObj);
            Toast.makeText(this, "Successfully Added", Toast.LENGTH_SHORT).show();
        }


    }
}
