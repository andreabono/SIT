/*
 * Apollo Web Services
 *  # Rate limit:   The **Rate Limit** and **Rate Reset** are specified in the HTTP-header with `X-RateLimit-Limit` and `X-RateLimit-Reset`; the rate limit is *per* `IP`.
 *
 * The version of the OpenAPI document: 2.32.0
 * Contact: valentino.lauciani@ingv.it
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.ingv.apollo.model;

import java.util.Objects;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.ingv.apollo.JSON;

/**
 * Hyp2000arcPhasesInner
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class Hyp2000arcPhasesInner {
  public static final String SERIALIZED_NAME_NET = "net";
  @SerializedName(SERIALIZED_NAME_NET)
  private String net;

  public static final String SERIALIZED_NAME_STA = "sta";
  @SerializedName(SERIALIZED_NAME_STA)
  private String sta;

  public static final String SERIALIZED_NAME_COMP = "comp";
  @SerializedName(SERIALIZED_NAME_COMP)
  private String comp;

  public static final String SERIALIZED_NAME_LOC = "loc";
  @SerializedName(SERIALIZED_NAME_LOC)
  private String loc = "--";

  public static final String SERIALIZED_NAME_PLABEL = "Plabel";
  @SerializedName(SERIALIZED_NAME_PLABEL)
  private String plabel;

  public static final String SERIALIZED_NAME_SLABEL = "Slabel";
  @SerializedName(SERIALIZED_NAME_SLABEL)
  private String slabel;

  public static final String SERIALIZED_NAME_PONSET = "Ponset";
  @SerializedName(SERIALIZED_NAME_PONSET)
  private String ponset;

  public static final String SERIALIZED_NAME_SONSET = "Sonset";
  @SerializedName(SERIALIZED_NAME_SONSET)
  private String sonset;

  public static final String SERIALIZED_NAME_PAT = "Pat";
  @SerializedName(SERIALIZED_NAME_PAT)
  private OffsetDateTime pat;

  public static final String SERIALIZED_NAME_SAT = "Sat";
  @SerializedName(SERIALIZED_NAME_SAT)
  private OffsetDateTime sat;

  public static final String SERIALIZED_NAME_PRES = "Pres";
  @SerializedName(SERIALIZED_NAME_PRES)
  private Double pres;

  public static final String SERIALIZED_NAME_SRES = "Sres";
  @SerializedName(SERIALIZED_NAME_SRES)
  private Double sres;

  public static final String SERIALIZED_NAME_PQUAL = "Pqual";
  @SerializedName(SERIALIZED_NAME_PQUAL)
  private Double pqual;

  public static final String SERIALIZED_NAME_SQUAL = "Squal";
  @SerializedName(SERIALIZED_NAME_SQUAL)
  private Double squal;

  public static final String SERIALIZED_NAME_CODALEN = "codalen";
  @SerializedName(SERIALIZED_NAME_CODALEN)
  private Double codalen;

  public static final String SERIALIZED_NAME_CODAWT = "codawt";
  @SerializedName(SERIALIZED_NAME_CODAWT)
  private Double codawt;

  public static final String SERIALIZED_NAME_PFM = "Pfm";
  @SerializedName(SERIALIZED_NAME_PFM)
  private String pfm;

  public static final String SERIALIZED_NAME_SFM = "Sfm";
  @SerializedName(SERIALIZED_NAME_SFM)
  private String sfm;

  public static final String SERIALIZED_NAME_DATASRC = "datasrc";
  @SerializedName(SERIALIZED_NAME_DATASRC)
  private String datasrc;

  public static final String SERIALIZED_NAME_MD = "Md";
  @SerializedName(SERIALIZED_NAME_MD)
  private Double md;

  public static final String SERIALIZED_NAME_AZM = "azm";
  @SerializedName(SERIALIZED_NAME_AZM)
  private Double azm;

  public static final String SERIALIZED_NAME_TAKEOFF = "takeoff";
  @SerializedName(SERIALIZED_NAME_TAKEOFF)
  private Double takeoff;

  public static final String SERIALIZED_NAME_DIST = "dist";
  @SerializedName(SERIALIZED_NAME_DIST)
  private Double dist;

  public static final String SERIALIZED_NAME_PWT = "Pwt";
  @SerializedName(SERIALIZED_NAME_PWT)
  private Double pwt;

  public static final String SERIALIZED_NAME_SWT = "Swt";
  @SerializedName(SERIALIZED_NAME_SWT)
  private Double swt;

  public static final String SERIALIZED_NAME_PAMP = "pamp";
  @SerializedName(SERIALIZED_NAME_PAMP)
  private Double pamp;

  public static final String SERIALIZED_NAME_CODALEN_OBS = "codalenObs";
  @SerializedName(SERIALIZED_NAME_CODALEN_OBS)
  private Double codalenObs;

  public static final String SERIALIZED_NAME_CCNTR = "ccntr";
  @SerializedName(SERIALIZED_NAME_CCNTR)
  private List<Long> ccntr;

  public static final String SERIALIZED_NAME_CAAV = "caav";
  @SerializedName(SERIALIZED_NAME_CAAV)
  private List<Long> caav;

  public static final String SERIALIZED_NAME_AMPLITUDE = "amplitude";
  @SerializedName(SERIALIZED_NAME_AMPLITUDE)
  private Float amplitude;

  public static final String SERIALIZED_NAME_AMP_UNITS_CODE = "ampUnitsCode";
  @SerializedName(SERIALIZED_NAME_AMP_UNITS_CODE)
  private Long ampUnitsCode;

  public static final String SERIALIZED_NAME_AMP_TYPE = "ampType";
  @SerializedName(SERIALIZED_NAME_AMP_TYPE)
  private Long ampType;

  public static final String SERIALIZED_NAME_AMP_MAG = "ampMag";
  @SerializedName(SERIALIZED_NAME_AMP_MAG)
  private Float ampMag;

  public static final String SERIALIZED_NAME_AMP_MAG_WEIGHT_CODE = "ampMagWeightCode";
  @SerializedName(SERIALIZED_NAME_AMP_MAG_WEIGHT_CODE)
  private Long ampMagWeightCode;

  public static final String SERIALIZED_NAME_IMPORTANCE_P = "importanceP";
  @SerializedName(SERIALIZED_NAME_IMPORTANCE_P)
  private Float importanceP;

  public static final String SERIALIZED_NAME_IMPORTANCE_S = "importanceS";
  @SerializedName(SERIALIZED_NAME_IMPORTANCE_S)
  private Float importanceS;

  public Hyp2000arcPhasesInner() {
  }

  public Hyp2000arcPhasesInner net(String net) {
    this.net = net;
    return this;
  }

   /**
   * Channel net code | char(2)
   * @return net
  **/
  @javax.annotation.Nonnull
  public String getNet() {
    return net;
  }

  public void setNet(String net) {
    this.net = net;
  }


  public Hyp2000arcPhasesInner sta(String sta) {
    this.sta = sta;
    return this;
  }

   /**
   * Channel station code | varchar(5)
   * @return sta
  **/
  @javax.annotation.Nonnull
  public String getSta() {
    return sta;
  }

  public void setSta(String sta) {
    this.sta = sta;
  }


  public Hyp2000arcPhasesInner comp(String comp) {
    this.comp = comp;
    return this;
  }

   /**
   * Channel code | char(3)
   * @return comp
  **/
  @javax.annotation.Nonnull
  public String getComp() {
    return comp;
  }

  public void setComp(String comp) {
    this.comp = comp;
  }


  public Hyp2000arcPhasesInner loc(String loc) {
    this.loc = loc;
    return this;
  }

   /**
   * Channel location | char(2)
   * @return loc
  **/
  @javax.annotation.Nonnull
  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }


  public Hyp2000arcPhasesInner plabel(String plabel) {
    this.plabel = plabel;
    return this;
  }

   /**
   * Todo description | ???
   * @return plabel
  **/
  @javax.annotation.Nullable
  public String getPlabel() {
    return plabel;
  }

  public void setPlabel(String plabel) {
    this.plabel = plabel;
  }


  public Hyp2000arcPhasesInner slabel(String slabel) {
    this.slabel = slabel;
    return this;
  }

   /**
   * Todo description | ???
   * @return slabel
  **/
  @javax.annotation.Nullable
  public String getSlabel() {
    return slabel;
  }

  public void setSlabel(String slabel) {
    this.slabel = slabel;
  }


  public Hyp2000arcPhasesInner ponset(String ponset) {
    this.ponset = ponset;
    return this;
  }

   /**
   * Todo description | ???
   * @return ponset
  **/
  @javax.annotation.Nullable
  public String getPonset() {
    return ponset;
  }

  public void setPonset(String ponset) {
    this.ponset = ponset;
  }


  public Hyp2000arcPhasesInner sonset(String sonset) {
    this.sonset = sonset;
    return this;
  }

   /**
   * Todo description | ???
   * @return sonset
  **/
  @javax.annotation.Nullable
  public String getSonset() {
    return sonset;
  }

  public void setSonset(String sonset) {
    this.sonset = sonset;
  }


  public Hyp2000arcPhasesInner pat(OffsetDateTime pat) {
    this.pat = pat;
    return this;
  }

   /**
   * Origin time | datetime(3)
   * @return pat
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getPat() {
    return pat;
  }

  public void setPat(OffsetDateTime pat) {
    this.pat = pat;
  }


  public Hyp2000arcPhasesInner sat(OffsetDateTime sat) {
    this.sat = sat;
    return this;
  }

   /**
   * Origin time | datetime(3)
   * @return sat
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getSat() {
    return sat;
  }

  public void setSat(OffsetDateTime sat) {
    this.sat = sat;
  }


  public Hyp2000arcPhasesInner pres(Double pres) {
    this.pres = pres;
    return this;
  }

   /**
   * Todo description | ???
   * @return pres
  **/
  @javax.annotation.Nullable
  public Double getPres() {
    return pres;
  }

  public void setPres(Double pres) {
    this.pres = pres;
  }


  public Hyp2000arcPhasesInner sres(Double sres) {
    this.sres = sres;
    return this;
  }

   /**
   * Todo description | ???
   * @return sres
  **/
  @javax.annotation.Nullable
  public Double getSres() {
    return sres;
  }

  public void setSres(Double sres) {
    this.sres = sres;
  }


  public Hyp2000arcPhasesInner pqual(Double pqual) {
    this.pqual = pqual;
    return this;
  }

   /**
   * Todo description | ???
   * @return pqual
  **/
  @javax.annotation.Nullable
  public Double getPqual() {
    return pqual;
  }

  public void setPqual(Double pqual) {
    this.pqual = pqual;
  }


  public Hyp2000arcPhasesInner squal(Double squal) {
    this.squal = squal;
    return this;
  }

   /**
   * Todo description | ???
   * @return squal
  **/
  @javax.annotation.Nullable
  public Double getSqual() {
    return squal;
  }

  public void setSqual(Double squal) {
    this.squal = squal;
  }


  public Hyp2000arcPhasesInner codalen(Double codalen) {
    this.codalen = codalen;
    return this;
  }

   /**
   * Todo description | ???
   * @return codalen
  **/
  @javax.annotation.Nullable
  public Double getCodalen() {
    return codalen;
  }

  public void setCodalen(Double codalen) {
    this.codalen = codalen;
  }


  public Hyp2000arcPhasesInner codawt(Double codawt) {
    this.codawt = codawt;
    return this;
  }

   /**
   * Todo description | ???
   * @return codawt
  **/
  @javax.annotation.Nullable
  public Double getCodawt() {
    return codawt;
  }

  public void setCodawt(Double codawt) {
    this.codawt = codawt;
  }


  public Hyp2000arcPhasesInner pfm(String pfm) {
    this.pfm = pfm;
    return this;
  }

   /**
   * Todo description | ???
   * @return pfm
  **/
  @javax.annotation.Nullable
  public String getPfm() {
    return pfm;
  }

  public void setPfm(String pfm) {
    this.pfm = pfm;
  }


  public Hyp2000arcPhasesInner sfm(String sfm) {
    this.sfm = sfm;
    return this;
  }

   /**
   * Todo description | ???
   * @return sfm
  **/
  @javax.annotation.Nullable
  public String getSfm() {
    return sfm;
  }

  public void setSfm(String sfm) {
    this.sfm = sfm;
  }


  public Hyp2000arcPhasesInner datasrc(String datasrc) {
    this.datasrc = datasrc;
    return this;
  }

   /**
   * Todo description | ???
   * @return datasrc
  **/
  @javax.annotation.Nullable
  public String getDatasrc() {
    return datasrc;
  }

  public void setDatasrc(String datasrc) {
    this.datasrc = datasrc;
  }


  public Hyp2000arcPhasesInner md(Double md) {
    this.md = md;
    return this;
  }

   /**
   * Todo description | ???
   * @return md
  **/
  @javax.annotation.Nullable
  public Double getMd() {
    return md;
  }

  public void setMd(Double md) {
    this.md = md;
  }


  public Hyp2000arcPhasesInner azm(Double azm) {
    this.azm = azm;
    return this;
  }

   /**
   * Todo description | ???
   * @return azm
  **/
  @javax.annotation.Nullable
  public Double getAzm() {
    return azm;
  }

  public void setAzm(Double azm) {
    this.azm = azm;
  }


  public Hyp2000arcPhasesInner takeoff(Double takeoff) {
    this.takeoff = takeoff;
    return this;
  }

   /**
   * Todo description | ???
   * @return takeoff
  **/
  @javax.annotation.Nullable
  public Double getTakeoff() {
    return takeoff;
  }

  public void setTakeoff(Double takeoff) {
    this.takeoff = takeoff;
  }


  public Hyp2000arcPhasesInner dist(Double dist) {
    this.dist = dist;
    return this;
  }

   /**
   * Todo description | ???
   * @return dist
  **/
  @javax.annotation.Nullable
  public Double getDist() {
    return dist;
  }

  public void setDist(Double dist) {
    this.dist = dist;
  }


  public Hyp2000arcPhasesInner pwt(Double pwt) {
    this.pwt = pwt;
    return this;
  }

   /**
   * Todo description | ???
   * @return pwt
  **/
  @javax.annotation.Nullable
  public Double getPwt() {
    return pwt;
  }

  public void setPwt(Double pwt) {
    this.pwt = pwt;
  }


  public Hyp2000arcPhasesInner swt(Double swt) {
    this.swt = swt;
    return this;
  }

   /**
   * Todo description | ???
   * @return swt
  **/
  @javax.annotation.Nullable
  public Double getSwt() {
    return swt;
  }

  public void setSwt(Double swt) {
    this.swt = swt;
  }


  public Hyp2000arcPhasesInner pamp(Double pamp) {
    this.pamp = pamp;
    return this;
  }

   /**
   * Todo description | ???
   * @return pamp
  **/
  @javax.annotation.Nullable
  public Double getPamp() {
    return pamp;
  }

  public void setPamp(Double pamp) {
    this.pamp = pamp;
  }


  public Hyp2000arcPhasesInner codalenObs(Double codalenObs) {
    this.codalenObs = codalenObs;
    return this;
  }

   /**
   * Todo description | ???
   * @return codalenObs
  **/
  @javax.annotation.Nullable
  public Double getCodalenObs() {
    return codalenObs;
  }

  public void setCodalenObs(Double codalenObs) {
    this.codalenObs = codalenObs;
  }


  public Hyp2000arcPhasesInner ccntr(List<Long> ccntr) {
    this.ccntr = ccntr;
    return this;
  }

  public Hyp2000arcPhasesInner addCcntrItem(Long ccntrItem) {
    if (this.ccntr == null) {
      this.ccntr = new ArrayList<>();
    }
    this.ccntr.add(ccntrItem);
    return this;
  }

   /**
   * Get ccntr
   * @return ccntr
  **/
  @javax.annotation.Nullable
  public List<Long> getCcntr() {
    return ccntr;
  }

  public void setCcntr(List<Long> ccntr) {
    this.ccntr = ccntr;
  }


  public Hyp2000arcPhasesInner caav(List<Long> caav) {
    this.caav = caav;
    return this;
  }

  public Hyp2000arcPhasesInner addCaavItem(Long caavItem) {
    if (this.caav == null) {
      this.caav = new ArrayList<>();
    }
    this.caav.add(caavItem);
    return this;
  }

   /**
   * Get caav
   * @return caav
  **/
  @javax.annotation.Nullable
  public List<Long> getCaav() {
    return caav;
  }

  public void setCaav(List<Long> caav) {
    this.caav = caav;
  }


  public Hyp2000arcPhasesInner amplitude(Float amplitude) {
    this.amplitude = amplitude;
    return this;
  }

   /**
   * Amplitude (Normally peak-to-peak) | col55 F7.2
   * @return amplitude
  **/
  @javax.annotation.Nullable
  public Float getAmplitude() {
    return amplitude;
  }

  public void setAmplitude(Float amplitude) {
    this.amplitude = amplitude;
  }


  public Hyp2000arcPhasesInner ampUnitsCode(Long ampUnitsCode) {
    this.ampUnitsCode = ampUnitsCode;
    return this;
  }

   /**
   * Amp units code. 0&#x3D;PP mm, 1&#x3D;0 to peak mm (UCB), 2&#x3D;digital counts | col62 I2
   * @return ampUnitsCode
  **/
  @javax.annotation.Nullable
  public Long getAmpUnitsCode() {
    return ampUnitsCode;
  }

  public void setAmpUnitsCode(Long ampUnitsCode) {
    this.ampUnitsCode = ampUnitsCode;
  }


  public Hyp2000arcPhasesInner ampType(Long ampType) {
    this.ampType = ampType;
    return this;
  }

   /**
   * Amplitude type 0&#x3D;unspecified 1&#x3D;Wood-Anderson 2&#x3D;velocity 3&#x3D;acceleration 4&#x3D;no magnitude | col114 I2
   * @return ampType
  **/
  @javax.annotation.Nullable
  public Long getAmpType() {
    return ampType;
  }

  public void setAmpType(Long ampType) {
    this.ampType = ampType;
  }


  public Hyp2000arcPhasesInner ampMag(Float ampMag) {
    this.ampMag = ampMag;
    return this;
  }

   /**
   * Amplitude magnitude for this station | col98 F3.2
   * @return ampMag
  **/
  @javax.annotation.Nullable
  public Float getAmpMag() {
    return ampMag;
  }

  public void setAmpMag(Float ampMag) {
    this.ampMag = ampMag;
  }


  public Hyp2000arcPhasesInner ampMagWeightCode(Long ampMagWeightCode) {
    this.ampMagWeightCode = ampMagWeightCode;
    return this;
  }

   /**
   * Amplitude magnitude weight code | col82 I1
   * @return ampMagWeightCode
  **/
  @javax.annotation.Nullable
  public Long getAmpMagWeightCode() {
    return ampMagWeightCode;
  }

  public void setAmpMagWeightCode(Long ampMagWeightCode) {
    this.ampMagWeightCode = ampMagWeightCode;
  }


  public Hyp2000arcPhasesInner importanceP(Float importanceP) {
    this.importanceP = importanceP;
    return this;
  }

   /**
   * Importance of P arrival | col101 F4.3
   * @return importanceP
  **/
  @javax.annotation.Nullable
  public Float getImportanceP() {
    return importanceP;
  }

  public void setImportanceP(Float importanceP) {
    this.importanceP = importanceP;
  }


  public Hyp2000arcPhasesInner importanceS(Float importanceS) {
    this.importanceS = importanceS;
    return this;
  }

   /**
   * Importance of S arrival | col105 F4.3
   * @return importanceS
  **/
  @javax.annotation.Nullable
  public Float getImportanceS() {
    return importanceS;
  }

  public void setImportanceS(Float importanceS) {
    this.importanceS = importanceS;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Hyp2000arcPhasesInner hyp2000arcPhasesInner = (Hyp2000arcPhasesInner) o;
    return Objects.equals(this.net, hyp2000arcPhasesInner.net) &&
        Objects.equals(this.sta, hyp2000arcPhasesInner.sta) &&
        Objects.equals(this.comp, hyp2000arcPhasesInner.comp) &&
        Objects.equals(this.loc, hyp2000arcPhasesInner.loc) &&
        Objects.equals(this.plabel, hyp2000arcPhasesInner.plabel) &&
        Objects.equals(this.slabel, hyp2000arcPhasesInner.slabel) &&
        Objects.equals(this.ponset, hyp2000arcPhasesInner.ponset) &&
        Objects.equals(this.sonset, hyp2000arcPhasesInner.sonset) &&
        Objects.equals(this.pat, hyp2000arcPhasesInner.pat) &&
        Objects.equals(this.sat, hyp2000arcPhasesInner.sat) &&
        Objects.equals(this.pres, hyp2000arcPhasesInner.pres) &&
        Objects.equals(this.sres, hyp2000arcPhasesInner.sres) &&
        Objects.equals(this.pqual, hyp2000arcPhasesInner.pqual) &&
        Objects.equals(this.squal, hyp2000arcPhasesInner.squal) &&
        Objects.equals(this.codalen, hyp2000arcPhasesInner.codalen) &&
        Objects.equals(this.codawt, hyp2000arcPhasesInner.codawt) &&
        Objects.equals(this.pfm, hyp2000arcPhasesInner.pfm) &&
        Objects.equals(this.sfm, hyp2000arcPhasesInner.sfm) &&
        Objects.equals(this.datasrc, hyp2000arcPhasesInner.datasrc) &&
        Objects.equals(this.md, hyp2000arcPhasesInner.md) &&
        Objects.equals(this.azm, hyp2000arcPhasesInner.azm) &&
        Objects.equals(this.takeoff, hyp2000arcPhasesInner.takeoff) &&
        Objects.equals(this.dist, hyp2000arcPhasesInner.dist) &&
        Objects.equals(this.pwt, hyp2000arcPhasesInner.pwt) &&
        Objects.equals(this.swt, hyp2000arcPhasesInner.swt) &&
        Objects.equals(this.pamp, hyp2000arcPhasesInner.pamp) &&
        Objects.equals(this.codalenObs, hyp2000arcPhasesInner.codalenObs) &&
        Objects.equals(this.ccntr, hyp2000arcPhasesInner.ccntr) &&
        Objects.equals(this.caav, hyp2000arcPhasesInner.caav) &&
        Objects.equals(this.amplitude, hyp2000arcPhasesInner.amplitude) &&
        Objects.equals(this.ampUnitsCode, hyp2000arcPhasesInner.ampUnitsCode) &&
        Objects.equals(this.ampType, hyp2000arcPhasesInner.ampType) &&
        Objects.equals(this.ampMag, hyp2000arcPhasesInner.ampMag) &&
        Objects.equals(this.ampMagWeightCode, hyp2000arcPhasesInner.ampMagWeightCode) &&
        Objects.equals(this.importanceP, hyp2000arcPhasesInner.importanceP) &&
        Objects.equals(this.importanceS, hyp2000arcPhasesInner.importanceS);
  }

  @Override
  public int hashCode() {
    return Objects.hash(net, sta, comp, loc, plabel, slabel, ponset, sonset, pat, sat, pres, sres, pqual, squal, codalen, codawt, pfm, sfm, datasrc, md, azm, takeoff, dist, pwt, swt, pamp, codalenObs, ccntr, caav, amplitude, ampUnitsCode, ampType, ampMag, ampMagWeightCode, importanceP, importanceS);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Hyp2000arcPhasesInner {\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    comp: ").append(toIndentedString(comp)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    plabel: ").append(toIndentedString(plabel)).append("\n");
    sb.append("    slabel: ").append(toIndentedString(slabel)).append("\n");
    sb.append("    ponset: ").append(toIndentedString(ponset)).append("\n");
    sb.append("    sonset: ").append(toIndentedString(sonset)).append("\n");
    sb.append("    pat: ").append(toIndentedString(pat)).append("\n");
    sb.append("    sat: ").append(toIndentedString(sat)).append("\n");
    sb.append("    pres: ").append(toIndentedString(pres)).append("\n");
    sb.append("    sres: ").append(toIndentedString(sres)).append("\n");
    sb.append("    pqual: ").append(toIndentedString(pqual)).append("\n");
    sb.append("    squal: ").append(toIndentedString(squal)).append("\n");
    sb.append("    codalen: ").append(toIndentedString(codalen)).append("\n");
    sb.append("    codawt: ").append(toIndentedString(codawt)).append("\n");
    sb.append("    pfm: ").append(toIndentedString(pfm)).append("\n");
    sb.append("    sfm: ").append(toIndentedString(sfm)).append("\n");
    sb.append("    datasrc: ").append(toIndentedString(datasrc)).append("\n");
    sb.append("    md: ").append(toIndentedString(md)).append("\n");
    sb.append("    azm: ").append(toIndentedString(azm)).append("\n");
    sb.append("    takeoff: ").append(toIndentedString(takeoff)).append("\n");
    sb.append("    dist: ").append(toIndentedString(dist)).append("\n");
    sb.append("    pwt: ").append(toIndentedString(pwt)).append("\n");
    sb.append("    swt: ").append(toIndentedString(swt)).append("\n");
    sb.append("    pamp: ").append(toIndentedString(pamp)).append("\n");
    sb.append("    codalenObs: ").append(toIndentedString(codalenObs)).append("\n");
    sb.append("    ccntr: ").append(toIndentedString(ccntr)).append("\n");
    sb.append("    caav: ").append(toIndentedString(caav)).append("\n");
    sb.append("    amplitude: ").append(toIndentedString(amplitude)).append("\n");
    sb.append("    ampUnitsCode: ").append(toIndentedString(ampUnitsCode)).append("\n");
    sb.append("    ampType: ").append(toIndentedString(ampType)).append("\n");
    sb.append("    ampMag: ").append(toIndentedString(ampMag)).append("\n");
    sb.append("    ampMagWeightCode: ").append(toIndentedString(ampMagWeightCode)).append("\n");
    sb.append("    importanceP: ").append(toIndentedString(importanceP)).append("\n");
    sb.append("    importanceS: ").append(toIndentedString(importanceS)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("net");
    openapiFields.add("sta");
    openapiFields.add("comp");
    openapiFields.add("loc");
    openapiFields.add("Plabel");
    openapiFields.add("Slabel");
    openapiFields.add("Ponset");
    openapiFields.add("Sonset");
    openapiFields.add("Pat");
    openapiFields.add("Sat");
    openapiFields.add("Pres");
    openapiFields.add("Sres");
    openapiFields.add("Pqual");
    openapiFields.add("Squal");
    openapiFields.add("codalen");
    openapiFields.add("codawt");
    openapiFields.add("Pfm");
    openapiFields.add("Sfm");
    openapiFields.add("datasrc");
    openapiFields.add("Md");
    openapiFields.add("azm");
    openapiFields.add("takeoff");
    openapiFields.add("dist");
    openapiFields.add("Pwt");
    openapiFields.add("Swt");
    openapiFields.add("pamp");
    openapiFields.add("codalenObs");
    openapiFields.add("ccntr");
    openapiFields.add("caav");
    openapiFields.add("amplitude");
    openapiFields.add("ampUnitsCode");
    openapiFields.add("ampType");
    openapiFields.add("ampMag");
    openapiFields.add("ampMagWeightCode");
    openapiFields.add("importanceP");
    openapiFields.add("importanceS");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("net");
    openapiRequiredFields.add("sta");
    openapiRequiredFields.add("comp");
    openapiRequiredFields.add("loc");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to Hyp2000arcPhasesInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Hyp2000arcPhasesInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Hyp2000arcPhasesInner is not found in the empty JSON string", Hyp2000arcPhasesInner.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Hyp2000arcPhasesInner.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Hyp2000arcPhasesInner` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : Hyp2000arcPhasesInner.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("net").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `net` to be a primitive type in the JSON string but got `%s`", jsonObj.get("net").toString()));
      }
      if (!jsonObj.get("sta").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sta` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sta").toString()));
      }
      if (!jsonObj.get("comp").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `comp` to be a primitive type in the JSON string but got `%s`", jsonObj.get("comp").toString()));
      }
      if (!jsonObj.get("loc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `loc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("loc").toString()));
      }
      if ((jsonObj.get("Plabel") != null && !jsonObj.get("Plabel").isJsonNull()) && !jsonObj.get("Plabel").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Plabel` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Plabel").toString()));
      }
      if ((jsonObj.get("Slabel") != null && !jsonObj.get("Slabel").isJsonNull()) && !jsonObj.get("Slabel").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Slabel` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Slabel").toString()));
      }
      if ((jsonObj.get("Ponset") != null && !jsonObj.get("Ponset").isJsonNull()) && !jsonObj.get("Ponset").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Ponset` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Ponset").toString()));
      }
      if ((jsonObj.get("Sonset") != null && !jsonObj.get("Sonset").isJsonNull()) && !jsonObj.get("Sonset").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Sonset` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Sonset").toString()));
      }
      if ((jsonObj.get("Pfm") != null && !jsonObj.get("Pfm").isJsonNull()) && !jsonObj.get("Pfm").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Pfm` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Pfm").toString()));
      }
      if ((jsonObj.get("Sfm") != null && !jsonObj.get("Sfm").isJsonNull()) && !jsonObj.get("Sfm").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `Sfm` to be a primitive type in the JSON string but got `%s`", jsonObj.get("Sfm").toString()));
      }
      if ((jsonObj.get("datasrc") != null && !jsonObj.get("datasrc").isJsonNull()) && !jsonObj.get("datasrc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `datasrc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("datasrc").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("ccntr") != null && !jsonObj.get("ccntr").isJsonNull() && !jsonObj.get("ccntr").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `ccntr` to be an array in the JSON string but got `%s`", jsonObj.get("ccntr").toString()));
      }
      // ensure the optional json data is an array if present
      if (jsonObj.get("caav") != null && !jsonObj.get("caav").isJsonNull() && !jsonObj.get("caav").isJsonArray()) {
        throw new IllegalArgumentException(String.format("Expected the field `caav` to be an array in the JSON string but got `%s`", jsonObj.get("caav").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Hyp2000arcPhasesInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Hyp2000arcPhasesInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Hyp2000arcPhasesInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Hyp2000arcPhasesInner.class));

       return (TypeAdapter<T>) new TypeAdapter<Hyp2000arcPhasesInner>() {
           @Override
           public void write(JsonWriter out, Hyp2000arcPhasesInner value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Hyp2000arcPhasesInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of Hyp2000arcPhasesInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of Hyp2000arcPhasesInner
  * @throws IOException if the JSON string is invalid with respect to Hyp2000arcPhasesInner
  */
  public static Hyp2000arcPhasesInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Hyp2000arcPhasesInner.class);
  }

 /**
  * Convert an instance of Hyp2000arcPhasesInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

