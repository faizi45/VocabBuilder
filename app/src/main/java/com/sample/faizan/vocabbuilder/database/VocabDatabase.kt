package com.sample.faizan.vocabbuilder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.faizan.vocabbuilder.models.VocabData

@Database(entities = [VocabData::class], version = 1, exportSchema = false)
abstract class VocabDatabase: RoomDatabase() {

    abstract fun dao() : VocabDao
}