package com.example.findgithub.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.findgithub.api.RetrofitClient
import com.example.findgithub.data.model.User
import com.example.findgithub.data.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserViewModel : ViewModel() {
    val listUsers = MutableLiveData<ArrayList<User>>()

    fun setFindUser(query: String) {
        RetrofitClient.apiInstance
            .getSearchUsers(query)
            .enqueue(object : Callback<UserResponse>{
                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful){
                        listUsers.postValue(response.body()?.items)
                    }
                }
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                   Log.d("Failure", t.message.toString())
                }
            })
    }
    fun getFindUsers(): LiveData<ArrayList<User>>{
        return listUsers
    }
}