package com.sample.faizan.vocabbuilder.modules

import android.content.Context
import androidx.room.Room
import com.sample.faizan.vocabbuilder.database.VocabDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Singleton
    @Provides
    fun provideVocabDao(vocabDatabase: VocabDatabase) = vocabDatabase.dao()

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): VocabDatabase =
        Room.databaseBuilder(
            context, VocabDatabase::class.java,
            "vocab_db")
            .fallbackToDestructiveMigration()
            .build()
}