package samuel.com.br.testedaggerapp.ui.post

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import samuel.com.br.testedaggerapp.model.Post

@Dao
interface PostDao {

    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg posts: Post)
}