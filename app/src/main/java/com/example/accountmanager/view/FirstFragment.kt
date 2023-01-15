package com.example.accountmanager.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.accountmanager.R
import com.example.accountmanager.databinding.FragmentFirstBinding
import com.example.accountmanager.model.AccountObject
import com.example.accountmanager.viewmodel.RecyclerViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), RecyclerAdapter.onAccountClickerListener {

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

        /*val recyclerList: ArrayList<AccountObject> = arrayListOf(
            AccountObject("Crunchyroll", "2144crunchyroll", "crunchyroll2144"),
            AccountObject("Hulu", "2144hulu", "hulu2144"), AccountObject("Netflix", "2144netflix", "netflix2144"),
            AccountObject("Gmail", "2144gmail", "gmail2144"), AccountObject("Twitter", "2144twitter", "twitter2144"),
            AccountObject("Instagram", "2144instagram", "instagram2144"),
            AccountObject("Facebook", "2144facebook", "facebook2144"),
            AccountObject("Snapchat", "2144snapchat", "snapchat2144"),
            AccountObject("BeReal", "2144bereal", "bereal2144"),
            AccountObject("Youtube", "2144youtube", "youtube2144")
        )*/
        val recyclerList: ArrayList<AccountObject> = ArrayList()

        recyclerViewModel.setList(recyclerList)

        binding.recyclerView.adapter = RecyclerAdapter(recyclerList, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onAccountClickerListener(position: Int) {
        recyclerViewModel.setChosenAccount(recyclerViewModel.getList()[position])
        findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }
}