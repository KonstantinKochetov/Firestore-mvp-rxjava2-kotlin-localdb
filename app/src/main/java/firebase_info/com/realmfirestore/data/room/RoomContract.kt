package firebase_info.com.realmfirestore.data.room

class RoomContract {

    companion object {

        const val DATABASE_APP = "app.db"

        const val TABLE_USERS = "currencies"

        private const val SELECT_FROM = "SELECT * FROM "

        const val SELECT_CURRENCIES = SELECT_FROM + TABLE_USERS

    }
}

