package com.fanhl.rxcache.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.fanhl.rxcache.rcCache
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { refreshData() }
    }

    private fun refreshData() {
        Observable
                .create<Data> {
                    Thread.sleep(1000)
                    it.onNext(Data("io data"))
                }
                .subscribeOn(Schedulers.io())
                .rcCache("KEY", "id123", "car233"/*, type = object : TypeToken<CacheWrap<Data>>() {}.type*/)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            print(it.toString())
                        },
                        {
                            print("error")
                        }
                )
    }

    private fun print(msg: String) {
        Snackbar.make(fab, msg, Snackbar.LENGTH_LONG).show()
    }
}


class Data(var value: String) {
    override fun toString(): String {
        return value
    }
}