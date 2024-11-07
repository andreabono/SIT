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
import org.ingv.dante.model.ObjectLocalspaceForVwWithDOI;
import org.ingv.dante.model.ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup;
import org.ingv.dante.model.ObjectOriginForMagnitudesOriginsEventsAndEventsGroup;
import org.ingv.dante.model.ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup;
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
 * ObjectVwEventCatalogInner
 */
//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class ObjectVwEventCatalogInner {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Long id;

  public static final String SERIALIZED_NAME_ID_LOCALSPACE = "id_localspace";
  @SerializedName(SERIALIZED_NAME_ID_LOCALSPACE)
  private Long idLocalspace;

  public static final String SERIALIZED_NAME_EVENT_GROUP_ID = "event_group_id";
  @SerializedName(SERIALIZED_NAME_EVENT_GROUP_ID)
  private Long eventGroupId = 0l;

  public static final String SERIALIZED_NAME_PREFERRED_ORIGIN_ID = "preferred_origin_id";
  @SerializedName(SERIALIZED_NAME_PREFERRED_ORIGIN_ID)
  private Long preferredOriginId;

  public static final String SERIALIZED_NAME_PREFERRED_MAGNITUDE_ID = "preferred_magnitude_id";
  @SerializedName(SERIALIZED_NAME_PREFERRED_MAGNITUDE_ID)
  private Long preferredMagnitudeId;

  public static final String SERIALIZED_NAME_PREFERRED_FOCALMECHANISM_ID = "preferred_focalmechanism_id";
  @SerializedName(SERIALIZED_NAME_PREFERRED_FOCALMECHANISM_ID)
  private Long preferredFocalmechanismId;

  public static final String SERIALIZED_NAME_FLAGS = "flags";
  @SerializedName(SERIALIZED_NAME_FLAGS)
  private String flags;

  public static final String SERIALIZED_NAME_TYPE_GROUP = "type_group";
  @SerializedName(SERIALIZED_NAME_TYPE_GROUP)
  private Long typeGroup = 0l;

  public static final String SERIALIZED_NAME_TYPE_EVENT = "type_event";
  @SerializedName(SERIALIZED_NAME_TYPE_EVENT)
  private String typeEvent;

  public static final String SERIALIZED_NAME_ORIGINDIRECTLINKTOEVENT = "origindirectlinktoevent";
  @SerializedName(SERIALIZED_NAME_ORIGINDIRECTLINKTOEVENT)
  private Boolean origindirectlinktoevent = false;

  public static final String SERIALIZED_NAME_MAGNITUDEDIRECTLINKTOORIGIN = "magnitudedirectlinktoorigin";
  @SerializedName(SERIALIZED_NAME_MAGNITUDEDIRECTLINKTOORIGIN)
  private Boolean magnitudedirectlinktoorigin = false;

  public static final String SERIALIZED_NAME_MAGNITUDEDIRECTLINKTOEVENT = "magnitudedirectlinktoevent";
  @SerializedName(SERIALIZED_NAME_MAGNITUDEDIRECTLINKTOEVENT)
  private Boolean magnitudedirectlinktoevent = false;

  public static final String SERIALIZED_NAME_MODIFIED = "modified";
  @SerializedName(SERIALIZED_NAME_MODIFIED)
  private OffsetDateTime modified;

  public static final String SERIALIZED_NAME_INSERTED = "inserted";
  @SerializedName(SERIALIZED_NAME_INSERTED)
  private OffsetDateTime inserted;

  public static final String SERIALIZED_NAME_LOCALSPACE = "localspace";
  @SerializedName(SERIALIZED_NAME_LOCALSPACE)
  private ObjectLocalspaceForVwWithDOI localspace;

  public static final String SERIALIZED_NAME_PROVENANCE = "provenance";
  @SerializedName(SERIALIZED_NAME_PROVENANCE)
  private ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup provenance;

  public static final String SERIALIZED_NAME_ORIGIN = "origin";
  @SerializedName(SERIALIZED_NAME_ORIGIN)
  private ObjectOriginForMagnitudesOriginsEventsAndEventsGroup origin;

  public static final String SERIALIZED_NAME_MAGNITUDE = "magnitude";
  @SerializedName(SERIALIZED_NAME_MAGNITUDE)
  private ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup magnitude;

  public ObjectVwEventCatalogInner() {
  }

  public ObjectVwEventCatalogInner(
     Long eventGroupId, 
     Long preferredOriginId, 
     Long preferredMagnitudeId, 
     Long preferredFocalmechanismId, 
     String flags, 
     Long typeGroup, 
     OffsetDateTime modified, 
     OffsetDateTime inserted
  ) {
    this();
    this.eventGroupId = eventGroupId;
    this.preferredOriginId = preferredOriginId;
    this.preferredMagnitudeId = preferredMagnitudeId;
    this.preferredFocalmechanismId = preferredFocalmechanismId;
    this.flags = flags;
    this.typeGroup = typeGroup;
    this.modified = modified;
    this.inserted = inserted;
  }

  public ObjectVwEventCatalogInner id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Unique incremental id | bigint(20)
   * @return id
  **/
  @javax.annotation.Nullable
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public ObjectVwEventCatalogInner idLocalspace(Long idLocalspace) {
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


   /**
   * Link event group | bigint(20)
   * @return eventGroupId
  **/
  @javax.annotation.Nullable
  public Long getEventGroupId() {
    return eventGroupId;
  }



   /**
   * Link: preferred origin. It can be NULL. | bigint(20)
   * @return preferredOriginId
  **/
  @javax.annotation.Nullable
  public Long getPreferredOriginId() {
    return preferredOriginId;
  }



   /**
   * Link: preferred magnitude. It can be NULL. | bigint(20)
   * @return preferredMagnitudeId
  **/
  @javax.annotation.Nullable
  public Long getPreferredMagnitudeId() {
    return preferredMagnitudeId;
  }



   /**
   * Link: preferred focalmechanism. It can be NULL. | bigint(20)
   * @return preferredFocalmechanismId
  **/
  @javax.annotation.Nullable
  public Long getPreferredFocalmechanismId() {
    return preferredFocalmechanismId;
  }



   /**
   * Flags for origin | varchar(255)
   * @return flags
  **/
  @javax.annotation.Nullable
  public String getFlags() {
    return flags;
  }



   /**
   * Group type. Used by clustering algorithm | tinyint(4)
   * @return typeGroup
  **/
  @javax.annotation.Nullable
  public Long getTypeGroup() {
    return typeGroup;
  }



  public ObjectVwEventCatalogInner typeEvent(String typeEvent) {
    this.typeEvent = typeEvent;
    return this;
  }

   /**
   * Name | varchar(255)
   * @return typeEvent
  **/
  @javax.annotation.Nullable
  public String getTypeEvent() {
    return typeEvent;
  }

  public void setTypeEvent(String typeEvent) {
    this.typeEvent = typeEvent;
  }


  public ObjectVwEventCatalogInner origindirectlinktoevent(Boolean origindirectlinktoevent) {
    this.origindirectlinktoevent = origindirectlinktoevent;
    return this;
  }

   /**
   * Origin entity is directly linked to Event entity (origin.fk_event&#x3D;event.id)
   * @return origindirectlinktoevent
  **/
  @javax.annotation.Nullable
  public Boolean getOrigindirectlinktoevent() {
    return origindirectlinktoevent;
  }

  public void setOrigindirectlinktoevent(Boolean origindirectlinktoevent) {
    this.origindirectlinktoevent = origindirectlinktoevent;
  }


  public ObjectVwEventCatalogInner magnitudedirectlinktoorigin(Boolean magnitudedirectlinktoorigin) {
    this.magnitudedirectlinktoorigin = magnitudedirectlinktoorigin;
    return this;
  }

   /**
   * Magnitude entity is directly linked to Origin entity (magnitude.fk_origin&#x3D;origin.id)
   * @return magnitudedirectlinktoorigin
  **/
  @javax.annotation.Nullable
  public Boolean getMagnitudedirectlinktoorigin() {
    return magnitudedirectlinktoorigin;
  }

  public void setMagnitudedirectlinktoorigin(Boolean magnitudedirectlinktoorigin) {
    this.magnitudedirectlinktoorigin = magnitudedirectlinktoorigin;
  }


  public ObjectVwEventCatalogInner magnitudedirectlinktoevent(Boolean magnitudedirectlinktoevent) {
    this.magnitudedirectlinktoevent = magnitudedirectlinktoevent;
    return this;
  }

   /**
   * Magnitude entity is directly linked to Event entity (magnitude.fk_origin&#x3D;origin.id -&gt; origin.fk_event&#x3D;event.id)
   * @return magnitudedirectlinktoevent
  **/
  @javax.annotation.Nullable
  public Boolean getMagnitudedirectlinktoevent() {
    return magnitudedirectlinktoevent;
  }

  public void setMagnitudedirectlinktoevent(Boolean magnitudedirectlinktoevent) {
    this.magnitudedirectlinktoevent = magnitudedirectlinktoevent;
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



  public ObjectVwEventCatalogInner localspace(ObjectLocalspaceForVwWithDOI localspace) {
    this.localspace = localspace;
    return this;
  }

   /**
   * Get localspace
   * @return localspace
  **/
  @javax.annotation.Nullable
  public ObjectLocalspaceForVwWithDOI getLocalspace() {
    return localspace;
  }

  public void setLocalspace(ObjectLocalspaceForVwWithDOI localspace) {
    this.localspace = localspace;
  }


  public ObjectVwEventCatalogInner provenance(ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup provenance) {
    this.provenance = provenance;
    return this;
  }

   /**
   * Get provenance
   * @return provenance
  **/
  @javax.annotation.Nullable
  public ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup getProvenance() {
    return provenance;
  }

  public void setProvenance(ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup provenance) {
    this.provenance = provenance;
  }


  public ObjectVwEventCatalogInner origin(ObjectOriginForMagnitudesOriginsEventsAndEventsGroup origin) {
    this.origin = origin;
    return this;
  }

   /**
   * Get origin
   * @return origin
  **/
  @javax.annotation.Nullable
  public ObjectOriginForMagnitudesOriginsEventsAndEventsGroup getOrigin() {
    return origin;
  }

  public void setOrigin(ObjectOriginForMagnitudesOriginsEventsAndEventsGroup origin) {
    this.origin = origin;
  }


  public ObjectVwEventCatalogInner magnitude(ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup magnitude) {
    this.magnitude = magnitude;
    return this;
  }

   /**
   * Get magnitude
   * @return magnitude
  **/
  @javax.annotation.Nullable
  public ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup getMagnitude() {
    return magnitude;
  }

  public void setMagnitude(ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup magnitude) {
    this.magnitude = magnitude;
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
   * @return the ObjectVwEventCatalogInner instance itself
   */
  public ObjectVwEventCatalogInner putAdditionalProperty(String key, Object value) {
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
    ObjectVwEventCatalogInner objectVwEventCatalogInner = (ObjectVwEventCatalogInner) o;
    return Objects.equals(this.id, objectVwEventCatalogInner.id) &&
        Objects.equals(this.idLocalspace, objectVwEventCatalogInner.idLocalspace) &&
        Objects.equals(this.eventGroupId, objectVwEventCatalogInner.eventGroupId) &&
        Objects.equals(this.preferredOriginId, objectVwEventCatalogInner.preferredOriginId) &&
        Objects.equals(this.preferredMagnitudeId, objectVwEventCatalogInner.preferredMagnitudeId) &&
        Objects.equals(this.preferredFocalmechanismId, objectVwEventCatalogInner.preferredFocalmechanismId) &&
        Objects.equals(this.flags, objectVwEventCatalogInner.flags) &&
        Objects.equals(this.typeGroup, objectVwEventCatalogInner.typeGroup) &&
        Objects.equals(this.typeEvent, objectVwEventCatalogInner.typeEvent) &&
        Objects.equals(this.origindirectlinktoevent, objectVwEventCatalogInner.origindirectlinktoevent) &&
        Objects.equals(this.magnitudedirectlinktoorigin, objectVwEventCatalogInner.magnitudedirectlinktoorigin) &&
        Objects.equals(this.magnitudedirectlinktoevent, objectVwEventCatalogInner.magnitudedirectlinktoevent) &&
        Objects.equals(this.modified, objectVwEventCatalogInner.modified) &&
        Objects.equals(this.inserted, objectVwEventCatalogInner.inserted) &&
        Objects.equals(this.localspace, objectVwEventCatalogInner.localspace) &&
        Objects.equals(this.provenance, objectVwEventCatalogInner.provenance) &&
        Objects.equals(this.origin, objectVwEventCatalogInner.origin) &&
        Objects.equals(this.magnitude, objectVwEventCatalogInner.magnitude)&&
        Objects.equals(this.additionalProperties, objectVwEventCatalogInner.additionalProperties);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idLocalspace, eventGroupId, preferredOriginId, preferredMagnitudeId, preferredFocalmechanismId, flags, typeGroup, typeEvent, origindirectlinktoevent, magnitudedirectlinktoorigin, magnitudedirectlinktoevent, modified, inserted, localspace, provenance, origin, magnitude, additionalProperties);
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
    sb.append("class ObjectVwEventCatalogInner {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idLocalspace: ").append(toIndentedString(idLocalspace)).append("\n");
    sb.append("    eventGroupId: ").append(toIndentedString(eventGroupId)).append("\n");
    sb.append("    preferredOriginId: ").append(toIndentedString(preferredOriginId)).append("\n");
    sb.append("    preferredMagnitudeId: ").append(toIndentedString(preferredMagnitudeId)).append("\n");
    sb.append("    preferredFocalmechanismId: ").append(toIndentedString(preferredFocalmechanismId)).append("\n");
    sb.append("    flags: ").append(toIndentedString(flags)).append("\n");
    sb.append("    typeGroup: ").append(toIndentedString(typeGroup)).append("\n");
    sb.append("    typeEvent: ").append(toIndentedString(typeEvent)).append("\n");
    sb.append("    origindirectlinktoevent: ").append(toIndentedString(origindirectlinktoevent)).append("\n");
    sb.append("    magnitudedirectlinktoorigin: ").append(toIndentedString(magnitudedirectlinktoorigin)).append("\n");
    sb.append("    magnitudedirectlinktoevent: ").append(toIndentedString(magnitudedirectlinktoevent)).append("\n");
    sb.append("    modified: ").append(toIndentedString(modified)).append("\n");
    sb.append("    inserted: ").append(toIndentedString(inserted)).append("\n");
    sb.append("    localspace: ").append(toIndentedString(localspace)).append("\n");
    sb.append("    provenance: ").append(toIndentedString(provenance)).append("\n");
    sb.append("    origin: ").append(toIndentedString(origin)).append("\n");
    sb.append("    magnitude: ").append(toIndentedString(magnitude)).append("\n");
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
    openapiFields.add("event_group_id");
    openapiFields.add("preferred_origin_id");
    openapiFields.add("preferred_magnitude_id");
    openapiFields.add("preferred_focalmechanism_id");
    openapiFields.add("flags");
    openapiFields.add("type_group");
    openapiFields.add("type_event");
    openapiFields.add("origindirectlinktoevent");
    openapiFields.add("magnitudedirectlinktoorigin");
    openapiFields.add("magnitudedirectlinktoevent");
    openapiFields.add("modified");
    openapiFields.add("inserted");
    openapiFields.add("localspace");
    openapiFields.add("provenance");
    openapiFields.add("origin");
    openapiFields.add("magnitude");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
  }

 /**
  * Validates the JSON Element and throws an exception if issues found
  *
  * @param jsonElement JSON Element
  * @throws IOException if the JSON Element is invalid with respect to ObjectVwEventCatalogInner
  */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!ObjectVwEventCatalogInner.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in ObjectVwEventCatalogInner is not found in the empty JSON string", ObjectVwEventCatalogInner.openapiRequiredFields.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if ((jsonObj.get("flags") != null && !jsonObj.get("flags").isJsonNull()) && !jsonObj.get("flags").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `flags` to be a primitive type in the JSON string but got `%s`", jsonObj.get("flags").toString()));
      }
      if ((jsonObj.get("type_event") != null && !jsonObj.get("type_event").isJsonNull()) && !jsonObj.get("type_event").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `type_event` to be a primitive type in the JSON string but got `%s`", jsonObj.get("type_event").toString()));
      }
      // validate the optional field `localspace`
      if (jsonObj.get("localspace") != null && !jsonObj.get("localspace").isJsonNull()) {
        ObjectLocalspaceForVwWithDOI.validateJsonElement(jsonObj.get("localspace"));
      }
      // validate the optional field `provenance`
      if (jsonObj.get("provenance") != null && !jsonObj.get("provenance").isJsonNull()) {
        ObjectProvenaceForMagnitudesOriginsEventsAndEventsGroup.validateJsonElement(jsonObj.get("provenance"));
      }
      // validate the optional field `origin`
      if (jsonObj.get("origin") != null && !jsonObj.get("origin").isJsonNull()) {
        ObjectOriginForMagnitudesOriginsEventsAndEventsGroup.validateJsonElement(jsonObj.get("origin"));
      }
      // validate the optional field `magnitude`
      if (jsonObj.get("magnitude") != null && !jsonObj.get("magnitude").isJsonNull()) {
        ObjectMagnitudeForMagnitudesOriginsEventsAndEventsGroup.validateJsonElement(jsonObj.get("magnitude"));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!ObjectVwEventCatalogInner.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'ObjectVwEventCatalogInner' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<ObjectVwEventCatalogInner> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(ObjectVwEventCatalogInner.class));

       return (TypeAdapter<T>) new TypeAdapter<ObjectVwEventCatalogInner>() {
           @Override
           public void write(JsonWriter out, ObjectVwEventCatalogInner value) throws IOException {
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
           public ObjectVwEventCatalogInner read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             JsonObject jsonObj = jsonElement.getAsJsonObject();
             // store additional fields in the deserialized instance
             ObjectVwEventCatalogInner instance = thisAdapter.fromJsonTree(jsonObj);
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
  * Create an instance of ObjectVwEventCatalogInner given an JSON string
  *
  * @param jsonString JSON string
  * @return An instance of ObjectVwEventCatalogInner
  * @throws IOException if the JSON string is invalid with respect to ObjectVwEventCatalogInner
  */
  public static ObjectVwEventCatalogInner fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, ObjectVwEventCatalogInner.class);
  }

 /**
  * Convert an instance of ObjectVwEventCatalogInner to an JSON string
  *
  * @return JSON string
  */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

