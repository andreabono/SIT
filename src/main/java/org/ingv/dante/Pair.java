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


package org.ingv.dante;

//@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-07-01T12:56:18.203916966Z[Etc/UTC]")
public class Pair {
    private String name = "";
    private String value = "";

    public Pair (String name, String value) {
        setName(name);
        setValue(value);
    }

    private void setName(String name) {
        if (!isValidString(name)) {
            return;
        }

        this.name = name;
    }

    private void setValue(String value) {
        if (!isValidString(value)) {
            return;
        }

        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public String getValue() {
        return this.value;
    }

    private boolean isValidString(String arg) {
        if (arg == null) {
            return false;
        }

        return true;
    }
}
