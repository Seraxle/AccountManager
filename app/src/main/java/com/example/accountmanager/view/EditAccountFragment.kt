package com.example.accountmanager.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.accountmanager.databinding.FragmentEditAccountBinding
import com.example.accountmanager.databinding.FragmentFirstBinding
import com.example.accountmanager.model.AccountObject
import com.example.accountmanager.viewmodel.AccountViewModel
import com.example.accountmanager.viewmodel.RecyclerViewModel

class EditAccountFragment: Fragment() {

    private var _binding: FragmentEditAccountBinding? = null
    private lateinit var accountViewModel: AccountViewModel
    private val recyclerViewModel: RecyclerViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditAccountBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        val account = recyclerViewModel.getChosenAccount()

        if (account != null) {
            binding.editAccountName.setText(account.platform, TextView.BufferType.EDITABLE)
            binding.editAccountUsername.setText(account.username, TextView.BufferType.EDITABLE)
            binding.editAccountPassword.setText(account.password, TextView.BufferType.EDITABLE)
        }

        binding.editAccountButton.setOnClickListener {
            if (!IsInvalidInput()) {
                val acct = account?.let { it1 ->
                    AccountObject(it1.id,
                        binding.editAccountName.text.toString(),
                        binding.editAccountUsername.text.toString(),
                        binding.editAccountPassword.text.toString()
                    )
                }
                if (acct != null) {
                    accountViewModel.updateAccount(acct)
                }
                Toast.makeText(this.context, "Account successfully updated", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            else Toast.makeText(this.context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }

        binding.deleteAccountButton.setOnClickListener {
            if (account != null) {
                accountViewModel.deleteAccount(account)
            }
            Toast.makeText(this.context, "Account successfully delete", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

    }

    private fun IsInvalidInput(): Boolean {
        return binding.editAccountName.text.toString().isEmpty()
                || binding.editAccountUsername.text.toString().isEmpty()
                || binding.editAccountPassword.text.toString().isEmpty()
    }
}