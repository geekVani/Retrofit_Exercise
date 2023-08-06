package com.example.retrofitexercise.view.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import androidx.databinding.DataBindingUtil
import com.example.retrofitexercise.R
import com.example.retrofitexercise.databinding.CustomListitemBinding
import com.example.retrofitexercise.databinding.FragmentWebBinding

class WebFragment : Fragment() {
    lateinit var binding: CustomListitemBinding
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // view binding
        val binding: FragmentWebBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_web,
            container, false
        )
        webView = binding.webView
        webView.settings.javaScriptEnabled = true


        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val url = arguments?.getString("url") ?: ""
        webView.loadUrl(url)
    }
}