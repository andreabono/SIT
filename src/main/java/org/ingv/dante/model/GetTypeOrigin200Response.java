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
import org.ingv.dante.model.MetaDefinitionLinksInner;
import org.ingv.dante.model.ObjectTableTypeOrigin;

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
 * GetTypeOrigin200Response
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class GetTypeOrigin200Response {
  public static final String SERIALIZED_NAME_CURRENT_PAGE = "current_page";
  @SerializedName(SERIALIZED_NAME_CURRENT_PAGE)
  private Integer currentPage;

  public static final String SERIALIZED_NAME_FROM = "from";
  @SerializedName(SERIALIZED_NAME_FROM)
  private Integer from;

  public static final String SERIALIZED_NAME_LAST_PAGE = "last_page";
  @SerializedName(SERIALIZED_NAME_LAST_PAGE)
  private Integer lastPage;

  public static final String SERIALIZED_NAME_LINKS = "links";
  @SerializedName(SERIALIZED_NAME_LINKS)
  private List<MetaDefinitionLinksInner> links;

  public static final String SERIALIZED_NAME_PATH = "path";
  @SerializedName(SERIALIZED_NAME_PATH)
  private String path;

  public static final String SERIALIZED_NAME_PER_PAGE = "per_page";
  @SerializedName(SERIALIZED_NAME_PER_PAGE)
  private Integer perPage;

  public static final String SERIALIZED_NAME_TO = "to";
  @SerializedName(SERIALIZED_NAME_TO)
  private Integer to;

  public static final String SERIALIZED_NAME_TOTAL = "total";
  @SerializedName(SERIALIZED_NAME_TOTAL)
  private Integer total;

  public static final String SERIALIZED_NAME_DATA = "data";
  @SerializedName(SERIALIZED_NAME_DATA)
  private List<ObjectTableTypeOrigin> data;

  public GetTypeOrigin200Response() {
  }

  public GetTypeOrigin200Response currentPage(Integer currentPage) {
    this.currentPage = currentPage;
    return this;
  }

   /**
   * Current page.
   * @return currentPage
  **/
  @javax.annotation.Nullable
  public Integer getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(Integer currentPage) {
    this.currentPage = currentPage;
  }


  public GetTypeOrigin200Response from(Integer from) {
    this.from = from;
    return this;
  }

   /**
   * Showed record from.
   * @return from
  **/
  @javax.annotation.Nullable
  public Integer getFrom() {
    return from;
  }

  public void setFrom(Integer from) {
    this.from = from;
  }


  public GetTypeOrigin200Response lastPage(Integer lastPage) {
    this.lastPage = lastPage;
    return this;
  }

   /**
   * Last page.
   * @return lastPage
  **/
  @javax.annotation.Nullable
  public Integer getLastPage() {
    return lastPage;
  }

  public void setLastPage(Integer lastPage) {
    this.lastPage = lastPage;
  }


  public GetTypeOrigin200Response links(List<MetaDefinitionLinksInner> links) {
    this.links = links;
    return this;
  }

  public GetTypeOrigin200Response addLinksItem(MetaDefinitionLinksInner linksItem) {
    if (this.links == null) {
      this.links = new ArrayList<>();
    }
    this.links.add(linksItem);
    return this;
  }

   /**
   * Get links
   * @return links
  **/
  @javax.annotation.Nullable
  public List<MetaDefinitionLinksInner> getLinks() {
    return links;
  }

  public void setLinks(List<MetaDefinitionLinksInner> links) {
    this.links = links;
  }


  public GetTypeOrigin200Response path(String path) {
    this.path = path;
    return this;
  }

   /**
   * URL base path.
   * @return path
  **/
  @javax.annotation.Nullable
  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }


  public GetTypeOrigin200Response perPage(Integer perPage) {
    this.perPage = perPage;
    return this;
  }

   /**
   * Record per page.
   * @return perPage
  **/
  @javax.annotation.Nullable
  public Integer getPerPage() {
    return perPage;
  }

  public void setPerPage(Integer perPage) {
    this.perPage = perPage;
  }


  public GetTypeOrigin200Response to(Integer to) {
    this.to = to;
    return this;
  }

   /**
   * Showed record to.
   * @return to
  **/
  @javax.annotation.Nullable
  public Integer getTo() {
    return to;
  }

  public void setTo(Integer to) {
    this.to = to;
  }


  public GetTypeOrigin200Response total(Integer total) {
    this.total = total;
    return this;
  }

   /**
   * Total records.
   * @return total
  **/
  @javax.annotation.Nullable
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  public GetTypeOrigin200Response data(List<ObjectTableTypeOrigin> data) {
    this.data = data;
    return this;
  }

  public GetTypeOrigin200Response addDataItem(ObjectTableTypeOrigin dataItem) {
    if (this.data == null) {
      this.data = new ArrayList<>();
    }
    this.data.add(dataItem);
    return this;
  }

   /**
   * Get data
   * @return data
  **/
  @javax.annotation.Nullable
  public List<ObjectTableTypeOrigin> getData() {
    return data;
  }

  public void setData(List<ObjectTableTypeOrigin> data) {
    this.data = data;
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
   * @return the GetTypeOrigin200Response instance itself
   */
  public GetTypeOrigin200Response putAdditionalProperty(String key, Object value) {
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
    GetTypeOrigin200Response getTypeOrigin200Response = (GetTypeOrigin200Response) o;
    return Objects.equals(this.currentPage, getTypeOrigin200Response.currentPage) &&
        Objects.equals(this.from, getTypeOrigin200Response.from) &&
        Objects.equals(this.lastPage, getTypeOrigin200Response.lastPage) &&
        Objects.equals(this.links, getTypeOrigin200Response.links) &&
        Objects.equals(this.path, getTypeOrigin200Response.path) &&
        Objects.equals(this.perPage, getTypeOrigin200Response.perPage) &&
        Objects.equals(this.to, getTypeOrigin200Response.to) &&
        Objects.equals(this.total, getTypeOrigin200Response.total) &&
        Objects.equals(this.data, getTypeOrigin200Response.data)&&
        Objects.equals(this.additionalProperties, getTypeOrigin200Response.additionalProperties);
  }

  @Override
  public int hashCode() {
    return Objects.hash(currentPage, from, lastPage, links, path, perPage, to, total, data, additionalProperties);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetTypeOrigin200Response {\n");
    sb.append("    currentPage: ").append(toIndentedString(currentPage)).append("\n");
    sb.append("    from: ").append(toIndentedString(from)).append("\n");
    sb.append("    lastPage: ").append(toIndentedString(lastPage)).append("\n");
    sb.append("    links: ").append(toIndentedString(links)).append("\n");
    sb.append("    path: ").append(toIndentedString(path)).append("\n");
    sb.append("    perPage: ").append(toIndentedString(perPage)).append("\n");
    sb.append("    to: ").append(toIndentedString(to)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
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
    openapiFields.add("current_page");
    openapiFields.add("from");
    openapiFields.add("last_page");
    openapiFields.add("links");
    openapiFields.add("path");
    openapiFields.add("per_page");
    openapiFields.add("to");
    openapiFields.add("total");
    openapiFields.add("data");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to GetTypeOrigin200Response
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!GetTypeOrigin200Response.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in GetTypeOrigin200Response is not found in the empty JSON string", GetTypeOrigin200Response.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (jsonObj.get("links") != null && !jsonObj.get("links").isJsonNull()) {
        JsonArray jsonArraylinks = jsonObj.getAsJsonArray("links");
        if (jsonArraylinks != null) {
          // ensure the json data is an array
          if (!jsonObj.get("links").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `links` to be an array in the JSON string but got `%s`", jsonObj.get("links").toString()));
          }

          // validate the optional field `links` (array)
          for (int i = 0; i < jsonArraylinks.size(); i++) {
            MetaDefinitionLinksInner.validateJsonElement(jsonArraylinks.get(i));
          };
        }
      }
      if ((jsonObj.get("path") != null && !jsonObj.get("path").isJsonNull()) && !jsonObj.get("path").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `path` to be a primitive type in the JSON string but got `%s`", jsonObj.get("path").toString()));
      }
      if (jsonObj.get("data") != null && !jsonObj.get("data").isJsonNull()) {
        JsonArray jsonArraydata = jsonObj.getAsJsonArray("data");
        if (jsonArraydata != null) {
          // ensure the json data is an array
          if (!jsonObj.get("data").isJsonArray()) {
            throw new IllegalArgumentException(String.format("Expected the field `data` to be an array in the JSON string but got `%s`", jsonObj.get("data").toString()));
          }

          // validate the optional field `data` (array)
          for (int i = 0; i < jsonArraydata.size(); i++) {
            ObjectTableTypeOrigin.validateJsonElement(jsonArraydata.get(i));
          };
        }
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!GetTypeOrigin200Response.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'GetTypeOrigin200Response' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<GetTypeOrigin200Response> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(GetTypeOrigin200Response.class));

       return (TypeAdapter<T>) new TypeAdapter<GetTypeOrigin200Response>() {
           @Override
           public void write(JsonWriter out, GetTypeOrigin200Response value) throws IOException {
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
           public GetTypeOrigin200Response read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             GetTypeOrigin200Response instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of GetTypeOrigin200Response given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of GetTypeOrigin200Response
  * @throws IOException if the JSON string is invalid with respect to GetTypeOrigin200Response
  */
  public static GetTypeOrigin200Response fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, GetTypeOrigin200Response.class);
  }

 /**
  * Convert an instance of GetTypeOrigin200Response to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

