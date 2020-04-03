package bumi.emptyactivity

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val button_plantas = findViewById<ImageButton>(R.id.boton_plantas)
        button_plantas.setOnClickListener{
            val intent = Intent(this, PlantasActivity::class.java)
            startActivity(intent)
        }

        val button_agua = findViewById<ImageButton>(R.id.boton_agua)
        button_agua.setOnClickListener{
            val intent = Intent(this, activity_agua::class.java)
            startActivity(intent)
        }
    }
}
