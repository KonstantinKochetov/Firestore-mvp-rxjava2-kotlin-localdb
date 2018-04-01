package firebaseinfo.com.firestoremvp.ui.main

import android.os.Bundle
import android.support.annotation.StringRes
import android.util.Log
import android.widget.TextView
import firebaseinfo.com.firestoremvp.R
import firebaseinfo.com.firestoremvp.MainApp
import firebaseinfo.com.firestoremvp.data.model.firestore.*
import firebaseinfo.com.firestoremvp.ui.base.BaseActivity
import kotterknife.bindView
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    val TAG = "MainActivity"

    private val addUserButton: TextView by bindView(R.id.b_add_user_sv_db)
    private val getUserFromDbButton: TextView by bindView(R.id.b_get_user_db)
    private val getUserListFromDbButton: TextView by bindView(R.id.b_get_user_list_db)
    private val getSyncUsersQueryButton: TextView by bindView(R.id.b_sync_list_query)
    private val getSyncUsersListenerButton: TextView by bindView(R.id.b_sync_list_listener)
    private val deleteButton: TextView by bindView(R.id.b_delete_all)
    private val dataTextView: TextView by bindView(R.id.tv_data)

    @Inject
    lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
        presenter.initializeViews()
    }

    override fun initializeViews() {
        addUserButton.setOnClickListener({ presenter.addUser() })
        getUserFromDbButton.setOnClickListener({ presenter.getUserFromDatabase() })
        getUserListFromDbButton.setOnClickListener({ presenter.getUserListFromDatabase() })
        getSyncUsersQueryButton.setOnClickListener({ presenter.syncUsersWithQuery() })
        getSyncUsersListenerButton.setOnClickListener({ presenter.syncUsersWithListener() })
        deleteButton.setOnClickListener({ presenter.deleteAll() })
    }

    override fun displayUser(user: User) {
        Log.d(TAG, "display user")
        dataTextView.text = user.name
    }

    override fun displayUserList(users: List<User>) {
        Log.d(TAG, "display user list")
        val stringBuilder = StringBuilder()
        users.forEach {
            stringBuilder.append(" ")
            stringBuilder.append(it.name)
        }
        dataTextView.text = stringBuilder.toString()
    }

    override fun showMessage(@StringRes message: Int?) {
        message?.apply {
            toast(applicationContext.getString(message))
        }
    }

    override fun showError(message: String?) {
        message?.apply {
            toast(message)
        }
    }

    override fun cleanTextView() {
        dataTextView.text = ""
    }

    override fun inject() {
        MainApp[this].component.inject(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
