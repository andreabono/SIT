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
 * ObjectPyMLConfPreconditions
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class ObjectPyMLConfPreconditions {
  public static final String SERIALIZED_NAME_THEORETICAL_P = "theoretical_p";
  @SerializedName(SERIALIZED_NAME_THEORETICAL_P)
  private Boolean theoreticalP = true;

  public static final String SERIALIZED_NAME_THEORETICAL_S = "theoretical_s";
  @SerializedName(SERIALIZED_NAME_THEORETICAL_S)
  private Boolean theoreticalS = true;

  public static final String SERIALIZED_NAME_DELTA_CORNER = "delta_corner";
  @SerializedName(SERIALIZED_NAME_DELTA_CORNER)
  private Long deltaCorner;

  public static final String SERIALIZED_NAME_MAX_LOWCORNER = "max_lowcorner";
  @SerializedName(SERIALIZED_NAME_MAX_LOWCORNER)
  private Long maxLowcorner;

  public ObjectPyMLConfPreconditions() {
  }

  public ObjectPyMLConfPreconditions theoreticalP(Boolean theoreticalP) {
    this.theoreticalP = theoreticalP;
    return this;
  }

   /**
   * Get theoreticalP
   * @return theoreticalP
  **/
  @javax.annotation.Nullable
  public Boolean getTheoreticalP() {
    return theoreticalP;
  }

  public void setTheoreticalP(Boolean theoreticalP) {
    this.theoreticalP = theoreticalP;
  }


  public ObjectPyMLConfPreconditions theoreticalS(Boolean theoreticalS) {
    this.theoreticalS = theoreticalS;
    return this;
  }

   /**
   * Get theoreticalS
   * @return theoreticalS
  **/
  @javax.annotation.Nullable
  public Boolean getTheoreticalS() {
    return theoreticalS;
  }

  public void setTheoreticalS(Boolean theoreticalS) {
    this.theoreticalS = theoreticalS;
  }


  public ObjectPyMLConfPreconditions deltaCorner(Long deltaCorner) {
    this.deltaCorner = deltaCorner;
    return this;
  }

   /**
   * Get deltaCorner
   * @return deltaCorner
  **/
  @javax.annotation.Nullable
  public Long getDeltaCorner() {
    return deltaCorner;
  }

  public void setDeltaCorner(Long deltaCorner) {
    this.deltaCorner = deltaCorner;
  }


  public ObjectPyMLConfPreconditions maxLowcorner(Long maxLowcorner) {
    this.maxLowcorner = maxLowcorner;
    return this;
  }

   /**
   * Get maxLowcorner
   * @return maxLowcorner
  **/
  @javax.annotation.Nullable
  public Long getMaxLowcorner() {
    return maxLowcorner;
  }

  public void setMaxLowcorner(Long maxLowcorner) {
    this.maxLowcorner = maxLowcorner;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectPyMLConfPreconditions objectPyMLConfPreconditions = (ObjectPyMLConfPreconditions) o;
    return Objects.equals(this.theoreticalP, objectPyMLConfPreconditions.theoreticalP) &&
        Objects.equals(this.theoreticalS, objectPyMLConfPreconditions.theoreticalS) &&
        Objects.equals(this.deltaCorner, objectPyMLConfPreconditions.deltaCorner) &&
        Objects.equals(this.maxLowcorner, objectPyMLConfPreconditions.maxLowcorner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(theoreticalP, theoreticalS, deltaCorner, maxLowcorner);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectPyMLConfPreconditions {\n");
    sb.append("    theoreticalP: ").append(toIndentedString(theoreticalP)).append("\n");
    sb.append("    theoreticalS: ").append(toIndentedString(theoreticalS)).append("\n");
    sb.append("    deltaCorner: ").append(toIndentedString(deltaCorner)).append("\n");
    sb.append("    maxLowcorner: ").append(toIndentedString(maxLowcorner)).append("\n");
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
    openapiFields.add("theoretical_p");
    openapiFields.add("theoretical_s");
    openapiFields.add("delta_corner");
    openapiFields.add("max_lowcorner");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectPyMLConfPreconditions
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectPyMLConfPreconditions.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectPyMLConfPreconditions is not found in the empty JSON string", ObjectPyMLConfPreconditions.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ObjectPyMLConfPreconditions.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ObjectPyMLConfPreconditions` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectPyMLConfPreconditions.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectPyMLConfPreconditions' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectPyMLConfPreconditions> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectPyMLConfPreconditions.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectPyMLConfPreconditions>() {
           @Override
           public void write(JsonWriter out, ObjectPyMLConfPreconditions value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ObjectPyMLConfPreconditions read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ObjectPyMLConfPreconditions given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectPyMLConfPreconditions
  * @throws IOException if the JSON string is invalid with respect to ObjectPyMLConfPreconditions
  */
  public static ObjectPyMLConfPreconditions fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectPyMLConfPreconditions.class);
  }

 /**
  * Convert an instance of ObjectPyMLConfPreconditions to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
