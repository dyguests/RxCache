package com.fanhl.rxcache.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.fanhl.rxcache.rcCache
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { refreshData() }
    }

    private fun refreshData() {
        Observable
                .create<String> {
                    Thread.sleep(1000)
                    it.onNext("io data")
                }
                .subscribeOn(Schedulers.io())
                .rcCache("KEY", "id123", "car233")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { print(it) }
    }

    private fun print(msg: String) {
        Snackbar.make(fab, msg, Snackbar.LENGTH_LONG).show()
    }
}
