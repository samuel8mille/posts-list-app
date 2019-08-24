package samuel.com.br.testedaggerapp.ui.post

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import samuel.com.br.testedaggerapp.R
import samuel.com.br.testedaggerapp.databinding.ActivityPostListBinding
import samuel.com.br.testedaggerapp.injection.ViewModelFactory

class PostListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostListBinding
    private lateinit var viewModel: PostListViewModel

    private var erroSnackBar: Snackbar?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(PostListViewModel::class.java)
        viewModel.errorMessage.observe(this, Observer {
            errorMessage -> if (errorMessage != null) showError(errorMessage) else hideError()
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
