package bumi.emptyactivity

import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reloj.*

class Reloj : AppCompatActivity() {
    var milisegundos: Int = 0;
    var segundo: Int = 0;
    var minuto: Int = 0;
    var hora: Int = 0;
    var segundoString: String = "0"
    var minutosString: String = "0"
    var horaString: String = "0"
    var isOn: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reloj)
        var botonTiempo: Button = findViewById(R.id.botonReloj) as Button
        Thread(Runnable {
            while (true) {
                if(isOn) {
                    if(milisegundos == 440) {
                        milisegundos=0
                        this.runOnUiThread(Runnable { actualizarReloj() })
                    }
                    Thread.sleep(1)
                    milisegundos++

                }
            }
        }).start()



        botonTiempo.setOnClickListener {
            isOn=!isOn
            if(isOn) {
                botonTiempo.setText("Detener")
            } else {
                botonTiempo.setText("Iniciar")
            }

        }
    }

    fun actualizarReloj() {
            segundo ++
            if (segundo == 59) {
                segundo = 0
                minuto ++
                if (minuto == 59) {
                    minuto = 0
                    hora ++
                }
            }
            if (segundo < 10) {
                segundoString = "0" + segundo
            } else {
                segundoString = "" + segundo
            }

            if (minuto < 10) {
                minutosString = "0" + minuto
            } else {
                minutosString = "" + minuto
            }

            if (hora < 10) {
                horaString = "0" + hora
            } else {
                horaString = "" + hora
            }
        vistaTiempo.setText(horaString + ":" + minutosString + ":" + segundoString)
    }
}
