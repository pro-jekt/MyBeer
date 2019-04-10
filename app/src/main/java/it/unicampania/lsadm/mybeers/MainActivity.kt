package it.unicampania.lsadm.mybeers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

/**
 * Main activity
 */

var package_name : String? = null
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        package_name = this.getPackageName()
    }


}
