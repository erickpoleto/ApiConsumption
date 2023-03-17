package com.example.apiconsumption.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apiconsumption.R
import com.example.apiconsumption.models.Post

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
class PostsAdapter (private val posts: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
        
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Your holder should contain and initialize a member variable
        // for any view that will be set as you render a row
        val titleTextView = itemView.findViewById<TextView>(R.id.text_in_list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val dashView = inflater.inflate(R.layout.fragment_dashboard, parent, false)
        // Return a new holder instance
        return ViewHolder(dashView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: PostsAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val post: Post = posts[position]
        // Set item views based on your views and data model
        val textView = viewHolder.titleTextView
        textView.text = post.title + "" + post.body
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return posts.size
    }
}