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
import java.util.Arrays;
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
 * MetaDefinitionLinksInner
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class MetaDefinitionLinksInner {
  public static final String SERIALIZED_NAME_URL = "url";
  @SerializedName(SERIALIZED_NAME_URL)
  private String url;

  public static final String SERIALIZED_NAME_LABEL = "label";
  @SerializedName(SERIALIZED_NAME_LABEL)
  private String label;

  public static final String SERIALIZED_NAME_ACTIVE = "active";
  @SerializedName(SERIALIZED_NAME_ACTIVE)
  private String active;

  public MetaDefinitionLinksInner() {
  }

  public MetaDefinitionLinksInner url(String url) {
    this.url = url;
    return this;
  }

   /**
   * URL base path.
   * @return url
  **/
  @javax.annotation.Nullable
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }


  public MetaDefinitionLinksInner label(String label) {
    this.label = label;
    return this;
  }

   /**
   * Page label.
   * @return label
  **/
  @javax.annotation.Nullable
  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }


  public MetaDefinitionLinksInner active(String active) {
    this.active = active;
    return this;
  }

   /**
   * Get active
   * @return active
  **/
  @javax.annotation.Nullable
  public String getActive() {
    return active;
  }

  public void setActive(String active) {
    this.active = active;
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
   * @return the MetaDefinitionLinksInner instance itself
   */
  public MetaDefinitionLinksInner putAdditionalProperty(String key, Object value) {
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
    MetaDefinitionLinksInner metaDefinitionLinksInner = (MetaDefinitionLinksInner) o;
    return Objects.equals(this.url, metaDefinitionLinksInner.url) &&
        Objects.equals(this.label, metaDefinitionLinksInner.label) &&
        Objects.equals(this.active, metaDefinitionLinksInner.active)&&
        Objects.equals(this.additionalProperties, metaDefinitionLinksInner.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, label, active, additionalProperties);
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
    sb.append("class MetaDefinitionLinksInner {\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
    sb.append("    label: ").append(toIndentedString(label)).append("\n");
    sb.append("    active: ").append(toIndentedString(active)).append("\n");
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
    openapiFields.add("url");
    openapiFields.add("label");
    openapiFields.add("active");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to MetaDefinitionLinksInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!MetaDefinitionLinksInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in MetaDefinitionLinksInner is not found in the empty JSON string", MetaDefinitionLinksInner.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("url") != null && !jsonObj.get("url").isJsonNull()) && !jsonObj.get("url").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `url` to be a primitive type in the JSON string but got `%s`", jsonObj.get("url").toString()));
      }
      if ((jsonObj.get("label") != null && !jsonObj.get("label").isJsonNull()) && !jsonObj.get("label").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `label` to be a primitive type in the JSON string but got `%s`", jsonObj.get("label").toString()));
      }
      if ((jsonObj.get("active") != null && !jsonObj.get("active").isJsonNull()) && !jsonObj.get("active").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `active` to be a primitive type in the JSON string but got `%s`", jsonObj.get("active").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!MetaDefinitionLinksInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'MetaDefinitionLinksInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<MetaDefinitionLinksInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(MetaDefinitionLinksInner.class));

       return (TypeAdapter<T>) new TypeAdapter<MetaDefinitionLinksInner>() {
           @Override
           public void write(JsonWriter out, MetaDefinitionLinksInner value) throws IOException {
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
           public MetaDefinitionLinksInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             MetaDefinitionLinksInner instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of MetaDefinitionLinksInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of MetaDefinitionLinksInner
  * @throws IOException if the JSON string is invalid with respect to MetaDefinitionLinksInner
  */
  public static MetaDefinitionLinksInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, MetaDefinitionLinksInner.class);
  }

 /**
  * Convert an instance of MetaDefinitionLinksInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}
