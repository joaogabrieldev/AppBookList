package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao1Binding

class ActivityAcao1 : AppCompatActivity() {

    lateinit var binding:ActivityAcao1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao1)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao1)

        binding.confirmarButton.setOnClickListener {
            val intent = Intent()
            intent.putExtra("resultado1", binding.editText1.text.toString())
            setResult(RESULT_OK, intent)
            finish()
        }

        binding.cancelarButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}