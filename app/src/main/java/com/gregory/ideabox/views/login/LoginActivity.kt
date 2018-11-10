package com.gregory.ideabox.views.login

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.gregory.ideabox.R
import com.gregory.ideabox.views.mainActivity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginContract.View {
    override lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        LoginPresenter(this)

        presenter.getUserSignedIn()

        // Setup click listeners
        button_login.setOnClickListener {
            presenter.authenticate(REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode) {
            REQUEST_CODE -> {
                presenter.getUser(data)
            }
        }
    }

    override fun doOnAuthentication(account: FirebaseUser) {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
    }

    override fun doOnError(t: Throwable?) {
        Log.d("Error", t?.message)
        Toast.makeText(applicationContext, getString(R.string.general_errors_cant_sign_in), Toast.LENGTH_SHORT)
            .show()
    }

    companion object {
        const val REQUEST_CODE = 1
    }

}
