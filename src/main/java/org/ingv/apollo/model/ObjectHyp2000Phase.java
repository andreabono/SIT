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
import org.ingv.apollo.model.PickEmersio;
import org.ingv.apollo.model.PickFirstmotion;
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

import org.ingv.apollo.JSON;

/**
 * ObjectHyp2000Phase
 */
////////////@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-01-03T13:14:51.077355279Z[Etc/UTC]")
public class ObjectHyp2000Phase {
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

  public static final String SERIALIZED_NAME_ARRIVAL_TIME = "arrival_time";
  @SerializedName(SERIALIZED_NAME_ARRIVAL_TIME)
  private OffsetDateTime arrivalTime;

  public static final String SERIALIZED_NAME_ISC_CODE = "isc_code";
  @SerializedName(SERIALIZED_NAME_ISC_CODE)
  private String iscCode;

  public static final String SERIALIZED_NAME_FIRSTMOTION = "firstmotion";
  @SerializedName(SERIALIZED_NAME_FIRSTMOTION)
  private PickFirstmotion firstmotion;

  public static final String SERIALIZED_NAME_EMERSIO = "emersio";
  @SerializedName(SERIALIZED_NAME_EMERSIO)
  private PickEmersio emersio;

  /**
   * Assigned weight code | 0&#x3D;full weight, 1&#x3D;3/4 weight, 2&#x3D;half weight, 3&#x3D;1/4 weight, 4-9&#x3D;no weight
   */
  @JsonAdapter(WeightEnum.Adapter.class)
  public enum WeightEnum {
    NUMBER_0(0),
    
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3),
    
    NUMBER_4(4),
    
    NUMBER_5(5),
    
    NUMBER_6(6),
    
    NUMBER_7(7),
    
    NUMBER_8(8),
    
    NUMBER_9(9);

    private Integer value;

    WeightEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static WeightEnum fromValue(Integer value) {
      for (WeightEnum b : WeightEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<WeightEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final WeightEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public WeightEnum read(final JsonReader jsonReader) throws IOException {
        Integer value =  jsonReader.nextInt();
        return WeightEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      Integer value = jsonElement.getAsInt();
      WeightEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_WEIGHT = "weight";
  @SerializedName(SERIALIZED_NAME_WEIGHT)
  private WeightEnum weight;

  public static final String SERIALIZED_NAME_AMPLITUDE = "amplitude";
  @SerializedName(SERIALIZED_NAME_AMPLITUDE)
  private Double amplitude;

  /**
   * Type of the amplitude | 0&#x3D;unspecified 1&#x3D;Wood-Anderson 2&#x3D;velocity 3&#x3D;acceleration 4&#x3D;no magnitude
   */
  @JsonAdapter(AmpTypeEnum.Adapter.class)
  public enum AmpTypeEnum {
    NUMBER_0(0),
    
    NUMBER_1(1),
    
    NUMBER_2(2),
    
    NUMBER_3(3),
    
    NUMBER_4(4);

    private Integer value;

    AmpTypeEnum(Integer value) {
      this.value = value;
    }

    public Integer getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static AmpTypeEnum fromValue(Integer value) {
      for (AmpTypeEnum b : AmpTypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<AmpTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final AmpTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public AmpTypeEnum read(final JsonReader jsonReader) throws IOException {
        Integer value =  jsonReader.nextInt();
        return AmpTypeEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      Integer value = jsonElement.getAsInt();
      AmpTypeEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_AMP_TYPE = "ampType";
  @SerializedName(SERIALIZED_NAME_AMP_TYPE)
  private AmpTypeEnum ampType = AmpTypeEnum.NUMBER_4;

  public ObjectHyp2000Phase() {
  }

  public ObjectHyp2000Phase net(String net) {
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


  public ObjectHyp2000Phase sta(String sta) {
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


  public ObjectHyp2000Phase cha(String cha) {
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


  public ObjectHyp2000Phase loc(String loc) {
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


  public ObjectHyp2000Phase arrivalTime(OffsetDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
    return this;
  }

   /**
   * Arrival time with microseconds | datetime(3)
   * @return arrivalTime
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(OffsetDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  public ObjectHyp2000Phase iscCode(String iscCode) {
    this.iscCode = iscCode;
    return this;
  }

   /**
   * ISC arrival code | varchar(8)
   * @return iscCode
  **/
  @javax.annotation.Nullable
  public String getIscCode() {
    return iscCode;
  }

  public void setIscCode(String iscCode) {
    this.iscCode = iscCode;
  }


  public ObjectHyp2000Phase firstmotion(PickFirstmotion firstmotion) {
    this.firstmotion = firstmotion;
    return this;
  }

   /**
   * Get firstmotion
   * @return firstmotion
  **/
  @javax.annotation.Nullable
  public PickFirstmotion getFirstmotion() {
    return firstmotion;
  }

  public void setFirstmotion(PickFirstmotion firstmotion) {
    this.firstmotion = firstmotion;
  }


  public ObjectHyp2000Phase emersio(PickEmersio emersio) {
    this.emersio = emersio;
    return this;
  }

   /**
   * Get emersio
   * @return emersio
  **/
  @javax.annotation.Nullable
  public PickEmersio getEmersio() {
    return emersio;
  }

  public void setEmersio(PickEmersio emersio) {
    this.emersio = emersio;
  }


  public ObjectHyp2000Phase weight(WeightEnum weight) {
    this.weight = weight;
    return this;
  }

   /**
   * Assigned weight code | 0&#x3D;full weight, 1&#x3D;3/4 weight, 2&#x3D;half weight, 3&#x3D;1/4 weight, 4-9&#x3D;no weight
   * @return weight
  **/
  @javax.annotation.Nullable
  public WeightEnum getWeight() {
    return weight;
  }

  public void setWeight(WeightEnum weight) {
    this.weight = weight;
  }


  public ObjectHyp2000Phase amplitude(Double amplitude) {
    this.amplitude = amplitude;
    return this;
  }

   /**
   * Amplitude value | double
   * @return amplitude
  **/
  @javax.annotation.Nullable
  public Double getAmplitude() {
    return amplitude;
  }

  public void setAmplitude(Double amplitude) {
    this.amplitude = amplitude;
  }


  public ObjectHyp2000Phase ampType(AmpTypeEnum ampType) {
    this.ampType = ampType;
    return this;
  }

   /**
   * Type of the amplitude | 0&#x3D;unspecified 1&#x3D;Wood-Anderson 2&#x3D;velocity 3&#x3D;acceleration 4&#x3D;no magnitude
   * @return ampType
  **/
  @javax.annotation.Nullable
  public AmpTypeEnum getAmpType() {
    return ampType;
  }

  public void setAmpType(AmpTypeEnum ampType) {
    this.ampType = ampType;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ObjectHyp2000Phase objectHyp2000Phase = (ObjectHyp2000Phase) o;
    return Objects.equals(this.net, objectHyp2000Phase.net) &&
        Objects.equals(this.sta, objectHyp2000Phase.sta) &&
        Objects.equals(this.cha, objectHyp2000Phase.cha) &&
        Objects.equals(this.loc, objectHyp2000Phase.loc) &&
        Objects.equals(this.arrivalTime, objectHyp2000Phase.arrivalTime) &&
        Objects.equals(this.iscCode, objectHyp2000Phase.iscCode) &&
        Objects.equals(this.firstmotion, objectHyp2000Phase.firstmotion) &&
        Objects.equals(this.emersio, objectHyp2000Phase.emersio) &&
        Objects.equals(this.weight, objectHyp2000Phase.weight) &&
        Objects.equals(this.amplitude, objectHyp2000Phase.amplitude) &&
        Objects.equals(this.ampType, objectHyp2000Phase.ampType);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(net, sta, cha, loc, arrivalTime, iscCode, firstmotion, emersio, weight, amplitude, ampType);
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
    sb.append("class ObjectHyp2000Phase {\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    cha: ").append(toIndentedString(cha)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
    sb.append("    iscCode: ").append(toIndentedString(iscCode)).append("\n");
    sb.append("    firstmotion: ").append(toIndentedString(firstmotion)).append("\n");
    sb.append("    emersio: ").append(toIndentedString(emersio)).append("\n");
    sb.append("    weight: ").append(toIndentedString(weight)).append("\n");
    sb.append("    amplitude: ").append(toIndentedString(amplitude)).append("\n");
    sb.append("    ampType: ").append(toIndentedString(ampType)).append("\n");
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
    openapiFields.add("arrival_time");
    openapiFields.add("isc_code");
    openapiFields.add("firstmotion");
    openapiFields.add("emersio");
    openapiFields.add("weight");
    openapiFields.add("amplitude");
    openapiFields.add("ampType");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectHyp2000Phase
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectHyp2000Phase.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectHyp2000Phase is not found in the empty JSON string", ObjectHyp2000Phase.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!ObjectHyp2000Phase.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `ObjectHyp2000Phase` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
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
      if ((jsonObj.get("isc_code") != null && !jsonObj.get("isc_code").isJsonNull()) && !jsonObj.get("isc_code").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `isc_code` to be a primitive type in the JSON string but got `%s`", jsonObj.get("isc_code").toString()));
      }
      // validate the optional field `firstmotion`
      if (jsonObj.get("firstmotion") != null && !jsonObj.get("firstmotion").isJsonNull()) {
        PickFirstmotion.validateJsonElement(jsonObj.get("firstmotion"));
      }
      // validate the optional field `emersio`
      if (jsonObj.get("emersio") != null && !jsonObj.get("emersio").isJsonNull()) {
        PickEmersio.validateJsonElement(jsonObj.get("emersio"));
      }
      // validate the optional field `weight`
      if (jsonObj.get("weight") != null && !jsonObj.get("weight").isJsonNull()) {
        WeightEnum.validateJsonElement(jsonObj.get("weight"));
      }
      // validate the optional field `ampType`
      if (jsonObj.get("ampType") != null && !jsonObj.get("ampType").isJsonNull()) {
        AmpTypeEnum.validateJsonElement(jsonObj.get("ampType"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectHyp2000Phase.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectHyp2000Phase' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectHyp2000Phase> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectHyp2000Phase.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectHyp2000Phase>() {
           @Override
           public void write(JsonWriter out, ObjectHyp2000Phase value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public ObjectHyp2000Phase read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

 /**
  * Create an instance of ObjectHyp2000Phase given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectHyp2000Phase
  * @throws IOException if the JSON string is invalid with respect to ObjectHyp2000Phase
  */
  public static ObjectHyp2000Phase fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectHyp2000Phase.class);
  }

 /**
  * Convert an instance of ObjectHyp2000Phase to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

