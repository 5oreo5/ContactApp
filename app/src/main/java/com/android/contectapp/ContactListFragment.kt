package com.android.contectapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.FragmentContactListBinding


class ContactListFragment : Fragment(R.layout.fragment_contact_list) {

    private lateinit var binding : FragmentContactListBinding
    private lateinit var rv : RecyclerView
    private lateinit var adapter : Adapter
    private var items = NewListRepository.getNewList()
    private var isGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    private val items = NewListRepository.getNewList().also {
        Log.d(log, "${it.size}뉴리포지터리")
    }
    var log="로그"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        adapter = Adapter(items, isGridMode)
        
        rv.adapter = adapter
        val spinner = binding.mainSpinner
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                // 라이브러리
                Log.d("p2", "onItemSelected: p2 =$p2")

                isGridMode = p2 == 1
                rv.layoutManager = if (isGridMode) {
                    GridLayoutManager(context, 3)
                } else {
                    LinearLayoutManager(context)
                }
                Log.d("isGridMode", "isGridMode=$isGridMode")
                adapter.setGridMode(isGridMode)
                rv.adapter = adapter // 계속 같은 holder를 쓰는걸 다시 만들어준다.
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        // 리스너를 구현한 Adapter 클래스를 Callback 클래스의 생성자로 지정
        val itemTouchHelperCallback = CallHelper(requireContext(), adapter)
        // ItemTouchHelper의 생성자로 ItemTouchHelper.Callback 객체 셋팅
        val helper = ItemTouchHelper(itemTouchHelperCallback)
        // RecyclerView에 ItemTouchHelper 연결
        helper.attachToRecyclerView(rv)
        
        return binding.root
    }

    private fun updateRecyclerViewMode() {
        adapter = Adapter(items as MutableList<Item>, isGridMode)
        rv.layoutManager = if (isGridMode) {
            GridLayoutManager(requireContext(), 2)
        } else {
            LinearLayoutManager(requireContext())
        }
        rv.adapter = adapter
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            rv = binding.recyclerview
            rv.layoutManager = LinearLayoutManager(requireContext())
            adapter = Adapter(items, isGridMode)

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
