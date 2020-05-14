package bumi.emptyactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_opciones.*

class Opciones : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)


        val bundle = intent.extras

        if(bundle!=null){
            val type= bundle.getString("type")
            when(type) {
                "plantas"->{
                    boton1.setText("Inicio")
                    boton2.setText("Favoritos")
                    boton3.setText("Destacados")
                    boton4.setText("Mis plantas")
                    texto.setText("Plantas")
                    imagen.setImageResource(R.drawable.plantas)
                    boton1.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","plantasInicio")
                        startActivity(intentPost)
                    })
                    boton2.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","plantasFavoritos")
                        startActivity(intentPost)
                    })
                    boton3.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","plantasDestacados")
                        startActivity(intentPost)
                    })
                    boton4.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","plantasMisPlantas")
                        startActivity(intentPost)
                    })
                }
                "agua"->{
                    boton1.setText("Consejos")
                    boton2.setText("Temporizador")
                    boton3.setText("Herramientas Canciones")
                    boton4.setText("Litros Ahorrados")
                    texto.setText("Agua")
                    imagen.setImageResource(R.drawable.agua)
                    boton1.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","aguaConsejos")
                        startActivity(intentPost)
                    })
                    boton2.setOnClickListener(View.OnClickListener {
                        var intentoReloj: Intent = Intent(this, Reloj::class.java)
                        intentoReloj.putExtra("tipo","aguaTemporizador")
                        startActivity(intentoReloj)
                    })
                    boton3.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","aguaCanciones")
                        startActivity(intentPost)
                    })
                    boton4.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,AhorroAgua::class.java)
                        //intentPost.putExtra("tipo","aguaLitros")
                        startActivity(intentPost)
                    })
                }
                "reciclaje"->{
                    boton1.setText("Inicio")
                    boton2.setText("Favoritos")
                    boton3.setText("Destacados")
                    boton4.setText("Mis posts")
                    texto.setText("Reciclaje")
                    imagen.setImageResource(R.drawable.reciclaje)
                    boton1.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","reciclajeInicio")
                        startActivity(intentPost)
                    })
                    boton2.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","reciclajeFavoritos")
                        startActivity(intentPost)
                    })
                    boton3.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","reciclajeMispost")
                        startActivity(intentPost)
                    })
                    boton4.setOnClickListener(View.OnClickListener {
                        var intentPost = Intent(this,PantallaPost::class.java)
                        intentPost.putExtra("tipo","reciclajeReciclaje")
                        startActivity(intentPost)
                    })
                }
            }
        }
        //listaOpciones.adapter= adaptador
    }


    /**private class AdaptadorOpciones: BaseAdapter {
    var contexto: Context? = null
    var opciones = ArrayList<Opcion>()
    constructor(context: Context, opciones:ArrayList<Opcion>){
    this.contexto=context
    this.opciones = opciones
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    var option = opciones[position]
    //var inflator = LayoutInflater.from(contexto)
    var inflator = contexto!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var vista = inflator.inflate(R.layout.pantalla_opciones, null)
    vista.botonOpcion.setText(option.opcion)
    if(option.opcion.equals("Temporizador")) {
    vista.botonOpcion.setOnClickListener{
    var intentoReloj: Intent = Intent(contexto, Reloj::class.java)
    contexto!!.startActivity(intentoReloj)
    }
    } else {
    vista.botonOpcion.setOnClickListener{
    var intento = Intent(contexto,PantallaPost::class.java)
    intento.putExtra("opcion",option.opcion)
    intento.putExtra("catalogo",option.catalogo)
    contexto!!.startActivity(intento)
    }
    }
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
    }**/
}
