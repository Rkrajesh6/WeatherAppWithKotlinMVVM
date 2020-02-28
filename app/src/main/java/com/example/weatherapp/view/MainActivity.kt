package com.example.weatherapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.R
import com.example.weatherapp.adapter.MainAdapter
import com.example.weatherapp.util.Constants
import com.example.weatherapp.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mViewModel: MainViewModel
    val TAG = MainActivity::class.java.simpleName
    private lateinit var mMainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViewModel()

        btnSearch.setOnClickListener {
            val view = this.currentFocus
            if (view != null) {
                val imm =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            if (!editText.text.isEmpty()) {
                shimmer_text.visibility = View.VISIBLE
                shimmer_text.startShimmerAnimation()
                setRecyclerview()
                if (Constants.isNetworkAvailable(this)) {
                    successconstraintlayout.visibility = View.VISIBLE
                    val zipcode = editText.text.toString() + ",IN"
                    mViewModel.getCurrentWeatherData(zipcode)
                } else {
                    successconstraintlayout.visibility = View.GONE
                    editText.error = getString(R.string.please_enter_valid_zip_code)
                    editText.isFocusable = true
                }
            } else {
                shimmer_text.visibility = View.GONE
                shimmer_text.stopShimmerAnimation()
                editText.error = getString(R.string.please_enter_the_zip_code)
                editText.isFocusable = true
            }
        }
    }

    private fun setRecyclerview() {
        mMainAdapter = MainAdapter(this, mViewModel.listData.value ?: emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = mMainAdapter
    }

    private fun setViewModel() {
        mViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        mViewModel.successfulLiveData.observe(this, Observer { listItem ->
            listItem?.let {
                Log.e(TAG, "success response = $it")
                shimmer_text.visibility = View.GONE
                shimmer_text.stopShimmerAnimation()
                mMainAdapter.update(it)
            }
        })

        mViewModel.failureLiveData.observe(this, Observer { isFailed ->
            isFailed?.let {
                Log.e(TAG, "failure response = $it")
                successconstraintlayout.visibility = View.GONE
                editText.error = getString(R.string.please_enter_valid_zip_code)
                editText.isFocusable = true
            }
        })
    }
}
