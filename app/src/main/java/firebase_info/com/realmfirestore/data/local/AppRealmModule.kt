package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.model.realm.RealmUser
import firebase_info.com.realmfirestore.data.model.realm.Social
import io.realm.annotations.RealmModule

/**
 * Created by konstantinkochetov on 11.03.18.
 */
@RealmModule(
    classes = [(RealmUser::class),
        (Social::class)]
)
class AppRealmModule