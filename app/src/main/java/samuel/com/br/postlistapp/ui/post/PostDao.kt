package samuel.com.br.postlistapp.ui.post

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import samuel.com.br.postlistapp.model.Post

@Dao
interface PostDao {

    @get:Query("SELECT * FROM post")
    val all: List<Post>

    @Insert
    fun insertAll(vararg posts: Post)
}