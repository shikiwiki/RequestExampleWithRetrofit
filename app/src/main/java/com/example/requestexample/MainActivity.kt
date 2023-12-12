package com.example.requestexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "CHECK RESPONSE"

class MainActivity : AppCompatActivity() {
    private val mainUrl = "https://jsonplaceholder.typicode.com/"

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api = Retrofit.Builder()
            .baseUrl(mainUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AlbumService::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            val response = api.getAlbums()
            if (response.isSuccessful) {
                for (item in response.body()!!) {
                    Log.i(TAG, "getAllAlbums: ${item.title}")
                }
            }
        }
    }
}