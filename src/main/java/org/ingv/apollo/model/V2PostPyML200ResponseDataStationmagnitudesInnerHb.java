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
 * V2PostPyML200ResponseDataStationmagnitudesInnerHb
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class V2PostPyML200ResponseDataStationmagnitudesInnerHb {
  public static final String SERIALIZED_NAME_ML = "ml";
  @SerializedName(SERIALIZED_NAME_ML)
  private Double ml;

  public static final String SERIALIZED_NAME_W = "w";
  @SerializedName(SERIALIZED_NAME_W)
  private Double w;

  public V2PostPyML200ResponseDataStationmagnitudesInnerHb() {
  }

  public V2PostPyML200ResponseDataStationmagnitudesInnerHb ml(Double ml) {
    this.ml = ml;
    return this;
  }

   /**
   * Magnitude value
   * @return ml
  **/
  @javax.annotation.Nullable
  public Double getMl() {
    return ml;
  }

  public void setMl(Double ml) {
    this.ml = ml;
  }


  public V2PostPyML200ResponseDataStationmagnitudesInnerHb w(Double w) {
    this.w = w;
    return this;
  }

   /**
   * Weight value
   * @return w
  **/
  @javax.annotation.Nullable
  public Double getW() {
    return w;
  }

  public void setW(Double w) {
    this.w = w;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PostPyML200ResponseDataStationmagnitudesInnerHb v2PostPyML200ResponseDataStationmagnitudesInnerHb = (V2PostPyML200ResponseDataStationmagnitudesInnerHb) o;
    return Objects.equals(this.ml, v2PostPyML200ResponseDataStationmagnitudesInnerHb.ml) &&
        Objects.equals(this.w, v2PostPyML200ResponseDataStationmagnitudesInnerHb.w);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ml, w);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PostPyML200ResponseDataStationmagnitudesInnerHb {\n");
    sb.append("    ml: ").append(toIndentedString(ml)).append("\n");
    sb.append("    w: ").append(toIndentedString(w)).append("\n");
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
    openapiFields.add("ml");
    openapiFields.add("w");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to V2PostPyML200ResponseDataStationmagnitudesInnerHb
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!V2PostPyML200ResponseDataStationmagnitudesInnerHb.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in V2PostPyML200ResponseDataStationmagnitudesInnerHb is not found in the empty JSON string", V2PostPyML200ResponseDataStationmagnitudesInnerHb.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!V2PostPyML200ResponseDataStationmagnitudesInnerHb.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `V2PostPyML200ResponseDataStationmagnitudesInnerHb` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!V2PostPyML200ResponseDataStationmagnitudesInnerHb.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'V2PostPyML200ResponseDataStationmagnitudesInnerHb' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<V2PostPyML200ResponseDataStationmagnitudesInnerHb> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(V2PostPyML200ResponseDataStationmagnitudesInnerHb.class));

       return (TypeAdapter<T>) new TypeAdapter<V2PostPyML200ResponseDataStationmagnitudesInnerHb>() {
           @Override
           public void write(JsonWriter out, V2PostPyML200ResponseDataStationmagnitudesInnerHb value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public V2PostPyML200ResponseDataStationmagnitudesInnerHb read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of V2PostPyML200ResponseDataStationmagnitudesInnerHb given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of V2PostPyML200ResponseDataStationmagnitudesInnerHb
  * @throws IOException if the JSON string is invalid with respect to V2PostPyML200ResponseDataStationmagnitudesInnerHb
  */
  public static V2PostPyML200ResponseDataStationmagnitudesInnerHb fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, V2PostPyML200ResponseDataStationmagnitudesInnerHb.class);
  }

 /**
  * Convert an instance of V2PostPyML200ResponseDataStationmagnitudesInnerHb to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
