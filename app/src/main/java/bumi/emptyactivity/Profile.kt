package bumi.emptyactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        var intento: Intent = Intent(this, PlantasActivity::class.java)

        val boton: ImageButton = findViewById(R.id.botonPlantas) as ImageButton

        boton.setOnClickListener {
            startActivity(intento)
        }
    }
}
