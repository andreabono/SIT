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
import java.util.Arrays;
import org.ingv.dante.model.ObjectAmplitudeTypeAmplitude;
import org.ingv.dante.model.ObjectLocalspace;
import org.ingv.dante.model.ObjectProvenance;
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
 * ObjectAmplitude
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectAmplitude {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_ID_LOCALSPACE = "id_localspace";
  @SerializedName(SERIALIZED_NAME_ID_LOCALSPACE)
  private Long idLocalspace;

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

  public static final String SERIALIZED_NAME_TIME1 = "time1";
  @SerializedName(SERIALIZED_NAME_TIME1)
  private OffsetDateTime time1;

  public static final String SERIALIZED_NAME_AMP1 = "amp1";
  @SerializedName(SERIALIZED_NAME_AMP1)
  private Double amp1;

  public static final String SERIALIZED_NAME_TIME2 = "time2";
  @SerializedName(SERIALIZED_NAME_TIME2)
  private OffsetDateTime time2;

  public static final String SERIALIZED_NAME_AMP2 = "amp2";
  @SerializedName(SERIALIZED_NAME_AMP2)
  private Double amp2;

  public static final String SERIALIZED_NAME_PICK_ID = "pick_id";
  @SerializedName(SERIALIZED_NAME_PICK_ID)
  private Long pickId;

  public static final String SERIALIZED_NAME_PERIOD = "period";
  @SerializedName(SERIALIZED_NAME_PERIOD)
  private Double period;

  public static final String SERIALIZED_NAME_REVISED = "revised";
  @SerializedName(SERIALIZED_NAME_REVISED)
  private Integer revised;

  public static final String SERIALIZED_NAME_TYPE_AMPLITUDE = "type_amplitude";
  @SerializedName(SERIALIZED_NAME_TYPE_AMPLITUDE)
  private ObjectAmplitudeTypeAmplitude typeAmplitude;

  public static final String SERIALIZED_NAME_LOCALSPACE = "localspace";
  @SerializedName(SERIALIZED_NAME_LOCALSPACE)
  private ObjectLocalspace localspace;

  public static final String SERIALIZED_NAME_PROVENANCE = "provenance";
  @SerializedName(SERIALIZED_NAME_PROVENANCE)
  private ObjectProvenance provenance;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public static final String SERIALIZED_NAME_TIMEWINDOW_REFERENCE = "timewindow_reference";
  @SerializedName(SERIALIZED_NAME_TIMEWINDOW_REFERENCE)
  private OffsetDateTime timewindowReference;

  public ObjectAmplitude() {
  }

  public ObjectAmplitude(
     Long id, 
     OffsetDateTime modified, 
     OffsetDateTime inserted, 
     OffsetDateTime timewindowReference
  ) {
    this();
    this.id = id;
    this.modified = modified;
    this.inserted = inserted;
    this.timewindowReference = timewindowReference;
  }

   /**
   * Unique incremental id | bigint(20)
   * @return id
  **/
  @javax.annotation.Nullable
  public Long getId() {
    return id;
  }



  public ObjectAmplitude idLocalspace(Long idLocalspace) {
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


  public ObjectAmplitude net(String net) {
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


  public ObjectAmplitude sta(String sta) {
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


  public ObjectAmplitude cha(String cha) {
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


  public ObjectAmplitude loc(String loc) {
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


  public ObjectAmplitude time1(OffsetDateTime time1) {
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


  public ObjectAmplitude amp1(Double amp1) {
    this.amp1 = amp1;
    return this;
  }

   /**
   * Amplitude value | double
   * @return amp1
  **/
  @javax.annotation.Nullable
  public Double getAmp1() {
    return amp1;
  }

  public void setAmp1(Double amp1) {
    this.amp1 = amp1;
  }


  public ObjectAmplitude time2(OffsetDateTime time2) {
    this.time2 = time2;
    return this;
  }

   /**
   *  | datetime(3)
   * @return time2
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getTime2() {
    return time2;
  }

  public void setTime2(OffsetDateTime time2) {
    this.time2 = time2;
  }


  public ObjectAmplitude amp2(Double amp2) {
    this.amp2 = amp2;
    return this;
  }

   /**
   * Amplitude value | double
   * @return amp2
  **/
  @javax.annotation.Nullable
  public Double getAmp2() {
    return amp2;
  }

  public void setAmp2(Double amp2) {
    this.amp2 = amp2;
  }


  public ObjectAmplitude pickId(Long pickId) {
    this.pickId = pickId;
    return this;
  }

   /**
   * Unique incremental id | bigint(20)
   * @return pickId
  **/
  @javax.annotation.Nullable
  public Long getPickId() {
    return pickId;
  }

  public void setPickId(Long pickId) {
    this.pickId = pickId;
  }


  public ObjectAmplitude period(Double period) {
    this.period = period;
    return this;
  }

   /**
   * Amlitude period | double
   * @return period
  **/
  @javax.annotation.Nullable
  public Double getPeriod() {
    return period;
  }

  public void setPeriod(Double period) {
    this.period = period;
  }


  public ObjectAmplitude revised(Integer revised) {
    this.revised = revised;
    return this;
  }

   /**
   * Dichiara se tale ampiezza è stata rivista dall&#39;analista, eventualmente anche non modificata, oppure no. (1/0, true/false) | tinyint(3)
   * @return revised
  **/
  @javax.annotation.Nullable
  public Integer getRevised() {
    return revised;
  }

  public void setRevised(Integer revised) {
    this.revised = revised;
  }


  public ObjectAmplitude typeAmplitude(ObjectAmplitudeTypeAmplitude typeAmplitude) {
    this.typeAmplitude = typeAmplitude;
    return this;
  }

   /**
   * Get typeAmplitude
   * @return typeAmplitude
  **/
  @javax.annotation.Nullable
  public ObjectAmplitudeTypeAmplitude getTypeAmplitude() {
    return typeAmplitude;
  }

  public void setTypeAmplitude(ObjectAmplitudeTypeAmplitude typeAmplitude) {
    this.typeAmplitude = typeAmplitude;
  }


  public ObjectAmplitude localspace(ObjectLocalspace localspace) {
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


  public ObjectAmplitude provenance(ObjectProvenance provenance) {
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



   /**
   * Describes a time window for amplitude measurements, given by a central point in time
   * @return timewindowReference
  **/
  @javax.annotation.Nullable
  public OffsetDateTime getTimewindowReference() {
    return timewindowReference;
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
   * @return the ObjectAmplitude instance itself
   */
  public ObjectAmplitude putAdditionalProperty(String key, Object value) {
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
    ObjectAmplitude objectAmplitude = (ObjectAmplitude) o;
    return Objects.equals(this.id, objectAmplitude.id) &&
        Objects.equals(this.idLocalspace, objectAmplitude.idLocalspace) &&
        Objects.equals(this.net, objectAmplitude.net) &&
        Objects.equals(this.sta, objectAmplitude.sta) &&
        Objects.equals(this.cha, objectAmplitude.cha) &&
        Objects.equals(this.loc, objectAmplitude.loc) &&
        Objects.equals(this.time1, objectAmplitude.time1) &&
        Objects.equals(this.amp1, objectAmplitude.amp1) &&
        Objects.equals(this.time2, objectAmplitude.time2) &&
        Objects.equals(this.amp2, objectAmplitude.amp2) &&
        Objects.equals(this.pickId, objectAmplitude.pickId) &&
        Objects.equals(this.period, objectAmplitude.period) &&
        Objects.equals(this.revised, objectAmplitude.revised) &&
        Objects.equals(this.typeAmplitude, objectAmplitude.typeAmplitude) &&
        Objects.equals(this.localspace, objectAmplitude.localspace) &&
        Objects.equals(this.provenance, objectAmplitude.provenance) &&
        Objects.equals(this.modified, objectAmplitude.modified) &&
        Objects.equals(this.inserted, objectAmplitude.inserted) &&
        Objects.equals(this.timewindowReference, objectAmplitude.timewindowReference)&&
        Objects.equals(this.additionalProperties, objectAmplitude.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idLocalspace, net, sta, cha, loc, time1, amp1, time2, amp2, pickId, period, revised, typeAmplitude, localspace, provenance, modified, inserted, timewindowReference, additionalProperties);
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
    sb.append("class ObjectAmplitude {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idLocalspace: ").append(toIndentedString(idLocalspace)).append("\n");
    sb.append("    net: ").append(toIndentedString(net)).append("\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    cha: ").append(toIndentedString(cha)).append("\n");
    sb.append("    loc: ").append(toIndentedString(loc)).append("\n");
    sb.append("    time1: ").append(toIndentedString(time1)).append("\n");
    sb.append("    amp1: ").append(toIndentedString(amp1)).append("\n");
    sb.append("    time2: ").append(toIndentedString(time2)).append("\n");
    sb.append("    amp2: ").append(toIndentedString(amp2)).append("\n");
    sb.append("    pickId: ").append(toIndentedString(pickId)).append("\n");
    sb.append("    period: ").append(toIndentedString(period)).append("\n");
    sb.append("    revised: ").append(toIndentedString(revised)).append("\n");
    sb.append("    typeAmplitude: ").append(toIndentedString(typeAmplitude)).append("\n");
    sb.append("    localspace: ").append(toIndentedString(localspace)).append("\n");
    sb.append("    provenance: ").append(toIndentedString(provenance)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    inserted: ").append(toIndentedString(inserted)).append("\n");
    sb.append("    timewindowReference: ").append(toIndentedString(timewindowReference)).append("\n");
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
    openapiFields.add("id");
    openapiFields.add("id_localspace");
    openapiFields.add("net");
    openapiFields.add("sta");
    openapiFields.add("cha");
    openapiFields.add("loc");
    openapiFields.add("time1");
    openapiFields.add("amp1");
    openapiFields.add("time2");
    openapiFields.add("amp2");
    openapiFields.add("pick_id");
    openapiFields.add("period");
    openapiFields.add("revised");
    openapiFields.add("type_amplitude");
    openapiFields.add("localspace");
    openapiFields.add("provenance");
    openapiFields.add("modified");
    openapiFields.add("inserted");
    openapiFields.add("timewindow_reference");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("time1");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectAmplitude
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectAmplitude.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectAmplitude is not found in the empty JSON string", ObjectAmplitude.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : ObjectAmplitude.openapiRequiredFields) {
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
      // validate the optional field `type_amplitude`
      if (jsonObj.get("type_amplitude") != null && !jsonObj.get("type_amplitude").isJsonNull()) {
        ObjectAmplitudeTypeAmplitude.validateJsonElement(jsonObj.get("type_amplitude"));
      }
      // validate the optional field `localspace`
      if (jsonObj.get("localspace") != null && !jsonObj.get("localspace").isJsonNull()) {
        ObjectLocalspace.validateJsonElement(jsonObj.get("localspace"));
      }
      // validate the optional field `provenance`
      if (jsonObj.get("provenance") != null && !jsonObj.get("provenance").isJsonNull()) {
        ObjectProvenance.validateJsonElement(jsonObj.get("provenance"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectAmplitude.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectAmplitude' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectAmplitude> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectAmplitude.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectAmplitude>() {
           @Override
           public void write(JsonWriter out, ObjectAmplitude value) throws IOException {
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
           public ObjectAmplitude read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectAmplitude instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ObjectAmplitude given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectAmplitude
  * @throws IOException if the JSON string is invalid with respect to ObjectAmplitude
  */
  public static ObjectAmplitude fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectAmplitude.class);
  }

 /**
  * Convert an instance of ObjectAmplitude to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
