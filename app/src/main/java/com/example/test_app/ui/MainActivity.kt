package com.example.test_app.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test_app.adapter.ProjectAdapter
import com.example.test_app.data.ProjectData
import com.example.test_app.databinding.ActivityMainBinding
import com.example.test_app.network.ApiServices
import com.example.test_app.repository.ProjectRepository
import com.example.test_app.viewmodel.ProjectViewModel
import com.example.test_app.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ProjectViewModel
    private val retrofitService = ApiServices.getInstance()
    var myadapter: ProjectAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        myadapter = ProjectAdapter(listOf(), this)
        viewModel = ViewModelProvider(this, ViewModelFactory(ProjectRepository(retrofitService))).get(
            ProjectViewModel::class.java
        )
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = myadapter

        viewModel.projectlist.observe(this, Observer {
            myadapter?.setprojectlist(it)
        })

        binding.imageView.setOnClickListener {
            viewModel.getProjectlist()
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0!!.isEmpty()){
                    viewModel.getProjectlist()
                }
                else{
                    viewModel.searchProject(p0.toString())
                }
            }
            override fun afterTextChanged(p0: Editable?) {}

        })

        viewModel.error.observe(this, Observer {
        })

        viewModel.getProjectlist()

    }

}