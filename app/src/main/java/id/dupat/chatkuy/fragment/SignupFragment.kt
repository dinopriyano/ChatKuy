package id.dupat.chatkuy.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.dupat.chatkuy.R
import id.dupat.chatkuy.databinding.FragmentSignupBinding
import id.dupat.chatkuy.other.chocobar
import id.dupat.chatkuy.other.isValidEmail
import id.dupat.chatkuy.other.snackbar
import id.dupat.chatkuy.other.toast
import id.dupat.chatkuy.viewmodel.SignupViewModel
import id.dupat.chatkuy.viewmodel.state.ViewState
import kotlinx.android.synthetic.main.fragment_signup.*
import kotlinx.android.synthetic.main.fragment_signup.btnLogin
import kotlinx.android.synthetic.main.fragment_signup.btnSignup
import kotlinx.android.synthetic.main.fragment_signup.txtPassword
import kotlinx.android.synthetic.main.fragment_signup.txtUsername
import kotlinx.android.synthetic.main.fragment_signup.view.*

class SignupFragment : Fragment(R.layout.fragment_signup),View.OnClickListener,TextWatcher {

    lateinit var viewModel: SignupViewModel
    private var binding: FragmentSignupBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SignupViewModel::class.java)
        binding = FragmentSignupBinding.bind(view)
        binding!!.viewmodel = viewModel
        btnLogin.setOnClickListener(this)
        txtEmail.addTextChangedListener(this)
        txtName.addTextChangedListener(this)
        txtUsername.addTextChangedListener(this)
        txtPassword.addTextChangedListener(this)
        requireActivity().toast("view created")
        handleUIState(view)
    }

    private fun handleUIState(view: View) {
        viewModel.getState().observer(requireActivity(), Observer {
            when (it) {
                is ViewState.IsLoading -> {
                    if (it.state) {
                        requireView().btnSignup.text = "."
                        requireView().progressSignup.visibility = View.VISIBLE
                    } else {
                        requireView().btnSignup.text = getString(R.string.login)
                        requireView().progressSignup.visibility = View.GONE
                    }
                }

                is ViewState.Error -> {
                    requireView().containerSignup.snackbar(it.err!!)
                }

                is ViewState.IsSuccess -> {
                    when (it.what) {
                        1 -> {
                            requireActivity().chocobar("Sign up success")
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnLogin -> {
                findNavController().popBackStack()
            }
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(!txtEmail.text.toString().isValidEmail()){
            tilEmail.isErrorEnabled = true
            tilEmail.error = "Email not valid"
        }
        else{
            tilEmail.isErrorEnabled = false
        }

        btnSignup.isEnabled = !(txtPassword.text.isNullOrEmpty() || txtUsername.text.isNullOrEmpty() || txtEmail.text.isNullOrEmpty() || txtName.text.isNullOrEmpty() || !txtEmail.text.toString().isValidEmail())
    }

    override fun afterTextChanged(s: Editable?) {

    }

}