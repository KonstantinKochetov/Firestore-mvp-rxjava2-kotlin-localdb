package firebase_info.com.realmfirestore.data.local

import firebase_info.com.realmfirestore.data.model.realm.RealmUser
import firebase_info.com.realmfirestore.data.model.realm.Social
import io.realm.annotations.RealmModule

@RealmModule(
    classes = [(RealmUser::class),
        (Social::class)]
)
class AppRealmModule