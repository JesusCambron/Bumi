package bumi.emptyactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

<<<<<<< HEAD
        val button_plantas = findViewById<ImageButton>(R.id.boton_plantas)
        button_plantas.setOnClickListener{
            val intent = Intent(this, PlantasActivity::class.java)
            startActivity(intent)
        }

        val button_agua = findViewById<ImageButton>(R.id.boton_agua)
        button_agua.setOnClickListener{
            val intent = Intent(this, AguaActivity::class.java)
            startActivity(intent)
=======
        var intento: Intent = Intent(this, Opciones::class.java)

        val botonPlantas: ImageButton = findViewById(R.id.botonPlantas) as ImageButton
        val botonAgua: ImageButton = findViewById(R.id.botonAgua) as ImageButton
        val botonReciclaje: ImageButton = findViewById(R.id.botonReciclaje) as ImageButton

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
>>>>>>> jcambron
        }
    }
}
