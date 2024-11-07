
package org.ingv.sit.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ingv.dante.api.UpdateApi;
import org.ingv.dante.api.UserApi;

/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
public class User {
    private String Name;
    private boolean LoggedIn;
    private String token;
    private String email;
    private String google_picture_url;
    
    public void Init(){
        Name=null;  
        LoggedIn=false;
        token=null;
        email=null; 
        google_picture_url=null; 
    }
    
    public void InitFromJson(String json){
    //public void InitFromJson(String json, String in_refresh_token, int token_expiration_seconds){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            email = jsonNode.get("email").asText();
            Name = jsonNode.get("given_name").asText() + "." + jsonNode.get("family_name").asText();
            google_picture_url = jsonNode.get("picture").asText() ;         
            LoggedIn= true;
        } catch (Exception e) {
            System.out.println("Errore durante l'init dell'utente: " + e.getMessage());
           
        }
    
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the LoggedIn
     */
    public boolean isLoggedIn() {
        return LoggedIn;
    }

    /**
     * @param LoggedIn the LoggedIn to set
     */
    public void setLoggedIn(boolean LoggedIn) {
        this.LoggedIn = LoggedIn;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the google_picture_url
     */
    public String getGoogle_picture_url() {
        return google_picture_url;
    }

    /**
     * @param google_picture_url the google_picture_url to set
     */
    public void setGoogle_picture_url(String google_picture_url) {
        this.google_picture_url = google_picture_url;
    }

}
