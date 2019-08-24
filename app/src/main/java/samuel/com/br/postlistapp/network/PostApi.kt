package samuel.com.br.postlistapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import samuel.com.br.postlistapp.model.Post

interface PostApi {

    @GET("/posts")
    fun getPost(): Observable<List<Post>>
}