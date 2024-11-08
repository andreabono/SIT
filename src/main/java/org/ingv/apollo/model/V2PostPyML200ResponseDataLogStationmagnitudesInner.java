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
import org.ingv.apollo.model.V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner;

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
 * V2PostPyML200ResponseDataLogStationmagnitudesInner
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class V2PostPyML200ResponseDataLogStationmagnitudesInner {
  public static final String SERIALIZED_NAME_NET = "net";
  @SerializedName(SERIALIZED_NAME_NET)
  private String net;

  public static final String SERIALIZED_NAME_STA = "sta";
  @SerializedName(SERIALIZED_NAME_STA)
  private String sta;

  public static final String SERIALIZED_NAME_LOC = "loc";
  @SerializedName(SERIALIZED_NAME_LOC)
  private String loc = "--";

  public static final String SERIALIZED_NAME_BAND_INST = "band_inst";
  @SerializedName(SERIALIZED_NAME_BAND_INST)
  private String bandInst;

  public static final String SERIALIZED_NAME_STATUS = "status";
  @SerializedName(SERIALIZED_NAME_STATUS)
  private String status;

  public static final String SERIALIZED_NAME_SUMMARY = "summary";
  @SerializedName(SERIALIZED_NAME_SUMMARY)
  private String summary;

  public static final String SERIALIZED_NAME_EXTENDED = "extended";
  @SerializedName(SERIALIZED_NAME_EXTENDED)
  private String extended;

  public static final String SERIALIZED_NAME_CHANNELS = "channels";
  @SerializedName(SERIALIZED_NAME_CHANNELS)
  private List<V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner> channels;

  public V2PostPyML200ResponseDataLogStationmagnitudesInner() {
  }

  public V2PostPyML200ResponseDataLogStationmagnitudesInner net(String net) {
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


  public V2PostPyML200ResponseDataLogStationmagnitudesInner sta(String sta) {
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


  public V2PostPyML200ResponseDataLogStationmagnitudesInner loc(String loc) {
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


  public V2PostPyML200ResponseDataLogStationmagnitudesInner bandInst(String bandInst) {
    this.bandInst = bandInst;
    return this;
  }

   /**
   * band_inst
   * @return bandInst
  **/
  @javax.annotation.Nullable
  public String getBandInst() {
    return bandInst;
  }

  public void setBandInst(String bandInst) {
    this.bandInst = bandInst;
  }


  public V2PostPyML200ResponseDataLogStationmagnitudesInner status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Log status description
   * @return status
  **/
  @javax.annotation.Nullable
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public V2PostPyML200ResponseDataLogStationmagnitudesInner summary(String summary) {
    this.summary = summary;
    return this;
  }

   /**
   * Summary description
   * @return summary
  **/
  @javax.annotation.Nullable
  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }


  public V2PostPyML200ResponseDataLogStationmagnitudesInner extended(String extended) {
    this.extended = extended;
    return this;
  }

   /**
   * Extendend description
   * @return extended
  **/
  @javax.annotation.Nullable
  public String getExtended() {
    return extended;
  }

  public void setExtended(String extended) {
    this.extended = extended;
  }


  public V2PostPyML200ResponseDataLogStationmagnitudesInner channels(List<V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner> channels) {
    this.channels = channels;
    return this;
  }

  public V2PostPyML200ResponseDataLogStationmagnitudesInner addChannelsItem(V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner channelsItem) {
    if (this.channels == null) {
      this.channels = new ArrayList<>();
    }
    this.channels.add(channelsItem);
    return this;
  }

   /**
   * Get channels
   * @return channels
  **/
  @javax.annotation.Nullable
  public List<V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner> getChannels() {
    return channels;
  }

  public void setChannels(List<V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner> channels) {
    this.channels = channels;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    V2PostPyML200ResponseDataLogStationmagnitudesInner v2PostPyML200ResponseDataLogStationmagnitudesInner = (V2PostPyML200ResponseDataLogStationmagnitudesInner) o;
    return Objects.equals(this.net, v2PostPyML200ResponseDataLogStationmagnitudesInner.net) &&
        Objects.equals(this.sta, v2PostPyML200ResponseDataLogStationmagnitudesInner.sta) &&
        Objects.equals(this.loc, v2PostPyML200ResponseDataLogStationmagnitudesInner.loc) &&
        Objects.equals(this.bandInst, v2PostPyML200ResponseDataLogStationmagnitudesInner.bandInst) &&
        Objects.equals(this.status, v2PostPyML200ResponseDataLogStationmagnitudesInner.status) &&
        Objects.equals(this.summary, v2PostPyML200ResponseDataLogStationmagnitudesInner.summary) &&
        Objects.equals(this.extended, v2PostPyML200ResponseDataLogStationmagnitudesInner.extended) &&
        Objects.equals(this.channels, v2PostPyML200ResponseDataLogStationmagnitudesInner.channels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(net, sta, loc, bandInst, status, summary, extended, channels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class V2PostPyML200ResponseDataLogStationmagnitudesInner {\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    bandInst: ").append(toIndentedString(bandInst)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    summary: ").append(toIndentedString(summary)).append("\n");
    sb.append("    extended: ").append(toIndentedString(extended)).append("\n");
    sb.append("    channels: ").append(toIndentedString(channels)).append("\n");
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
    openapiFields.add("loc");
    openapiFields.add("band_inst");
    openapiFields.add("status");
    openapiFields.add("summary");
    openapiFields.add("extended");
    openapiFields.add("channels");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to V2PostPyML200ResponseDataLogStationmagnitudesInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!V2PostPyML200ResponseDataLogStationmagnitudesInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in V2PostPyML200ResponseDataLogStationmagnitudesInner is not found in the empty JSON string", V2PostPyML200ResponseDataLogStationmagnitudesInner.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!V2PostPyML200ResponseDataLogStationmagnitudesInner.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `V2PostPyML200ResponseDataLogStationmagnitudesInner` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("net") != null && !jsonObj.get("net").isJsonNull()) && !jsonObj.get("net").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `net` to be a primitive type in the JSON string but got `%s`", jsonObj.get("net").toString()));
      }
      if ((jsonObj.get("sta") != null && !jsonObj.get("sta").isJsonNull()) && !jsonObj.get("sta").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sta` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sta").toString()));
      }
      if ((jsonObj.get("loc") != null && !jsonObj.get("loc").isJsonNull()) && !jsonObj.get("loc").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `loc` to be a primitive type in the JSON string but got `%s`", jsonObj.get("loc").toString()));
      }
      if ((jsonObj.get("band_inst") != null && !jsonObj.get("band_inst").isJsonNull()) && !jsonObj.get("band_inst").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `band_inst` to be a primitive type in the JSON string but got `%s`", jsonObj.get("band_inst").toString()));
      }
      if ((jsonObj.get("status") != null && !jsonObj.get("status").isJsonNull()) && !jsonObj.get("status").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `status` to be a primitive type in the JSON string but got `%s`", jsonObj.get("status").toString()));
      }
      if ((jsonObj.get("summary") != null && !jsonObj.get("summary").isJsonNull()) && !jsonObj.get("summary").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `summary` to be a primitive type in the JSON string but got `%s`", jsonObj.get("summary").toString()));
      }
      if ((jsonObj.get("extended") != null && !jsonObj.get("extended").isJsonNull()) && !jsonObj.get("extended").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `extended` to be a primitive type in the JSON string but got `%s`", jsonObj.get("extended").toString()));
      }
      if (jsonObj.get("channels") != null && !jsonObj.get("channels").isJsonNull()) {
        JsonArray jsonArraychannels = jsonObj.getAsJsonArray("channels");
        if (jsonArraychannels != null) {
          // ensure the json data is an array
          if (!jsonObj.get("channels").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `channels` to be an array in the JSON string but got `%s`", jsonObj.get("channels").toString()));
          }

          // validate the optional field `channels` (array)
          for (int i = 0; i < jsonArraychannels.size(); i++) {
            V2PostPyML200ResponseDataLogStationmagnitudesInnerChannelsInner.validateJsonElement(jsonArraychannels.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!V2PostPyML200ResponseDataLogStationmagnitudesInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'V2PostPyML200ResponseDataLogStationmagnitudesInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<V2PostPyML200ResponseDataLogStationmagnitudesInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(V2PostPyML200ResponseDataLogStationmagnitudesInner.class));

       return (TypeAdapter<T>) new TypeAdapter<V2PostPyML200ResponseDataLogStationmagnitudesInner>() {
           @Override
           public void write(JsonWriter out, V2PostPyML200ResponseDataLogStationmagnitudesInner value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public V2PostPyML200ResponseDataLogStationmagnitudesInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of V2PostPyML200ResponseDataLogStationmagnitudesInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of V2PostPyML200ResponseDataLogStationmagnitudesInner
  * @throws IOException if the JSON string is invalid with respect to V2PostPyML200ResponseDataLogStationmagnitudesInner
  */
  public static V2PostPyML200ResponseDataLogStationmagnitudesInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, V2PostPyML200ResponseDataLogStationmagnitudesInner.class);
  }

 /**
  * Convert an instance of V2PostPyML200ResponseDataLogStationmagnitudesInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

