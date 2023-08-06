package com.example.retrofitexercise.view.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexercise.api.adapter.DataAdapter
import com.example.retrofitexercise.R
import com.example.retrofitexercise.api.RetrofitInstance
import com.example.retrofitexercise.model.retrofit.Item
import com.example.retrofitexercise.viewModel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DataAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_list, container, false)


        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewData)
        recyclerView.layoutManager = LinearLayoutManager(context)

        fetchDataFromApi()

        /*prepareRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.fetchDataFromApi()
        viewModel.observeUserLiveData().observe(viewLifecycleOwner,
        Observer { dataList->
            adapter.dataList
        })
    }
    private fun prepareRecyclerView(){
        adapter = DataAdapter(emptyList())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = adapter
        }*/

    }
    fun fetchDataFromApi() {

        RetrofitInstance.api.getAllData().enqueue(object : Callback<Item> {
            override fun onResponse(
                call: Call<Item>,
                response: Response<Item>
            ) {
                if (response.body() != null) {

                    Log.d("msg", "Successfully receiving data")

                    // add response to arraylist from rest api
                    val dataItem = response.body()
                    val dataEntries = dataItem?.entries ?: emptyList()
                    adapter = DataAdapter(dataEntries) { url->
                        navigateToWeb(url)
                    }
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())

                } else {
                    return
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                t.localizedMessage?.let {
                    Log.d("ERROR", it)
                }
            }
        })
    }

    private fun navigateToWeb(url: String) {
        val bundle = bundleOf("url" to url)
        findNavController().navigate(R.id.action_listFragment2_to_webFragment, bundle)
    }
}