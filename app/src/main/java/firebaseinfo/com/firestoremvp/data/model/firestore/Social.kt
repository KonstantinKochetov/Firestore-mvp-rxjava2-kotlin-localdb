package firebaseinfo.com.firestoremvp.data.model.firestore

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Social(
    @PrimaryKey var socialID: String = "testSocialID",
    var url: String = "testUrl",
    var iconRef: String = "testIconRef"
) : RealmObject()