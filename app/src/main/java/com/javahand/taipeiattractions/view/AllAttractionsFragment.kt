package com.javahand.taipeiattractions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.javahand.taipeiattractions.R
import com.javahand.taipeiattractions.databinding.FragmentAllAttractionsBinding
import com.javahand.taipeiattractions.i18n.LanguagePreference
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

        val viewModel by activityViewModels<AllAttractionsViewModel>(
            factoryProducer = {
                AllAttractionsViewModelFactory.provide(this)
            } // lambda
        ) // invoke

        if (LanguagePreference.hasBeenChanged) {
            viewModel.invalidatePagingSource()
            LanguagePreference.clearChangedFlag()
        } // if

        val attractions = viewModel.attractions
        val allAttractionsAdapter = AllAttractionsAdapter { attraction ->
            viewModel.attractionClicked(attraction)
        } // constructor -> trailing lambda

        binding.recylerAllAttractions.run {
            adapter = allAttractionsAdapter
            layoutManager = LinearLayoutManager(context)
        } // run

        viewLifecycleOwner.lifecycleScope.launch {
            // 如果將此段 collectLatest 放入下面的 launch 中，會導致無資料載入
            allAttractionsAdapter.loadStateFlow.collectLatest {
                if (it.refresh is LoadState.NotLoading) {
                    binding.progressAllAttractions.visibility = View.GONE
                } // if
            } // collectLatest
        } // launch

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                attractions.collectLatest {
                    allAttractionsAdapter.submitData(it)
                } // collectLatest
            } // lambda
        } // launch

        viewModel.attraction.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.run {
                findNavController().navigate(
                    R.id.action_AllAttractionsFragment_to_AttractionFragment
                ) // invoke
            } // run
        } // lambda
    } // fun onViewCreated( View, Bundle?)

    override fun onDestroyView() {
        _binding = null

        super.onDestroyView()
    } // fun onDestroyView()
}