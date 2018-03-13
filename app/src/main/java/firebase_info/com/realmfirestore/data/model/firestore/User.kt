package firebase_info.com.realmfirestore.data.model.realm

import io.realm.RealmList
import io.realm.annotations.PrimaryKey

/**
 * Created by konstantinkochetov on 11.03.18.
 */
// TODO rename social to socialList or socials
open class User(
    @PrimaryKey var userID: String = "testUserId",
    var token: String = "testToken",
    var login: String = "testLogin",
    var name: String = "testName",
    var date: Long = 0L,
    var createAt: Long = 0L,
    var updateAt: Long = 0L,
    var social: ArrayList<Social> = ArrayList()
) {
    fun transformForRealm() : RealmUser {
        // TODO with extension of list/arraylist, use generic T
        return RealmUser(
            this.userID, this.token, this.login,
            this.name, this.date, this.createAt,
            this.updateAt, toRealmList(this.social))
    }

    // TODO move this function
    private fun toRealmList(social: ArrayList<Social>): RealmList<Social> {
        val list : RealmList<Social> = RealmList()
        social.forEach({list.add(it)})
        return list

    }


}