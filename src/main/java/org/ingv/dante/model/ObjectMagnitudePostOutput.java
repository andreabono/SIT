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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.ingv.dante.model.UpdateEvent200ResponseDataEvent;

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
 * ObjectMagnitudePostOutput
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectMagnitudePostOutput {
  public static final String SERIALIZED_NAME_MAGNITUDES = "magnitudes";
  @SerializedName(SERIALIZED_NAME_MAGNITUDES)
  private List<UpdateEvent200ResponseDataEvent> magnitudes;

  public ObjectMagnitudePostOutput() {
  }

  public ObjectMagnitudePostOutput magnitudes(List<UpdateEvent200ResponseDataEvent> magnitudes) {
    this.magnitudes = magnitudes;
    return this;
  }

  public ObjectMagnitudePostOutput addMagnitudesItem(UpdateEvent200ResponseDataEvent magnitudesItem) {
    if (this.magnitudes == null) {
      this.magnitudes = new ArrayList<>();
    }
    this.magnitudes.add(magnitudesItem);
    return this;
  }

   /**
   * Get magnitudes
   * @return magnitudes
  **/
  @javax.annotation.Nullable
  public List<UpdateEvent200ResponseDataEvent> getMagnitudes() {
    return magnitudes;
  }

  public void setMagnitudes(List<UpdateEvent200ResponseDataEvent> magnitudes) {
    this.magnitudes = magnitudes;
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
   * @return the ObjectMagnitudePostOutput instance itself
   */
  public ObjectMagnitudePostOutput putAdditionalProperty(String key, Object value) {
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
    ObjectMagnitudePostOutput objectMagnitudePostOutput = (ObjectMagnitudePostOutput) o;
    return Objects.equals(this.magnitudes, objectMagnitudePostOutput.magnitudes)&&
        Objects.equals(this.additionalProperties, objectMagnitudePostOutput.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(magnitudes, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ObjectMagnitudePostOutput {\n");
    sb.append("    magnitudes: ").append(toIndentedString(magnitudes)).append("\n");
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
    openapiFields.add("magnitudes");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectMagnitudePostOutput
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectMagnitudePostOutput.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectMagnitudePostOutput is not found in the empty JSON string", ObjectMagnitudePostOutput.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (jsonObj.get("magnitudes") != null && !jsonObj.get("magnitudes").isJsonNull()) {
        JsonArray jsonArraymagnitudes = jsonObj.getAsJsonArray("magnitudes");
        if (jsonArraymagnitudes != null) {
          // ensure the json data is an array
          if (!jsonObj.get("magnitudes").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `magnitudes` to be an array in the JSON string but got `%s`", jsonObj.get("magnitudes").toString()));
          }

          // validate the optional field `magnitudes` (array)
          for (int i = 0; i < jsonArraymagnitudes.size(); i++) {
            UpdateEvent200ResponseDataEvent.validateJsonElement(jsonArraymagnitudes.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectMagnitudePostOutput.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectMagnitudePostOutput' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectMagnitudePostOutput> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectMagnitudePostOutput.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectMagnitudePostOutput>() {
           @Override
           public void write(JsonWriter out, ObjectMagnitudePostOutput value) throws IOException {
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
           public ObjectMagnitudePostOutput read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectMagnitudePostOutput instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ObjectMagnitudePostOutput given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectMagnitudePostOutput
  * @throws IOException if the JSON string is invalid with respect to ObjectMagnitudePostOutput
  */
  public static ObjectMagnitudePostOutput fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectMagnitudePostOutput.class);
  }

 /**
  * Convert an instance of ObjectMagnitudePostOutput to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

