package com.seunome.vitalcheck.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.seunome.vitalcheck.data.repository.VitalsRepository

class DashboardViewModelFactory(
    private val repo: VitalsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DashboardViewModel(repo) as T
    }
}