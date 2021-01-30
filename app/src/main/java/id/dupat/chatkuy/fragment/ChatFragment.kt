package id.dupat.chatkuy.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.dupat.chatkuy.R
import kotlinx.android.synthetic.main.activity_navigation.*

class ChatFragment : Fragment(R.layout.fragment_chat) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().bottom_nav.visibility = View.VISIBLE
    }
}