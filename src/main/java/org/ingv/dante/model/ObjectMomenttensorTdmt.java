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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ingv.dante.model.ObjectMomenttensorTdmtStationsInner;

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
 * ObjectMomenttensorTdmt
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectMomenttensorTdmt {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public static final String SERIALIZED_NAME_STATIONS = "stations";
  @SerializedName(SERIALIZED_NAME_STATIONS)
  private List<ObjectMomenttensorTdmtStationsInner> stations;

  public ObjectMomenttensorTdmt() {
  }

  public ObjectMomenttensorTdmt(
     Long id, 
     OffsetDateTime modified, 
     OffsetDateTime inserted
  ) {
    this();
    this.id = id;
    this.modified = modified;
    this.inserted = inserted;
  }

   /**
   * Unique incremental id | bigint(20)
   * @return id
  **/
  @javax.annotation.Nullable
  public Long getId() {
    return id;
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



  public ObjectMomenttensorTdmt stations(List<ObjectMomenttensorTdmtStationsInner> stations) {
    this.stations = stations;
    return this;
  }

  public ObjectMomenttensorTdmt addStationsItem(ObjectMomenttensorTdmtStationsInner stationsItem) {
    if (this.stations == null) {
      this.stations = new ArrayList<>();
    }
    this.stations.add(stationsItem);
    return this;
  }

   /**
   * Get stations
   * @return stations
  **/
  @javax.annotation.Nullable
  public List<ObjectMomenttensorTdmtStationsInner> getStations() {
    return stations;
  }

  public void setStations(List<ObjectMomenttensorTdmtStationsInner> stations) {
    this.stations = stations;
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
   * @return the ObjectMomenttensorTdmt instance itself
   */
  public ObjectMomenttensorTdmt putAdditionalProperty(String key, Object value) {
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
    ObjectMomenttensorTdmt objectMomenttensorTdmt = (ObjectMomenttensorTdmt) o;
    return Objects.equals(this.id, objectMomenttensorTdmt.id) &&
        Objects.equals(this.modified, objectMomenttensorTdmt.modified) &&
        Objects.equals(this.inserted, objectMomenttensorTdmt.inserted) &&
        Objects.equals(this.stations, objectMomenttensorTdmt.stations)&&
        Objects.equals(this.additionalProperties, objectMomenttensorTdmt.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, modified, inserted, stations, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectMomenttensorTdmt {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    inserted: ").append(toIndentedString(inserted)).append("\n");
    sb.append("    stations: ").append(toIndentedString(stations)).append("\n");
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
    openapiFields.add("modified");
    openapiFields.add("inserted");
    openapiFields.add("stations");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectMomenttensorTdmt
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectMomenttensorTdmt.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectMomenttensorTdmt is not found in the empty JSON string", ObjectMomenttensorTdmt.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (jsonObj.get("stations") != null && !jsonObj.get("stations").isJsonNull()) {
        JsonArray jsonArraystations = jsonObj.getAsJsonArray("stations");
        if (jsonArraystations != null) {
          // ensure the json data is an array
          if (!jsonObj.get("stations").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `stations` to be an array in the JSON string but got `%s`", jsonObj.get("stations").toString()));
          }

          // validate the optional field `stations` (array)
          for (int i = 0; i < jsonArraystations.size(); i++) {
            ObjectMomenttensorTdmtStationsInner.validateJsonElement(jsonArraystations.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectMomenttensorTdmt.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectMomenttensorTdmt' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectMomenttensorTdmt> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectMomenttensorTdmt.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectMomenttensorTdmt>() {
           @Override
           public void write(JsonWriter out, ObjectMomenttensorTdmt value) throws IOException {
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
           public ObjectMomenttensorTdmt read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectMomenttensorTdmt instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ObjectMomenttensorTdmt given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectMomenttensorTdmt
  * @throws IOException if the JSON string is invalid with respect to ObjectMomenttensorTdmt
  */
  public static ObjectMomenttensorTdmt fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectMomenttensorTdmt.class);
  }

 /**
  * Convert an instance of ObjectMomenttensorTdmt to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

