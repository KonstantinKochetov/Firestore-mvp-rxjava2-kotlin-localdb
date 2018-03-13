package firebase_info.com.realmfirestore.ui.main

import android.os.Bundle
import android.widget.TextView
import firebase_info.com.realmfirestore.R
import firebase_info.com.realmfirestore.RealmFirestoreApp
import firebase_info.com.realmfirestore.base.BaseActivity
import firebase_info.com.realmfirestore.data.model.realm.User
import kotterknife.bindView
import org.jetbrains.anko.toast
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.View {

    private val dataTextView: TextView by bindView(R.id.tv_data)
    private val setDataButton: TextView by bindView(R.id.b_set_data)
    private val getDataButton: TextView by bindView(R.id.b_get_data)
    @Inject
    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attachView(this)
        presenter.initializeViews()
    }

    override fun initializeViews() {
        setDataButton.setOnClickListener({ presenter.loadData() })
        getDataButton.setOnClickListener({ presenter.sync() })

    }

    override fun display(user: User) {
        dataTextView.text = user.name
    }

    override fun showError(message: String?) {
        message?.apply {
            toast(message)
        }
    }

    override fun inject() {
       RealmFirestoreApp[this].component.inject(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

}
