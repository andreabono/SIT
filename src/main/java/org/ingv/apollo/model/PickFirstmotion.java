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
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * First motion polarity associated with the pick | varchar(1)
 */
@JsonAdapter(PickFirstmotion.Adapter.class)
public enum PickFirstmotion {
  
  U("U"),
  
  D("D"),
  
  NULL("null");

  private String value;

  PickFirstmotion(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static PickFirstmotion fromValue(String value) {
    for (PickFirstmotion b : PickFirstmotion.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    return null;
  }

  public static class Adapter extends TypeAdapter<PickFirstmotion> {
    @Override
    public void write(final JsonWriter jsonWriter, final PickFirstmotion enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public PickFirstmotion read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return PickFirstmotion.fromValue(value);
    }
  }

  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
    String value = jsonElement.getAsString();
    PickFirstmotion.fromValue(value);
  }
}
