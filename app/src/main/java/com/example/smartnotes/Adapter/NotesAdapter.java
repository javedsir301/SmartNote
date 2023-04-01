package com.example.smartnotes.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartnotes.Activity.UpdateNotesActivity;
import com.example.smartnotes.MainActivity;
import com.example.smartnotes.Model.Notes;
import com.example.smartnotes.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;


    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity = mainActivity;
        this.notes = notes;
    }

    @NonNull
    @Override
    public notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull notesViewHolder holder, int position) {

        Notes note = notes.get(position);

        switch (note.notesPriority) {
            case "1":
                holder.notesPriority.setBackgroundResource(R.drawable.green_priority_design);
                break;
            case "2":
                holder.notesPriority.setBackgroundResource(R.drawable.yellow_priority_design);

                break;
            case "3":
                holder.notesPriority.setBackgroundResource(R.drawable.red_priority_design);

                break;
        }


        holder.title.setText(note.notesTitle);
        holder.subtitle.setText(note.notesSubtitle);
        holder.notesDate.setText(note.notesDate);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mainActivity, UpdateNotesActivity.class);
                intent.putExtra("id", note.id);
                intent.putExtra("title", note.notesTitle);
                intent.putExtra("subtitle", note.notesSubtitle);
                intent.putExtra("note", note.notes);
                intent.putExtra("priority", note.notesPriority);

                mainActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }


    //-- we can also make this cls as static no prob!!
    class notesViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, notesDate;
        View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notes_title);
            subtitle = itemView.findViewById(R.id.notes_subtitle);
            notesDate = itemView.findViewById(R.id.notes_dates);
            notesPriority = itemView.findViewById(R.id.notes_priority);

        }
    }

}
