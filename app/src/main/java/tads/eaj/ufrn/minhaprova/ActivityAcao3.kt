package tads.eaj.ufrn.minhaprova

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import tads.eaj.ufrn.minhaprova.databinding.ActivityAcao3Binding

class ActivityAcao3 : AppCompatActivity() {

    lateinit var binding: ActivityAcao3Binding
    lateinit var bd: BookDBOpenner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acao3)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_acao3)
        bd = BookDBOpenner(this)

        var Books = bd.findBookAll()
        var id = 1
        var b = bd.findBookID(id)

        binding.TituloView.text = b.nome
        binding.AutorView.text = b.autor
        binding.AnoView.text = b.ano.toString()
        binding.NotaView.text = b.nota.toString()


        binding.NextButton.setOnClickListener {
            if(id == Books.size){
                binding.NextButton.isEnabled = false
            } else {
                id++
                var b = bd.findBookID(id)
                binding.TituloView.text = b.nome
                binding.AutorView.text = b.autor
                binding.AnoView.text = b.ano.toString()
                binding.NotaView.text = b.nota.toString()

                binding.NextButton.isEnabled = true
                binding.PreviusButton.isEnabled = true

            }
        }

        binding.PreviusButton.setOnClickListener {
            if(id == 1){
                binding.PreviusButton.isEnabled = false
            } else {
                id--
                var b = bd.findBookID(id)

                binding.TituloView.text = b.nome
                binding.AutorView.text = b.autor
                binding.AnoView.text = b.ano.toString()
                binding.NotaView.text = b.nota.toString()

                binding.NextButton.isEnabled = true
                binding.PreviusButton.isEnabled = true

            }
        }

    }
}