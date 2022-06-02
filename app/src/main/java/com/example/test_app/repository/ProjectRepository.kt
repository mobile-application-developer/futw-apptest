package com.example.test_app.repository

import com.example.test_app.network.ApiServices

class ProjectRepository constructor(private val apiServices: ApiServices) {

    fun getprojectlist() = apiServices.getprojectlist()

}