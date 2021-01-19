package id.dupat.chatkuy.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.dupat.chatkuy.R
import id.dupat.chatkuy.databinding.FragmentLoginBinding
import id.dupat.chatkuy.entities.User
import id.dupat.chatkuy.other.Encryption
import id.dupat.chatkuy.other.snackbar
import id.dupat.chatkuy.other.toast
import id.dupat.chatkuy.viewmodel.LoginViewModel
import id.dupat.chatkuy.viewmodel.state.ViewState
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener, TextWatcher {

    lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    var resEnc: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(LoginViewModel::class.java)
        binding.viewmodel = viewModel
        btnSignup.setOnClickListener(this)
        btnForgotPass.setOnClickListener(this)
        btnLoginWithGoogle.setOnClickListener(this)
        txtUsername.addTextChangedListener(this)
        txtPassword.addTextChangedListener(this)
        handleUIState()
    }

    private fun handleUIState() {
        viewModel.getState().observer(requireActivity(), Observer {
            when (it) {
                is ViewState.IsLoading -> {
                    if (it.state) {
                        btnLogin.text = ""
                        progressLogin.visibility = View.VISIBLE
                    } else {
                        btnLogin.text = getString(R.string.login)
                        progressLogin.visibility = View.GONE
                    }
                }

                is ViewState.Error -> {
                    containerLogin.snackbar(it.err!!)
                }

                is ViewState.IsSuccess -> {
                    when (it.what) {
                        1 -> {
                            val data = it.data as User
                            Log.d("TAG", "handleUIState: ${data}")
                        }
                    }
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnSignup -> {
                val action = LoginFragmentDirections.actionLoginFragmentToSignupFragment()
                findNavController().navigate(action)
            }

            R.id.btnForgotPass -> {
                val enc = Encryption()
                val encr = enc.encryptionMethod("pass")
                resEnc = encr
                requireActivity().toast(encr!!)
            }

            R.id.btnLoginWithGoogle -> {
                val enc = Encryption()
                val encr = enc.decryptionMethod(resEnc!!)
                requireActivity().toast(encr!!)
            }
        }
    }



    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        btnLogin.isEnabled = !(txtPassword.text.isNullOrEmpty() || txtUsername.text.isNullOrEmpty())
    }

    override fun afterTextChanged(s: Editable?) {

    }
}