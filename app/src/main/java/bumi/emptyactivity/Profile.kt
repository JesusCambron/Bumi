package bumi.emptyactivity

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import data.Post
import kotlinx.android.synthetic.main.activity_profile.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var intento: Intent = Intent(this, Opciones::class.java)

        val botonPlantas: ImageButton = findViewById(R.id.botonPlantas) as ImageButton
        val botonAgua: ImageButton = findViewById(R.id.botonAgua) as ImageButton
        val botonReciclaje: ImageButton = findViewById(R.id.botonReciclaje) as ImageButton

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
/*
        boton_cerrarsesion.setOnClickListener {
            finish()
        }

 */
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
