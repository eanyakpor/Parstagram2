package com.example.parstagram2.fragments

import android.os.Bundle
import android.util.Log
import android.util.proto.ProtoOutputStream
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parstagram2.MainActivity
import com.example.parstagram2.Post
import com.example.parstagram2.PostAdapter
import com.example.parstagram2.R
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery


open class FeedFragment : Fragment() {

    lateinit var postsRecyclerView: RecyclerView

    lateinit var adapter: PostAdapter

    var allPosts:MutableList<Post> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This is where we setup our views and click listeners

        postsRecyclerView = view.findViewById(R.id.postRecyclerView)

        //Steps to populate a RecyclerView
        //1. Create layout for each row in list
        //2. Create data source for each row (this is the Post class)
        //3. Create adapter that will bridge data and row layout (Post adapter class)
        //4. Set adapter on RecyclerView
        adapter = PostAdapter(requireContext(), allPosts)
        postsRecyclerView.adapter = adapter
        //5. Set layout manager on RecyclerView
        postsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()
    }
    //Query for all posts in our server
    open fun queryPosts() {
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //finds all posts objects in our server
        query.include(Post.KEY_USER)
        //return posts in descending order, new posts appear first
        query.addDescendingOrder("createdAt")
        query.findInBackground(object: FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if(e != null) {
                    Log.e(TAG, "Error fetching posts")
                } else {
                    if(posts!= null) {
                        for(post in posts){
                            Log.i(TAG, "Post: " + post.getDescription() + ", username: " +
                                    post.getUser()?.username)
                        }
                        allPosts.addAll(posts)
                        adapter.notifyDataSetChanged()
                    }
                }
            }


        })
    }
    companion object{
        const val TAG = "FeedFragment"
    }

}