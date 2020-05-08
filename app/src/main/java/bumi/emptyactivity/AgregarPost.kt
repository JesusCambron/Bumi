package bumi.emptyactivity

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import data.Datos
import data.Post
import kotlinx.android.synthetic.main.activity_agregar_post.*


class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val VIDEO_PICK_CODE: Int = 999
    var tipo:String = ""
    var imgUri: Uri? = null
    var imageButton: ImageButton? = null
    var videoButton: ImageButton? = null
    var text:EditText? = null
    private var storage: StorageReference? = null
    private var dataBase: DatabaseReference? = null
    var datos : Datos? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_post)

        imageButton = findViewById(R.id.botonImagen)
        videoButton = findViewById(R.id.botonVideo)
        text = findViewById(R.id.texto)
        datos = Datos()
        //progressBar = findViewById(R.id.progressPost)


        imageButton?.setOnClickListener(View.OnClickListener {
            pickFromGallery()
            tipo = "Imagen"
        })

        videoButton?.setOnClickListener(View.OnClickListener {
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
            Log.i("uri",imgUri.toString())
        }

        botonPost.setOnClickListener {
            //PantallaPost.posts.add(0,Post(tipo,imgUri, fname.text.toString()))
            if(tipo == "Imagen"){
                dataBase = FirebaseDatabase.getInstance().reference.child("Posts")
                storage = FirebaseStorage.getInstance().getReference("Images")
                uploadFile()
            }
            this.finish()
            val returnIntent = Intent()
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()
        }

    }

    private fun getExtension(uri: Uri): String? {
        var cr:ContentResolver = contentResolver
        var mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
    }
    private fun uploadFile(){
        var imgId = System.currentTimeMillis().toString()+"."+ imgUri?.let { getExtension(it) }
        datos?.tipo = tipo
        datos?.descripcion = fname.text.toString()
        datos?.imageId = imgId
        dataBase?.push()?.setValue(datos)
        var ref:StorageReference = storage!!.child(imgId)
        imgUri?.let {
            ref.putFile(it)
                .addOnSuccessListener(OnSuccessListener<UploadTask.TaskSnapshot> {   // Get a URL to the uploaded content
                    //val downloadUrl: Uri = taskSnapshot.getDownloadUrl()
                    Toast.makeText(this,"Imagen subida",Toast.LENGTH_SHORT).show()
                })
                .addOnFailureListener(OnFailureListener {
                    // Handle unsuccessful uploads
                    Toast.makeText(this,"La imagen no ha podido ser subida",Toast.LENGTH_SHORT).show()
                })
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
                    imgUri = data!!.data!!
                    //preimagen.setImageURI(imageUri)
                    //val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)
                    //IMAGEN = bitmap

                }
                VIDEO_PICK_CODE -> {
                    imgUri = data!!.data!!
                    //pre.setVideoURI(mVideoURI)
                /*MediaController mediaController = new MediaController(this);
                        mediaController.setAnchorView(videoView);
                        videoView.setMediaController(mediaController);
                    pre.start()*/
                }

        }
    }


}
