package bumi.emptyactivity

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.widget.ImageButton
import data.Post
import kotlinx.android.synthetic.main.activity_agregar_post.*
import kotlinx.android.synthetic.main.activity_opciones.*
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val PERMISSION_CODE: Int = 1001
    var tipo:String = ""
    val PICK_IMAGE = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_post)

        var imageButton: ImageButton = findViewById(R.id.botonImagen)
        var videoButton: ImageButton = findViewById(R.id.botonVideo)

        imageButton.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            tipo = "Imagen"

        })
        videoButton.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
            tipo = "Video"
        })
        var botonGif:ImageButton = findViewById(R.id.botonGif) as ImageButton

        botonGif.setOnClickListener {
            tipo = "Gif"
        }

        botonPost.setOnClickListener {
            Opciones.listaDeOpcionesPlantas.get(0).catalogo.add(Post(tipo,R.drawable.sunflower, fname.text.toString()
            ))

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }
    

}
