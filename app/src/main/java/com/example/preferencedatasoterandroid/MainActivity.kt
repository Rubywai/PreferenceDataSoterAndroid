package com.example.preferencedatasoterandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.preferencedatasoterandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var myTextView: TextView
    private val myViewModel : MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            add.setOnClickListener{
                if(editText.text.isEmpty()){
                    Toast.makeText(applicationContext,"Please Enter Text",Toast.LENGTH_SHORT).show()
                }
                else{
                    myViewModel.write(editText.text.toString().trim())
                }
            }
            myTextView = textView
        }
        myViewModel.readDataStore.observe(this){
            myTextView.text = it
        }

    }
}