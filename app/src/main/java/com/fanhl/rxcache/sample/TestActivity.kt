package com.fanhl.rxcache.sample

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { Snackbar.make(it, "Replace with your own action", Snackbar.LENGTH_LONG).show() }
    }

}
