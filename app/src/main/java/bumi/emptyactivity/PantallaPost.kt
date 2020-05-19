package bumi.emptyactivity

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.StorageReference
import data.Datos
import data.Post
import kotlinx.android.synthetic.main.activity_pantalla_post.*
import kotlinx.android.synthetic.main.post_view.view.*
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class PantallaPost : AppCompatActivity() {

    companion object{
        var posts = ArrayList<Post>()
        var postFav = ArrayList<Post>()
        var postDest = ArrayList<Post>()
        var misPost = ArrayList<Post>()
    }

    var dataBase:DatabaseReference? = null
    var storage: StorageReference? = null
    var adaptador:AdaptadorPosts? = null
    lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_post)

        barraProgreso.visibility = View.VISIBLE
        dataBase = FirebaseDatabase.getInstance().getReference().child("Posts")
        val bundle = intent.extras

        mAuth = FirebaseAuth.getInstance()
        /*dataBase!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                Toast.makeText()
            }

        })*/
       dataBase!!.addListenerForSingleValueEvent(object : ValueEventListener {

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                posts.clear()
                postFav.clear()
                postDest.clear()
                misPost.clear()
                val children = p0!!.children
                // This returns the correct child count...
                children.forEach {
                    var data: Datos? = it.getValue(Datos::class.java)
                    if (data != null) {
                        //data.favorito.equals("true")
                        data.id?.let { it1 -> data.tipo?.let { it2 ->
                            data.descripcion?.let { it3 ->
                                data.favorito?.let { it4 ->
                                    data.destacado?.let { it5 ->
                                        Post(it1,
                                            it2,data.imageId, it3, it4, it5
                                        )
                                    }
                                }
                            }
                        } }
                            ?.let { it2 -> posts.add(it2) }
                        if(data.favorito.equals("true")){
                            data.id?.let { it1 -> data.tipo?.let { it2 ->
                                data.descripcion?.let { it3 ->
                                    data.favorito?.let { it4 ->
                                        data.destacado?.let { it5 ->
                                            Post(it1,
                                                it2,data.imageId, it3, it4, it5
                                            )
                                        }
                                    }
                                }
                            } }
                                ?.let { it2 -> postFav.add(it2) }
                        }
                        if(data.destacado.equals("true")){
                            data.id?.let { it1 -> data.tipo?.let { it2 ->
                                data.descripcion?.let { it3 ->
                                    data.favorito?.let { it4 ->
                                        data.destacado?.let { it5 ->
                                            Post(it1,
                                                it2,data.imageId, it3, it4, it5
                                            )
                                        }
                                    }
                                }
                            } }
                                ?.let { it2 -> postDest.add(it2) }
                        }
                        if(data.usuario.equals(mAuth.currentUser?.email)){
                            data.id?.let { it1 -> data.tipo?.let { it2 ->
                                data.descripcion?.let { it3 ->
                                    data.favorito?.let { it4 ->
                                        data.destacado?.let { it5 ->
                                            Post(it1,
                                                it2,data.imageId, it3, it4, it5
                                            )
                                        }
                                    }
                                }
                            } }
                                ?.let { it2 -> misPost.add(it2) }
                        }
                    }
                }
                posts.reverse()
                postFav.reverse()
                postDest.reverse()
                misPost.reverse()
                if(bundle != null) {
                    val type = bundle.getString("tipo")
                    when(type){
                        "plantasInicio" -> {
                            barraProgreso.visibility = View.GONE
                            adaptador = AdaptadorPosts(this@PantallaPost,posts)
                            opcion.text = "Inicio"
                        }
                        "plantasFavoritos" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Favoritos"
                            adaptador = AdaptadorPosts(this@PantallaPost, postFav)
                        }
                        "plantasDestacados" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Destacados"
                            adaptador = AdaptadorPosts(this@PantallaPost, postDest)
                        }
                        "plantasMisPlantas" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Mis Plantas"
                            adaptador = AdaptadorPosts(this@PantallaPost, misPost)
                        }

                        "aguaConsejos" -> {
                            opcion.text = "Consejos"
                            //adaptador = AdaptadorPosts(this,cargarCatalogoConsejosAgua())
                        }
                        "aguaTemporizador" -> {
                            opcion.text = "Temporizador"
                            //adaptador = AdaptadorPosts(this,cargarCatalogoTemporizadorAgua())
                        }
                        "aguaCanciones" -> {
                            opcion.text = "Canciones"
                            //adaptador = AdaptadorPosts(this,cargarCatalogoHerramientasAgua())
                        }
                        "aguaLitros" -> {
                            opcion.text = "Litros Usados"
                            //adaptador = AdaptadorPosts(this,cargarCatalogoLitrosAhorradosAgua())
                        }

                        "reciclajeInicio" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Inicio"
                            adaptador = AdaptadorPosts(this@PantallaPost,posts)
                        }
                        "reciclajeFavoritos" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Favoritos"
                            adaptador = AdaptadorPosts(this@PantallaPost, postFav)
                        }
                        "reciclajeMispost" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Mis Posts"
                            adaptador = AdaptadorPosts(this@PantallaPost, misPost)
                        }
                        "reciclajeReciclaje" -> {
                            barraProgreso.visibility = View.GONE
                            opcion.text = "Mis Posts"
                            adaptador = AdaptadorPosts(this@PantallaPost, misPost)
                        }

                    }

                }
                listaPost.adapter = adaptador
            }
        })


        var nPrevSelGridItem = -1
        /*listaPost.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            var viewPrev: View? = null
            override fun onItemClick(
                parent: AdapterView<*>?, view: View,
                position: Int, id: Long
            ) {
                try {
                    botonAgregar.setText("position = " + position)
/*
                    if (nPrevSelGridItem != -1) {
                        viewPrev = listaPost.getChildAt(nPrevSelGridItem)
                        viewPrev!!.setBackgroundColor(Color.WHITE)
                    }

                    nPrevSelGridItem = position
                    if (nPrevSelGridItem == position) {
                        //View viewPrev = (View) gridview.getChildAt(nPrevSelGridItem);
                        //Toast.makeText(this, "NIA", Toast.LENGTH_LONG).show()
                        view.setBackgroundColor(Color.BLACK);
                    }

 */
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })*/

        botonAgregar.setOnClickListener{
            var intento: Intent = Intent(this, AgregarPost::class.java)
            startActivityForResult(intento,0)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        finish()
    }

  /* override fun onStart() {
        super.onStart()
       dataBase!!.child("Posts").addChildEventListener(object :ChildEventListener{
           override fun onCancelled(p0: DatabaseError) {
               TODO("Not yet implemented")
           }

           override fun onChildMoved(p0: DataSnapshot, p1: String?) {
               TODO("Not yet implemented")
           }

           override fun onChildChanged(p0: DataSnapshot, p1: String?) {
               TODO("Not yet implemented")
           }

           override fun onChildAdded(p0: DataSnapshot, p1: String?) {
               var data: Datos? = p0.getValue(Datos::class.java)
               if (data != null) {
                   data.key = p0.key
               }
               if (data != null) {
                   var uri:String? = data.imageId
                   data.descripcion?.let { data.tipo?.let { it1 -> Post(it1,uri, it) } }?.let { posts.add(it) }
               }
           }
           override fun onChildRemoved(p0: DataSnapshot) {
               TODO("Not yet implemented")
           }


       })
    }*/

/*
    fun cargarCatalogoInicioPlantas():ArrayList<Post> {
        var lista = ArrayList<Post>()
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        lista.add(
            Post(
                "Foto",
                BitmapFactory.decodeResource(resources,R.drawable.sunflower),
                "El dia de hoy vengo a compartirles el progreso de mi jardin de girasoles"
            )
        )
        return lista
    }*/

    fun actualizarView(){
        var intento = this.intent
        this.finish()
        startActivity(intento)
    }

    inner class obtenerImagen : AsyncTask<Post,Void,Bitmap>(){

        override fun onPostExecute(result: Bitmap?) {
            //View.image.setImageBitmap(image)
        }

        override fun doInBackground(vararg params: Post?): Bitmap? {
            var post:Post? = params.get(0)
            val url = URL(post?.image)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.setDoInput(true)
            connection.connect()
            val input: InputStream = connection.getInputStream()
            var bitmap = BitmapFactory.decodeStream(input)
            input.close()
            return bitmap
        }
    }

    inner class AdaptadorPosts: BaseAdapter {
        var contexto: Context? = null
        var opciones = ArrayList<Post>()

        constructor(context: Context, opciones:ArrayList<Post>){
            this.contexto=context
            this.opciones = opciones
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var option = opciones[position]
            var inflator = LayoutInflater.from(contexto)
            var vista = inflator.inflate(R.layout.post_view, null)

            //Si el registro es una imagen
            if(option.tipo.equals("Imagen")){
                vista.videof.visibility = INVISIBLE
                vista.image.visibility = VISIBLE
                var task = obtenerImagen()
                var image = task.execute(option).get()
                vista.image.setImageBitmap(image)
            }else if(option.tipo.equals("Gif")){
                vista.videof.visibility = INVISIBLE
                vista.image.visibility = VISIBLE
                val urlGif = option.image
                val uri: Uri = Uri.parse(urlGif)
                Glide.with(applicationContext).load(uri).into(vista.image)
            }else{
                //En caso de que sea video
                vista.videof.visibility = VISIBLE
                vista.image.visibility = INVISIBLE
                vista.videof.setVideoPath(option.image)
                vista.videof.seekTo(100)
                var media:MediaController = MediaController(contexto)
                media.setAnchorView(vista.videof)
                vista.videof.setMediaController(media)
                //vista.video.setVideoURI(option.image)
            }
            vista.tv_title.setText(option.tipo)

            vista.descripcion.setText(option.descripcion)
            var seleccionfavorito: Boolean = false
            var seleccionDestacado: Boolean = false
            if (option.favorito.equals("true")){
                vista.favoritos.setImageResource(R.drawable.ic_star_black_24dp)
                seleccionfavorito = true
            }
            //Agrega un post a favoritos
            vista.favoritos.setOnClickListener {
                seleccionfavorito = !seleccionfavorito
                if(seleccionfavorito){
                    vista.favoritos.setImageResource(R.drawable.ic_star_black_24dp)
                    Toast.makeText(contexto, "Agregado a favoritos", Toast.LENGTH_SHORT).show()
                    //aqui asignar nuevo valor
                    var referenciaPost = dataBase?.child(option.idPost)
                    var referenciaFavorito = referenciaPost?.child("favorito")
                    referenciaFavorito?.setValue("true")
                } else {
                    vista.favoritos.setImageResource(R.drawable.ic_star_border_black_24dp)
                    Toast.makeText(contexto, "Eliminado de favoritos", Toast.LENGTH_SHORT).show()
                    actualizarView()
                    //aqui asignar nuevo valor
                    var referenciaPost = dataBase?.child(option.idPost)
                    var referenciaFavorito = referenciaPost?.child("favorito")
                    referenciaFavorito?.setValue("false")
                }

            }

            if(option.destacado.equals("true")){
                vista.destacado.setImageResource(R.drawable.ic_whatshot1_black_24dp)
                seleccionDestacado = true
            }
            vista.destacado.setOnClickListener {
                seleccionDestacado = !seleccionDestacado
                if(seleccionDestacado){
                    vista.destacado.setImageResource(R.drawable.ic_whatshot1_black_24dp)
                    Toast.makeText(contexto, "Agregado a destacados", Toast.LENGTH_SHORT).show()
                    //aqui asignar nuevo valor
                    var referenciaPost = dataBase?.child(option.idPost)
                    var referenciaDestacado = referenciaPost?.child("destacado")
                    referenciaDestacado?.setValue("true")
                } else {
                    vista.destacado.setImageResource(R.drawable.ic_whatshot0_black_24dp)
                    Toast.makeText(contexto, "Eliminado de destacados", Toast.LENGTH_SHORT).show()
                    actualizarView()
                    //aqui asignar nuevo valor
                    var referenciaPost = dataBase?.child(option.idPost)
                    var referenciaDestacado = referenciaPost?.child("destacado")
                    referenciaDestacado?.setValue("false")
                }
            }
/*
            vista.image.setOnLongClickListener(OnLongClickListener { //Pulsación larga
                Toast.makeText(contexto, "YIAUAUUA", Toast.LENGTH_SHORT).show()
                false
            })

 */

            return vista
        }

        override fun getItem(position: Int): Any {
            return opciones[position]
        }

        override fun getItemId(position: Int): Long {
            return 1
        }

        override fun getCount(): Int {
            return opciones.size
        }

    }
}
