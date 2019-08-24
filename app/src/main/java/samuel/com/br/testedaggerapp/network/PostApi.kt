package samuel.com.br.testedaggerapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import samuel.com.br.testedaggerapp.model.Post

interface PostApi {

    @GET("/posts")
    fun getPost(): Observable<List<Post>>
}