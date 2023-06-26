package com.javahand.taipeiattractions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.javahand.taipeiattractions.databinding.FragmentAllAttractionsBinding
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModel
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AllAttractionsFragment : Fragment() {

    private var _binding: FragmentAllAttractionsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllAttractionsBinding.inflate(
            inflater,
            container,
            false
        ) // invoke

        return binding.root
    } // fun onCreateView( LayoutInflater, ViewGroup?, Bundle?)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<AllAttractionsViewModel>(
            factoryProducer = {
                AllAttractionsViewModelFactory.provide(this)
            } // lambda
        ) // invoke

        viewModel.invalidatePagingSource()

        val attractions = viewModel.attractions
        val allAttractionsAdapter = AllAttractionsAdapter()

        binding.recylerAllAttractions.run {
            adapter = allAttractionsAdapter
            layoutManager = LinearLayoutManager(context)
        } // run

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                attractions.collectLatest {
                    allAttractionsAdapter.submitData(it)
                } // lambda
            } // lambda
        } // launch
    } // fun onViewCreated( View, Bundle?)

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    } // fun onDestroyView()
}