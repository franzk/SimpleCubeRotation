package net.franzka.simplecuberotation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var drawingView: DrawingView

    private var delay : Long? = null

    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawingView = findViewById(R.id.viewCanvas)

        findViewById<View>(R.id.button_start).setOnClickListener {
            start()
        }

    }

    private fun start() {

        val angleX = textFieldToFloat(findViewById(R.id.edit_angle_x))
        val angleY = textFieldToFloat(findViewById(R.id.edit_angle_y))
        val angleZ = textFieldToFloat(findViewById(R.id.edit_angle_z))

        drawingView.setAngles(angleX, angleY, angleZ)

        delay = try { findViewById<TextInputEditText>(R.id.edit_delay).text.toString().toLong() }
                catch (e: Exception) { 0 }

        // launch
        handler.removeCallbacks(drawThread)
        handler.post(drawThread)

    }

    private fun textFieldToFloat(textField: TextInputEditText) : Float {
        return try {
            textField.text.toString().toFloat() / 100
        } catch (e: Exception) {
            0F
        }
    }


    private val drawThread = object : Runnable {
        override fun run() { // fonction appelée à chaque expiration du délai
            drawingView.run {
                rotation()
                invalidate()
            }
            handler.postDelayed(this, delay ?: 0 ) // on relance le délai
        }
    }



}