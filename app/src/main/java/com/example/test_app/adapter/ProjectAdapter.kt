package com.example.test_app.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test_app.R
import com.example.test_app.data.ProjectData
import com.squareup.picasso.Picasso


class ProjectAdapter(private var projects: List<ProjectData>, private var context: Context) :
    RecyclerView.Adapter<ProjectAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.project_design, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return projects.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projects[position]
        holder.Bind(project)
    }

    class ViewHolder(binding: View) : RecyclerView.ViewHolder(binding) {

        var title: TextView = binding.findViewById(R.id.title)
        var desc: TextView = binding.findViewById(R.id.desc)
        var earn: TextView = binding.findViewById(R.id.earn)
        var logo: ImageView = binding.findViewById(R.id.logo)

        fun Bind(project: ProjectData) {
            title.text = project.title + " - " + project.description
            desc.text = project.description
            earn.text = project.earning
            Picasso.get().load("https://i.imgur.com/9iJaWvJ.png").into(logo)
        }
    }

    fun setprojectlist(projects: List<ProjectData>) {
        this.projects = projects
        notifyDataSetChanged()
    }

}




