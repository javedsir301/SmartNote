package com.example.smartnotes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.smartnotes.Activity.AddNotesActivity;
import com.example.smartnotes.Adapter.NotesAdapter;
import com.example.smartnotes.ViewModel.NotesViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton newNotes;
    NotesViewModel notesViewModel;
    RecyclerView notes_mainRecyclerView;
    NotesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotes = findViewById(R.id.new_Notes_btn);
        notes_mainRecyclerView = findViewById(R.id.notes_mainRecyclerView);

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        newNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddNotesActivity.class));
            }
        });

            notesViewModel.getAllNotes.observe(this, notes -> {

                notes_mainRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
                adapter = new NotesAdapter(MainActivity.this,notes);
                notes_mainRecyclerView.setAdapter(adapter);

            });

    }
}