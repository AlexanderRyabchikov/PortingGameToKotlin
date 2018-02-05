package com.example.skender.kotlin

import android.content.Context
import android.app.AlertDialog
import android.view.GestureDetector
import android.view.MotionEvent
import android.content.DialogInterface


/**
 * Created by skender on 06.02.18.
 */
class GestureListener(var context: Context) : GestureDetector.SimpleOnGestureListener() {

    @Override
    override fun onDown(e: MotionEvent): Boolean = true

    override fun onDoubleTap(e: MotionEvent): Boolean{
        var builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Change window")
                .setMessage("You double tap")
                .setCancelable(false)
                .setNegativeButton("OK",
                        DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
                            @Override
                            fun onClick(dialog: DialogInterface, wich: Int) = dialog.cancel()
                        })
        var alertDialog: AlertDialog = builder.create()
        alertDialog.show()
        return true
    }
}