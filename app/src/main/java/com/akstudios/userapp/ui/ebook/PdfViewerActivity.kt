package com.akstudios.userapp.ui.ebook

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akstudios.userapp.R
import com.github.barteksc.pdfviewer.PDFView
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class PdfViewerActivity : AppCompatActivity() {
    private lateinit var url: String
    private lateinit var pdfViewer: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        url = intent.getStringExtra("url").toString()
        pdfViewer = findViewById(R.id.pdfView)

        val pdfDownload = PdfDownload()
        pdfDownload.execute(url)
    }

    inner class PdfDownload: AsyncTask<String, Void, InputStream>() {
        var inputStream: InputStream? = null
        override fun doInBackground(vararg p0: String?): InputStream? {
            try {
                val url = URL(p0[0])
                val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection

                if (urlConnection.responseCode == 200) {
                    inputStream = BufferedInputStream(urlConnection.inputStream)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return inputStream
        }

        override fun onPostExecute(result: InputStream?) {
            pdfViewer.fromStream(inputStream).load()
        }

        override fun onProgressUpdate(vararg values: Void?) {
            // progress bar
        }
    }
}