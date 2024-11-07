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
package org.ingv.sit.security;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.util.UriComponentsBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.client.HttpResponseException;
import org.ingv.sit.App;
import org.ingv.sit.AuthenticationController;
import org.ingv.sit.utils.pfxDialog;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class KeyCloakClient {
    
    private String openIdConfigurationURL;

    private String KEYCLOAK_CLIENT_ID = "pfx";
    private String AUTHORIZATION_ENDPOINT;
    private String TOKEN_ENDPOINT;
    private String USERINFO_ENDPOINT;
    private String END_SESSION_ENDPOINT;

    private String userToken, refreshToken, logoutIdToken;
    private int token_expiration;
    private String userInfo_jsonstring;
    
    private ScheduledExecutorService token_refresh_scheduler;
//------------------------------------------------------------------------------    
    public KeyCloakClient(String in_openIdConfigurationURL){
        openIdConfigurationURL = in_openIdConfigurationURL;
        ReadConfiguration();
    }
//------------------------------------------------------------------------------        
    private void ReadConfiguration(){
        TrustManager[] trustAllCertificates = new TrustManager[]{
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
        // Configura il gestore SSL per ignorare tutti i certificati
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCertificates, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> userInfoResponse = restTemplate.exchange(
                    openIdConfigurationURL,
                    HttpMethod.GET,
                    null,
                    String.class
            );
            
            String json_config = userInfoResponse.getBody();
            
            
            
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(json_config);
                AUTHORIZATION_ENDPOINT = jsonNode.get("authorization_endpoint").asText(); 
                TOKEN_ENDPOINT = jsonNode.get("token_endpoint").asText(); 
                USERINFO_ENDPOINT = jsonNode.get("userinfo_endpoint").asText() ;
                END_SESSION_ENDPOINT = jsonNode.get("end_session_endpoint").asText() ;
            } catch (Exception e) {
                System.out.println("Errore durante l'init del client KeyCloak: " + e.getMessage());

            }
    }
//------------------------------------------------------------------------------             
    public boolean handleLogin() {
        try {
            String redirectUri = "http://localhost:8585/pfxauth"; 
            String responseType = "code"; // Tipo di risposta
            String scope = "openid"; // Scope

            // URL per ottenere il codice di autorizzazione
            //String authorizationUrl = KEYCLOAK_SERVER_URL + "/realms/" + KEYCLOAK_REALM + "/protocol/openid-connect/auth";

            // Costruzione dell'URL di autorizzazione
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUTHORIZATION_ENDPOINT)
                    .queryParam("client_id", KEYCLOAK_CLIENT_ID)
                    //.queryParam("client_secret", KEYCLOAK_CLIENT_SECRET)
                    .queryParam("redirect_uri", redirectUri)
                    .queryParam("response_type", responseType)
                    .queryParam("scope", scope);

            // Esegui il reindirizzamento dell'utente al URL di autorizzazione
            String authorizationRedirectUrl = builder.toUriString();
            System.out.println("Authorize URL: " + authorizationRedirectUrl);


             //L'utente deve accedere al URL di autorizzazione, autorizzare l'applicazione e verrà reindirizzato a redirectUri con il codice di autorizzazione
            FXMLLoader fxmlLoader1 = new FXMLLoader(App.class.getResource("Authentication.fxml"));                       
            Parent root1 = (Parent) fxmlLoader1.load();

            ((AuthenticationController)fxmlLoader1.getController()).setAuth_url(authorizationRedirectUrl);

            Stage stage = new Stage();

            stage.setScene(new Scene(root1));  
            stage.setTitle("I.N.G.V. PFX - Authentication dialog");
            stage.initModality(Modality.APPLICATION_MODAL);


            ((AuthenticationController)fxmlLoader1.getController()).ShowPage();

            stage.setMaximized(false);


           //((AuthenticationController)fxmlLoader1.getController()).ShowPage();
            stage.showAndWait();

            // Una volta ottenuto il codice di autorizzazione, esegui la richiesta per scambiare il codice con un token di accesso
            String code = ((AuthenticationController)fxmlLoader1.getController()).getResult(); // Codice di autorizzazione ottenuto dalla richiesta di autorizzazione

            if (code.trim().length()==0) return false;

            //String tokenUrl = KEYCLOAK_SERVER_URL + "/realms/" + KEYCLOAK_REALM  + "/protocol/openid-connect/token";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/x-www-form-urlencoded");

            String requestBody = "grant_type=authorization_code" +
                    "&client_id=" + KEYCLOAK_CLIENT_ID +
                    "&redirect_uri=" + redirectUri +
                    "&code=" + code;

            // Esegui la richiesta per scambiare il codice di autorizzazione con un token di accesso
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    TOKEN_ENDPOINT,
                    HttpMethod.POST,
                    new HttpEntity<>(requestBody, headers),
                    String.class
            );
            userToken = ExtractField("access_token", response.getBody());
            refreshToken = ExtractField("refresh_token", response.getBody());
            token_expiration = (int) Integer.valueOf(ExtractField("expires_in", response.getBody()));
            logoutIdToken = ExtractField("id_token", response.getBody());
    //
            // 
            //String userInfoUrl = KEYCLOAK_SERVER_URL + "/realms/" + KEYCLOAK_REALM  + "/protocol/openid-connect/userinfo";
            HttpHeaders userInfoHeaders = new HttpHeaders();
            userInfoHeaders.set("Authorization", "Bearer " + userToken);
            ResponseEntity<String> userInfoResponse = restTemplate.exchange(
                    USERINFO_ENDPOINT,
                    HttpMethod.GET,
                    new HttpEntity<>(userInfoHeaders),
                    String.class
            );
            
            userInfo_jsonstring =userInfoResponse.getBody();
            //          
            StartTokenRefreshScheduler();
            return true;
        } catch (Exception ex) {
            System.out.println("ERRORE: " + ex.getMessage());
            userInfo_jsonstring ="";
            return false;
        }
         
    }
//------------------------------------------------------------------------------
    private void StartTokenRefreshScheduler(){
        //token_refresh_scheduler = Executors.newSingleThreadScheduledExecutor();   //.newScheduledThreadPool(1);
        token_refresh_scheduler = Executors.newScheduledThreadPool(1);
        //token_refresh_scheduler.scheduleAtFixedRate(this::refreshAccessToken, token_expiration-50, token_expiration-50, TimeUnit.SECONDS);
        token_refresh_scheduler.scheduleAtFixedRate(this::refreshAccessToken, 20, 60, TimeUnit.SECONDS);
        
        
    }
//------------------------------------------------------------------------------    
    private void StopTokenRefreshScheduler(){ 
        if (token_refresh_scheduler==null) return;
        try {
            token_refresh_scheduler.shutdown();
            if (!token_refresh_scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                token_refresh_scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            token_refresh_scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
//------------------------------------------------------------------------------     
    public boolean handleLogout(){
        boolean res;
        try {
            URL url = new URL(END_SESSION_ENDPOINT+ "?client_id=" + KEYCLOAK_CLIENT_ID + "&id_token_hint="+logoutIdToken+"&post_logout_redirect_uri=https://accounts.google.com/logout");
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
           
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Logout effettuato con successo.");
                res=true;
            } else {
                System.out.println("Errore durante il logout. Codice di risposta: " + responseCode);
                res = false;
            }

            connection.disconnect();
            return res;
        } catch (IOException e) {
            //e.printStackTrace();
            return false;
        }
    }
    
    public boolean refreshAccessToken(){
        HttpURLConnection con=null;
        try {
            Logger.getLogger("org.ingv.pfx ").log(Level.INFO, 
                             "Refreshing user token...");
           
            String postData = "client_id=" + KEYCLOAK_CLIENT_ID + "&grant_type=refresh_token&refresh_token=" + refreshToken; 

            URL obj = new URL(TOKEN_ENDPOINT);
            con = (HttpURLConnection) obj.openConnection();

            // Imposta il metodo della richiesta e abilita la scrittura
            con.setRequestMethod("POST");
            con.setDoOutput(true);

            // Scrivi i dati nel corpo della richiesta
            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                byte[] postDataBytes = postData.getBytes(StandardCharsets.UTF_8);
                wr.write(postDataBytes);
            }

            // Leggi la risposta
            int responseCode=-1; 
            try {
                responseCode=con.getResponseCode();
            } catch (HttpResponseException e){
                pfxDialog.ShowErrorMessage("Errore... " + e.getReasonPhrase(), null);
                e.printStackTrace();
                
                return false;
                
            }
            
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer responseBuffer = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    responseBuffer.append(inputLine);
                }
                in.close();

                String responseBody = responseBuffer.toString();
                
                ObjectMapper objectMapper = new ObjectMapper();

                // Converte la stringa JSON in un oggetto JsonNode di Jackson
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                // 
                userToken = jsonNode.get("access_token").asText();
                App.G.User.setToken(userToken);
                refreshToken = jsonNode.get("refresh_token").asText();
                
                Logger.getLogger("org.ingv.pfx ").log(Level.INFO, 
                             "Refreshing user token... done!!");
                return true;
            } else {
                System.out.println("Errore nella risposta HTTP in referesh token: " + responseCode);
                
                Logger.getLogger("org.ingv.pfx ").log(Level.SEVERE, 
                             "Errore nella risposta HTTP in referesh token: " + responseCode);
                return false;
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println(">>>>>>>> ERROR REFRESHING TOKEN");
            Logger.getLogger("org.ingv.pfx ").log(Level.SEVERE, 
                             e.getMessage());
            return false;
        } finally {
            try {
                if (con!=null) 
                    con.disconnect(); 
            } catch (Exception ex){}
        }
    }
    
    
    private void handleTokenRefreshError(){
    
    }
//------------------------------------------------------------------------------        

//------------------------------------------------------------------------------    
    private String ExtractField(String field_name, String json){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(json);
            //String accessToken = jsonNode.get("access_token").asText();
            String accessToken = jsonNode.get(field_name).asText();

           return accessToken;
        } catch (Exception e) {
            System.out.println("Errore durante l'estrazione del campo " + field_name  + ": " + e.getMessage());
            return null;
        }   
    }
//------------------------------------------------------------------------------    
    /**
     * @return the KEYCLOAK_CLIENT_ID
     */
    public String getKEYCLOAK_CLIENT_ID() {
        return KEYCLOAK_CLIENT_ID;
    }

    /**
     * @param KEYCLOAK_CLIENT_ID the KEYCLOAK_CLIENT_ID to set
     */
    public void setKEYCLOAK_CLIENT_ID(String KEYCLOAK_CLIENT_ID) {
        this.KEYCLOAK_CLIENT_ID = KEYCLOAK_CLIENT_ID;
    }

    /**
     * @return the userToken
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * @param userToken the userToken to set
     */
    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    /**
     * @return the userInfo_jsonstring
     */
    public String getUserInfo_jsonstring() {
        return userInfo_jsonstring;
    }

    /**
     * @param userInfo_jsonstring the userInfo_jsonstring to set
     */
    public void setUserInfo_jsonstring(String userInfo_jsonstring) {
        this.userInfo_jsonstring = userInfo_jsonstring;
    }

    /**
     * @return the openIdConfigurationURL
     */
    public String getOpenIdConfigurationURL() {
        return openIdConfigurationURL;
    }

    /**
     * @param openIdConfigurationURL the openIdConfigurationURL to set
     */
    public void setOpenIdConfigurationURL(String openIdConfigurationURL) {
        this.openIdConfigurationURL = openIdConfigurationURL;
    }

    /**
     * @return the refreshToken
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken the refreshToken to set
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * @return the token_expiration
     */
    public int getToken_expiration() {
        return token_expiration;
    }

    /**
     * @param token_expiration the token_expiration to set
     */
    public void setToken_expiration(int token_expiration) {
        this.token_expiration = token_expiration;
    }
    
}
