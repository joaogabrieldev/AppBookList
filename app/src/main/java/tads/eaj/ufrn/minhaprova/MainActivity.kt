package tads.eaj.ufrn.minhaprova

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import tads.eaj.ufrn.minhaprova.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val CODIGO_BOTAO1 = 99
    val CODIGO_BOTAO3 = 98
    val CODIGO_BOTAO4 = 97

    lateinit var binding: ActivityMainBinding
    lateinit var viewmodel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this

        binding.button1.setOnClickListener {
            val intent = Intent(this, ActivityAcao1::class.java)
            startActivityForResult(intent, CODIGO_BOTAO1)
        }

        binding.button2.setOnClickListener {
            val dialog = FragDialog()
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, "Dialog")
        }

        binding.button3.setOnClickListener {
            val intent = Intent(this, ActivityAcao2::class.java)
            startActivityForResult(intent, CODIGO_BOTAO3)
        }

        binding.button4.setOnClickListener {
            val intent = Intent(this, ActivityAcao3::class.java)
            startActivityForResult(intent, CODIGO_BOTAO4)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            CODIGO_BOTAO1 -> {
                when(resultCode){
                    Activity.RESULT_OK -> {
                        binding.textView.text = data?.getStringExtra("resultado1").toString()
                        viewmodel._resposta1 = data?.getStringExtra("resultado1").toString()
                        binding.invalidateAll()
                    }
                    Activity.RESULT_CANCELED -> {
                        Snackbar.make(binding.button1, "Atualização cancelada", Snackbar.LENGTH_SHORT)
                            .setAction("Cancelar"){
                                Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
                            }
                            .show()
                    }
                }
            }

            CODIGO_BOTAO3 -> {
                when(resultCode){
                    Activity.RESULT_OK -> {
                        binding.textView2.text = data?.getStringExtra("resultado2").toString()
                        viewmodel._resposta2 = data?.getStringExtra("resultado2").toString()
                        binding.invalidateAll()
                    }
                    Activity.RESULT_CANCELED -> {
                        Toast.makeText(this, "Cancelado", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }
    }
}