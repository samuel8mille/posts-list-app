package samuel.com.br.postlistapp.ui.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import samuel.com.br.postlistapp.R
import samuel.com.br.postlistapp.databinding.ActivityPostListBinding
import samuel.com.br.postlistapp.injection.ViewModelFactory

class PostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private var erroSnackBar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            this,
            androidx.recyclerview.widget.LinearLayoutManager.VERTICAL,
            false
        )

        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(this)
        ).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })
        binding.viewModel = viewModel
    }

    private fun showError(errorMessage: Int) {
        erroSnackBar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        erroSnackBar?.setAction(R.string.retry, viewModel.errorClickListener)
        erroSnackBar?.show()
    }

    private fun hideError() {
        erroSnackBar?.dismiss()
    }
}
