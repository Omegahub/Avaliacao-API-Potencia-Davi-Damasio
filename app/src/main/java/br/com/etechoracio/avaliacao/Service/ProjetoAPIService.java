package br.com.etechoracio.avaliacao.Service;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProjetoAPIService {

    @POST(".")
    Call<String> executar(@Path("num1") String num1, @Path("num2") String num2);
}
