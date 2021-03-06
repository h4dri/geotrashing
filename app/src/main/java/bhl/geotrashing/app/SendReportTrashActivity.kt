package bhl.geotrashing.app

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import bhl.geotrashing.app.firestore.DataBase
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_send_report_trash.*


class SendReportTrashActivity : AppCompatActivity() {

    lateinit var location: LatLng;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_report_trash)
        val intent: Intent = getIntent()
        val takenImage = intent.getStringExtra("path")
        val decodedTakenImage = BitmapFactory.decodeFile(takenImage)
        val db = DataBase(this)
        location = intent.getParcelableExtra("location")
        Toast.makeText(this, location.latitude.toString(), Toast.LENGTH_LONG)
        activity_send_report_trash_imageViewId.setImageBitmap(decodedTakenImage)
        val description = activity_send_report_trash_descriptionId.text

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.send -> {
                    db.uploadTrash(location, description.toString(), decodedTakenImage)
                    true
                }
                else -> false
            }

        }

        topAppBar.setNavigationOnClickListener {
            super.finish()
        }

    }
}
