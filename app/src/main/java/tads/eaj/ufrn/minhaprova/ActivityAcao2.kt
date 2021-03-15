package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao2Binding

class ActivityAcao2 : AppCompatActivity() {

    lateinit var binding:ActivityAcao2Binding
    lateinit var db: BookDBOpenner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao2)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao2)
        db = BookDBOpenner(this)
        var b = Book()

        binding.ConfirmButton.setOnClickListener {

            if(binding.TituloEditText.text.isNotBlank() &&
               binding.AutorEditText.text.isNotBlank() &&
               binding.AnoEditText.text.isNotBlank())
            {
               b.nome = binding.TituloEditText.text.toString()
               b.autor = binding.AutorEditText.text.toString()
               b.ano = binding.AnoEditText.text.toString().toInt()
               b.nota = binding.NotaRatingBar.rating

               db.createBook(b)

               var intent = Intent()
               intent.putExtra("resultado2", "Cadastrado")
               setResult(Activity.RESULT_OK, intent)
               finish()

            } else {
                Toast.makeText(this@ActivityAcao2, "Erro, preencha todas as informações !!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.CancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}