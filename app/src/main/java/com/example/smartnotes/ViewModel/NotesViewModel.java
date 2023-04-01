package com.example.smartnotes.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.smartnotes.Model.Notes;
import com.example.smartnotes.Repositry.NotesRepositry;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    public NotesRepositry repositry;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(@NonNull Application application) {
        super(application);

         repositry = new NotesRepositry(application);
         getAllNotes = repositry.getallNotes;

    }

    public void insertNote(Notes notes){
        repositry.insertNotes(notes);

    }

    public void deleteNote(int id){
        repositry.deleteNotes(id);

    }

    public void updateNote(Notes notes){
        repositry.updateNotes(notes);

    }

}
