package bumi.emptyactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import data.Post
import kotlinx.android.synthetic.main.activity_agregar_post.*
import kotlinx.android.synthetic.main.activity_opciones.*

class AgregarPost : AppCompatActivity() {

    var tipo:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_post)
        var botonVideo:ImageButton = findViewById(R.id.botonVideo) as ImageButton
        var botonImagen:ImageButton = findViewById(R.id.botonImagen) as ImageButton
        var botonGif:ImageButton = findViewById(R.id.botonGif) as ImageButton

        botonVideo.setOnClickListener {
            tipo = "Video"
        }

        botonImagen.setOnClickListener {
            tipo = "Imagen"
        }

        botonGif.setOnClickListener {
            tipo = "Gif"
        }

        botonPost.setOnClickListener {
            Opciones.listaDeOpcionesPlantas.get(0).catalogo.add(Post(tipo,R.drawable.sunflower, fname.text.toString()
            ))

        }

    }
}
