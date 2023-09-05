package com.android.contectapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addContactDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class addContactDialogFragment : Fragment(){
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var useTextWatcher: TextWatcher




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)






        //isCancelable = true
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_contact_dialog, container, false)
        val saveBtn = view.findViewById<Button>(R.id.add_saveBtn)
        val cancleBtn = view.findViewById<Button>(R.id.add_cancelBtn)
        val editName = view.findViewById<EditText>(R.id.add_editName)
        val editMobile = view.findViewById<EditText>(R.id.add_mobileEdit)
        val editSpecial = view.findViewById<EditText>(R.id.add_specialEdit)
        val editMail = view.findViewById<EditText>(R.id.add_mailEdit)

        useTextWatcher = object : TextWatcher {
            val maxLength = 15
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length ?: 0 > maxLength) {
                    editName.error = "최대 $maxLength 글자까지 입력 가능합니다."
                } else {
                    editName.error = null // 이전에 설정된 오류 지우기.
                }
            }
        }



        saveBtn.setOnClickListener(){
            // 이름, 번호, 담당 ,메일주소
            val name = editName.text.toString()
            val mobile = editMobile.text.toString()
            val special = editSpecial.text.toString()
            val mail = editMail.text.toString()

            when {
                (name.isEmpty()) -> {
                    Toast.makeText(requireContext(),"아이디를 입력해주세요!",Toast.LENGTH_SHORT).show()
                }
                (mobile.isEmpty())->{
                    Toast.makeText(requireContext(),"번호를 입력해주세요!",Toast.LENGTH_SHORT).show()
                }
                (special.isEmpty())->{
                    Toast.makeText(requireContext(),"담당과를 입력해주세요!",Toast.LENGTH_SHORT).show()
                }
                (mail.isEmpty())->{
                    Toast.makeText(requireContext(),"이메일을 입력해주세요!",Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(),DetailContactFragment::class.java)
                    intent.putExtra("aName",name)
                    intent.putExtra("aMobile",mobile)
                    intent.putExtra("aSpecial",special)
                    intent.putExtra("aMail",mail)


                }

            }

        }

        cancleBtn.setOnClickListener(){
            DetailContactFragment()
        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact_dialog, container, false)
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment addContactDialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addContactDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}