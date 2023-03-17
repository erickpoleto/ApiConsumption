package com.example.apiconsumption.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.apiconsumption.databinding.FragmentHomeBinding
import com.example.apiconsumption.models.User


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        println("here");
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome

        val queue = Volley.newRequestQueue(activity!!.applicationContext)

        val request = JsonObjectRequest(Request.Method.GET,
            "https://jsonplaceholder.typicode.com/users/1",
            null,
            {response ->
                println("here $response")
                var user: User? = null
                user = User(
                    response.getInt("id"),
                    response.getString("username"),
                    response.getString("email")
                )
                textView.text = "Olá " + user.username;
            }, { err ->
                println(err)
                textView.text = "!error!"
            })
        queue.add(request)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}