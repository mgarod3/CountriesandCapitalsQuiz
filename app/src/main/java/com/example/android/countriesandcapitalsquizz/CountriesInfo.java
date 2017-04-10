package com.example.android.countriesandcapitalsquizz;

/**
 * Created by Merchy on 08/04/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Merchy on 08/04/2017.
 */


public class CountriesInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("capital")
    @Expose
    private String capital;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("flag")
    @Expose
    private String flag;
    @SerializedName("translations")
    @Expose
    private Translations translations;


    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The capital
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @param capital The capital
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * @return The region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region The region
     */
    public void setRegion(String region) {
        this.region = region;
    }


    /**
     * @return The translations
     */
    public Translations getTranslations() {
        return translations;
    }

    /**
     * @param translations The translations
     */
    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    /**
     * @return The flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag The flag
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}