package com.aconex.FaceRecognition.services;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.net.URI;


@Service
public class FaceIdentity{

    public void identifyFace(String FaceId){

        HttpClient httpclient = HttpClients.createDefault();

        try{

            URIBuilder builder = new URIBuilder("https://westcentralus.api.cognitive.microsoft.com/face/v1.0/identify");

            URI uri = builder.build();
            HttpPost request = new HttpPost(uri);
            request.setHeader("Content-Type", "application/json");
            request.setHeader("Ocp-Apim-Subscription-Key", "a57575674bb44929b470d4e60a4994f0");

            String body = "{\"personGroupId\":\"interns-group-00\",\"faceIds\":[\""+FaceId+"\"],\"maxNumOfCandidatesReturned\":1,\"confidenceThreshold\": \"0.5\"}";

            StringEntity reqEntity = new StringEntity(body);
            request.setEntity(reqEntity);

            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null){
                String RecognizedPersonID= EntityUtils.toString(entity);
                System.out.println(RecognizedPersonID);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}