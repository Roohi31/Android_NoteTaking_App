package me.sabapro.udemy_notetaking_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.sabapro.udemy_notetaking_app.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase(){

    // template code only to change to entities and below name

    abstract fun getNoteDao() : NoteDAO

    companion object{
        @Volatile
        private var instance : NoteDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
            synchronized(LOCK){
                instance ?:
                createDatabase(context).also{
                    instance = it
                }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java,
                "note_db"
            ).build()

    }

}