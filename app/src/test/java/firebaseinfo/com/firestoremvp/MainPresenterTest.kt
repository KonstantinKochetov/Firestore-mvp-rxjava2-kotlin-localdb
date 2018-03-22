package firebaseinfo.com.firestoremvp

import com.nhaarman.mockito_kotlin.*
import firebaseinfo.com.firestoremvp.data.AppCallback
import firebaseinfo.com.firestoremvp.data.DataManagerImpl
import firebaseinfo.com.firestoremvp.data.model.realm.User
import firebaseinfo.com.firestoremvp.ui.main.MainContract
import firebaseinfo.com.firestoremvp.ui.main.MainPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainPresenterTest {

    companion object {
        val testMessage = "testMessage"
    }

    private lateinit var presenter: MainPresenter
    private val data: DataManagerImpl = mock()
    private val view: MainContract.View = mock()
    private var user: User = mock()
    private var users: List<User> = mock()
    private var disposable: Disposable = mock()

    @Before
    fun setUp() {
        val compositeDisposable = CompositeDisposable()
        presenter = spy(MainPresenter(data, compositeDisposable))
        presenter.attachView(view)
    }

    @Test
    fun testAddUser() {
        whenever(data.addUser(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<User>>()
        presenter.addUser()
        verify(data).addUser(captor.capture())

        captor.firstValue.onSuccess(user)
        verify(view).showMessage(R.string.s_user_added)
        verify(view).displayUser(user)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @Test
    fun testGetUserFromDatabase() {
        whenever(data.getUserFromDatabase(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<User>>()
        presenter.getUserFromDatabase()
        verify(data).getUserFromDatabase(captor.capture())

        captor.firstValue.onSuccess(user)
        verify(view).showMessage(R.string.s_user_extracted_db)
        verify(view).displayUser(user)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @Test
    fun testGetUserListFromDatabase() {
        whenever(data.getUserListFromDatabase(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<List<User>>>()
        presenter.getUserListFromDatabase()
        verify(data).getUserListFromDatabase(captor.capture())

        captor.firstValue.onSuccess(users)
        verify(view).showMessage(R.string.s_users_extracted_db)
        verify(view).displayUserList(users)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @Test
    fun testSyncUsersWithQuery() {
        whenever(data.syncUsersWithQuery(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<List<User>>>()
        presenter.syncUsersWithQuery()
        verify(data).syncUsersWithQuery(captor.capture())

        captor.firstValue.onSuccess(users)
        verify(view).showMessage(R.string.s_sync_users_query_message)
        verify(view).displayUserList(users)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @Test
    fun testSyncUsersWithListener() {
        whenever(data.syncUsersWithListener(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<List<User>>>()
        presenter.syncUsersWithListener()
        verify(data).syncUsersWithListener(captor.capture())

        captor.firstValue.onSuccess(users)
        verify(view).showMessage(R.string.s_sync_users_listener_message)
        verify(view).displayUserList(users)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @Test
    fun testDeleteAll() {
        whenever(data.deleteAll(any())).thenReturn(disposable)

        val captor = argumentCaptor<AppCallback<String>>()
        presenter.deleteAll()
        verify(data).deleteAll(captor.capture())

        captor.firstValue.onSuccess(testMessage)
        verify(view).cleanTextView()
        verify(view).showMessage(R.string.s_everything_is_deleted)

        captor.firstValue.onFailure(testMessage, Throwable())
        verify(view).showError(testMessage)
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        presenter.detachView()
    }

}