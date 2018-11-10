package com.gregory.ideabox.managers

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.gregory.ideabox.models.Category
import com.gregory.ideabox.models.User
import com.gregory.ideabox.system.readList
import com.gregory.ideabox.system.readValue

class FirebaseManager(authenticationManager: AuthenticationManager) {
    private val db = FirebaseDatabase.getInstance()
    private val usersRef = db.getReference(USERS)
    private val messagesRef = db.getReference(MESSAGES)
    private val categoriesRef = db.getReference(CATEGORIES)
    val mAuth: FirebaseAuth = FirebaseAuth.getInstance()

    init {
        val categories = ArrayList<Category>()
        categories.add(Category("Movies"))
        categories.add(Category("Activities"))
        categoriesRef.setValue(categories)
    }


    suspend fun addUser(user: FirebaseUser) {
        try {
            usersRef.child(user.uid).readValue<User>().second
        } catch (e: Exception) {
            usersRef.child(user.uid).setValue(User.createUser(user))
        }
    }

    suspend fun getCategories(): ArrayList<Category> {
        return categoriesRef.readList<Category>() as ArrayList<Category>
    }

    companion object {
        const val USERS = "Users"
        const val MESSAGES = "Messages"
        const val CATEGORIES = "Categories"
    }
}