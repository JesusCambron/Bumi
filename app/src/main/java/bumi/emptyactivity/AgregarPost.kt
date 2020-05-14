package bumi.emptyactivity

import android.app.Activity


import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_agregar_post.*
import data.Post





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
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import data.Datos
import kotlinx.android.synthetic.main.activity_agregar_post.*



import kotlinx.android.synthetic.main.post_view.*



class AgregarPost : AppCompatActivity() {


    val IMAG_PICK_CODE: Int = 1000
    val VIDEO_PICK_CODE: Int = 999

    val PERMISSION_CODE: Int = 1001
    var tipo:String = ""
    var IMAGEN:Bitmap? = null



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

        var imageButton: ImageButton = findViewById(R.id.botonImagen)
        var videoButton: ImageButton = findViewById(R.id.botonVideo)

        imageButton.setOnClickListener(View.OnClickListener {


        imageButton = findViewById(R.id.botonImagen)
        videoButton = findViewById(R.id.botonVideo)
        text = findViewById(R.id.texto)
        datos = Datos()


        imageButton?.setOnClickListener(View.OnClickListener {


            pickFromGallery()
            tipo = "Imagen"
        })


        videoButton.setOnClickListener(View.OnClickListener {


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
      }

        botonPost.setOnClickListener {
            PantallaPost.posts.add(0,Post(tipo,IMAGEN, fname.text.toString()))

            Log.i("uri",imgUri.toString())


        }

        botonPost.setOnClickListener {
            var texto:String = fname.text.toString()
            texto = texto.trim()
            if(!texto.equals("")) {
                if(tipo == "Imagen"){
                    dataBase = FirebaseDatabase.getInstance().reference.child("Posts")
                    storage = FirebaseStorage.getInstance().getReference("Images")
                    uploadFile()
                }else{
                    dataBase = FirebaseDatabase.getInstance().reference.child("Posts")
                    storage = FirebaseStorage.getInstance().getReference("Videos")
                    uploadFile()
                }
                finish()
                setResult(0)
            }


        }

        botonPost.setOnClickListener {
            //PantallaPost.posts.add(0,Post(tipo,imgUri, fname.text.toString()))
            if(tipo == "Imagen"){
                dataBase = FirebaseDatabase.getInstance().reference.child("Posts")
                storage = FirebaseStorage.getInstance().getReference("Images")
                uploadFile()
                /*guardarPostFirebase()*/
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
    /*private fun guardarPostFirebase(){
        var imgId = System.currentTimeMillis().toString()+"."+ imgUri?.let { getExtension(it) }
        datos?.tipo = tipo
        datos?.imageId = " "
        datos?.descripcion = fname.text.toString()
        datos?.favorito = false
        var postReference = dataBase?.push()
        postReference?.setValue(datos)
        var key = postReference?.key
        postReference?.key



    }*/

    private fun uploadFile(){
        var imgId = System.currentTimeMillis().toString()+"."+ imgUri?.let { getExtension(it) }
        datos?.tipo = tipo
        datos?.imageId = " "
        datos?.descripcion = fname.text.toString()
      datos?.favorito = "false"
        datos?.destacado = "false"

        //datos?.favorito = false


        var ref:StorageReference = storage!!.child(imgId)

        var uploadTask: UploadTask? = imgUri?.let { ref.putFile(it) }

        uploadTask!!.addOnSuccessListener { taskSnapshot -> // this is where we will end up if our image uploads successfully.
            val snapshotMetadata = taskSnapshot.metadata
            val downloadUrl: Task<Uri> = ref.downloadUrl
            downloadUrl.addOnSuccessListener { uri ->
                var imageReference = uri
                Log.i("img",imageReference.toString())
                datos?.imageId = imageReference.toString()


                var postReference = dataBase?.push()?.key
                datos?.id = postReference
                Log.i("post",postReference)
                if (postReference != null) {
                    dataBase?.child(postReference)?.setValue(datos)
                }
               var postReference = dataBase?.push()
                postReference?.setValue(datos)

                Toast.makeText(this,"Imagen subida",Toast.LENGTH_SHORT).show()
            }
        }
        uploadTask.addOnFailureListener(OnFailureListener {
            Toast.makeText(this,"La imagen no ha podido ser subida", Toast.LENGTH_SHORT).show()
        })





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
                    val selectedVideoUri: Uri = data!!.data!!
                    val selectedVideoPath: String? = getRealPathFromURI(selectedVideoUri)
                    textv.text = selectedVideoPath
                }

            }
    }

    fun getRealPathFromURI(uri: Uri): String? {
        var cursor: Cursor? = null
        return try {
            val proj =
                arrayOf(MediaStore.Images.Media.DATA)
            cursor = this.getContentResolver().query(uri, proj, null, null, null)
            val column_index = cursor!!.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            cursor.getString(column_index)
        } finally {
            cursor?.close()
        }
    }



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
