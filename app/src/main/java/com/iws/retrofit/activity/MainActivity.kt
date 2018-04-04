package com.iws.retrofit.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.iws.retrofit.R
import com.iws.retrofit.model.CountryList
import com.iws.retrofit.rest.APIClass
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var binding: ViewDataBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this@MainActivity, R.layout.activity_main)
        textView.movementMethod = ScrollingMovementMethod()
        getCountryList()
    }

    private fun getCountryList() {
        val countryList: Call<CountryList> = APIClass.getRetrofitInterface().countrylist
        countryList.enqueue(object : Callback<CountryList> {
            override fun onFailure(call: Call<CountryList>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<CountryList>?, response: Response<CountryList>?) {
                Log.e("Response", response!!.body().country.toString())
                binding!!.setVariable(0,response.body().country.toString())
                for (i in 0 until response.body().country.size)
                    textView.text = textView.text.toString() + "\n" + response.body().country[i].countryName
            }
        })
    }
}
