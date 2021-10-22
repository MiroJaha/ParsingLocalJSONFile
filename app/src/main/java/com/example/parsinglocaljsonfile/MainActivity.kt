package com.example.parsinglocaljsonfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainRV= findViewById<RecyclerView>(R.id.mainRV)
        val dataList= arrayListOf<Data>()

        val inputStream= assets.open("data.json")
        val data= ByteArray(inputStream.available())
        inputStream.read(data)
        inputStream.close()

        val jsonArray= JSONArray(String(data))
        for (i in 0 until jsonArray.length()) {
            val jsonObject = JSONObject(jsonArray[i].toString())
            dataList.add(Data(jsonObject.getString("title"), jsonObject.getString("url")))
        }

        val rvAdapter= RVAdapter(dataList)
        mainRV.adapter= rvAdapter
        mainRV.layoutManager= LinearLayoutManager(this)

        rvAdapter.setOnItemClickListener(object : RVAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val showImage= dataList[position]
                val intent= Intent(this@MainActivity,ImageShow::class.java)
                intent.putExtra("title",showImage.title)
                intent.putExtra("photoID",showImage.image)
                startActivity(intent)
            }
        })
    }
}