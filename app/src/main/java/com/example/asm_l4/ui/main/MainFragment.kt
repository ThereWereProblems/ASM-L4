package com.example.asm_l4.ui.main

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

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
            getPage()
            //var res = doInBackground(ur)
        }).start()
    }

    /*fun getPage(){
        var google: URL? = null
        try {
            google = URL("https://www.google.com")
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        var `in`: BufferedReader? = null
        try {
            `in` = BufferedReader(InputStreamReader(google!!.openStream()))
        } catch (e: IOException) {
            e.printStackTrace()
        }
        var input: String? = null
        val stringBuffer = StringBuffer()
        while (true) {
            try {
                if (`in`.readLine().also { input = it } == null) break
            } catch (e: IOException) {
                e.printStackTrace()
            }
            stringBuffer.append(input)
        }
        try {
            `in`.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val htmlData = stringBuffer.toString()

    }*/

    fun getPage() {
        var google: URL? = null
        try {
            google = URL("https://www.google.com")

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

    /*fun getPage(String ul){
        StrictMode.ThreadPolicy policy = new StrictMode().ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(ul);
        try {
            HttpResponse response;
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

        }
        catch (Exception ex){

        }
    }*/

    /*protected fun doInBackground(vararg urls: String?): String? {
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
    }*/

}