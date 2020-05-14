package bumi.emptyactivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import bumi.emptyactivity.R.drawable.circle
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import data.Post
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.text.FieldPosition

class Profile : AppCompatActivity() {
    private lateinit var mPagerViewAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var intento: Intent = Intent(this, Opciones::class.java)

        val botonPlantas: ImageButton = findViewById(R.id.botonPlantas) as ImageButton
        val botonAgua: ImageButton = findViewById(R.id.botonAgua) as ImageButton
        val botonReciclaje: ImageButton = findViewById(R.id.botonReciclaje) as ImageButton

        //var viewPager:ViewPager = pager

        bioBoton.setOnClickListener {
            pager.currentItem = 0
            bioBoton.setBackgroundResource(R.drawable.circle)
            bioBoton.setTextColor(Color.WHITE)
            muroBoton.background = null
            muroBoton.setTextColor(Color.GRAY)

        }

        muroBoton.setOnClickListener {
            pager.currentItem = 1
            muroBoton.setBackgroundResource(R.drawable.circle)
            muroBoton.setTextColor(Color.WHITE)
            bioBoton.background = null
            bioBoton.setTextColor(Color.GRAY)
        }

        mPagerViewAdapter = ViewPagerAdapter(supportFragmentManager)
        pager.adapter = mPagerViewAdapter
        pager.offscreenPageLimit = 3

        pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                changeTabs(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
        })

        pager.currentItem = 0


        val bundle = intent.extras
        if(bundle != null){
            val nombre = bundle.getString("name")
            val imagenUrl = bundle.getString("image")
            profile_name.setText(nombre)
            var task = obtenerImagen()
            //var image = task.execute(imagenUrl).get()
            //profile_image.setImageBitmap(image)
            //Log.i("image",imagenUrl)
        }

        botonPlantas.setOnClickListener {
            intento.putExtra("type","plantas")
            startActivity(intento)
        }

        botonAgua.setOnClickListener {
            intento.putExtra("type","agua")
            startActivity(intento)
        }

        botonReciclaje.setOnClickListener {
            intento.putExtra("type","reciclaje")
            startActivity(intento)
        }

    }

    fun changeTabs(position: Int){
        if(position == 0) {
            bioBoton.setBackgroundResource(R.drawable.circle)
            bioBoton.setTextColor(Color.WHITE)
            muroBoton.background = null
            muroBoton.setTextColor(Color.GRAY)
        }

        if(position == 1) {
            muroBoton.setBackgroundResource(R.drawable.circle)
            muroBoton.setTextColor(Color.WHITE)
            bioBoton.background = null
            bioBoton.setTextColor(Color.GRAY)
        }
    }

    inner class obtenerImagen : AsyncTask<String, Void, Bitmap>(){

        override fun onPostExecute(result: Bitmap?) {
            //View.image.setImageBitmap(image)
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            val url = URL(params.get(0))
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            var bitmap = BitmapFactory.decodeStream(input)
            input.close()
            return bitmap
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.cerrarSesion -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
