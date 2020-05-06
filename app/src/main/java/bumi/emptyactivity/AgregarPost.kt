package bumi.emptyactivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import data.Post
import kotlinx.android.synthetic.main.activity_agregar_post.*


class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val PERMISSION_CODE: Int = 1001
    var tipo:String = ""
    var IMAGEN:Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_post)

        var imageButton: ImageButton = findViewById(R.id.botonImagen)
        var videoButton: ImageButton = findViewById(R.id.botonVideo)

        imageButton.setOnClickListener(View.OnClickListener {
            pickFromGallery()
            tipo = "Imagen"
        })
        videoButton.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAG_PICK_CODE)
            tipo = "Video"
        })
        var botonGif:ImageButton = findViewById(R.id.botonGif) as ImageButton

        botonGif.setOnClickListener {
            tipo = "Gif"
        }

        botonPost.setOnClickListener {
            PantallaPost.posts.add(Post(tipo,IMAGEN, fname.text.toString()))
            this.finish()
        }

    }
    private fun pickFromGallery() {
        //Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        val mimeTypes =
            arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
        // Launching the Intent
        startActivityForResult(intent, IMAG_PICK_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode === Activity.RESULT_OK)
            when (requestCode) {
                IMAG_PICK_CODE -> {
                    val imageUri = data!!.data
                    val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                    IMAGEN = bitmap

            }
        }
    }
    

}
