package com.sample.faizan.vocabbuilder.repository

import com.sample.faizan.vocabbuilder.database.VocabDao
import com.sample.faizan.vocabbuilder.models.VocabData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VocabRepository @Inject constructor(val dao: VocabDao) {

    suspend fun addVocab(vocabData: VocabData) = dao.insert(vocabData)

    fun deleteVocab(vocabData: VocabData) = dao.delete(vocabData)

    fun getAllVocabs(): Flow<List<VocabData>> =dao.getVocabs().flowOn(Dispatchers.IO).conflate()
}