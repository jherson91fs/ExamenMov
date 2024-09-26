package com.example.game.api

import com.example.game.model.Tres
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("api/juegotres")
    fun crearPartida(@Body nuevaPartida: Tres): Call<Tres>

    @PATCH("api/juegotres/{id}")
    fun actualizarResultado(
        @Path("id") id: Long,
        @Body resultadoActualizado: Tres
    ): Call<Tres>

    @GET("api/juegotres")
    fun obtenerResultados(): Call<List<Tres>>
}
