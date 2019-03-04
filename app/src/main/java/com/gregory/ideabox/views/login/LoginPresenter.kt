package com.gregory.ideabox.views.login

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.gregory.ideabox.managers.AuthenticationManager
import com.gregory.ideabox.managers.FirebaseManager
import com.gregory.ideabox.managers.IBManager
import android.support.v4.app.ActivityCompat.startActivityForResult
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch


class LoginPresenter(
    override var myView: LoginContract.View,
    var firebaseManager: FirebaseManager = IBManager.firebaseManager,
    var authenticationManager: AuthenticationManager = IBManager.authenticationManager
) : LoginContract.Presenter {

    init {
        myView.presenter = this
    }

    override fun getUserSignedIn() {
        authenticationManager.getCurrentUser()?.let {
            myView.doOnAuthentication(it)
        }
    }

    override fun authenticate(requestCode: Int) {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(OAUTH_WEB_KEY) // TODO remove from here, put into auth manager
            .requestEmail()
            .build()
        (myView as? AppCompatActivity)?.let { activity ->
            val mGoogleSignInClient = GoogleSignIn.getClient(activity, gso)
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(activity, signInIntent, requestCode, null)
        }
    }

    override fun getUser(data: Intent?) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken, null);
            firebaseManager.mAuth.signInWithCredential(credential)
                .addOnCompleteListener { resultTask ->
                    if (resultTask.isSuccessful) {
                        launch {
                            val user = authenticationManager.getCurrentUser()!!
                            // Add user to UserTable if not there
                            firebaseManager.addUser(user)
                            myView.doOnAuthentication(user)
                        }
                    } else {
                        // If sign in fails, display a message to the user.
                        myView.doOnError(resultTask.exception)
                    }
                }
        } catch (e: ApiException) {
            myView.doOnError(e)
        }
    }

    companion object {
        const val OAUTH_WEB_KEY = "1073356852440-eddpj2efu16k18bq0cbpa1klbg46n2lr.apps.googleusercontent.com"
    }
}