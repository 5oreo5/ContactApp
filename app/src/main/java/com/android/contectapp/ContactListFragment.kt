package com.android.contectapp

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.contectapp.databinding.FragmentContactListBinding
import android.content.ContentResolver


class ContactListFragment : Fragment(R.layout.fragment_contact_list) {


    private lateinit var binding: FragmentContactListBinding
    private lateinit var rv: RecyclerView
    private lateinit var adapter: Adapter
    private lateinit var requestLauncher:ActivityResultLauncher<Intent>
    private var items = NewListRepository.getNewList()
    private var isGridMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding=FragmentContactListBinding.inflate(layoutInflater)
        //퍼미션 했는지체크
        val status= ContextCompat.checkSelfPermission(requireContext(),"android.permission.READ_CONTACTS")
        if(status==PackageManager.PERMISSION_GRANTED){
            Log.d("test","permission granted")
        }else{
            //안했으면 하라고 창 표시
            ActivityCompat.requestPermissions(requireActivity(),arrayOf<String>("android.permission.READ_CONTACTS"),100)
            Log.d("test","permission denied")
        }



    }

    //다이얼 로그에서 퍼미션 허용 확인

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactListBinding.inflate(layoutInflater)
        items.sortBy {it.name}
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv = binding.recyclerview
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = Adapter(items, isGridMode)

        rv.adapter = adapter

        adapter.setOnItemClickListener(object : Adapter.OnItemClickListener {

            override fun onItemClick(data: Item, position: Int) {

                val bundle = Bundle().apply{
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
    fun addData(contactName: String, phoneNumber: String) {
        val newItem = Item(
            image = 0, // 이미지 리소스 ID를 여기에 추가
            name = contactName,
            nickname = "",
            phone = phoneNumber,
            specialist = "",
            email = "",
            event = "",
            status = ""
        )

        // 아이템을 리스트에 추가
        items.add(newItem)
        if(::adapter.isInitialized) {

            adapter.notifyDataSetChanged()
        }
    }
}
