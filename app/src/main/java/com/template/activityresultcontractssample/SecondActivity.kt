package com.template.activityresultcontractssample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        findViewById<Button>(R.id.button_result_ok).setOnClickListener {
            setResult(RESULT_OK, Intent().putExtra("key", "result OK"))
            finish()
        }
        findViewById<Button>(R.id.button_result_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED, Intent().putExtra("key", "result Cancel"))
            finish()
        }
    }
}