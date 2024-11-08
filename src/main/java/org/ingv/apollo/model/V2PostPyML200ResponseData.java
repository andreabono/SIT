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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ingv.apollo.model.V2PostPyML200ResponseDataLog;
import org.ingv.apollo.model.V2PostPyML200ResponseDataMagnitudes;
import org.ingv.apollo.model.V2PostPyML200ResponseDataStationmagnitudesInner;

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
 * V2PostPyML200ResponseData
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class V2PostPyML200ResponseData {
  public static final String SERIALIZED_NAME_RANDOM_STRING = "random_string";
  @SerializedName(SERIALIZED_NAME_RANDOM_STRING)
  private String randomString;

  public static final String SERIALIZED_NAME_HOSTNAME = "hostname";
  @SerializedName(SERIALIZED_NAME_HOSTNAME)
  private String hostname;

  public static final String SERIALIZED_NAME_MAGNITUDES = "magnitudes";
  @SerializedName(SERIALIZED_NAME_MAGNITUDES)
  private V2PostPyML200ResponseDataMagnitudes magnitudes;

  public static final String SERIALIZED_NAME_STATIONMAGNITUDES = "stationmagnitudes";
  @SerializedName(SERIALIZED_NAME_STATIONMAGNITUDES)
  private List<V2PostPyML200ResponseDataStationmagnitudesInner> stationmagnitudes;

  public static final String SERIALIZED_NAME_LOG = "log";
  @SerializedName(SERIALIZED_NAME_LOG)
  private V2PostPyML200ResponseDataLog log;

  public V2PostPyML200ResponseData() {
  }

  public V2PostPyML200ResponseData randomString(String randomString) {
    this.randomString = randomString;
    return this;
  }

   /**
   * Processing unique string
   * @return randomString
  **/
  @javax.annotation.Nullable
  public String getRandomString() {
    return randomString;
  }

  public void setRandomString(String randomString) {
    this.randomString = randomString;
  }


  public V2PostPyML200ResponseData hostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

   /**
   * Hostname
   * @return hostname
  **/
  @javax.annotation.Nullable
  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }


  public V2PostPyML200ResponseData magnitudes(V2PostPyML200ResponseDataMagnitudes magnitudes) {
    this.magnitudes = magnitudes;
    return this;
  }

   /**
   * Get magnitudes
   * @return magnitudes
  **/
  @javax.annotation.Nullable
  public V2PostPyML200ResponseDataMagnitudes getMagnitudes() {
    return magnitudes;
  }

  public void setMagnitudes(V2PostPyML200ResponseDataMagnitudes magnitudes) {
    this.magnitudes = magnitudes;
  }


  public V2PostPyML200ResponseData stationmagnitudes(List<V2PostPyML200ResponseDataStationmagnitudesInner> stationmagnitudes) {
    this.stationmagnitudes = stationmagnitudes;
    return this;
  }

  public V2PostPyML200ResponseData addStationmagnitudesItem(V2PostPyML200ResponseDataStationmagnitudesInner stationmagnitudesItem) {
    if (this.stationmagnitudes == null) {
      this.stationmagnitudes = new ArrayList<>();
    }
    this.stationmagnitudes.add(stationmagnitudesItem);
    return this;
  }

   /**
   * Get stationmagnitudes
   * @return stationmagnitudes
  **/
  @javax.annotation.Nullable
  public List<V2PostPyML200ResponseDataStationmagnitudesInner> getStationmagnitudes() {
    return stationmagnitudes;
  }

  public void setStationmagnitudes(List<V2PostPyML200ResponseDataStationmagnitudesInner> stationmagnitudes) {
    this.stationmagnitudes = stationmagnitudes;
  }


  public V2PostPyML200ResponseData log(V2PostPyML200ResponseDataLog log) {
    this.log = log;
    return this;
  }

   /**
   * Get log
   * @return log
  **/
  @javax.annotation.Nullable
  public V2PostPyML200ResponseDataLog getLog() {
    return log;
  }

  public void setLog(V2PostPyML200ResponseDataLog log) {
    this.log = log;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PostPyML200ResponseData v2PostPyML200ResponseData = (V2PostPyML200ResponseData) o;
    return Objects.equals(this.randomString, v2PostPyML200ResponseData.randomString) &&
        Objects.equals(this.hostname, v2PostPyML200ResponseData.hostname) &&
        Objects.equals(this.magnitudes, v2PostPyML200ResponseData.magnitudes) &&
        Objects.equals(this.stationmagnitudes, v2PostPyML200ResponseData.stationmagnitudes) &&
        Objects.equals(this.log, v2PostPyML200ResponseData.log);
  }

  @Override
  public int hashCode() {
    return Objects.hash(randomString, hostname, magnitudes, stationmagnitudes, log);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PostPyML200ResponseData {\n");
    sb.append("    randomString: ").append(toIndentedString(randomString)).append("\n");
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    magnitudes: ").append(toIndentedString(magnitudes)).append("\n");
    sb.append("    stationmagnitudes: ").append(toIndentedString(stationmagnitudes)).append("\n");
    sb.append("    log: ").append(toIndentedString(log)).append("\n");
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
    openapiFields.add("random_string");
    openapiFields.add("hostname");
    openapiFields.add("magnitudes");
    openapiFields.add("stationmagnitudes");
    openapiFields.add("log");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to V2PostPyML200ResponseData
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!V2PostPyML200ResponseData.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in V2PostPyML200ResponseData is not found in the empty JSON string", V2PostPyML200ResponseData.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!V2PostPyML200ResponseData.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `V2PostPyML200ResponseData` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("random_string") != null && !jsonObj.get("random_string").isJsonNull()) && !jsonObj.get("random_string").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `random_string` to be a primitive type in the JSON string but got `%s`", jsonObj.get("random_string").toString()));
      }
      if ((jsonObj.get("hostname") != null && !jsonObj.get("hostname").isJsonNull()) && !jsonObj.get("hostname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `hostname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("hostname").toString()));
      }
      // validate the optional field `magnitudes`
      if (jsonObj.get("magnitudes") != null && !jsonObj.get("magnitudes").isJsonNull()) {
        V2PostPyML200ResponseDataMagnitudes.validateJsonElement(jsonObj.get("magnitudes"));
      }
      if (jsonObj.get("stationmagnitudes") != null && !jsonObj.get("stationmagnitudes").isJsonNull()) {
        JsonArray jsonArraystationmagnitudes = jsonObj.getAsJsonArray("stationmagnitudes");
        if (jsonArraystationmagnitudes != null) {
          // ensure the json data is an array
          if (!jsonObj.get("stationmagnitudes").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `stationmagnitudes` to be an array in the JSON string but got `%s`", jsonObj.get("stationmagnitudes").toString()));
          }

          // validate the optional field `stationmagnitudes` (array)
          for (int i = 0; i < jsonArraystationmagnitudes.size(); i++) {
            V2PostPyML200ResponseDataStationmagnitudesInner.validateJsonElement(jsonArraystationmagnitudes.get(i));
          };
        }
      }
      // validate the optional field `log`
      if (jsonObj.get("log") != null && !jsonObj.get("log").isJsonNull()) {
        V2PostPyML200ResponseDataLog.validateJsonElement(jsonObj.get("log"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!V2PostPyML200ResponseData.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'V2PostPyML200ResponseData' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<V2PostPyML200ResponseData> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(V2PostPyML200ResponseData.class));

       return (TypeAdapter<T>) new TypeAdapter<V2PostPyML200ResponseData>() {
           @Override
           public void write(JsonWriter out, V2PostPyML200ResponseData value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public V2PostPyML200ResponseData read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of V2PostPyML200ResponseData given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of V2PostPyML200ResponseData
  * @throws IOException if the JSON string is invalid with respect to V2PostPyML200ResponseData
  */
  public static V2PostPyML200ResponseData fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, V2PostPyML200ResponseData.class);
  }

 /**
  * Convert an instance of V2PostPyML200ResponseData to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

