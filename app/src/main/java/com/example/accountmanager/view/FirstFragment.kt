package com.example.accountmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentFirstBinding
import com.example.accountmanager.model.AccountObject
import com.example.accountmanager.viewmodel.AccountViewModel
import com.example.accountmanager.viewmodel.RecyclerViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), RecyclerAdapter.onAccountClickerListener {

    private lateinit var accountViewModel: AccountViewModel

    private var _binding: FragmentFirstBinding? = null
    private val recyclerViewModel: RecyclerViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        accountViewModel.readAllData().observe(viewLifecycleOwner) { accounts ->
            binding.recyclerView.adapter = RecyclerAdapter(accounts, this)
            recyclerViewModel.setList(accounts)
        }

        val recyclerList: ArrayList<AccountObject> = ArrayList()

        recyclerViewModel.setList(recyclerList)

        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.setHasFixedSize(true)

        binding.addAccountFab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_addAccountFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAccountClickerListener(position: Int) {
        recyclerViewModel.getList()?.get(position)?.let { recyclerViewModel.setChosenAccount(it) }
        findNavController().navigate(R.id.action_FirstFragment_to_editAccountFragment)
    }
}