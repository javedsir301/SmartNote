package com.example.smartnotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Toast;

import com.example.smartnotes.Model.Notes;
import com.example.smartnotes.R;
import com.example.smartnotes.ViewModel.NotesViewModel;
import com.example.smartnotes.databinding.ActivityAddNotesBinding;

import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {

    ActivityAddNotesBinding binding;
    String title, subtitle, notes;
    NotesViewModel notesViewModel;
    String priority = "3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        binding.greenPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(R.drawable.check_btn);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                priority = "1";

            }
        });

        binding.yellowPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.check_btn);
                binding.redPriority.setImageResource(0);
                priority = "2";

            }
        });

        binding.redPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.check_btn);
                priority = "3";

            }
        });


        binding.doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title = binding.topicEt.getText().toString();
                subtitle = binding.headingEt.getText().toString();
                notes = binding.writeNotes.getText().toString();

                CreateNotes(title, subtitle, notes);
            }
        });

    }

    private void CreateNotes(String title, String subtitle, String notes) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());


        Notes notes1 = new Notes();
        notes1.notesTitle = title;
        notes1.notesSubtitle = subtitle;
        notes1.notes = notes;
        notes1.notesPriority = priority;
        notes1.notesDate = sequence.toString();

        notesViewModel.insertNote(notes1);


        Toast.makeText(this, "Noted Saved Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}