package com.example.retrofitexample

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder
import java.text.FieldPosition
import java.util.*

class MainActivity : AppCompatActivity() {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listView = findViewById<ListView>(R.id.listView)

        val nameOfEditText = intent.getStringExtra("key").toString()

        //listView.adapter = MyCustomAdapter(this)


        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val jsonPlaceHolderAPI = retrofit.create(JSONPlaceholderAPI::class.java)
        val myCall: Call<List<JsonPlaceAPI>> = jsonPlaceHolderAPI.getUsers()

        myCall.enqueue(object : Callback<List<JsonPlaceAPI>> {
            override fun onFailure(call: Call<List<JsonPlaceAPI>>, t: Throwable) {
                Log.e("ERROR", t.message.toString())

            }

            override fun onResponse(
                call: Call<List<JsonPlaceAPI>>,
                response: Response<List<JsonPlaceAPI>>
            ) {
                listView.adapter = MyCustomAdapter(this@MainActivity, response.body()!!, nameOfEditText)

                /*     listView.onItemClickListener = object : AdapterView.OnItemClickListener {

                    override fun onItemClick(parent: AdapterView<*>, view: View,
                                             position: Int, id: Long) {

                        // value of item that is clicked
                        val itemValue = listView.getItemAtPosition(position) as String

                        // Toast the values
                        Toast.makeText(applicationContext,
                            "Position :$position\nItem Value : $itemValue", Toast.LENGTH_LONG)
                            .show()
                    }*/
            }

        })

        /*listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val intent = Intent(this@MainActivity, Main2Activity::class.java)
                val nameOfEditText = intent.getStringExtra("key").toString()
                intent.putExtra("nameOfEditText", nameOfEditText)
                startActivity(intent)
            }


        }*/

        listView.onItemClickListener = object : AdapterView.OnItemClickListener {

            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val intent = Intent(this@MainActivity, Main4Activity::class.java)
               //val nameOfEditText = intent.getStringExtra("key").toString()

                intent.putExtra("nameOfEditText", nameOfEditText)
                startActivity(intent)
            }


        }


    }


    private class MyCustomAdapter(mContext: Context, val Users: List<JsonPlaceAPI>, nameOfEditText:String) :
        BaseAdapter() {

        private val context: Context

        init {
            context = mContext
        }
        private val nameOfEditTextt: String

        init {
            nameOfEditTextt = nameOfEditText
        }

        private val users: List<JsonPlaceAPI> = Users

        override fun getCount(): Int {
            return users.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return "START"
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutInflater = LayoutInflater.from(context)
            val rowMain = layoutInflater.inflate(R.layout.row_list, viewGroup, false)

            val nameTextView = rowMain.findViewById<TextView>(R.id.nameID)
            //nameTextView.text = users.get(position).name

            val idNumberTextView = rowMain.findViewById<TextView>(R.id.idNumberID)
            idNumberTextView.text = users.get(position).id.toString()

            val emailTextView = rowMain.findViewById<TextView>(R.id.emailID)
            emailTextView.text = users.get(position).email

            val usernameTextView = rowMain.findViewById<TextView>(R.id.usernameID)
            usernameTextView.text = users.get(position).username

            if (nameOfEditTextt==users.get(position).name){
                nameTextView.text = users.get(position).name
                nameTextView.setTextColor(Color.BLUE)
                nameTextView.setTypeface(nameTextView.typeface, Typeface.BOLD_ITALIC)
            }else{
                nameTextView.text =  users.get(position).name
            }

            return rowMain


        }


    }
}




