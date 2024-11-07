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
import java.net.URI;
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
 * This schema describes RFC7807 Problem Details for HTTP APIs. The specification is intentionally flexible, and allows to adapt the schema adding further properties, or not enforcing any of the properties below.  To make this schema safe by default, additionalProperties are forbidden though. In case you need them, you can either re-define Problem or extend it using &#x60;allOf&#x60;.
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class Problem {
  public static final String SERIALIZED_NAME_DETAIL = "detail";
  @SerializedName(SERIALIZED_NAME_DETAIL)
  private String detail;

  public static final String SERIALIZED_NAME_INSTANCE = "instance";
  @SerializedName(SERIALIZED_NAME_INSTANCE)
  private URI instance;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private Integer status;

  public static final String SERIALIZED_NAME_TITLE = "title";
  @SerializedName(SERIALIZED_NAME_TITLE)
  private String title;

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private URI type = URI.create("about:blank");

  public Problem() {
  }

  public Problem detail(String detail) {
    this.detail = detail;
    return this;
  }

   /**
   * A human readable explanation specific to this occurrence of the problem. You MUST NOT expose internal informations, personal data or implementation details through this field.  &#x60;detail&#x60; supports localized patterns whereas &#x60;title&#x60; pattern is only in ascii. 
   * @return detail
  **/
  @javax.annotation.Nullable
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }


  public Problem instance(URI instance) {
    this.instance = instance;
    return this;
  }

   /**
   * An absolute URI that identifies the specific occurrence of the problem. It may or may not yield further information if dereferenced. 
   * @return instance
  **/
  @javax.annotation.Nullable
  public URI getInstance() {
    return instance;
  }

  public void setInstance(URI instance) {
    this.instance = instance;
  }


  public Problem status(Integer status) {
    this.status = status;
    return this;
  }

   /**
   * The HTTP status code generated by the origin server for this occurrence of the problem. 
   * minimum: 100
   * maximum: 600
   * @return status
  **/
  @javax.annotation.Nullable
  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }


  public Problem title(String title) {
    this.title = title;
    return this;
  }

   /**
   * A short, summary of the problem type. Written in english and readable for engineers (usually not suited for non technical stakeholders and not localized): the expressed pattern does not support unicode characters. 
   * @return title
  **/
  @javax.annotation.Nullable
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public Problem type(URI type) {
    this.type = type;
    return this;
  }

   /**
   * An absolute URI that identifies the problem type.  When dereferenced, it SHOULD provide human-readable documentation for the problem type (e.g., using HTML). 
   * @return type
  **/
  @javax.annotation.Nullable
  public URI getType() {
    return type;
  }

  public void setType(URI type) {
    this.type = type;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Problem problem = (Problem) o;
    return Objects.equals(this.detail, problem.detail) &&
        Objects.equals(this.instance, problem.instance) &&
        Objects.equals(this.status, problem.status) &&
        Objects.equals(this.title, problem.title) &&
        Objects.equals(this.type, problem.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(detail, instance, status, title, type);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Problem {\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
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
    openapiFields.add("detail");
    openapiFields.add("instance");
    openapiFields.add("status");
    openapiFields.add("title");
    openapiFields.add("type");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to Problem
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Problem.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Problem is not found in the empty JSON string", Problem.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Problem.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Problem` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("detail") != null && !jsonObj.get("detail").isJsonNull()) && !jsonObj.get("detail").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `detail` to be a primitive type in the JSON string but got `%s`", jsonObj.get("detail").toString()));
      }
      if ((jsonObj.get("instance") != null && !jsonObj.get("instance").isJsonNull()) && !jsonObj.get("instance").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `instance` to be a primitive type in the JSON string but got `%s`", jsonObj.get("instance").toString()));
      }
      if ((jsonObj.get("title") != null && !jsonObj.get("title").isJsonNull()) && !jsonObj.get("title").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `title` to be a primitive type in the JSON string but got `%s`", jsonObj.get("title").toString()));
      }
      if ((jsonObj.get("type") != null && !jsonObj.get("type").isJsonNull()) && !jsonObj.get("type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("type").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Problem.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Problem' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Problem> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Problem.class));

       return (TypeAdapter<T>) new TypeAdapter<Problem>() {
           @Override
           public void write(JsonWriter out, Problem value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Problem read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of Problem given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of Problem
  * @throws IOException if the JSON string is invalid with respect to Problem
  */
  public static Problem fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Problem.class);
  }

 /**
  * Convert an instance of Problem to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

