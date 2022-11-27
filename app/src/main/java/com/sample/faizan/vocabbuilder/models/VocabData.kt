package com.sample.faizan.vocabbuilder.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "vocab_data")
data class VocabData(
    @PrimaryKey
    val id: UUID = UUID.randomUUID(),

    var word: String,
    var meaning: String,
    var sentence: String

//    @ColumnInfo(name = "note_entry_date")
//    val entryDate: Date = Date.from(Instant.now())

)
