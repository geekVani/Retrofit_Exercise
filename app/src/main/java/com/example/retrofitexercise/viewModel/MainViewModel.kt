package com.example.retrofitexercise.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.retrofitexercise.model.retrofit.Entries
import com.example.retrofitexercise.model.retrofit.Item
import com.example.retrofitexercise.api.RetrofitInstance
import com.example.retrofitexercise.model.roomDB.UserEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {
    private var userLiveData = MutableLiveData<List<Entries>>()

    fun fetchDataFromApi(){

        RetrofitInstance.api.getAllData().enqueue(object :Callback<Item>{
            override fun onResponse(
                call: Call<Item>,
                response: Response<Item>) {
                if (response.body() != null) {

                    Log.d("msg", "Successfully receiving data")
                    userLiveData.value = response.body()!!.entries
                    /*// add response to arraylist from rest api
                    val dataItem = response.body()
                    val dataEntries = dataItem?.entries ?: emptyList()
                    adapter = DataAdapter(dataEntries)
                    recyclerView.adapter = adapter*/

                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<Item>, t: Throwable) {
                    t.localizedMessage?.let {
                        Log.d("ERROR", it)
                    }
                    /*Toast.makeText(response
                        ,t.localizedMessage
                        , Toast.LENGTH_LONG).show()*/
                }
            })
        }

    fun observeUserLiveData(): LiveData<List<Entries>>{
        return userLiveData
    }
}