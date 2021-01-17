package id.dupat.chatkuy.other
import android.util.Patterns

fun String.isValidEmail() : Boolean
{
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}