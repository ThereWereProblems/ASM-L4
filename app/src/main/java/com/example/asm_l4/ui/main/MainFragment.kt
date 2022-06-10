package com.example.asm_l4.ui.main

import android.content.Context
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.asm_l4.R
import com.example.asm_l4.databinding.MainFragmentBinding
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }
    private lateinit var binding: MainFragmentBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var thiscontext: Context

    private var imageUrl = "https://www.wallpaperbetter.com/wallpaper/35/263/660/volkswagen-passat-cc-car-tuning-gold-city-720P-wallpaper-middle-size.jpg"

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

        binding.button2.setOnClickListener {
            wyswietlObrazek()
        }

        if (container != null) {
            thiscontext = container.context
        }

        if(viewModel.isImageInitialized()){
            binding.imageView.setImageBitmap(viewModel.resImage)
        }

        return binding.root
    }

    private fun wyswietlStrone(){
        var ur = binding.editText.text.toString()
        Thread( Runnable {
            getPage(ur)
        }).start()
    }

    private fun wyswietlObrazek(){
        var ur = binding.editText.text.toString()
        Thread( Runnable {
            getImage()
        }).start()
    }

    fun getPage(ur: String) {
        var google: URL? = null
        try {
            google = URL(ur)

            var `in`: BufferedReader? = null

            `in` = BufferedReader(InputStreamReader(google!!.openStream()))

            var input: String? = null
            val stringBuffer = StringBuffer()
            while (true) {

                if (`in`.readLine().also { input = it } == null) break

                stringBuffer.append(input)
            }

            `in`.close()

            val htmlData = stringBuffer.toString()

            getActivity()?.runOnUiThread(Runnable {
                viewModel.setText(htmlData)
            })

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


    fun getImage(){
        var link = URL(imageUrl)
        viewModel.resImage = BitmapFactory.decodeStream(link.openConnection().getInputStream());
        getActivity()?.runOnUiThread(Runnable {
            viewModel.setText("Your website will appear here")
            binding.imageView.setImageBitmap(viewModel.resImage)
        })
    }
}
