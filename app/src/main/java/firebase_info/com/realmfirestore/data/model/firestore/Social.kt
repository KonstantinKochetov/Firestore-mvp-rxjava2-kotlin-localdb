package firebase_info.com.realmfirestore.data.model.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by konstantinkochetov on 11.03.18.
 */
open class Social(
    @PrimaryKey var socialID: String = "testSocialID",
    var url: String = "testUrl",
    var iconRef: String = "testIconRef"
) : RealmObject()