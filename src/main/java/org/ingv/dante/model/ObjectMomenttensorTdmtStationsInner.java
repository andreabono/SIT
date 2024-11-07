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
 * ObjectMomenttensorTdmtStationsInner
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectMomenttensorTdmtStationsInner {
  public static final String SERIALIZED_NAME_STA = "sta";
  @SerializedName(SERIALIZED_NAME_STA)
  private String sta;

  public static final String SERIALIZED_NAME_R = "r";
  @SerializedName(SERIALIZED_NAME_R)
  private Float r;

  public static final String SERIALIZED_NAME_AZI = "azi";
  @SerializedName(SERIALIZED_NAME_AZI)
  private Float azi;

  public static final String SERIALIZED_NAME_W = "w";
  @SerializedName(SERIALIZED_NAME_W)
  private Double w;

  public static final String SERIALIZED_NAME_VR = "vr";
  @SerializedName(SERIALIZED_NAME_VR)
  private Double vr;

  public static final String SERIALIZED_NAME_ZCOR = "zcor";
  @SerializedName(SERIALIZED_NAME_ZCOR)
  private Double zcor;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public ObjectMomenttensorTdmtStationsInner() {
  }

  public ObjectMomenttensorTdmtStationsInner(
     OffsetDateTime modified, 
     OffsetDateTime inserted
  ) {
    this();
    this.modified = modified;
    this.inserted = inserted;
  }

  public ObjectMomenttensorTdmtStationsInner sta(String sta) {
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


  public ObjectMomenttensorTdmtStationsInner r(Float r) {
    this.r = r;
    return this;
  }

   /**
   * epicenter distance
   * @return r
  **/
  @javax.annotation.Nullable
  public Float getR() {
    return r;
  }

  public void setR(Float r) {
    this.r = r;
  }


  public ObjectMomenttensorTdmtStationsInner azi(Float azi) {
    this.azi = azi;
    return this;
  }

   /**
   * Azimutal gap | float4
   * @return azi
  **/
  @javax.annotation.Nullable
  public Float getAzi() {
    return azi;
  }

  public void setAzi(Float azi) {
    this.azi = azi;
  }


  public ObjectMomenttensorTdmtStationsInner w(Double w) {
    this.w = w;
    return this;
  }

   /**
   * Weight | double
   * @return w
  **/
  @javax.annotation.Nullable
  public Double getW() {
    return w;
  }

  public void setW(Double w) {
    this.w = w;
  }


  public ObjectMomenttensorTdmtStationsInner vr(Double vr) {
    this.vr = vr;
    return this;
  }

   /**
   * vr
   * @return vr
  **/
  @javax.annotation.Nullable
  public Double getVr() {
    return vr;
  }

  public void setVr(Double vr) {
    this.vr = vr;
  }


  public ObjectMomenttensorTdmtStationsInner zcor(Double zcor) {
    this.zcor = zcor;
    return this;
  }

   /**
   * zcor
   * @return zcor
  **/
  @javax.annotation.Nullable
  public Double getZcor() {
    return zcor;
  }

  public void setZcor(Double zcor) {
    this.zcor = zcor;
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
   * @return the ObjectMomenttensorTdmtStationsInner instance itself
   */
  public ObjectMomenttensorTdmtStationsInner putAdditionalProperty(String key, Object value) {
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
    ObjectMomenttensorTdmtStationsInner objectMomenttensorTdmtStationsInner = (ObjectMomenttensorTdmtStationsInner) o;
    return Objects.equals(this.sta, objectMomenttensorTdmtStationsInner.sta) &&
        Objects.equals(this.r, objectMomenttensorTdmtStationsInner.r) &&
        Objects.equals(this.azi, objectMomenttensorTdmtStationsInner.azi) &&
        Objects.equals(this.w, objectMomenttensorTdmtStationsInner.w) &&
        Objects.equals(this.vr, objectMomenttensorTdmtStationsInner.vr) &&
        Objects.equals(this.zcor, objectMomenttensorTdmtStationsInner.zcor) &&
        Objects.equals(this.modified, objectMomenttensorTdmtStationsInner.modified) &&
        Objects.equals(this.inserted, objectMomenttensorTdmtStationsInner.inserted)&&
        Objects.equals(this.additionalProperties, objectMomenttensorTdmtStationsInner.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sta, r, azi, w, vr, zcor, modified, inserted, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectMomenttensorTdmtStationsInner {\n");
    sb.append("    sta: ").append(toIndentedString(sta)).append("\n");
    sb.append("    r: ").append(toIndentedString(r)).append("\n");
    sb.append("    azi: ").append(toIndentedString(azi)).append("\n");
    sb.append("    w: ").append(toIndentedString(w)).append("\n");
    sb.append("    vr: ").append(toIndentedString(vr)).append("\n");
    sb.append("    zcor: ").append(toIndentedString(zcor)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    inserted: ").append(toIndentedString(inserted)).append("\n");
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
    openapiFields.add("sta");
    openapiFields.add("r");
    openapiFields.add("azi");
    openapiFields.add("w");
    openapiFields.add("vr");
    openapiFields.add("zcor");
    openapiFields.add("modified");
    openapiFields.add("inserted");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectMomenttensorTdmtStationsInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectMomenttensorTdmtStationsInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectMomenttensorTdmtStationsInner is not found in the empty JSON string", ObjectMomenttensorTdmtStationsInner.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("sta") != null && !jsonObj.get("sta").isJsonNull()) && !jsonObj.get("sta").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `sta` to be a primitive type in the JSON string but got `%s`", jsonObj.get("sta").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectMomenttensorTdmtStationsInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectMomenttensorTdmtStationsInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectMomenttensorTdmtStationsInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectMomenttensorTdmtStationsInner.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectMomenttensorTdmtStationsInner>() {
           @Override
           public void write(JsonWriter out, ObjectMomenttensorTdmtStationsInner value) throws IOException {
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
           public ObjectMomenttensorTdmtStationsInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectMomenttensorTdmtStationsInner instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ObjectMomenttensorTdmtStationsInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectMomenttensorTdmtStationsInner
  * @throws IOException if the JSON string is invalid with respect to ObjectMomenttensorTdmtStationsInner
  */
  public static ObjectMomenttensorTdmtStationsInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectMomenttensorTdmtStationsInner.class);
  }

 /**
  * Convert an instance of ObjectMomenttensorTdmtStationsInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

