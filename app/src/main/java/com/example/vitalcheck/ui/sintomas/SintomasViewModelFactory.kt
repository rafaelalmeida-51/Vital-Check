package com.example.vitalcheck.ui.sintomas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vitalcheck.data.repository.SintomasRepository

class SintomasViewModelFactory(
    private val repo: SintomasRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SintomasViewModel(repo) as T
    }
}

