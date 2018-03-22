package firebaseinfo.com.firestoremvp.data

interface AppCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(message: String?, e: Throwable)
}