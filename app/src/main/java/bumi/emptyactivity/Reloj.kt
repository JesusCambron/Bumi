package bumi.emptyactivity

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reloj.*

class Reloj : AppCompatActivity() {
    var encendido:Boolean = false
    var time:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reloj)

        var botonTiempo: Button = findViewById(R.id.botonReloj) as Button
        var cronometro: Chronometer = findViewById(R.id.cronometro)

        botonTiempo.setOnClickListener {
            encendido = !encendido
            if(encendido) {
                cronometro.base = SystemClock.elapsedRealtime() - time
                cronometro.start()
            } else {
                time = SystemClock.elapsedRealtime() - cronometro.base
                cronometro.stop()
            }
        }
    }
}
