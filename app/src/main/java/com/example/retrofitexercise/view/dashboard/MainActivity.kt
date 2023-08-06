package com.example.retrofitexercise.view.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.retrofitexercise.R
import com.example.retrofitexercise.databinding.ActivityMainBinding
import com.example.retrofitexercise.view.login.LoginFragment
import com.example.retrofitexercise.view.register.RegisterFragment

class MainActivity : AppCompatActivity() {

    lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.btnLogin.setOnClickListener {
            mainBinding.btnLogin.isVisible = false
            mainBinding.btnSignUp.isVisible = false
            mainBinding.imgWelcome.isVisible = false
            mainBinding.fragmentContainerViewLogin.isVisible = true
            mainBinding.fragmentContainerViewRegister.isVisible = false
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerViewLogin
                    , LoginFragment())
                .commit()
        }

        mainBinding.btnSignUp.setOnClickListener {
            mainBinding.btnLogin.isVisible = false
            mainBinding.btnSignUp.isVisible = false
            mainBinding.imgWelcome.isVisible = false
            mainBinding.fragmentContainerViewRegister.isVisible = true
            mainBinding.fragmentContainerViewLogin.isVisible = false
            supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragmentContainerViewRegister, RegisterFragment())
                .commit()
        }
    }
}