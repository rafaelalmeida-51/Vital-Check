package com.example.vitalcheck.ui.sintomas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vitalcheck.R
import com.example.vitalcheck.data.local.db.AppDatabase
import com.example.vitalcheck.data.repository.SintomasRepository

class SintomasFragment : Fragment() {

    private lateinit var etSintoma: EditText
    private lateinit var btnSalvar: Button
    private lateinit var rvSintomas: RecyclerView

    private lateinit var adapter: SintomasAdapter
    private lateinit var viewModel: SintomasViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Cria a tela a partir do XML
        return inflater.inflate(R.layout.fragment_sintomas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1) Referências da UI (findViewById)
        etSintoma = view.findViewById(R.id.etSintomas)
        btnSalvar = view.findViewById(R.id.btnSalvarSintoma)
        rvSintomas = view.findViewById(R.id.rvSintomas)

        // 2) RecyclerView
        adapter = SintomasAdapter()
        rvSintomas.layoutManager = LinearLayoutManager(requireContext())
        rvSintomas.adapter = adapter

        // 3) Montagem manual (sem DI): Database -> DAO -> Repository -> ViewModel
        val db = AppDatabase.getInstance(requireContext())
        val repo = SintomasRepository(db.sintomaDao())
        val factory = SintomasViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory)[SintomasViewModel::class.java]

        // 4) Observa lista e atualiza RecyclerView
        viewModel.itens.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }

        // 5) Botão salvar
        btnSalvar.setOnClickListener {
            val texto = etSintoma.text.toString()
            viewModel.addSintomas(texto)
            etSintoma.setText("")
        }

        // 6) Começa a observar o banco (Flow -> LiveData)
        viewModel.start()
    }
}