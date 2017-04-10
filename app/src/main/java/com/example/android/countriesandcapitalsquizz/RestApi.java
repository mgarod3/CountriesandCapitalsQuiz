package com.example.android.countriesandcapitalsquizz;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Merchy on 08/04/2017.
 */

public interface RestApi {
    @GET("rest/v2/all?fields=name;capital;region;flag;translations")
    Call<ArrayList<CountriesInfo>> getCountryInfo();
}
