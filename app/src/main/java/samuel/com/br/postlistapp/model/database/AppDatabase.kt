package samuel.com.br.postlistapp.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import samuel.com.br.postlistapp.model.Post
import samuel.com.br.postlistapp.ui.post.PostDao

@Database(entities = arrayOf(Post::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}