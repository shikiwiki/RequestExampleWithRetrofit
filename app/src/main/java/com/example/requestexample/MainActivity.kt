package com.example.requestexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.requestexample.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            progressBar.visibility = View.VISIBLE
            theId.visibility = View.GONE
            userId.visibility = View.GONE
            title.visibility = View.GONE
            body.visibility = View.GONE
        }

        postRequest()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getUser()
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        theId.visibility = View.VISIBLE
                        userId.visibility = View.VISIBLE
                        body.visibility = View.VISIBLE
                        title.visibility = View.VISIBLE

                        theId.text = "id: ${response.body()!!.id}"
                        userId.text = "user id: ${response.body()!!.userId}"
                        title.text = response.body()!!.title
                        body.text = response.body()!!.body
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    @OptIn(DelicateCoroutinesApi::class)
    private fun postRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                //val user = User("new body",null,"new title",23)
                RetrofitInstance.api.createUrlPost(23, "url title", "url body")

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(binding.root, "${response.code()}", Snackbar.LENGTH_INDEFINITE)
                        .show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        theId.visibility = View.VISIBLE
                        userId.visibility = View.VISIBLE
                        body.visibility = View.VISIBLE
                        title.visibility = View.VISIBLE

                        theId.text = "id: ${response.body()!!.id.toString()}"
                        userId.text = "user id: ${response.body()!!.userId}"
                        title.text = "title: ${response.body()!!.title}"
                        body.text = "body: ${response.body()!!.body}"
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun putRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                val user = User(null, 10, "title", "body")
                RetrofitInstance.api.putPost(23, user)

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(
                        binding.root,
                        "code: ${response.code()}",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        theId.visibility = View.VISIBLE
                        userId.visibility = View.VISIBLE
                        body.visibility = View.VISIBLE
                        title.visibility = View.VISIBLE

                        theId.text = "id: ${response.body()!!.id.toString()}"
                        userId.text = "user id: ${response.body()!!.userId}"
                        title.text = "title: ${response.body()!!.title}"
                        body.text = "body: ${response.body()!!.body}"
                    }
                }
            }
        }
    }

    private fun patchRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {

                val user = User( null, 20, null, "patch body",)
                RetrofitInstance.api.patchPost(23, user)

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(
                        binding.root,
                        "code: ${response.code()}",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        theId.visibility = View.VISIBLE
                        userId.visibility = View.VISIBLE
                        body.visibility = View.VISIBLE
                        title.visibility = View.VISIBLE

                        theId.text = "id: ${response.body()!!.id.toString()}"
                        userId.text = "user id: ${response.body()!!.userId}"
                        title.text = "title: ${response.body()!!.title}"
                        body.text = "body: ${response.body()!!.body}"
                    }
                }
            }
        }
    }

    private fun deleteRequest() {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {


                RetrofitInstance.api.deletePost(23)

            } catch (e: Exception) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    Snackbar.make(
                        binding.root,
                        "code: ${response.code()}",
                        Snackbar.LENGTH_INDEFINITE
                    ).show()
                    binding.apply {
                        progressBar.visibility = View.GONE
                        theId.visibility = View.VISIBLE
                        userId.visibility = View.VISIBLE
                        body.visibility = View.VISIBLE
                        title.visibility = View.VISIBLE

                        theId.text = "id: ${response.body()!!.id.toString()}"
                        userId.text = "user id: ${response.body()!!.userId}"
                        title.text = "title: ${response.body()!!.title}"
                        body.text = "body: ${response.body()!!.body}"
                    }
                }
            }
        }
    }
}