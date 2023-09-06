package com.android.contectapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.FragmentContactListBinding

class ContactListFragment : Fragment(R.layout.fragment_contact_list) {

    private lateinit var binding: FragmentContactListBinding
    private lateinit var rv: RecyclerView
    private lateinit var adapter: Adapter
    private var items = NewListRepository.getNewList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.recyclerview
        rv.layoutManager = LinearLayoutManager(requireContext())

        adapter = Adapter(items as MutableList<Item>)
        rv.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {

            override fun onItemClick(data: Item, position: Int) {
                val image = data.image
                val name = data.name
                val nickname = data.nickname
                val phone = data.phone
                val specialist = data.specialist
                val email = data.email
                val event = data.event
                val status = data.status

                val detailContactFragment = DetailContactFragment()
                val bundle = Bundle()
                bundle.putInt("image", image)
                bundle.putString("name", name)
                bundle.putString("nickname", nickname)
                bundle.putString("phone", phone)
                bundle.putString("specialist", specialist)
                bundle.putString("email", email)
                bundle.putString("event", event)
                bundle.putString("status", status)
                detailContactFragment.arguments = bundle

                detailContactFragment.show(parentFragmentManager, "DetailContactFragment")
            }
        })

        val addButton = binding.btnContactAddList
        addButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val dialogFragment = AddContactDialogFragment()
            dialogFragment.show(fragmentTransaction, "AddcontactDialogFragment")
        }
    }
}