package samuel.com.br.postlistapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.samuel.navigation.features.PostsNavigation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startPosts()
    }

    private fun startPosts() = PostsNavigation.dynamicStart?.let { startActivity(it) }
}
