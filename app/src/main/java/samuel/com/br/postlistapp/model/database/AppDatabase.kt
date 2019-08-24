package samuel.com.br.postlistapp.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import samuel.com.br.postlistapp.model.Post
import samuel.com.br.postlistapp.ui.post.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}