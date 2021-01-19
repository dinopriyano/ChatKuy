package id.dupat.chatkuy.viewmodel

import android.view.View
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import id.dupat.chatkuy.entities.User
import id.dupat.chatkuy.other.Encryption
import id.dupat.chatkuy.other.SingleLiveEvent
import id.dupat.chatkuy.remote.UserDatabase
import id.dupat.chatkuy.viewmodel.state.ViewState

class SignupViewModel: ViewModel() {
    private var state : SingleLiveEvent<ViewState> = SingleLiveEvent()
    private val userDb = UserDatabase()
    private val encrypt = Encryption()
    var username: String? = null
    var password: String? = null
    var email: String? = null
    var fullname: String? = null

    fun onUserRegister(v: View){
        state.value = ViewState.IsLoading(true)
        val user = User(Timestamp.now(),email!!,fullname!!,"","",encrypt.encryptionMethod(password!!)!!,"",username!!)
        val taskRegister = userDb.addUser(user)
        taskRegister
            .addOnSuccessListener {
                state.value = ViewState.IsLoading(false)
                state.value = ViewState.IsSuccess(1)
            }
            .addOnFailureListener {
                state.value = ViewState.IsLoading(false)
                state.value = ViewState.Error(it.message)
            }
    }

    fun getState() = state
}