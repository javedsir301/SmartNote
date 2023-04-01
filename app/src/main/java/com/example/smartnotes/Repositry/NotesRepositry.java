package com.example.smartnotes.Repositry;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.smartnotes.Dao.NotesDao;
import com.example.smartnotes.Database.NotesDatabase;
import com.example.smartnotes.Model.Notes;

import java.util.List;

public class NotesRepositry {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;


    public NotesRepositry(Application application) {
        NotesDatabase database = NotesDatabase.getDatabaseInstance(application);
        notesDao = database.notesDao();
        getallNotes = notesDao.getallNotes();
    }

   public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }

     public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }

}
