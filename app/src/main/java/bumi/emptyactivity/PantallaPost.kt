package bumi.emptyactivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_pantalla_post.*
import kotlinx.android.synthetic.main.activity_pantalla_post.view.*
import kotlinx.android.synthetic.main.activity_pantalla_post.view.opcion
import kotlinx.android.synthetic.main.post_view.view.*

class PantallaPost : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_post)
        var adaptador:AdaptadorPosts?
        val bundle:Bundle? = intent.extras
        if(bundle!=null) {
            opcion.setText(bundle.getString("opcion"))
            adaptador=AdaptadorPosts(this, bundle.get("catalogo") as ArrayList<Post>)
            listaPost.adapter=adaptador
        }

        botonAgregar.setOnClickListener{
            var intento: Intent = Intent(this, AgregarPost::class.java)
            startActivity(intento)
        }
    }

    private class AdaptadorPosts: BaseAdapter {
        var contexto: Context? = null
        var opciones = ArrayList<Post>()
        constructor(context: Context, opciones:ArrayList<Post>){
            this.contexto=context
            this.opciones = opciones
        }
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var option = opciones[position]
            var inflator = LayoutInflater.from(contexto)
            //var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var vista = inflator.inflate(R.layout.post_view, null)
            vista.tv_title.setText(option.tipo)
            vista.image.setImageResource(option.image)
            vista.descripcion.setText(option.descripcion)

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
