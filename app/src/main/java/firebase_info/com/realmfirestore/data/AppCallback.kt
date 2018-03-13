package firebase_info.com.realmfirestore.data

interface AppCallback<T> {
    fun onSuccess(response: T)
    fun onFailure(message: String?, e: Throwable)
}