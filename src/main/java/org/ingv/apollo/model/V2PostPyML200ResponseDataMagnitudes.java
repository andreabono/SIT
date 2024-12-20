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
import java.util.Arrays;
import org.ingv.apollo.model.V2PostPyML200ResponseDataMagnitudesHb;

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
 * V2PostPyML200ResponseDataMagnitudes
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class V2PostPyML200ResponseDataMagnitudes {
  public static final String SERIALIZED_NAME_HB = "hb";
  @SerializedName(SERIALIZED_NAME_HB)
  private V2PostPyML200ResponseDataMagnitudesHb hb;

  public static final String SERIALIZED_NAME_DB = "db";
  @SerializedName(SERIALIZED_NAME_DB)
  private V2PostPyML200ResponseDataMagnitudesHb db;

  public static final String SERIALIZED_NAME_AMPMETHOD = "ampmethod";
  @SerializedName(SERIALIZED_NAME_AMPMETHOD)
  private String ampmethod;

  public static final String SERIALIZED_NAME_MAGMETHOD = "magmethod";
  @SerializedName(SERIALIZED_NAME_MAGMETHOD)
  private String magmethod;

  public static final String SERIALIZED_NAME_LOOPEXITCONDITION = "loopexitcondition";
  @SerializedName(SERIALIZED_NAME_LOOPEXITCONDITION)
  private String loopexitcondition;

  public V2PostPyML200ResponseDataMagnitudes() {
  }

  public V2PostPyML200ResponseDataMagnitudes hb(V2PostPyML200ResponseDataMagnitudesHb hb) {
    this.hb = hb;
    return this;
  }

   /**
   * Get hb
   * @return hb
  **/
  @javax.annotation.Nullable
  public V2PostPyML200ResponseDataMagnitudesHb getHb() {
    return hb;
  }

  public void setHb(V2PostPyML200ResponseDataMagnitudesHb hb) {
    this.hb = hb;
  }


  public V2PostPyML200ResponseDataMagnitudes db(V2PostPyML200ResponseDataMagnitudesHb db) {
    this.db = db;
    return this;
  }

   /**
   * Get db
   * @return db
  **/
  @javax.annotation.Nullable
  public V2PostPyML200ResponseDataMagnitudesHb getDb() {
    return db;
  }

  public void setDb(V2PostPyML200ResponseDataMagnitudesHb db) {
    this.db = db;
  }


  public V2PostPyML200ResponseDataMagnitudes ampmethod(String ampmethod) {
    this.ampmethod = ampmethod;
    return this;
  }

   /**
   * Amp method
   * @return ampmethod
  **/
  @javax.annotation.Nullable
  public String getAmpmethod() {
    return ampmethod;
  }

  public void setAmpmethod(String ampmethod) {
    this.ampmethod = ampmethod;
  }


  public V2PostPyML200ResponseDataMagnitudes magmethod(String magmethod) {
    this.magmethod = magmethod;
    return this;
  }

   /**
   * To Do
   * @return magmethod
  **/
  @javax.annotation.Nullable
  public String getMagmethod() {
    return magmethod;
  }

  public void setMagmethod(String magmethod) {
    this.magmethod = magmethod;
  }


  public V2PostPyML200ResponseDataMagnitudes loopexitcondition(String loopexitcondition) {
    this.loopexitcondition = loopexitcondition;
    return this;
  }

   /**
   * To Do
   * @return loopexitcondition
  **/
  @javax.annotation.Nullable
  public String getLoopexitcondition() {
    return loopexitcondition;
  }

  public void setLoopexitcondition(String loopexitcondition) {
    this.loopexitcondition = loopexitcondition;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PostPyML200ResponseDataMagnitudes v2PostPyML200ResponseDataMagnitudes = (V2PostPyML200ResponseDataMagnitudes) o;
    return Objects.equals(this.hb, v2PostPyML200ResponseDataMagnitudes.hb) &&
        Objects.equals(this.db, v2PostPyML200ResponseDataMagnitudes.db) &&
        Objects.equals(this.ampmethod, v2PostPyML200ResponseDataMagnitudes.ampmethod) &&
        Objects.equals(this.magmethod, v2PostPyML200ResponseDataMagnitudes.magmethod) &&
        Objects.equals(this.loopexitcondition, v2PostPyML200ResponseDataMagnitudes.loopexitcondition);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hb, db, ampmethod, magmethod, loopexitcondition);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PostPyML200ResponseDataMagnitudes {\n");
    sb.append("    hb: ").append(toIndentedString(hb)).append("\n");
    sb.append("    db: ").append(toIndentedString(db)).append("\n");
    sb.append("    ampmethod: ").append(toIndentedString(ampmethod)).append("\n");
    sb.append("    magmethod: ").append(toIndentedString(magmethod)).append("\n");
    sb.append("    loopexitcondition: ").append(toIndentedString(loopexitcondition)).append("\n");
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
    openapiFields.add("hb");
    openapiFields.add("db");
    openapiFields.add("ampmethod");
    openapiFields.add("magmethod");
    openapiFields.add("loopexitcondition");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to V2PostPyML200ResponseDataMagnitudes
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!V2PostPyML200ResponseDataMagnitudes.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in V2PostPyML200ResponseDataMagnitudes is not found in the empty JSON string", V2PostPyML200ResponseDataMagnitudes.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!V2PostPyML200ResponseDataMagnitudes.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `V2PostPyML200ResponseDataMagnitudes` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      // validate the optional field `hb`
      if (jsonObj.get("hb") != null && !jsonObj.get("hb").isJsonNull()) {
        V2PostPyML200ResponseDataMagnitudesHb.validateJsonElement(jsonObj.get("hb"));
      }
      // validate the optional field `db`
      if (jsonObj.get("db") != null && !jsonObj.get("db").isJsonNull()) {
        V2PostPyML200ResponseDataMagnitudesHb.validateJsonElement(jsonObj.get("db"));
      }
      if ((jsonObj.get("ampmethod") != null && !jsonObj.get("ampmethod").isJsonNull()) && !jsonObj.get("ampmethod").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `ampmethod` to be a primitive type in the JSON string but got `%s`", jsonObj.get("ampmethod").toString()));
      }
      if ((jsonObj.get("magmethod") != null && !jsonObj.get("magmethod").isJsonNull()) && !jsonObj.get("magmethod").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `magmethod` to be a primitive type in the JSON string but got `%s`", jsonObj.get("magmethod").toString()));
      }
      if ((jsonObj.get("loopexitcondition") != null && !jsonObj.get("loopexitcondition").isJsonNull()) && !jsonObj.get("loopexitcondition").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `loopexitcondition` to be a primitive type in the JSON string but got `%s`", jsonObj.get("loopexitcondition").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!V2PostPyML200ResponseDataMagnitudes.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'V2PostPyML200ResponseDataMagnitudes' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<V2PostPyML200ResponseDataMagnitudes> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(V2PostPyML200ResponseDataMagnitudes.class));

       return (TypeAdapter<T>) new TypeAdapter<V2PostPyML200ResponseDataMagnitudes>() {
           @Override
           public void write(JsonWriter out, V2PostPyML200ResponseDataMagnitudes value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public V2PostPyML200ResponseDataMagnitudes read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of V2PostPyML200ResponseDataMagnitudes given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of V2PostPyML200ResponseDataMagnitudes
  * @throws IOException if the JSON string is invalid with respect to V2PostPyML200ResponseDataMagnitudes
  */
  public static V2PostPyML200ResponseDataMagnitudes fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, V2PostPyML200ResponseDataMagnitudes.class);
  }

 /**
  * Convert an instance of V2PostPyML200ResponseDataMagnitudes to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

