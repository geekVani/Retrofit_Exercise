package com.example.retrofitexercise.view.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.room.Room
import com.example.retrofitexercise.R
import com.example.retrofitexercise.model.roomDB.UserDao
import com.example.retrofitexercise.model.roomDB.UserDatabase
import com.example.retrofitexercise.model.roomDB.UserEntity
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterFragment : Fragment() {

    private lateinit var buttonRegister: Button
    private lateinit var fullname: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var confirmPwd: TextInputEditText
    private var isAllFieldsChecked = false
    private lateinit var dao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_register, container, false)


        // Set NavController for the LinearLayout view
//        val navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewRegister)
//        navController.setGraph(R.navigation.nav_graph)

//        val navHostFragmentRegister = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerViewRegister) as NavHostFragment
//        val navControllerRegister = navHostFragmentRegister.navController
        val database = Room.databaseBuilder(requireContext(),
        UserDatabase::class.java, "app-db").build()
        dao = database.getUserDao()

        buttonRegister = view.findViewById(R.id.registerPage)
        fullname = view.findViewById(R.id.registerName)
        email = view.findViewById(R.id.registerEmail)
        phone = view.findViewById(R.id.registerPhone)
        password = view.findViewById(R.id.registerPassword)
        confirmPwd = view.findViewById(R.id.registerConfirm)

        buttonRegister.setOnClickListener {
            isAllFieldsChecked = checkAllFields()
            if (isAllFieldsChecked){
                val newUser = UserEntity(
                    email.text.toString(),
                    phone.text.toString(),
                    fullname.text.toString(),
                    password.text.toString(),
                    confirmPwd.text.toString()
                )

                lifecycleScope.launch {
                    dao.insert(newUser)
                }
//                navControllerRegister.navigate(R.id.action_registerFragment_to_loginFragment2)

                Navigation.findNavController(view)
                    .navigate(R.id.action_registerFragment_to_loginFragment2)
            }
        }


        // Inflate the layout for this fragment
        return view
    }

    // validation
    private fun checkAllFields(): Boolean {
        if (email.text.toString().isEmpty()){
            email.error = "Email is mandatory"
            return false
        }
        if (password.length() ==0 || confirmPwd.length() ==0){
            Toast.makeText(requireActivity(), "Password field/s missing", Toast.LENGTH_SHORT).show()
            return false
        }
        if (fullname.length() ==0){
            Toast.makeText(requireActivity(), "Name field missing", Toast.LENGTH_SHORT).show()
            return false
        }
        if (phone.length() ==0){
            phone.error = "Phone number is mandatory"
            return false
        }
        return true
    }
}