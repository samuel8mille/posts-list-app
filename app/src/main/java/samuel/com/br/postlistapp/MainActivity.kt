package samuel.com.br.postlistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.samuel.navigation.features.PostsNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPosts()
//        startLogin()
    }

    private fun startPosts() = PostsNavigation.dynamicStart?.let { startActivity(it) }
//    private fun startLogin() = LoginNavigation.dynamicStart?.let { startActivity(it) }
}
