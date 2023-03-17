package com.example.apiconsumption.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.apiconsumption.databinding.FragmentDashboardBinding
import com.example.apiconsumption.models.Post
import org.json.JSONArray


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val posts: ArrayList<Post> = ArrayList();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // ...
        // Lookup the recyclerview in activity layout
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvPosts = binding.listTextRecycler

        val queue = Volley.newRequestQueue(activity!!.applicationContext)

        val request = JsonArrayRequest(
            Request.Method.GET,
            "https://jsonplaceholder.typicode.com/posts",
            null,
            { response ->
                onResponse(response)
                val adapter = PostsAdapter(posts)
                rvPosts.adapter = adapter
                rvPosts.layoutManager = LinearLayoutManager(activity!!.applicationContext)
            }, { err ->
                println(err)
            })
        queue.add(request)

        // Attach the adapter to the recyclerview to populate items
        return root;
    }

    fun onResponse(response: JSONArray) {
        for (i in 0 until response.length()) {
            var post: Post?
            val json = response.getJSONObject(i)
            post = Post(
                json.getInt("id"),
                json.getString("title"),
                json.getString("body")
            )
            posts.add(post)
        }
        System.out.println(posts)
    }
}