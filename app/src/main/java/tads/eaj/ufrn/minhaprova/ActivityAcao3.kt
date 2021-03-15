package tads.eaj.ufrn.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao3Binding
import java.lang.Exception

class ActivityAcao3 : AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding
    lateinit var bd: BookDBOpenner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao3)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)
        bd = BookDBOpenner(this)

        try {
            var Books = bd.findBookAll()
            var id = 1
            var b = bd.findBookID(id)
            binding.TituloView.text = b.nome
            binding.AutorView.text = b.autor
            binding.AnoView.text = b.ano.toString()
            binding.NotaView.text = b.nota.toString()
            if(Books.size <= id){
                binding.NextButton.isEnabled = false
                binding.PreviusButton.isEnabled = false
            }
            if(Books.size > id){
                binding.NextButton.isEnabled = true
                binding.PreviusButton.isEnabled = false
            }

            binding.NextButton.setOnClickListener {
                id++
                if(id >= Books.size){
                    binding.NextButton.isEnabled = false
                }
                if (id > 1){
                    binding.PreviusButton.isEnabled = true
                }
                var b = bd.findBookID(id)
                binding.TituloView.text = b.nome
                binding.AutorView.text = b.autor
                binding.AnoView.text = b.ano.toString()
                binding.NotaView.text = b.nota.toString()
            }

            binding.PreviusButton.setOnClickListener {
                id--
                if(id == 1){
                    binding.PreviusButton.isEnabled = false
                }
                if(id < Books.size){
                    binding.NextButton.isEnabled = true
                }
                bd.findBookAll()
                var b = bd.findBookID(id)
                binding.TituloView.text = b.nome
                binding.AutorView.text = b.autor
                binding.AnoView.text = b.ano.toString()
                binding.NotaView.text = b.nota.toString()

            }

        } catch (e:Exception){
            binding.PreviusButton.isEnabled = false
            binding.NextButton.isEnabled = false
            Snackbar.make(binding.TituloView, "Banco vazio", Snackbar.LENGTH_INDEFINITE).setAction("Voltar"){
                finish()
            }.show()
        }
    }
}