package firebaseinfo.com.firestoremvp.data.model.firestore

import firebaseinfo.com.firestoremvp.data.model.realm.RealmUser
import io.realm.RealmList
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey var userID: String = "testUserId",
    var token: String = "testToken",
    var login: String = "testLogin",
    var name: String = "testName",
    var date: Long = 0L,
    var createAt: Long = 0L,
    var updateAt: Long = 0L,
    var socials: ArrayList<Social> = ArrayList()
) {

    fun transformForRealm() : RealmUser {
        return RealmUser(
            this.userID, this.token, this.login,
            this.name, this.date, this.createAt,
            this.updateAt, this.socials.toRealmList())
    }

    private fun ArrayList<Social>.toRealmList(): RealmList<Social> {
        val list : RealmList<Social> = RealmList()
        this.forEach({list.add(it)})
        return list
    }

}