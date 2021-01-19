package id.dupat.chatkuy.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import id.dupat.chatkuy.entities.User
import id.dupat.chatkuy.other.Constant.Companion.USER_COLLECTION
import id.dupat.chatkuy.other.Corountines
import id.dupat.chatkuy.other.Encryption
import id.dupat.chatkuy.other.SingleLiveEvent
import id.dupat.chatkuy.remote.UserDatabase
import id.dupat.chatkuy.viewmodel.state.ViewState

class LoginViewModel: ViewModel() {
    private var state : SingleLiveEvent<ViewState> = SingleLiveEvent()
    private val userDb = UserDatabase()
    private val encrypt = Encryption()
    var username: String? = null
    var password: String? = null

    fun onUserLogin(v: View){
        state.value = ViewState.IsLoading(true)
        Corountines.main {
            val users = userDb.getUserByAccount(username!!,encrypt.encryptionMethod(password!!)!!)
            state.value = ViewState.IsLoading(false)
            if(users.isEmpty())
            {
                state.value = ViewState.Error("Account not found")
            }
            else{
                state.value = ViewState.IsSuccess(1,users[0])
            }
        }
    }

    fun getState() = state

}