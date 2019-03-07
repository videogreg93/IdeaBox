package com.gregory.ideabox.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.gregory.ideabox.models.Category
import com.gregory.ideabox.models.Idea
import com.gregory.ideabox.models.User
import com.gregory.ideabox.system.readList
import com.gregory.ideabox.system.readValue

class FirebaseManager() {
    private val db = FirebaseDatabase.getInstance()
    private val usersRef = db.getReference(USERS)
    private val messagesRef = db.getReference(MESSAGES)
    private val categoriesRef = db.getReference(CATEGORIES)
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val categories = ArrayList<Category>()

    init {
        categories.add(Category("Movies"))
        categories.add(Category("Activities"))
    }


    suspend fun addUser(user: FirebaseUser) {
        try {
            usersRef.child(user.uid).readValue<User>().second
        } catch (e: Exception) {
            usersRef.child(user.uid).setValue(User.createUser(user, categories))
            usersRef.child(user.uid).child("Categories").setValue(categories)
        }
    }

    suspend fun getCategories(): ArrayList<Category> {
        return categoriesRef.readList<Category>() as ArrayList<Category>
    }

    suspend fun getUser(myId: String): User {
        return usersRef.child(myId).readValue<User>().second
    }

    suspend fun getIdeasForCategory(id: String, category: String): ArrayList<Pair<String,Idea>> {
        return usersRef.child(id).child("ideas").orderByChild("category").equalTo(category).readList()
    }

    suspend fun addIdea(myId: String, idea: Idea) {
        val ideas = usersRef.child(myId).child("ideas").readList<Idea>() as ArrayList
        ideas.add(idea)
        usersRef.child(myId).child("ideas").setValue(ideas)
    }

    companion object {
        const val USERS = "Users"
        const val MESSAGES = "Messages"
        const val CATEGORIES = "Categories"
    }
}