package com.javahand.taipeiattractions.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.javahand.taipeiattractions.R
import com.javahand.taipeiattractions.databinding.FragmentAttractionBinding
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModel
import com.javahand.taipeiattractions.viewmodel.AllAttractionsViewModelFactory

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AttractionFragment : Fragment() {

    private var _binding: FragmentAttractionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAttractionBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by activityViewModels<AllAttractionsViewModel>(
            factoryProducer = {
                AllAttractionsViewModelFactory.provide(this)
            } // lambda
        ) // invoke

        (activity as AppCompatActivity).supportActionBar?.let {
            viewModel.attraction.value?.run {
                it.title = peekContent().name
            } // run
        } // let
    } // fun onViewCreated( View, Bundle?)

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}