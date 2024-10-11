package com.example.myapps

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import android.net.Uri as Uri

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_PERSON = "key_hero"
    }

    private lateinit var tvDetailName: TextView
    private lateinit var tvDetailDescription: TextView
    private lateinit var ivDetailPhoto: ImageView
    private lateinit var classServant: TextView
    private lateinit var btnShare: Button
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        tvDetailName = findViewById(R.id.name_servant)
        tvDetailDescription = findViewById(R.id.deskripsi)
        ivDetailPhoto = findViewById(R.id.img_servant)
        classServant = findViewById(R.id.class_servant)
        btnShare = findViewById(R.id.btn_share)
        btnShare.setOnClickListener(this)
        val dataServant = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Servant>(EXTRA_PERSON, Servant::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Servant>(EXTRA_PERSON)
        }


        if (dataServant != null) {
            tvDetailName.text = dataServant.name
            tvDetailDescription.text = dataServant.desc
            Glide.with(this)
                .load(dataServant.photo)
                .into(ivDetailPhoto)
            classServant.text = dataServant.kelas
            imageUri = Uri.parse(dataServant.photo)

        }

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_share -> {
                val shareText = """
                                ${tvDetailName.text.toString()}
                                
                                ${tvDetailDescription.text.toString()}
                                
                                ${imageUri.toString()}
                            """.trimIndent()
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = "*/*"
                    putExtra(Intent.EXTRA_TEXT, shareText)
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                }
                startActivity(Intent.createChooser(shareIntent, "send"))
            }
        }
    }

}