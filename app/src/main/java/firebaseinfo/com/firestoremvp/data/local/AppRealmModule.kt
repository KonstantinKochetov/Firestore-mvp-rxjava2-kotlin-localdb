package firebaseinfo.com.firestoremvp.data.local

import firebaseinfo.com.firestoremvp.data.model.realm.RealmUser
import firebaseinfo.com.firestoremvp.data.model.realm.Social
import io.realm.annotations.RealmModule

@RealmModule(
    classes = [(RealmUser::class),
        (Social::class)]
)
class AppRealmModule