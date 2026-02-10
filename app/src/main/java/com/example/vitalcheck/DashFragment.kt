package com.seunome.vitalcheck.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.vitalcheck.R
import com.seunome.vitalcheck.data.repository.VitalsRepository

class DashFragment : Fragment() {

    private lateinit var tvHeartRate: TextView
    private lateinit var tvSteps: TextView
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Isso cria (infla) a UI a partir do XML fragment_dashboard.xml
        return inflater.inflate(R.layout.fragment_dash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) Pegar referências dos componentes do XML (TextViews)
        tvHeartRate = view.findViewById(R.id.tvHeartRate)
        tvSteps = view.findViewById(R.id.tvSteps)

        // 2) Criar repository (mock de sensores)
        val repo = VitalsRepository()

        // 3) Criar ViewModel usando Factory (sem DI)
        val factory = DashboardViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        // 4) Observar dados do ViewModel e atualizar UI
        viewModel.heartRate.observe(viewLifecycleOwner) { bpm ->
            tvHeartRate.text = "Frequência cardíaca: $bpm bpm"
        }

        viewModel.steps.observe(viewLifecycleOwner) { steps ->
            tvSteps.text = "Passos: $steps"
        }

        // 5) Começar a coletar o Flow do repository
        viewModel.start()
    }
}