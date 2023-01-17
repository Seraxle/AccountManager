package com.example.accountmanager.view

import android.accounts.Account
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentAddAccountBinding
import com.example.accountmanager.databinding.FragmentFirstBinding
import com.example.accountmanager.model.AccountObject
import com.example.accountmanager.viewmodel.AccountViewModel
import com.example.accountmanager.viewmodel.RecyclerViewModel

class AddAccountFragment : Fragment() {

    private var _binding: FragmentAddAccountBinding? = null
    private lateinit var accountViewModel: AccountViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddAccountBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

        binding.addAccountButton.setOnClickListener {
            if (!isInValidInput()) {
                val account = AccountObject(
                    0,
                    binding.accountName.text.toString(),
                    binding.accountUsername.text.toString(),
                    binding.accountPassword.text.toString()
                )

                accountViewModel.addAccount(account)
                Toast.makeText(this.context, "Account successfully added", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            else Toast.makeText(this.context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isInValidInput(): Boolean {
        return binding.accountName.text.toString().isEmpty()
                || binding.accountUsername.text.toString().isEmpty()
                || binding.accountPassword.text.toString().isEmpty()
    }
}