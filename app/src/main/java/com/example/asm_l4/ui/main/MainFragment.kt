package com.example.asm_l4.ui.main

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.asm_l4.R
import com.example.asm_l4.databinding.MainFragmentBinding
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var thiscontext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.main_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.dane = viewModel
        binding.lifecycleOwner = this

        binding.button.setOnClickListener {
            wyswietlStrone()
        }
        if (container != null) {
            thiscontext = container.context
        }

        return binding.root
    }

    private fun wyswietlStrone(){
        var ur = binding.editText.text.toString()
        Thread( Runnable {
            var res = doInBackground(ur)
        }).start()


    }

    fun getPage(): String? {
        val d1 = ContactsContract.Data.Builder()

    }

    protected fun doInBackground(vararg urls: String?): String? {
        var result: String? = ""
        Log.i("connnn","Im siema")
        try {
            var conn1 = new URL(urls).openConnection() as HttpURLConnection



            /*Log.i("connnn", "Im try")
            var url: URL
            Log.i("connnn", "Im try2")
            var urlConnection: HttpURLConnection? = null
            Log.i("connnn", "Im try3")
            url = URL(urls[0])
            urlConnection = url.openConnection() as HttpURLConnection
            val `in`: InputStream = urlConnection!!.getInputStream()
            val reader = InputStreamReader(`in`)
            var data: Int = reader.read()
            while (data != -1) {
                val current = data.toChar()
                result += current
                data = reader.read()
            }
            Log.i("connnn", "Im here")
            viewModel.setText(result)*/

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

}