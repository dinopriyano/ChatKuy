package id.dupat.chatkuy.other

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.NavOptions
import com.google.android.material.snackbar.Snackbar
import com.pd.chocobar.ChocoBar
import id.dupat.chatkuy.R

fun Context.toast(msg: String)
{
    Toast.makeText(this,msg, Toast.LENGTH_LONG).show()
}

fun View.snackbar(msg: String)
{
    Snackbar.make(this,msg, Snackbar.LENGTH_LONG).also { snackbar ->
        snackbar.setActionTextColor(resources.getColor(R.color.modern_blue))
        snackbar.setAction("OK"){
            snackbar.dismiss()
        }
    }.show()
}

fun Int.navOption() : NavOptions
{
    return NavOptions.Builder().setPopUpTo(this, true).build()
}

fun Context.hideKeyboard(view: View) {

    var imm: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken,0)

}

fun Context.showKeyboard() {
    (getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager)?.apply {
        toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun Activity.chocobar(msg: String)
{
    ChocoBar.builder().also { cb ->
        cb.setActivity(this)
        cb.setActionText("OK")
        cb.setActionTextColor(resources.getColor(R.color.modern_blue))
        cb.setText(msg)
        cb.setDuration(ChocoBar.LENGTH_LONG)
    }.build().show()
}
