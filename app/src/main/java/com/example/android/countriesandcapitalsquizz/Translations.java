package com.example.android.countriesandcapitalsquizz;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Merchy on 08/04/2017.
 */

public class Translations {

    @SerializedName("de")
    @Expose
    private String de;
    @SerializedName("es")
    @Expose
    private String es;
    @SerializedName("fr")
    @Expose
    private String fr;
    @SerializedName("ja")
    @Expose
    private String ja;
    @SerializedName("it")
    @Expose
    private String it;

    /**
     *
     * @return
     *     The de
     */
    public String getDe() {
        return de;
    }

    /**
     *
     * @param de
     *     The de
     */
    public void setDe(String de) {
        this.de = de;
    }

    /**
     *
     * @return
     *     The es
     */
    public String getEs() {
        return es;
    }

    /**
     *
     * @param es
     *     The es
     */
    public void setEs(String es) {
        this.es = es;
    }

    /**
     *
     * @return
     *     The fr
     */
    public String getFr() {
        return fr;
    }

    /**
     *
     * @param fr
     *     The fr
     */
    public void setFr(String fr) {
        this.fr = fr;
    }

    /**
     *
     * @return
     *     The ja
     */
    public String getJa() {
        return ja;
    }

    /**
     *
     * @param ja
     *     The ja
     */
    public void setJa(String ja) {
        this.ja = ja;
    }

    /**
     *
     * @return
     *     The it
     */
    public String getIt() {
        return it;
    }

    /**
     *
     * @param it
     *     The it
     */
    public void setIt(String it) {
        this.it = it;
    }

}

