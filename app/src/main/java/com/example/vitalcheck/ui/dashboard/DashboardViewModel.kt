package com.seunome.vitalcheck.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seunome.vitalcheck.data.repository.VitalsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val repo: VitalsRepository
) : ViewModel() {

    private val _heartRate = MutableLiveData<Int>()
    val heartRate: LiveData<Int> = _heartRate

    private val _steps = MutableLiveData<Int>()
    val steps: LiveData<Int> = _steps

    private var job: Job? = null

    fun start() {
        if (job != null) return

        job = viewModelScope.launch {
            repo.observeVitals().collect { vitals ->
                _heartRate.value = vitals.heartRateBpm
                _steps.value = vitals.steps
            }
        }
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}