package com.example.fetchrewardsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardsapp.adapter.FetchAdapter
import com.example.fetchrewardsapp.databinding.FetchItemBinding
import com.example.fetchrewardsapp.model.FetchListItem
import com.example.fetchrewardsapp.network.FetchApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
     lateinit var fetchAdapter: FetchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = recyclerView.findViewById(R.id.recycler_view)
        fetchAdapter = FetchAdapter(this)

        val layoutManager = LinearLayoutManager(
            this, RecyclerView.VERTICAL, false
        ).apply {
            recyclerView.layoutManager = this
        }
        DividerItemDecoration(
            this, layoutManager.orientation
        ).apply {
            recyclerView.addItemDecoration(this)
        }

        recyclerView.adapter = fetchAdapter

        val fetchApi = FetchApi.create().fetchItems()

        fetchApi.enqueue(object : Callback<List<FetchListItem>?> {
            override fun onResponse(
                call: Call<List<FetchListItem>?>,
                response: Response<List<FetchListItem>?>
            ) {
                if (response.isSuccessful) {
                    fetchAdapter.setFetchData(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<FetchListItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }


}