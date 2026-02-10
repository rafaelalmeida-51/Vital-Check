package com.example.vitalcheck.ui.sintomas

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vitalcheck.data.local.entity.SintomasEntity
import com.example.vitalcheck.data.repository.SintomasRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SintomasViewModel(
    private val repo: SintomasRepository
) : ViewModel() {

    private val _itens = MutableLiveData<List<SintomasEntity>>(emptyList())
    val itens: LiveData<List<SintomasEntity>> = _itens

    private var observeJob: Job? = null

    fun start() {
        // evita começar duas vezes
        if (observeJob != null) return

        observeJob = viewModelScope.launch {
            repo.observeSintomas().collect { list ->
                _itens.value = list
            }
        }
    }

    fun addSintomas(text: String) {
        viewModelScope.launch {
            repo.addSintomas(text)
        }
    }

    override fun onCleared() {
        observeJob?.cancel()
        super.onCleared()
    }
}
