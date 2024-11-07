/*
 * Dante Web Services
 *       # Introduction   Dante is an API Web Service used for iteract with earthquake data stored in database (**quakedb**); the **quakedb** database schema is used at INGV.   Use other schema would require cration of specific `Model` and `Controller` but this is the potential of web services.      Dante provides a set of routes to store message **event**, **origin**, **magnitude**, **arrival**, **...**.      # Input   As input, Dante acept:   - A `json` message (view '**store**' spec below)    - An Eartworm `json` message (view '**earthworm api**' spec below) produced by **ew2openapi** module      # Output   As output, Dante has a RESTful api foreach database table and implement three specific routes:    - `events-group`: returns the preferred origin and the preferred magnitude from all clusterd events.    - `events`: returns the preferred origin and the preferred magnitude from the same instance.    - `event`: returns the full event (event, origins, magnitudes, arrivals, amplitude, etc...) from an **eventid** or **originid**_/_**originid**.        
 *
 * The version of the OpenAPI document: 3.22.2
 * Contact: valentino.lauciani@ingv.it
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.ingv.dante.model;

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
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectProvenance;
import org.ingv.dante.model.ObjectStrongmotionAlternative;
import org.ingv.dante.model.ObjectStrongmotionRsaInner;
import org.openapitools.jackson.nullable.JsonNullable;

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

import org.ingv.dante.JSON;

/**
 * ObjectStrongmotion
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectStrongmotion {
  public static final String SERIALIZED_NAME_NET = "net";
  @SerializedName(SERIALIZED_NAME_NET)
  private String net;

  public static final String SERIALIZED_NAME_STA = "sta";
  @SerializedName(SERIALIZED_NAME_STA)
  private String sta;

  public static final String SERIALIZED_NAME_CHA = "cha";
  @SerializedName(SERIALIZED_NAME_CHA)
  private String cha;

  public static final String SERIALIZED_NAME_LOC = "loc";
  @SerializedName(SERIALIZED_NAME_LOC)
  private String loc = "--";

  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public static final String SERIALIZED_NAME_ID_LOCALSPACE = "id_localspace";
  @SerializedName(SERIALIZED_NAME_ID_LOCALSPACE)
  private Long idLocalspace;

  public static final String SERIALIZED_NAME_T_DT = "t_dt";
  @SerializedName(SERIALIZED_NAME_T_DT)
  private OffsetDateTime tDt;

  public static final String SERIALIZED_NAME_PGA = "pga";
  @SerializedName(SERIALIZED_NAME_PGA)
  private Float pga;

  public static final String SERIALIZED_NAME_TPGA_DT = "tpga_dt";
  @SerializedName(SERIALIZED_NAME_TPGA_DT)
  private OffsetDateTime tpgaDt;

  public static final String SERIALIZED_NAME_PGV = "pgv";
  @SerializedName(SERIALIZED_NAME_PGV)
  private Float pgv;

  public static final String SERIALIZED_NAME_TPGV_DT = "tpgv_dt";
  @SerializedName(SERIALIZED_NAME_TPGV_DT)
  private OffsetDateTime tpgvDt;

  public static final String SERIALIZED_NAME_PGD = "pgd";
  @SerializedName(SERIALIZED_NAME_PGD)
  private Float pgd;

  public static final String SERIALIZED_NAME_TPGD_DT = "tpgd_dt";
  @SerializedName(SERIALIZED_NAME_TPGD_DT)
  private OffsetDateTime tpgdDt;

  public static final String SERIALIZED_NAME_RSA030 = "rsa_030";
  @SerializedName(SERIALIZED_NAME_RSA030)
  private Float rsa030;

  public static final String SERIALIZED_NAME_RSA100 = "rsa_100";
  @SerializedName(SERIALIZED_NAME_RSA100)
  private Float rsa100;

  public static final String SERIALIZED_NAME_RSA300 = "rsa_300";
  @SerializedName(SERIALIZED_NAME_RSA300)
  private Float rsa300;

  public static final String SERIALIZED_NAME_LOCALSPACE = "localspace";
  @SerializedName(SERIALIZED_NAME_LOCALSPACE)
  private ObjectLocalspace localspace;

  public static final String SERIALIZED_NAME_PROVENANCE = "provenance";
  @SerializedName(SERIALIZED_NAME_PROVENANCE)
  private ObjectProvenance provenance;

  public static final String SERIALIZED_NAME_ALTERNATIVE = "alternative";
  @SerializedName(SERIALIZED_NAME_ALTERNATIVE)
  private ObjectStrongmotionAlternative alternative;

  public static final String SERIALIZED_NAME_RSA = "rsa";
  @SerializedName(SERIALIZED_NAME_RSA)
  private List<ObjectStrongmotionRsaInner> rsa;

  public ObjectStrongmotion() {
  }

  public ObjectStrongmotion(
     Long id, 
     OffsetDateTime modified, 
     OffsetDateTime inserted
  ) {
    this();
    this.id = id;
    this.modified = modified;
    this.inserted = inserted;
  }

  public ObjectStrongmotion net(String net) {
    this.net = net;
    return this;
  }

   /**
   * Channel net code | char(2)
   * @return net
  **/
  @javax.annotation.Nullable
  public String getNet() {
    return net;
  }

  public void setNet(String net) {
    this.net = net;
  }


  public ObjectStrongmotion sta(String sta) {
    this.sta = sta;
    return this;
  }

   /**
   * Channel station code | varchar(5)
   * @return sta
  **/
  @javax.annotation.Nullable
  public String getSta() {
    return sta;
  }

  public void setSta(String sta) {
    this.sta = sta;
  }


  public ObjectStrongmotion cha(String cha) {
    this.cha = cha;
    return this;
  }

   /**
   * Channel code | char(3)
   * @return cha
  **/
  @javax.annotation.Nullable
  public String getCha() {
    return cha;
  }

  public void setCha(String cha) {
    this.cha = cha;
  }


  public ObjectStrongmotion loc(String loc) {
    this.loc = loc;
    return this;
  }

   /**
   * Channel location | char(2)
   * @return loc
  **/
  @javax.annotation.Nullable
  public String getLoc() {
    return loc;
  }

  public void setLoc(String loc) {
    this.loc = loc;
  }


   /**
   * Unique incremental id | bigint(20)
   * @return id
  **/
  @javax.annotation.Nullable
  public Long getId() {
    return id;
  }



   /**
   * Last Review | timestamp
   * @return modified
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getModified() {
    return modified;
  }



   /**
   * Insert time | timestamp
   * @return inserted
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getInserted() {
    return inserted;
  }



  public ObjectStrongmotion idLocalspace(Long idLocalspace) {
    this.idLocalspace = idLocalspace;
    return this;
  }

   /**
   * Localspace Id | bigint(19)
   * @return idLocalspace
  **/
  @javax.annotation.Nullable
  public Long getIdLocalspace() {
    return idLocalspace;
  }

  public void setIdLocalspace(Long idLocalspace) {
    this.idLocalspace = idLocalspace;
  }


  public ObjectStrongmotion tDt(OffsetDateTime tDt) {
    this.tDt = tDt;
    return this;
  }

   /**
   * time: trigger reported by SM box - datetime part | datetime(6)
   * @return tDt
  **/
  @javax.annotation.Nonnull
  public OffsetDateTime gettDt() {
    return tDt;
  }

  public void settDt(OffsetDateTime tDt) {
    this.tDt = tDt;
  }


  public ObjectStrongmotion pga(Float pga) {
    this.pga = pga;
    return this;
  }

   /**
   * REQUIRED: peak ground acceleration (cm/s/s) | double
   * @return pga
  **/
  @javax.annotation.Nullable
  public Float getPga() {
    return pga;
  }

  public void setPga(Float pga) {
    this.pga = pga;
  }


  public ObjectStrongmotion tpgaDt(OffsetDateTime tpgaDt) {
    this.tpgaDt = tpgaDt;
    return this;
  }

   /**
   * OPTIONAL: time of pga - datetime part | datetime(3)
   * @return tpgaDt
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getTpgaDt() {
    return tpgaDt;
  }

  public void setTpgaDt(OffsetDateTime tpgaDt) {
    this.tpgaDt = tpgaDt;
  }


  public ObjectStrongmotion pgv(Float pgv) {
    this.pgv = pgv;
    return this;
  }

   /**
   * REQUIRED: peak ground velocity (cm/s) | double
   * @return pgv
  **/
  @javax.annotation.Nullable
  public Float getPgv() {
    return pgv;
  }

  public void setPgv(Float pgv) {
    this.pgv = pgv;
  }


  public ObjectStrongmotion tpgvDt(OffsetDateTime tpgvDt) {
    this.tpgvDt = tpgvDt;
    return this;
  }

   /**
   * OPTIONAL: time of pgv - datetime part | datetime(3)
   * @return tpgvDt
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getTpgvDt() {
    return tpgvDt;
  }

  public void setTpgvDt(OffsetDateTime tpgvDt) {
    this.tpgvDt = tpgvDt;
  }


  public ObjectStrongmotion pgd(Float pgd) {
    this.pgd = pgd;
    return this;
  }

   /**
   * REQUIRED: peak ground displacement (cm) | double
   * @return pgd
  **/
  @javax.annotation.Nullable
  public Float getPgd() {
    return pgd;
  }

  public void setPgd(Float pgd) {
    this.pgd = pgd;
  }


  public ObjectStrongmotion tpgdDt(OffsetDateTime tpgdDt) {
    this.tpgdDt = tpgdDt;
    return this;
  }

   /**
   * OPTIONAL: time of pgd - datetime part | datetime(3)
   * @return tpgdDt
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getTpgdDt() {
    return tpgdDt;
  }

  public void setTpgdDt(OffsetDateTime tpgdDt) {
    this.tpgdDt = tpgdDt;
  }


  public ObjectStrongmotion rsa030(Float rsa030) {
    this.rsa030 = rsa030;
    return this;
  }

   /**
   * RSA(response spectrum accel) value for period 0.30 | double
   * @return rsa030
  **/
  @javax.annotation.Nullable
  public Float getRsa030() {
    return rsa030;
  }

  public void setRsa030(Float rsa030) {
    this.rsa030 = rsa030;
  }


  public ObjectStrongmotion rsa100(Float rsa100) {
    this.rsa100 = rsa100;
    return this;
  }

   /**
   * RSA(response spectrum accel) value for period 1.00 | double
   * @return rsa100
  **/
  @javax.annotation.Nullable
  public Float getRsa100() {
    return rsa100;
  }

  public void setRsa100(Float rsa100) {
    this.rsa100 = rsa100;
  }


  public ObjectStrongmotion rsa300(Float rsa300) {
    this.rsa300 = rsa300;
    return this;
  }

   /**
   * RSA(response spectrum accel) value for period 3.00 | double
   * @return rsa300
  **/
  @javax.annotation.Nullable
  public Float getRsa300() {
    return rsa300;
  }

  public void setRsa300(Float rsa300) {
    this.rsa300 = rsa300;
  }


  public ObjectStrongmotion localspace(ObjectLocalspace localspace) {
    this.localspace = localspace;
    return this;
  }

   /**
   * Get localspace
   * @return localspace
  **/
  @javax.annotation.Nullable
  public ObjectLocalspace getLocalspace() {
    return localspace;
  }

  public void setLocalspace(ObjectLocalspace localspace) {
    this.localspace = localspace;
  }


  public ObjectStrongmotion provenance(ObjectProvenance provenance) {
    this.provenance = provenance;
    return this;
  }

   /**
   * Get provenance
   * @return provenance
  **/
  @javax.annotation.Nullable
  public ObjectProvenance getProvenance() {
    return provenance;
  }

  public void setProvenance(ObjectProvenance provenance) {
    this.provenance = provenance;
  }


  public ObjectStrongmotion alternative(ObjectStrongmotionAlternative alternative) {
    this.alternative = alternative;
    return this;
  }

   /**
   * Get alternative
   * @return alternative
  **/
  @javax.annotation.Nullable
  public ObjectStrongmotionAlternative getAlternative() {
    return alternative;
  }

  public void setAlternative(ObjectStrongmotionAlternative alternative) {
    this.alternative = alternative;
  }


  public ObjectStrongmotion rsa(List<ObjectStrongmotionRsaInner> rsa) {
    this.rsa = rsa;
    return this;
  }

  public ObjectStrongmotion addRsaItem(ObjectStrongmotionRsaInner rsaItem) {
    if (this.rsa == null) {
      this.rsa = new ArrayList<>();
    }
    this.rsa.add(rsaItem);
    return this;
  }

   /**
   * Get rsa
   * @return rsa
  **/
  @javax.annotation.Nullable
  public List<ObjectStrongmotionRsaInner> getRsa() {
    return rsa;
  }

  public void setRsa(List<ObjectStrongmotionRsaInner> rsa) {
    this.rsa = rsa;
  }

  /**
   * A container for additional, undeclared properties.
   * This is a holder for any undeclared properties as specified with
   * the 'additionalProperties' keyword in the OAS document.
   */
  private Map<String, Object> additionalProperties;

  /**
   * Set the additional (undeclared) property with the specified name and value.
   * If the property does not already exist, create it otherwise replace it.
   *
   * @param key name of the property
   * @param value value of the property
   * @return the ObjectStrongmotion instance itself
   */
  public ObjectStrongmotion putAdditionalProperty(String key, Object value) {
    if (this.additionalProperties == null) {
        this.additionalProperties = new HashMap<String, Object>();
    }
    this.additionalProperties.put(key, value);
    return this;
  }

  /**
   * Return the additional (undeclared) property.
   *
   * @return a map of objects
   */
  public Map<String, Object> getAdditionalProperties() {
    return additionalProperties;
  }

  /**
   * Return the additional (undeclared) property with the specified name.
   *
   * @param key name of the property
   * @return an object
   */
  public Object getAdditionalProperty(String key) {
    if (this.additionalProperties == null) {
        return null;
    }
    return this.additionalProperties.get(key);
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectStrongmotion objectStrongmotion = (ObjectStrongmotion) o;
    return Objects.equals(this.net, objectStrongmotion.net) &&
        Objects.equals(this.sta, objectStrongmotion.sta) &&
        Objects.equals(this.cha, objectStrongmotion.cha) &&
        Objects.equals(this.loc, objectStrongmotion.loc) &&
        Objects.equals(this.id, objectStrongmotion.id) &&
        Objects.equals(this.modified, objectStrongmotion.modified) &&
        Objects.equals(this.inserted, objectStrongmotion.inserted) &&
        Objects.equals(this.idLocalspace, objectStrongmotion.idLocalspace) &&
        Objects.equals(this.tDt, objectStrongmotion.tDt) &&
        Objects.equals(this.pga, objectStrongmotion.pga) &&
        Objects.equals(this.tpgaDt, objectStrongmotion.tpgaDt) &&
        Objects.equals(this.pgv, objectStrongmotion.pgv) &&
        Objects.equals(this.tpgvDt, objectStrongmotion.tpgvDt) &&
        Objects.equals(this.pgd, objectStrongmotion.pgd) &&
        Objects.equals(this.tpgdDt, objectStrongmotion.tpgdDt) &&
        Objects.equals(this.rsa030, objectStrongmotion.rsa030) &&
        Objects.equals(this.rsa100, objectStrongmotion.rsa100) &&
        Objects.equals(this.rsa300, objectStrongmotion.rsa300) &&
        Objects.equals(this.localspace, objectStrongmotion.localspace) &&
        Objects.equals(this.provenance, objectStrongmotion.provenance) &&
        Objects.equals(this.alternative, objectStrongmotion.alternative) &&
        Objects.equals(this.rsa, objectStrongmotion.rsa)&&
        Objects.equals(this.additionalProperties, objectStrongmotion.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(net, sta, cha, loc, id, modified, inserted, idLocalspace, tDt, pga, tpgaDt, pgv, tpgvDt, pgd, tpgdDt, rsa030, rsa100, rsa300, localspace, provenance, alternative, rsa, additionalProperties);
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectStrongmotion {\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    cha: ").append(toIndentedString(cha)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    inserted: ").append(toIndentedString(inserted)).append("\n");
    sb.append("    idLocalspace: ").append(toIndentedString(idLocalspace)).append("\n");
    sb.append("    tDt: ").append(toIndentedString(tDt)).append("\n");
    sb.append("    pga: ").append(toIndentedString(pga)).append("\n");
    sb.append("    tpgaDt: ").append(toIndentedString(tpgaDt)).append("\n");
    sb.append("    pgv: ").append(toIndentedString(pgv)).append("\n");
    sb.append("    tpgvDt: ").append(toIndentedString(tpgvDt)).append("\n");
    sb.append("    pgd: ").append(toIndentedString(pgd)).append("\n");
    sb.append("    tpgdDt: ").append(toIndentedString(tpgdDt)).append("\n");
    sb.append("    rsa030: ").append(toIndentedString(rsa030)).append("\n");
    sb.append("    rsa100: ").append(toIndentedString(rsa100)).append("\n");
    sb.append("    rsa300: ").append(toIndentedString(rsa300)).append("\n");
    sb.append("    localspace: ").append(toIndentedString(localspace)).append("\n");
    sb.append("    provenance: ").append(toIndentedString(provenance)).append("\n");
    sb.append("    alternative: ").append(toIndentedString(alternative)).append("\n");
    sb.append("    rsa: ").append(toIndentedString(rsa)).append("\n");
    sb.append("    additionalProperties: ").append(toIndentedString(additionalProperties)).append("\n");
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
    openapiFields.add("cha");
    openapiFields.add("loc");
    openapiFields.add("id");
    openapiFields.add("modified");
    openapiFields.add("inserted");
    openapiFields.add("id_localspace");
    openapiFields.add("t_dt");
    openapiFields.add("pga");
    openapiFields.add("tpga_dt");
    openapiFields.add("pgv");
    openapiFields.add("tpgv_dt");
    openapiFields.add("pgd");
    openapiFields.add("tpgd_dt");
    openapiFields.add("rsa_030");
    openapiFields.add("rsa_100");
    openapiFields.add("rsa_300");
    openapiFields.add("localspace");
    openapiFields.add("provenance");
    openapiFields.add("alternative");
    openapiFields.add("rsa");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("t_dt");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectStrongmotion
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectStrongmotion.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectStrongmotion is not found in the empty JSON string", ObjectStrongmotion.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ObjectStrongmotion.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("net") != null && !jsonObj.get("net").isJsonNull()) && !jsonObj.get("net").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `net` to be a primitive type in the JSON string but got `%s`", jsonObj.get("net").toString()));
      }
      if ((jsonObj.get("sta") != null && !jsonObj.get("sta").isJsonNull()) && !jsonObj.get("sta").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sta` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sta").toString()));
      }
      if ((jsonObj.get("cha") != null && !jsonObj.get("cha").isJsonNull()) && !jsonObj.get("cha").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `cha` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cha").toString()));
      }
      if ((jsonObj.get("loc") != null && !jsonObj.get("loc").isJsonNull()) && !jsonObj.get("loc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `loc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("loc").toString()));
      }
      // validate the optional field `localspace`
      if (jsonObj.get("localspace") != null && !jsonObj.get("localspace").isJsonNull()) {
        ObjectLocalspace.validateJsonElement(jsonObj.get("localspace"));
      }
      // validate the optional field `provenance`
      if (jsonObj.get("provenance") != null && !jsonObj.get("provenance").isJsonNull()) {
        ObjectProvenance.validateJsonElement(jsonObj.get("provenance"));
      }
      // validate the optional field `alternative`
      if (jsonObj.get("alternative") != null && !jsonObj.get("alternative").isJsonNull()) {
        ObjectStrongmotionAlternative.validateJsonElement(jsonObj.get("alternative"));
      }
      if (jsonObj.get("rsa") != null && !jsonObj.get("rsa").isJsonNull()) {
        JsonArray jsonArrayrsa = jsonObj.getAsJsonArray("rsa");
        if (jsonArrayrsa != null) {
          // ensure the json data is an array
          if (!jsonObj.get("rsa").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `rsa` to be an array in the JSON string but got `%s`", jsonObj.get("rsa").toString()));
          }

          // validate the optional field `rsa` (array)
          for (int i = 0; i < jsonArrayrsa.size(); i++) {
            ObjectStrongmotionRsaInner.validateJsonElement(jsonArrayrsa.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectStrongmotion.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectStrongmotion' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectStrongmotion> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectStrongmotion.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectStrongmotion>() {
           @Override
           public void write(JsonWriter out, ObjectStrongmotion value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             obj.remove("additionalProperties");
             // serialize additional properties
             if (value.getAdditionalProperties() != null) {
               for (Map.Entry<String, Object> entry : value.getAdditionalProperties().entrySet()) {
                 if (entry.getValue() instanceof String)
                   obj.addProperty(entry.getKey(), (String) entry.getValue());
                 else if (entry.getValue() instanceof Number)
                   obj.addProperty(entry.getKey(), (Number) entry.getValue());
                 else if (entry.getValue() instanceof Boolean)
                   obj.addProperty(entry.getKey(), (Boolean) entry.getValue());
                 else if (entry.getValue() instanceof Character)
                   obj.addProperty(entry.getKey(), (Character) entry.getValue());
                 else {
                   JsonElement jsonElement = gson.toJsonTree(entry.getValue());
                   if (jsonElement.isJsonArray()) {
                     obj.add(entry.getKey(), jsonElement.getAsJsonArray());
                   } else {
                     obj.add(entry.getKey(), jsonElement.getAsJsonObject());
                   }
                 }
               }
             }
             elementAdapter.write(out, obj);
           }

           @Override
           public ObjectStrongmotion read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectStrongmotion instance = thisAdapter.fromJsonTree(jsonObj);
             for (Map.Entry<String, JsonElement> entry : jsonObj.entrySet()) {
               if (!openapiFields.contains(entry.getKey())) {
                 if (entry.getValue().isJsonPrimitive()) { // primitive type
                   if (entry.getValue().getAsJsonPrimitive().isString())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsString());
                   else if (entry.getValue().getAsJsonPrimitive().isNumber())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsNumber());
                   else if (entry.getValue().getAsJsonPrimitive().isBoolean())
                     instance.putAdditionalProperty(entry.getKey(), entry.getValue().getAsBoolean());
                   else
                     throw new IllegalArgumentException(String.format("The field `%s` has unknown primitive type. Value: %s", entry.getKey(), entry.getValue().toString()));
                 } else if (entry.getValue().isJsonArray()) {
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), List.class));
                 } else { // JSON object
                     instance.putAdditionalProperty(entry.getKey(), gson.fromJson(entry.getValue(), HashMap.class));
                 }
               }
             }
             return instance;
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ObjectStrongmotion given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectStrongmotion
  * @throws IOException if the JSON string is invalid with respect to ObjectStrongmotion
  */
  public static ObjectStrongmotion fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectStrongmotion.class);
  }

 /**
  * Convert an instance of ObjectStrongmotion to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
