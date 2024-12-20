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
 * ObjectPyMLAmplitude
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class ObjectPyMLAmplitude {
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

  public static final String SERIALIZED_NAME_AMP1 = "amp1";
  @SerializedName(SERIALIZED_NAME_AMP1)
  private Double amp1;

  public static final String SERIALIZED_NAME_TIME1 = "time1";
  @SerializedName(SERIALIZED_NAME_TIME1)
  private OffsetDateTime time1;

  public static final String SERIALIZED_NAME_AMP2 = "amp2";
  @SerializedName(SERIALIZED_NAME_AMP2)
  private Double amp2;

  public static final String SERIALIZED_NAME_TIME2 = "time2";
  @SerializedName(SERIALIZED_NAME_TIME2)
  private OffsetDateTime time2;

  public ObjectPyMLAmplitude() {
  }

  public ObjectPyMLAmplitude net(String net) {
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


  public ObjectPyMLAmplitude sta(String sta) {
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


  public ObjectPyMLAmplitude cha(String cha) {
    this.cha = cha;
    return this;
  }

   /**
   * Channel code | char(3)
   * @return cha
  **/
  @javax.annotation.Nonnull
  public String getCha() {
    return cha;
  }

  public void setCha(String cha) {
    this.cha = cha;
  }


  public ObjectPyMLAmplitude loc(String loc) {
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


  public ObjectPyMLAmplitude amp1(Double amp1) {
    this.amp1 = amp1;
    return this;
  }

   /**
   * Amplitude value | double
   * @return amp1
  **/
  @javax.annotation.Nonnull
  public Double getAmp1() {
    return amp1;
  }

  public void setAmp1(Double amp1) {
    this.amp1 = amp1;
  }


  public ObjectPyMLAmplitude time1(OffsetDateTime time1) {
    this.time1 = time1;
    return this;
  }

   /**
   *  | datetime(3)
   * @return time1
  **/
  @javax.annotation.Nonnull
  public OffsetDateTime getTime1() {
    return time1;
  }

  public void setTime1(OffsetDateTime time1) {
    this.time1 = time1;
  }


  public ObjectPyMLAmplitude amp2(Double amp2) {
    this.amp2 = amp2;
    return this;
  }

   /**
   * Amplitude value | double
   * @return amp2
  **/
  @javax.annotation.Nonnull
  public Double getAmp2() {
    return amp2;
  }

  public void setAmp2(Double amp2) {
    this.amp2 = amp2;
  }


  public ObjectPyMLAmplitude time2(OffsetDateTime time2) {
    this.time2 = time2;
    return this;
  }

   /**
   *  | datetime(3)
   * @return time2
  **/
  @javax.annotation.Nonnull
  public OffsetDateTime getTime2() {
    return time2;
  }

  public void setTime2(OffsetDateTime time2) {
    this.time2 = time2;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectPyMLAmplitude objectPyMLAmplitude = (ObjectPyMLAmplitude) o;
    return Objects.equals(this.net, objectPyMLAmplitude.net) &&
        Objects.equals(this.sta, objectPyMLAmplitude.sta) &&
        Objects.equals(this.cha, objectPyMLAmplitude.cha) &&
        Objects.equals(this.loc, objectPyMLAmplitude.loc) &&
        Objects.equals(this.amp1, objectPyMLAmplitude.amp1) &&
        Objects.equals(this.time1, objectPyMLAmplitude.time1) &&
        Objects.equals(this.amp2, objectPyMLAmplitude.amp2) &&
        Objects.equals(this.time2, objectPyMLAmplitude.time2);
  }

  @Override
  public int hashCode() {
    return Objects.hash(net, sta, cha, loc, amp1, time1, amp2, time2);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectPyMLAmplitude {\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    cha: ").append(toIndentedString(cha)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    amp1: ").append(toIndentedString(amp1)).append("\n");
    sb.append("    time1: ").append(toIndentedString(time1)).append("\n");
    sb.append("    amp2: ").append(toIndentedString(amp2)).append("\n");
    sb.append("    time2: ").append(toIndentedString(time2)).append("\n");
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
    openapiFields.add("amp1");
    openapiFields.add("time1");
    openapiFields.add("amp2");
    openapiFields.add("time2");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("net");
    openapiRequiredFields.add("sta");
    openapiRequiredFields.add("cha");
    openapiRequiredFields.add("loc");
    openapiRequiredFields.add("amp1");
    openapiRequiredFields.add("time1");
    openapiRequiredFields.add("amp2");
    openapiRequiredFields.add("time2");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectPyMLAmplitude
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectPyMLAmplitude.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectPyMLAmplitude is not found in the empty JSON string", ObjectPyMLAmplitude.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ObjectPyMLAmplitude.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ObjectPyMLAmplitude` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ObjectPyMLAmplitude.openapiRequiredFields) {
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
      if (!jsonObj.get("cha").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `cha` to be a primitive type in the JSON string but got `%s`", jsonObj.get("cha").toString()));
      }
      if (!jsonObj.get("loc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `loc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("loc").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectPyMLAmplitude.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectPyMLAmplitude' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectPyMLAmplitude> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectPyMLAmplitude.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectPyMLAmplitude>() {
           @Override
           public void write(JsonWriter out, ObjectPyMLAmplitude value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ObjectPyMLAmplitude read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ObjectPyMLAmplitude given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectPyMLAmplitude
  * @throws IOException if the JSON string is invalid with respect to ObjectPyMLAmplitude
  */
  public static ObjectPyMLAmplitude fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectPyMLAmplitude.class);
  }

 /**
  * Convert an instance of ObjectPyMLAmplitude to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

