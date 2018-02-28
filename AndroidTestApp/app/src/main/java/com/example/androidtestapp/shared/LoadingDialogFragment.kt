package com.example.androidtestapp.shared

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.TextView
import com.example.androidtestapp.R

/**
 * Created by akiyama on 2018/02/26.
 */
class LoadingDialogFragment: DialogFragment() {

    companion object {
        val ARGS_KEY_MESSAGE = "ARGS_KEY_MESSAGE"

        fun newInstance(message: String? = null): LoadingDialogFragment {
            val f = LoadingDialogFragment()

            val args = Bundle()
            message?.let { args.putString(ARGS_KEY_MESSAGE, it) }
            f.arguments = args

            return f
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity).apply {
            val view = activity?.layoutInflater?.inflate(R.layout.dialog_loading, null)
            val message = arguments?.getString(ARGS_KEY_MESSAGE, getString(R.string.dialog_loading_message_loading))
            view?.findViewById<TextView>(R.id.loading_dialog_message)?.text = message
            setView(view)
        }

        return builder.create()
    }
}