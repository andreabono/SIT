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
import java.util.Arrays;

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
 * GetMunicipiDistanceKmPopolazione200ResponseDataInner
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class GetMunicipiDistanceKmPopolazione200ResponseDataInner {
  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name;

  public static final String SERIALIZED_NAME_SIGLA_PRO = "sigla_pro";
  @SerializedName(SERIALIZED_NAME_SIGLA_PRO)
  private String siglaPro;

  public static final String SERIALIZED_NAME_NOME_PRO = "nome_pro";
  @SerializedName(SERIALIZED_NAME_NOME_PRO)
  private String nomePro;

  public static final String SERIALIZED_NAME_POPOLAZIONE = "popolazione";
  @SerializedName(SERIALIZED_NAME_POPOLAZIONE)
  private Long popolazione;

  public static final String SERIALIZED_NAME_LAT = "lat";
  @SerializedName(SERIALIZED_NAME_LAT)
  private Double lat;

  public static final String SERIALIZED_NAME_LON = "lon";
  @SerializedName(SERIALIZED_NAME_LON)
  private Double lon;

  public static final String SERIALIZED_NAME_DIST_KM = "dist_km";
  @SerializedName(SERIALIZED_NAME_DIST_KM)
  private Float distKm;

  public static final String SERIALIZED_NAME_AZM = "azm";
  @SerializedName(SERIALIZED_NAME_AZM)
  private Float azm;

  public static final String SERIALIZED_NAME_DIR = "dir";
  @SerializedName(SERIALIZED_NAME_DIR)
  private String dir;

  public GetMunicipiDistanceKmPopolazione200ResponseDataInner() {
  }

  public GetMunicipiDistanceKmPopolazione200ResponseDataInner name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Italian \&quot;municipio\&quot; name.
   * @return name
  **/
  @javax.annotation.Nullable
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner siglaPro(String siglaPro) {
    this.siglaPro = siglaPro;
    return this;
  }

   /**
   * Italian province abbreviation.
   * @return siglaPro
  **/
  @javax.annotation.Nullable
  public String getSiglaPro() {
    return siglaPro;
  }

  public void setSiglaPro(String siglaPro) {
    this.siglaPro = siglaPro;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner nomePro(String nomePro) {
    this.nomePro = nomePro;
    return this;
  }

   /**
   * Italian province name.
   * @return nomePro
  **/
  @javax.annotation.Nullable
  public String getNomePro() {
    return nomePro;
  }

  public void setNomePro(String nomePro) {
    this.nomePro = nomePro;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner popolazione(Long popolazione) {
    this.popolazione = popolazione;
    return this;
  }

   /**
   * Popolazione.
   * @return popolazione
  **/
  @javax.annotation.Nullable
  public Long getPopolazione() {
    return popolazione;
  }

  public void setPopolazione(Long popolazione) {
    this.popolazione = popolazione;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner lat(Double lat) {
    this.lat = lat;
    return this;
  }

   /**
   * Latitude of a point expressed in:  * the ETRS89 system for Italian and European territories * and in WGS84 for the others.
   * minimum: -90
   * maximum: 90
   * @return lat
  **/
  @javax.annotation.Nullable
  public Double getLat() {
    return lat;
  }

  public void setLat(Double lat) {
    this.lat = lat;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner lon(Double lon) {
    this.lon = lon;
    return this;
  }

   /**
   * Longitude of a point expressed in:  * the ETRS89 system for Italian and European territories * and in WGS84 for the others.
   * minimum: -180
   * maximum: 180
   * @return lon
  **/
  @javax.annotation.Nullable
  public Double getLon() {
    return lon;
  }

  public void setLon(Double lon) {
    this.lon = lon;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner distKm(Float distKm) {
    this.distKm = distKm;
    return this;
  }

   /**
   * Distance in km.
   * @return distKm
  **/
  @javax.annotation.Nullable
  public Float getDistKm() {
    return distKm;
  }

  public void setDistKm(Float distKm) {
    this.distKm = distKm;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner azm(Float azm) {
    this.azm = azm;
    return this;
  }

   /**
   * Azimutal gap | float4
   * @return azm
  **/
  @javax.annotation.Nullable
  public Float getAzm() {
    return azm;
  }

  public void setAzm(Float azm) {
    this.azm = azm;
  }


  public GetMunicipiDistanceKmPopolazione200ResponseDataInner dir(String dir) {
    this.dir = dir;
    return this;
  }

   /**
   * Direction.
   * @return dir
  **/
  @javax.annotation.Nullable
  public String getDir() {
    return dir;
  }

  public void setDir(String dir) {
    this.dir = dir;
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
   * @return the GetMunicipiDistanceKmPopolazione200ResponseDataInner instance itself
   */
  public GetMunicipiDistanceKmPopolazione200ResponseDataInner putAdditionalProperty(String key, Object value) {
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
    GetMunicipiDistanceKmPopolazione200ResponseDataInner getMunicipiDistanceKmPopolazione200ResponseDataInner = (GetMunicipiDistanceKmPopolazione200ResponseDataInner) o;
    return Objects.equals(this.name, getMunicipiDistanceKmPopolazione200ResponseDataInner.name) &&
        Objects.equals(this.siglaPro, getMunicipiDistanceKmPopolazione200ResponseDataInner.siglaPro) &&
        Objects.equals(this.nomePro, getMunicipiDistanceKmPopolazione200ResponseDataInner.nomePro) &&
        Objects.equals(this.popolazione, getMunicipiDistanceKmPopolazione200ResponseDataInner.popolazione) &&
        Objects.equals(this.lat, getMunicipiDistanceKmPopolazione200ResponseDataInner.lat) &&
        Objects.equals(this.lon, getMunicipiDistanceKmPopolazione200ResponseDataInner.lon) &&
        Objects.equals(this.distKm, getMunicipiDistanceKmPopolazione200ResponseDataInner.distKm) &&
        Objects.equals(this.azm, getMunicipiDistanceKmPopolazione200ResponseDataInner.azm) &&
        Objects.equals(this.dir, getMunicipiDistanceKmPopolazione200ResponseDataInner.dir)&&
        Objects.equals(this.additionalProperties, getMunicipiDistanceKmPopolazione200ResponseDataInner.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, siglaPro, nomePro, popolazione, lat, lon, distKm, azm, dir, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetMunicipiDistanceKmPopolazione200ResponseDataInner {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    siglaPro: ").append(toIndentedString(siglaPro)).append("\n");
    sb.append("    nomePro: ").append(toIndentedString(nomePro)).append("\n");
    sb.append("    popolazione: ").append(toIndentedString(popolazione)).append("\n");
    sb.append("    lat: ").append(toIndentedString(lat)).append("\n");
    sb.append("    lon: ").append(toIndentedString(lon)).append("\n");
    sb.append("    distKm: ").append(toIndentedString(distKm)).append("\n");
    sb.append("    azm: ").append(toIndentedString(azm)).append("\n");
    sb.append("    dir: ").append(toIndentedString(dir)).append("\n");
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
    openapiFields.add("name");
    openapiFields.add("sigla_pro");
    openapiFields.add("nome_pro");
    openapiFields.add("popolazione");
    openapiFields.add("lat");
    openapiFields.add("lon");
    openapiFields.add("dist_km");
    openapiFields.add("azm");
    openapiFields.add("dir");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to GetMunicipiDistanceKmPopolazione200ResponseDataInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!GetMunicipiDistanceKmPopolazione200ResponseDataInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in GetMunicipiDistanceKmPopolazione200ResponseDataInner is not found in the empty JSON string", GetMunicipiDistanceKmPopolazione200ResponseDataInner.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("name") != null && !jsonObj.get("name").isJsonNull()) && !jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if ((jsonObj.get("sigla_pro") != null && !jsonObj.get("sigla_pro").isJsonNull()) && !jsonObj.get("sigla_pro").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sigla_pro` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sigla_pro").toString()));
      }
      if ((jsonObj.get("nome_pro") != null && !jsonObj.get("nome_pro").isJsonNull()) && !jsonObj.get("nome_pro").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `nome_pro` to be a primitive type in the JSON string but got `%s`", jsonObj.get("nome_pro").toString()));
      }
      if ((jsonObj.get("dir") != null && !jsonObj.get("dir").isJsonNull()) && !jsonObj.get("dir").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `dir` to be a primitive type in the JSON string but got `%s`", jsonObj.get("dir").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!GetMunicipiDistanceKmPopolazione200ResponseDataInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'GetMunicipiDistanceKmPopolazione200ResponseDataInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<GetMunicipiDistanceKmPopolazione200ResponseDataInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(GetMunicipiDistanceKmPopolazione200ResponseDataInner.class));

       return (TypeAdapter<T>) new TypeAdapter<GetMunicipiDistanceKmPopolazione200ResponseDataInner>() {
           @Override
           public void write(JsonWriter out, GetMunicipiDistanceKmPopolazione200ResponseDataInner value) throws IOException {
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
           public GetMunicipiDistanceKmPopolazione200ResponseDataInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             GetMunicipiDistanceKmPopolazione200ResponseDataInner instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of GetMunicipiDistanceKmPopolazione200ResponseDataInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of GetMunicipiDistanceKmPopolazione200ResponseDataInner
  * @throws IOException if the JSON string is invalid with respect to GetMunicipiDistanceKmPopolazione200ResponseDataInner
  */
  public static GetMunicipiDistanceKmPopolazione200ResponseDataInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, GetMunicipiDistanceKmPopolazione200ResponseDataInner.class);
  }

 /**
  * Convert an instance of GetMunicipiDistanceKmPopolazione200ResponseDataInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

