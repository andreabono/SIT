/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 * andrea.bono@ingv.it
// * +39 0651860290
// * 
// */
package org.ingv.sit.webservices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import org.ingv.dante.ApiClient;
import org.ingv.dante.ApiException;
import org.ingv.dante.ApiResponse;
import org.ingv.dante.Pair;
import org.ingv.sit.App;
//import org.ingv.pickfx.App;


public class Generic_client {
    
    List<Pair> Parametri;
    
//------------------------------------------------------------------------------    
    public Generic_client() {
        Parametri = new ArrayList<Pair>();
        
    }
//------------------------------------------------------------------------------        
    public void add_parameter(String name, String value) {
        this.Parametri.add(new Pair(name,value));
    }
//------------------------------------------------------------------------------  
public ApiResponse makeGET(String basepath, String in_depth_path){
        try {
           
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        
        String[] localVarAuthNames = new String[] {  };
            // 20190524
            //ApiClient myClient = new ApiClient(basepath);
            ApiClient myClient = new ApiClient();
            myClient.setDebugging(App.G.options.isDebugMode());
                       
            myClient.setBasePath(basepath);
            myClient.setConnectTimeout(30000);
            myClient.setReadTimeout(20000);          
            //    
            Call myCall = myClient.buildCall(basepath, in_depth_path, "GET", Parametri, localVarCollectionQueryParams, 
                    null, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, null); 
            
            if (myCall!=null) {
                ApiResponse resp = myClient.execute(myCall, String.class);
                return resp;
            }
            
            return null;
            
            } catch (ApiException ex) {
                //Logger.getLogger(clsMain.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            //} catch (Exception ge) {
                //Logger.getLogger(clsMain.class.getName()).log(Level.SEVERE, null, ge);
            //    return null;
            }
        }
//------------------------------------------------------------------------------
//    public Response makePOST(String basepath, String in_depth_path, String input_data, 
//            String media_type, StringBuilder out_error_message){
//        try {
//            //------------------------------------------------------------------
//            // "media_type" in ["text/plain; charset=utf-8", "application/json"]
//            // "input_data" can be an ASCII SCNL list or a JSON for hypo2000
//            //------------------------------------------------------------------
//            OkHttpClient client = new OkHttpClient();
//            
//            client.setConnectTimeout(60, TimeUnit.SECONDS); // connection timeout
//            client.setReadTimeout(60, TimeUnit.SECONDS);    // socket timeout
//                       
//            //MediaType TXT = MediaType.parse("text/plain; charset=utf-8");
//            MediaType MT = MediaType.parse(media_type);
//            RequestBody req_body = RequestBody.create(MT, input_data);
//
//            Request request = new Request.Builder()
//                .url(basepath + in_depth_path)
//                .post(req_body)
//                .build();
//            
//            Logger.getLogger("org.ingv.pickfx").log(Level.INFO, request.url().toString());
//
//            Response response = client.newCall(request).execute();
//            //
//            if (!response.isSuccessful()) {
//                
//                String s = new String(response.body().bytes());
//               
//                JsonObject convertedRespose = new Gson().fromJson(s, JsonObject.class);
//                JsonObject errori= (JsonObject)convertedRespose.get("errors");
//                if (errori.size()>0) {
//                    //String msg="";
//                    for (int idx=0; idx < errori.size(); idx++){
//                        //errori.keySet()
//                        out_error_message.append(errori.get((errori.keySet().toArray()[idx]).toString()) + "\n");
//                    }
//                    
//                    //System.out.println(msg);
//                }
//                Logger.getLogger("org.ingv.pickfx").log(Level.SEVERE, "Unexpected code " + response);
//                throw new IOException("Unexpected code " + response);
//            }
////       
//            return response;
//            
//            } catch (Exception ex) {
//                return null;
//            }
//        }
//------------------------------------------------------------------------------
//    public Response makePUT(String basepath, String in_depth_path, String input_data, String media_type){
//        try {
//            //------------------------------------------------------------------
//            // "media_type" in ["text/plain; charset=utf-8", "application/json"]
//            // "input_data" can be an ASCII SCNL list or a JSON for hypo2000
//            //------------------------------------------------------------------
//            OkHttpClient client = new OkHttpClient();
//            
//            client.setConnectTimeout(60, TimeUnit.SECONDS); // connection timeout
//            client.setReadTimeout(60, TimeUnit.SECONDS);    // socket timeout
//                       
//            //MediaType TXT = MediaType.parse("text/plain; charset=utf-8");
//            MediaType MT = MediaType.parse(media_type);
//            RequestBody req_body = RequestBody.create(MT, input_data);
//
//            Request request = new Request.Builder()
//                .url(basepath + in_depth_path)
//                .put(req_body)
//                .build();
//
//            Response response = client.newCall(request).execute();
////
//            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
////
//            return response;          
//            } catch (Exception ex) {
//                return null;
//            }
//        }    
}
