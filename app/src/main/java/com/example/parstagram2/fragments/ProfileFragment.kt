package com.example.parstagram2.fragments

import android.util.Log
import com.example.parstagram2.Post
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser

class ProfileFragment: FeedFragment() {

    override fun queryPosts(){
        // Specify which class to query
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        //finds all posts objects in our server
        query.include(Post.KEY_USER)
        // only return posts from currently signed in user
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
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
}