package com.co.universidad.rutas.utilities
import android.app.Activity
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.widget.Toast

open class ManagePermissions(private val activity: Activity, private val permissionList: List<String>, val code: Int) {


    fun checkPermissions() {
        if (isPermissionsGranted() != PackageManager.PERMISSION_GRANTED) {
            showAlert()
        } else {
            Toast.makeText(
                activity, "This is my Toast message!",
                Toast.LENGTH_LONG
            ).show();
        }
    }

    private fun isPermissionsGranted(): Int {
        var counter = 0;
        for (permission in permissionList) {
            counter += ContextCompat.checkSelfPermission(activity, permission)
        }
        return counter
    }


    private fun deniedPermission(): String {
        for (permission in permissionList) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    permission
                ) == PackageManager.PERMISSION_DENIED
            ) return permission
        }
        return ""
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Requerimos")
        builder.setMessage("Some permissions are required to do the task.")
        builder.setPositiveButton("OK") { _, _ -> requestPermissions() }
        builder.setNeutralButton("Cancel", null)
        val dialog = builder.create()
        dialog.show()
    }


    // Request the permissions at run time
    fun requestPermissions() {
        val permission = deniedPermission()
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            // Show an explanation asynchronously
            Toast.makeText(
                activity, "This is my Toast message!",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(activity, permissionList.toTypedArray(), code)
        }
    }

    fun processPermissionsResult(grantResults: IntArray): Boolean {
        var result = 0
        if (grantResults.isNotEmpty()) {
            for (item in grantResults) {
                result += item
            }
        }
        if (result == PackageManager.PERMISSION_GRANTED) return true
        return false
    }
}