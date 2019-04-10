package it.unicampania.lsadm.mybeers

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import it.unicampania.lsadm.mybeers.BirraEditFragment.Companion.PERMISSION_CODE

/**
 * Main activity
 */

var package_name : String? = null
var permission_granted : Boolean? = null
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        package_name = this.getPackageName()


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                    //show popup to request runtime permission
                    requestPermissions(permissions, BirraEditFragment.PERMISSION_CODE);
                }
            }

    }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
               when(requestCode){
                   PERMISSION_CODE -> {
                       if (grantResults.size >0 && grantResults[0] ==
                           PackageManager.PERMISSION_GRANTED){
                           //permission from popup granted
                           permission_granted = true
                       }
                       else{
                           //permission from popup denied
                           permission_granted = false
                       }
                   }
               }
           }
        }


