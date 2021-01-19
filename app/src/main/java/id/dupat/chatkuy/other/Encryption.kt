package id.dupat.chatkuy.other

import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.SecretKeySpec

class Encryption {
    private val encryptionKey = byteArrayOf(3, 19, 27, 9, 17, 99, -21, 10, -99, 29, 18, -70, 6, -103, 109, -74)
    private var cipher: Cipher? = null
    private  var decipher:Cipher? = null
    private var secretKeySpec: SecretKeySpec? = null

    init {
        try {
            cipher = Cipher.getInstance("AES")
            decipher = Cipher.getInstance("AES")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        } catch (e: NoSuchPaddingException) {
            e.printStackTrace()
        }

        secretKeySpec = SecretKeySpec(encryptionKey, "AES")
    }

    fun encryptionMethod(string: String): String? {
        val stringByte = string.toByteArray()
        var encryptedByte: ByteArray? = ByteArray(stringByte.size)
        try {
            cipher!!.init(Cipher.ENCRYPT_MODE, secretKeySpec)
            encryptedByte = cipher!!.doFinal(stringByte)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        var returnString: String? = null
        try {
            returnString = String(encryptedByte!!, charset("ISO-8859-1"))
        }
        catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return returnString
    }

    @Throws(UnsupportedEncodingException::class)
    fun decryptionMethod(string: String): String? {
        val EncryptedByte = string.toByteArray(charset("ISO-8859-1"))
        var decryptedString: String? = string
        val decryption: ByteArray
        try {
            decipher!!.init(Cipher.DECRYPT_MODE, secretKeySpec)
            decryption = decipher!!.doFinal(EncryptedByte)
            decryptedString = String(decryption)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        }
        return decryptedString
    }
}