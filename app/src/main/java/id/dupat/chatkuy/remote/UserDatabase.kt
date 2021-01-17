package id.dupat.chatkuy.database

import com.google.firebase.firestore.FirebaseFirestore
import id.dupat.chatkuy.entities.User
import id.dupat.chatkuy.other.Constant
import kotlinx.coroutines.tasks.await

class UserDatabase {
    val db = FirebaseFirestore.getInstance()
    val userColection = db.collection(Constant.USER_COLLECTION)

    suspend fun getUserByAccount(username: String, password: String): List<User> {
        return try{
            userColection
                    .whereEqualTo("username",username)
                    .whereEqualTo("password",password)
                    .get()
                    .await()
                    .toObjects(User::class.java)
        }
        catch (e : Exception){
            emptyList()
        }
    }
}