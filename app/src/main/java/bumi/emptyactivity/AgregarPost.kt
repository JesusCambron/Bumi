package bumi.emptyactivity

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import data.Post
import kotlinx.android.synthetic.main.activity_agregar_post.*


class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val VIDEO_PICK_CODE: Int = 999
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
            /*
            if (Build.VERSION.SDK_INT <= 19) {
                val i = Intent()
                i.type = "video/*"
                i.action = Intent.ACTION_GET_CONTENT
                i.addCategory(Intent.CATEGORY_OPENABLE)
                startActivityForResult(i, VIDEO_PICK_CODE)
            } else if (Build.VERSION.SDK_INT > 19) {
                val intent = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                )
                startActivityForResult(intent, VIDEO_PICK_CODE)
            }
            */
             */
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), VIDEO_PICK_CODE)
            tipo = "Video"
        })

        var botonGif:ImageButton = findViewById(R.id.botonGif) as ImageButton

        botonGif.setOnClickListener {
            tipo = "Gif"
        }

        botonPost.setOnClickListener {
            PantallaPost.posts.add(0,Post(tipo,IMAGEN, fname.text.toString()))
            this.finish()
            val returnIntent = Intent()
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()
        }

    }
    private fun pickFromGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), IMAG_PICK_CODE)
        tipo = "Imagen"
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
                VIDEO_PICK_CODE -> {
                    val mVideoURI = data!!.data
                    pre.setVideoURI(mVideoURI)
                /*MediaController mediaController = new MediaController(this);
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);*/
                    pre.start()
                }

        }
    }


}
