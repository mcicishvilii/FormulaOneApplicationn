package com.example.formulaoneapplicationn

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class CreatAccountFragment : Fragment() {

    companion object {
        fun newInstance() = CreatAccountFragment()
    }

    private lateinit var viewModel: CreatAccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_creat_account, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreatAccountViewModel::class.java)
        // TODO: Use the ViewModel
    }

}