package firebase_info.com.realmfirestore.data.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmUser(
    @PrimaryKey var userID: String = "testUserId",
    var token: String = "testToken",
    var login: String = "testLogin",
    var name: String = "testName",
    var date: Long = 0L,
    var createAt: Long = 0L,
    var updateAt: Long = 0L,
    var socials: RealmList<Social> = RealmList()
) : RealmObject() {

    fun transformFromRealm(): User {
        return User(
            this.userID, this.token, this.login,
            this.name, this.date, this.createAt,
            this.updateAt, this.socials.toArrayList())
    }

    private fun RealmList<Social>.toArrayList(): ArrayList<Social> {
        val list: ArrayList<Social> = ArrayList()
        socials.forEach({ list.add(it) })
        return list
    }

}
