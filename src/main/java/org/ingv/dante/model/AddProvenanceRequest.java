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
import org.ingv.dante.model.ProvenanceEvaluationmode;
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
 * AddProvenanceRequest
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class AddProvenanceRequest {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_NAME = "name";
  @SerializedName(SERIALIZED_NAME_NAME)
  private String name = "INGV";

  public static final String SERIALIZED_NAME_SOFTWARENAME = "softwarename";
  @SerializedName(SERIALIZED_NAME_SOFTWARENAME)
  private String softwarename;

  public static final String SERIALIZED_NAME_VERSION = "version";
  @SerializedName(SERIALIZED_NAME_VERSION)
  private String version;

  public static final String SERIALIZED_NAME_MODEL = "model";
  @SerializedName(SERIALIZED_NAME_MODEL)
  private String model;

  public static final String SERIALIZED_NAME_METHOD = "method";
  @SerializedName(SERIALIZED_NAME_METHOD)
  private String method;

  public static final String SERIALIZED_NAME_PARAMETERS = "parameters";
  @SerializedName(SERIALIZED_NAME_PARAMETERS)
  private String parameters;

  public static final String SERIALIZED_NAME_PROGRAM = "program";
  @SerializedName(SERIALIZED_NAME_PROGRAM)
  private String program;

  public static final String SERIALIZED_NAME_USERNAME = "username";
  @SerializedName(SERIALIZED_NAME_USERNAME)
  private String username;

  public static final String SERIALIZED_NAME_HOSTNAME = "hostname";
  @SerializedName(SERIALIZED_NAME_HOSTNAME)
  private String hostname;

  public static final String SERIALIZED_NAME_DESCRIPTION = "description";
  @SerializedName(SERIALIZED_NAME_DESCRIPTION)
  private String description;

  public static final String SERIALIZED_NAME_PRIORITY = "priority";
  @SerializedName(SERIALIZED_NAME_PRIORITY)
  private Long priority = 0l;

  public static final String SERIALIZED_NAME_EVALUATIONMODE = "evaluationmode";
  @SerializedName(SERIALIZED_NAME_EVALUATIONMODE)
  private ProvenanceEvaluationmode evaluationmode;

  public static final String SERIALIZED_NAME_URL = "url";
  @SerializedName(SERIALIZED_NAME_URL)
  private String url;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public AddProvenanceRequest() {
  }

  public AddProvenanceRequest(
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



  public AddProvenanceRequest name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of Provenance. i.e. INGV, ETH, USGS | varchar(255)
   * @return name
  **/
  @javax.annotation.Nonnull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public AddProvenanceRequest softwarename(String softwarename) {
    this.softwarename = softwarename;
    return this;
  }

   /**
   * Software name. i.e. SisPick, eqassemble, hypoinverse | char(255)
   * @return softwarename
  **/
  @javax.annotation.Nonnull
  public String getSoftwarename() {
    return softwarename;
  }

  public void setSoftwarename(String softwarename) {
    this.softwarename = softwarename;
  }


  public AddProvenanceRequest version(String version) {
    this.version = version;
    return this;
  }

   /**
   * Version name | varchar(255)
   * @return version
  **/
  @javax.annotation.Nullable
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public AddProvenanceRequest model(String model) {
    this.model = model;
    return this;
  }

   /**
   * Name/URI/DOI of the model | varchar(255)
   * @return model
  **/
  @javax.annotation.Nullable
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }


  public AddProvenanceRequest method(String method) {
    this.method = method;
    return this;
  }

   /**
   * Name/URI/DOI of the method | varchar(255)
   * @return method
  **/
  @javax.annotation.Nullable
  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }


  public AddProvenanceRequest parameters(String parameters) {
    this.parameters = parameters;
    return this;
  }

   /**
   * Name/URI/DOI of the parameters | varchar(255)
   * @return parameters
  **/
  @javax.annotation.Nullable
  public String getParameters() {
    return parameters;
  }

  public void setParameters(String parameters) {
    this.parameters = parameters;
  }


  public AddProvenanceRequest program(String program) {
    this.program = program;
    return this;
  }

   /**
   * Name/URI/DOI of the program | varchar(255)
   * @return program
  **/
  @javax.annotation.Nullable
  public String getProgram() {
    return program;
  }

  public void setProgram(String program) {
    this.program = program;
  }


  public AddProvenanceRequest username(String username) {
    this.username = username;
    return this;
  }

   /**
   * User name | char(255)
   * @return username
  **/
  @javax.annotation.Nullable
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public AddProvenanceRequest hostname(String hostname) {
    this.hostname = hostname;
    return this;
  }

   /**
   * Hostname | char(255)
   * @return hostname
  **/
  @javax.annotation.Nullable
  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }


  public AddProvenanceRequest description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Additional information | char(255)
   * @return description
  **/
  @javax.annotation.Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public AddProvenanceRequest priority(Long priority) {
    this.priority = priority;
    return this;
  }

   /**
   * Priority | int(8)
   * @return priority
  **/
  @javax.annotation.Nullable
  public Long getPriority() {
    return priority;
  }

  public void setPriority(Long priority) {
    this.priority = priority;
  }


  public AddProvenanceRequest evaluationmode(ProvenanceEvaluationmode evaluationmode) {
    this.evaluationmode = evaluationmode;
    return this;
  }

   /**
   * Get evaluationmode
   * @return evaluationmode
  **/
  @javax.annotation.Nullable
  public ProvenanceEvaluationmode getEvaluationmode() {
    return evaluationmode;
  }

  public void setEvaluationmode(ProvenanceEvaluationmode evaluationmode) {
    this.evaluationmode = evaluationmode;
  }


  public AddProvenanceRequest url(String url) {
    this.url = url;
    return this;
  }

   /**
   * External URL Reference | varchar(512)
   * @return url
  **/
  @javax.annotation.Nullable
  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
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
   * @return the AddProvenanceRequest instance itself
   */
  public AddProvenanceRequest putAdditionalProperty(String key, Object value) {
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
    AddProvenanceRequest addProvenanceRequest = (AddProvenanceRequest) o;
    return Objects.equals(this.id, addProvenanceRequest.id) &&
        Objects.equals(this.name, addProvenanceRequest.name) &&
        Objects.equals(this.softwarename, addProvenanceRequest.softwarename) &&
        Objects.equals(this.version, addProvenanceRequest.version) &&
        Objects.equals(this.model, addProvenanceRequest.model) &&
        Objects.equals(this.method, addProvenanceRequest.method) &&
        Objects.equals(this.parameters, addProvenanceRequest.parameters) &&
        Objects.equals(this.program, addProvenanceRequest.program) &&
        Objects.equals(this.username, addProvenanceRequest.username) &&
        Objects.equals(this.hostname, addProvenanceRequest.hostname) &&
        Objects.equals(this.description, addProvenanceRequest.description) &&
        Objects.equals(this.priority, addProvenanceRequest.priority) &&
        Objects.equals(this.evaluationmode, addProvenanceRequest.evaluationmode) &&
        Objects.equals(this.url, addProvenanceRequest.url) &&
        Objects.equals(this.modified, addProvenanceRequest.modified) &&
        Objects.equals(this.inserted, addProvenanceRequest.inserted)&&
        Objects.equals(this.additionalProperties, addProvenanceRequest.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, softwarename, version, model, method, parameters, program, username, hostname, description, priority, evaluationmode, url, modified, inserted, additionalProperties);
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
    sb.append("class AddProvenanceRequest {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    softwarename: ").append(toIndentedString(softwarename)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    model: ").append(toIndentedString(model)).append("\n");
    sb.append("    method: ").append(toIndentedString(method)).append("\n");
    sb.append("    parameters: ").append(toIndentedString(parameters)).append("\n");
    sb.append("    program: ").append(toIndentedString(program)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    hostname: ").append(toIndentedString(hostname)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
    sb.append("    evaluationmode: ").append(toIndentedString(evaluationmode)).append("\n");
    sb.append("    url: ").append(toIndentedString(url)).append("\n");
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
    openapiFields.add("id");
    openapiFields.add("name");
    openapiFields.add("softwarename");
    openapiFields.add("version");
    openapiFields.add("model");
    openapiFields.add("method");
    openapiFields.add("parameters");
    openapiFields.add("program");
    openapiFields.add("username");
    openapiFields.add("hostname");
    openapiFields.add("description");
    openapiFields.add("priority");
    openapiFields.add("evaluationmode");
    openapiFields.add("url");
    openapiFields.add("modified");
    openapiFields.add("inserted");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("name");
    openapiRequiredFields.add("softwarename");
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to AddProvenanceRequest
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!AddProvenanceRequest.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in AddProvenanceRequest is not found in the empty JSON string", AddProvenanceRequest.openapiRequiredFields.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : AddProvenanceRequest.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("name").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `name` to be a primitive type in the JSON string but got `%s`", jsonObj.get("name").toString()));
      }
      if (!jsonObj.get("softwarename").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `softwarename` to be a primitive type in the JSON string but got `%s`", jsonObj.get("softwarename").toString()));
      }
      if ((jsonObj.get("version") != null && !jsonObj.get("version").isJsonNull()) && !jsonObj.get("version").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `version` to be a primitive type in the JSON string but got `%s`", jsonObj.get("version").toString()));
      }
      if ((jsonObj.get("model") != null && !jsonObj.get("model").isJsonNull()) && !jsonObj.get("model").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `model` to be a primitive type in the JSON string but got `%s`", jsonObj.get("model").toString()));
      }
      if ((jsonObj.get("method") != null && !jsonObj.get("method").isJsonNull()) && !jsonObj.get("method").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `method` to be a primitive type in the JSON string but got `%s`", jsonObj.get("method").toString()));
      }
      if ((jsonObj.get("parameters") != null && !jsonObj.get("parameters").isJsonNull()) && !jsonObj.get("parameters").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `parameters` to be a primitive type in the JSON string but got `%s`", jsonObj.get("parameters").toString()));
      }
      if ((jsonObj.get("program") != null && !jsonObj.get("program").isJsonNull()) && !jsonObj.get("program").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `program` to be a primitive type in the JSON string but got `%s`", jsonObj.get("program").toString()));
      }
      if ((jsonObj.get("username") != null && !jsonObj.get("username").isJsonNull()) && !jsonObj.get("username").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `username` to be a primitive type in the JSON string but got `%s`", jsonObj.get("username").toString()));
      }
      if ((jsonObj.get("hostname") != null && !jsonObj.get("hostname").isJsonNull()) && !jsonObj.get("hostname").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `hostname` to be a primitive type in the JSON string but got `%s`", jsonObj.get("hostname").toString()));
      }
      if ((jsonObj.get("description") != null && !jsonObj.get("description").isJsonNull()) && !jsonObj.get("description").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `description` to be a primitive type in the JSON string but got `%s`", jsonObj.get("description").toString()));
      }
      // validate the optional field `evaluationmode`
      if (jsonObj.get("evaluationmode") != null && !jsonObj.get("evaluationmode").isJsonNull()) {
        ProvenanceEvaluationmode.validateJsonElement(jsonObj.get("evaluationmode"));
      }
      if ((jsonObj.get("url") != null && !jsonObj.get("url").isJsonNull()) && !jsonObj.get("url").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `url` to be a primitive type in the JSON string but got `%s`", jsonObj.get("url").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!AddProvenanceRequest.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'AddProvenanceRequest' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<AddProvenanceRequest> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(AddProvenanceRequest.class));

       return (TypeAdapter<T>) new TypeAdapter<AddProvenanceRequest>() {
           @Override
           public void write(JsonWriter out, AddProvenanceRequest value) throws IOException {
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
           public AddProvenanceRequest read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             AddProvenanceRequest instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of AddProvenanceRequest given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of AddProvenanceRequest
  * @throws IOException if the JSON string is invalid with respect to AddProvenanceRequest
  */
  public static AddProvenanceRequest fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, AddProvenanceRequest.class);
  }

 /**
  * Convert an instance of AddProvenanceRequest to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

