package cl.afernandez.testapiconcepto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import cl.afernandez.testapiconcepto.api.ApiCallBack
import cl.afernandez.testapiconcepto.api.ApiTask
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(), ApiCallBack {
    private lateinit var texto: TextView
    private lateinit var boton: Button
    private var URL : String = "https://random.dog/woof.json"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        texto = findViewById(R.id.textView)
        boton = findViewById(R.id.button)

        boton.setOnClickListener {
            val apiRequestTask = ApiTask(this)
            apiRequestTask.execute(URL)
        }

    }

    override fun OnRequestComplete(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        procesar(result)
    }

    fun procesar (result: String){

        try {
            // Parse the JSON string into a JSONObject
            val jsonObject = JSONObject(result)

            // Access values from the JSON object )
            val url = jsonObject.getString("url")

            texto.text = url

        } catch  (e: JSONException) {
            e.printStackTrace()
        }
    }
}