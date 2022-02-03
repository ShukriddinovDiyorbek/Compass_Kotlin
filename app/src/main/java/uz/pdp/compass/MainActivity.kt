package uz.pdp.compass

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity(), SensorEventListener {
    var manager: SensorManager? = null
    var current_degree:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        manager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    override fun onResume() {
        super.onResume()
        manager?.registerListener(this,manager?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_GAME)

    }

    override fun onPause() {
        super.onPause()
        manager?.unregisterListener(this)
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(p0: SensorEvent?) {
        val tvDegree = findViewById<TextView>(R.id.tvDegree)
        val imDinamic = findViewById<ImageView>(R.id.imDinamic)
        val degree:Int = p0?.values?.get(0)?.toInt()!!
        tvDegree.text = degree.toString()
        val rotationAnim = RotateAnimation(current_degree.toFloat(),(-degree).toFloat(),Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,0.5f)
        rotationAnim.duration = 210
        rotationAnim.fillAfter = true
        current_degree = -degree
        imDinamic.startAnimation(rotationAnim)

    }
}