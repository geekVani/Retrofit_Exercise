package com.example.retrofitexercise.view.login

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

class LoginFragment : Fragment() {

    private lateinit var buttonLogin: Button
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText

    private var isAllFieldsChecked = false
    private lateinit var dao: UserDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Set NavController for the LinearLayout view
//        val navController = Navigation.findNavController(requireActivity(), R.id.fragmentContainerViewLogin)
//        navController.setGraph(R.navigation.nav_login)
        // initialize nav fragment for login
//        val navHostFragmentLogin = requireActivity().supportFragmentManager.findFragmentById(R.id.fragmentContainerViewLogin) as NavHostFragment
//        val navControllerLogin = navHostFragmentLogin.navController

        val database = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "app-db").build()
        dao = database.getUserDao()

        buttonLogin = view.findViewById(R.id.loginPage)
        emailEditText = view.findViewById(R.id.loginEmail)
        passwordEditText = view.findViewById(R.id.loginPassword)

        buttonLogin.setOnClickListener {
            isAllFieldsChecked = CheckAllFields()

            if (isAllFieldsChecked){
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                // coroutine to handle and call suspend function
                lifecycleScope.launch {
                    val currUser: UserEntity? = dao.getUserByEmail(email)

                    if (currUser != null && currUser.userPwd == password) {
                        Navigation.findNavController(view)
                            .navigate(R.id.action_loginFragment3_to_listFragment3)

//                        navControllerLogin.navigate(R.id.action_loginFragment3_to_listFragment3)
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Invalid login crediantials",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
        // Inflate the layout for this fragment
        return view
    }
    // validation
    private fun CheckAllFields(): Boolean {
        if (emailEditText.length() == 0) {
            emailEditText.error = "Email is mandatory"
            return false
        }
        if (passwordEditText.length() == 0) {
            passwordEditText.error = "Password is mandatory"
            return false
        }
        return true
    }
}