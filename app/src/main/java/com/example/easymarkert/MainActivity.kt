package com.example.easymarkert

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.easymarkert.model.Data
import com.example.easymarkert.model.reqRes
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*



class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var cartFragment: CartFragment
    lateinit var chatFragment: ChatFragment
    lateinit var userFragment: UserFragment

    private val dataList: MutableList<Data> = mutableListOf()
    private lateinit var myAdapter: myAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val MyAdapter = myAdapter(dataList)

//        set up recycleView
        rvGet.layoutManager = LinearLayoutManager(this)
        rvGet.addItemDecoration(DividerItemDecoration(this, OrientationHelper.VERTICAL))
        rvGet.adapter = MyAdapter

//        set android networking
        AndroidNetworking.initialize(this)

        AndroidNetworking.get("https://reqres.in/api/users?page=2")
            .build()
            .getAsObject(reqRes::class.java, object : ParsedRequestListener<reqRes>{
                override fun onResponse(response: reqRes) {
                    dataList.addAll(response.data)

                    MyAdapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                    TODO("Not yet implemented")
                }

            })


//      Bottom navigation root
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.BottomNavigationMenu)
//      Defaut router
        homeFragment = HomeFragment()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, homeFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit()


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.content, homeFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()
                }

                R.id.cart -> {
                    cartFragment = CartFragment()
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.content, cartFragment)
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                            .commit()

                }

                R.id.chat -> {
                    chatFragment = ChatFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, chatFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }

                R.id.user -> {
                    userFragment = UserFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.content, userFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()

                }

            }
            true
        }

    }
}