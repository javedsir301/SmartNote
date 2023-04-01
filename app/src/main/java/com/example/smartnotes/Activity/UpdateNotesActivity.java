package com.example.smartnotes.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartnotes.Model.Notes;
import com.example.smartnotes.R;
import com.example.smartnotes.ViewModel.NotesViewModel;
import com.example.smartnotes.databinding.ActivityUpdateNotesBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

public class UpdateNotesActivity extends AppCompatActivity {
    ActivityUpdateNotesBinding binding;
    String priority = "3";
    String stitle, sSubtitle, snotes, spriority;
    NotesViewModel notesViewModel;
    int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        iid = getIntent().getIntExtra("id", 0);
        stitle = getIntent().getStringExtra("title");
        sSubtitle = getIntent().getStringExtra("subtitle");
        snotes = getIntent().getStringExtra("note");
        spriority = getIntent().getStringExtra("priority");

        binding.topicUpdateEt.setText(stitle);
        binding.headingUpdateEt.setText(sSubtitle);
        binding.writeUpdateNotes.setText(snotes);


        if (spriority.equals("1")) {
            binding.greenPriority.setImageResource(R.drawable.check_btn);
        } else if (spriority.equals("2")) {
            binding.yellowPriority.setImageResource(R.drawable.check_btn);
        } else if (spriority.equals("3")) {
            binding.redPriority.setImageResource(R.drawable.check_btn);
        }


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


        binding.updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = binding.topicUpdateEt.getText().toString();
                String subtitle = binding.headingUpdateEt.getText().toString();
                String notes = binding.writeUpdateNotes.getText().toString();

                UpdateNotes(title, subtitle, notes);

            }
        });

    }

    private void UpdateNotes(String title, String subtitle, String notes) {


        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());


        Notes updateNotes = new Notes();
        updateNotes.id = iid;
        updateNotes.notesTitle = title;
        updateNotes.notesSubtitle = subtitle;
        updateNotes.notes = notes;
        updateNotes.notesPriority = priority;
        updateNotes.notesDate = sequence.toString();


        notesViewModel.updateNote(updateNotes);


        Toast.makeText(this, "Update Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_action_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_button) {

            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this);

            View view = LayoutInflater.from(UpdateNotesActivity.this).
                    inflate(R.layout.delete_bottom_design, (LinearLayout) findViewById(R.id.bottom_alert_delete));

            sheetDialog.setContentView(view);

            TextView yes, no;

            yes = view.findViewById(R.id.yes_delete);
            no = view.findViewById(R.id.no_delete);

            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    notesViewModel.deleteNote(iid);
                    finish();

                }
            });

            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheetDialog.dismiss();
                }
            });

            sheetDialog.show();
        }
        return true;
    }
}