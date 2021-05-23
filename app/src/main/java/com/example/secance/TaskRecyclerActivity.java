package com.example.secance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class TaskRecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] s1;
    String[] s2;
    int[] images = {R.drawable.plumbing, R.drawable.mow, R.drawable.roof, R.drawable.electricals};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_recycler);

        recyclerView = findViewById(R.id.recyclerView);

        s1 = getResources().getStringArray(R.array.Tasks);
        s2 = getResources().getStringArray(R.array.description);

        TaskRecyclerActivityAdapter recyclerViewAdapter = new TaskRecyclerActivityAdapter(this, s1, s2,images);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}