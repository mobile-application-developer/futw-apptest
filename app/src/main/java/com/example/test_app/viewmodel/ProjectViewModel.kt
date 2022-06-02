package com.example.test_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test_app.data.ProjectData
import com.example.test_app.repository.ProjectRepository
import retrofit2.Call
import retrofit2.Callback
import java.util.*

class ProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {

    val projectlist = MutableLiveData<List<ProjectData>>()
    val error = MutableLiveData<String>()



    fun getProjectlist() {
        val dataResponse = projectRepository.getprojectlist()
        dataResponse.enqueue(object : Callback<List<ProjectData>> {

            override fun onFailure(call: Call<List<ProjectData>>, t: Throwable) {
                error.postValue(t.message)
            }

            override fun onResponse(
                call: Call<List<ProjectData>>,
                response: retrofit2.Response<List<ProjectData>>
            ) {
                projectlist.postValue(response.body())
            }
        })
    }

    fun searchProject(search: String) {
        var filtername = projectlist.value?.filter {
            it.title.lowercase(Locale.ROOT).contains(search.lowercase(Locale.ROOT))
        }
        projectlist.postValue(filtername)
    }
}




