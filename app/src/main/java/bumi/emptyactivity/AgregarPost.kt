package bumi.emptyactivity

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val PERMISSION_CODE: Int = 1001

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
        })
        videoButton.setOnClickListener(View.OnClickListener {
            val intent = Intent()
            intent.type = "video/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        })
    }

    val PICK_IMAGE = 1

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            //TODO: action
        }
    }



}
