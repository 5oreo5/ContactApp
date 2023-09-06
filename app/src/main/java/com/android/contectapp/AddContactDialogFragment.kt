package com.android.contectapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Intent
import android.widget.Toast
import com.android.contectapp.databinding.FragmentAddContactDialogBinding
import java.util.regex.Pattern



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [AddContactDialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddContactDialogFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var useTextWatcher: TextWatcher
    private lateinit var binding : FragmentAddContactDialogBinding



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
        binding = FragmentAddContactDialogBinding.inflate(inflater,container,false)
        //알람 context설정
        alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(requireContext(), RecyclerviewAdapter::class.java)
        pendingIntent = PendingIntent.getBroadcast(requireContext(), 0, intent,
            PendingIntent.FLAG_IMMUTABLE)

        binding.addNotiOffBtn.setOnClickListener {
            // 알람 끄기
            alarmManager.cancel(pendingIntent)
        }

        binding.addNoti10Btn.setOnClickListener {
            // 10분 뒤 알람 설정
            val delayMillis = 10 * 60 * 1000L
            val triggerAtMillis = System.currentTimeMillis() + delayMillis
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        }

        binding.addNoti20Btn.setOnClickListener {
            // 20분 뒤 알람 설정
            val delayMillis = 20 * 60 * 1000L
            val triggerAtMillis = System.currentTimeMillis() + delayMillis
            alarmManager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent)
        }


        var saveBtn = binding.addSaveBtn
        var cancelBtn = binding.addCancelBtn
        var editName = binding.addEditName
        var editMobile= binding.addMobileEdit
        var editSpecial = binding.addSpecialEdit
        var editMail = binding.addMailEdit
        
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

        saveBtn.setOnClickListener() {
            // 이름, 번호, 담당 ,메일주소
            val name = editName.text.toString()
            val mobile = editMobile.text.toString()
            val special = editSpecial.text.toString()
            val mail = editMail.text.toString()

            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (mobile.isEmpty()) {
                Toast.makeText(requireContext(), "번호를 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (special.isEmpty()) {
                Toast.makeText(requireContext(), "담당을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (mail.isEmpty()) {
                Toast.makeText(requireContext(), "이메일을 입력해주세요!", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                Toast.makeText(requireContext(), "이메일 형식이 아닙니다", Toast.LENGTH_SHORT).show()
            } else if (!Pattern.matches("^01(?:0|1|[6-9]) - (?:\\d{3}|\\d{4}) - \\d{4}$", mobile)) {
                Toast.makeText(requireContext(), "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
            } else if (!Pattern.matches("^[가-힣]*\$", special)) {
                Toast.makeText(requireContext(), "한글만 입력해 주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "회원가입 완료!", Toast.LENGTH_SHORT).show()

            }


            cancelBtn.setOnClickListener() {
                requireActivity().supportFragmentManager.popBackStack()
            }


        }
        return binding.root


    }

}
