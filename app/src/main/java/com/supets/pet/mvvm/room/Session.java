package com.supets.pet.mvvm.room;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

public class Session {

    private static AppDatabase session;

    public static AppDatabase getSession(Context context) {
        if (session == null) {
            session = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "database-name").addMigrations(MIGRATION_1_2).build();
        }
        return session;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

}
