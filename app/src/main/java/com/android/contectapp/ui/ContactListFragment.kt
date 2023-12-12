package com.android.contectapp.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.FragmentContactListBinding
import android.content.Context
import com.android.contectapp.adapter.Adapter
import com.android.contectapp.alarm.CallHelper
import com.android.contectapp.util.Item
import com.android.contectapp.util.NewListRepository
import com.android.contectapp.R


class ContactListFragment : Fragment(R.layout.fragment_contact_list), ContactChangedListener {

    private lateinit var binding: FragmentContactListBinding
    private lateinit var rv: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var requestLauncher: ActivityResultLauncher<Intent>
    private var items = NewListRepository.getNewList()
    private var isGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(layoutInflater)
        items.sortBy { it.name }
        return binding.root
    }
    // data 변경될 때만 불리게끔
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onResume() {
//        super.onResume()
//        adapter.notifyDataSetChanged()
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.recyclerview
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = Adapter(items, isGridMode)

        rv.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {

            override fun onItemClick(data: Item, position: Int) {

                val bundle = Bundle().apply {
                    putInt("image", data.image)
                    putString("name", data.name)
                    putString("nickname", data.nickname)
                    putString("phone", data.phone)
                    putString("specialist", data.specialist)
                    putString("email", data.email)
                    putString("event", data.event)
                    putString("status", data.status)
                }
                val detailContactFragment = DetailContactFragment().apply {
                    arguments = bundle
                }
                detailContactFragment.show(parentFragmentManager, "DetailContactFragment")
            }
        })

        val addButton = binding.btnContactAddList
        addButton.setOnClickListener {
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            val dialogFragment = AddContactDialogFragment()
            dialogFragment.show(fragmentTransaction, "AddcontactDialogFragment")
        }

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

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        // 리스너를 구현한 Adapter 클래스를 Callback 클래스의 생성자로 지정
        val itemTouchHelperCallback = CallHelper(requireContext(), adapter)
        val helper = ItemTouchHelper(itemTouchHelperCallback)
        // RecyclerView에 ItemTouchHelper 연결
        helper.attachToRecyclerView(rv)
    }
    override fun onContactInserted() {
        adapter.notifyDataSetChanged()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivity) {
            context.listener = this
        }
    }
    override fun onDetach() {
        super.onDetach()
        (activity as? MainActivity)?.listener = null
    }
}