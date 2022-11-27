package com.sample.faizan.vocabbuilder.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sample.faizan.vocabbuilder.models.VocabData
import kotlinx.coroutines.flow.Flow

@Dao
interface VocabDao {

    @Insert
    suspend fun insert(word: VocabData)

    @Delete
    fun delete(vocabData: VocabData)

    @Query("Select * from vocab_data")
    fun getVocabs(): Flow<List<VocabData>>
}