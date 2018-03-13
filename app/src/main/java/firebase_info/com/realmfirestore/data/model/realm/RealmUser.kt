package firebase_info.com.realmfirestore.data.model.realm

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by konstantinkochetov on 11.03.18.
 */
open class RealmUser(
    @PrimaryKey var userID: String = "testUserId",
    var token: String = "testToken",
    var login: String = "testLogin",
    var name: String = "testName",
    var date: Long = 0L,
    var createAt: Long = 0L,
    var updateAt: Long = 0L,
    var social: RealmList<Social> = RealmList()
) : RealmObject() {

    fun transformFromRealm(): User {
        // TODO with extension of list/arraylist, use generic T
        return User(
            this.userID, this.token, this.login,
            this.name, this.date, this.createAt,
            this.updateAt, toArrayList(this.social)
        )

    }

    // TODO move this function
    private fun toArrayList(social: RealmList<Social>): ArrayList<Social> {
        val list: ArrayList<Social> = ArrayList()
        social.forEach({ list.add(it) })
        return list

    }
}
