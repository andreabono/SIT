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
package org.ingv.sit;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker;
import javafx.scene.layout.AnchorPane;
import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

/**
 * FXML Controller class
 *
 * @author andreabono
 */
public class AuthenticationController implements Initializable {
    
    private String auth_url;
    private String result="";
    
    @FXML
    private WebView Browser;

    
    @FXML
    private AnchorPane anchor_main;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
    }    
        
    public void ShowPage(){    
        // Crea un gestore di fiducia personalizzato che ignori tutti i certificati SSL
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
       
        //   System.setProperty("https.protocols", "TLSv1.2");     
        Browser.getEngine().getLoadWorker().stateProperty().addListener((observable, oldState, newState) -> {
            if (newState == Worker.State.FAILED) {
                System.out.println(">>>> " + Browser.getEngine().getLoadWorker().getException());
            }
        });
        
        
        
        Browser.getEngine().locationProperty().addListener((observable, oldValue, newValue) -> {
            // Verifica se l'URL corrente è l'URI di reindirizzamento autorizzato
            if (newValue.startsWith("http://localhost:8585/pfxauth")) {
                // Gestisci il reindirizzamento e il codice di autorizzazione qui
                result = extractAuthorizationCodeFromUrl(newValue);
            }
        });
        
            
        Browser.getEngine().load(auth_url);
   
    }
    
    private String extractAuthorizationCodeFromUrl(String url) {
    // Dividi l'URL in base al carattere '?'
    String[] parts = url.split("\\?");
    if (parts.length > 1) {
        // Se ci sono più parti, prendi la seconda parte (dopo il '?')
        String query = parts[1];
        // Dividi la parte della query in base al carattere '&'
        String[] queryParams = query.split("&");
        // Cerca il parametro 'code' e restituisci il suo valore
        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            if (keyValue.length == 2 && keyValue[0].equals("code")) {
                return keyValue[1];
            }
        }
    }
    return ""; // Se il parametro 'code' non è stato trovato, restituisci null
}

    /**
     * @return the auth_url
     */
    public String getAuth_url() {
        return auth_url;
    }

    /**
     * @param auth_url the auth_url to set
     */
    public void setAuth_url(String auth_url) {
        this.auth_url = auth_url;
    }

    /**
     * @return the result
     */
    public String getResult() {
        return result;
    }
    
}
