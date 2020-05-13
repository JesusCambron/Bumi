package bumi.emptyactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_profile.*
class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        var intento: Intent = Intent(this, Opciones::class.java)
        //var intento2: Intent = Intent(this, Reloj::class.java)


        val botonPlantas: ImageButton = findViewById(R.id.botonPlantas) as ImageButton
        val botonAgua: ImageButton = findViewById(R.id.botonAgua) as ImageButton
        val botonReciclaje: ImageButton = findViewById(R.id.botonReciclaje) as ImageButton
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
