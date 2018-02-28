package com.example.androidtestapp.shared

import okhttp3.OkHttpClient
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by akiyama on 2018/02/26.
 */
object HttpRequest {

    private val baseUrl = ""

    private val httpClient = OkHttpClient.Builder().addInterceptor { chain ->
        val original = chain.request()

        val request = original.newBuilder().apply {
            method(original.method(), original.body())
        }.build()

        return@addInterceptor chain.proceed(request)
    }

    private val service = Retrofit.Builder().apply {
        baseUrl(baseUrl)
        addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
        client(httpClient.build())
    }.build().create(HttpService::class.java)

    private fun <T> httpRequest(service: Call<T>, success: ((value: T?)->Unit), failure: ((error: Any?)->Unit)) {
        println(service.request().url().toString())

        service.enqueue(object: Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>?){
                if (response?.isSuccessful ?: false) {
                    println("request is successful :${call?.request()?.url()}")
                    success(response?.body())
                } else {
                    println("response is error(code: ${response?.code()}): ${call?.request()?.url()}")

                    val json = response?.errorBody()?.string()?.toJsonObject()
                    failure(json)
                }
            }

            override fun onFailure(call: Call<T>?, t: Throwable?) {
                if (call?.isCanceled ?: false) {
                    println("request is canceled :${call?.request()?.url()}")
                    println(t?.message ?: "")
                } else {
                    println("request is failed :${call?.request()?.url()}")
                    println(t?.message ?: "")
                    failure(null)
                }
            }
        })
    }

    fun call(category: String, success: ((value: ResponseXMLModel?) -> Unit), failure: ((error: Any?) -> Unit)): Call<ResponseXMLModel> {
        val service = if (category.isEmpty()) service.pickup() else service.category(category)
        httpRequest<ResponseXMLModel>(service, success, failure)

        return service
    }


    private interface HttpService {
        @GET("rss.xml")
        fun pickup(): Call<ResponseXMLModel>

        @GET("{category}/rss.xml")
        fun category(@Path("category") category: String): Call<ResponseXMLModel>
    }
}