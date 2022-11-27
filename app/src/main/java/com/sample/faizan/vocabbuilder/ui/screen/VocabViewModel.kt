package com.sample.faizan.vocabbuilder.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.faizan.vocabbuilder.models.VocabData
import com.sample.faizan.vocabbuilder.repository.VocabRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VocabViewModel @Inject constructor(var repository: VocabRepository): ViewModel() {

    private val _noteList = MutableStateFlow<List<VocabData>>(emptyList())
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllVocabs().distinctUntilChanged()
                .collect { list->
                    if (list.isNotEmpty()) _noteList.value = list
                }
        }
    }

    fun addVocab(vocabData: VocabData) = viewModelScope.launch {
        repository.addVocab(vocabData)
    }

    fun deleteVocab(vocabData: VocabData) = viewModelScope.launch {
        repository.deleteVocab(vocabData)
    }
}