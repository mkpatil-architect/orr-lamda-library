package com.orr.lamda.library;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.HashMap;


public class Hello
        implements RequestHandler<Object, String> {


    @Override
    public String handleRequest(Object input, Context context) {
        context.getLogger().log("Input: " + input);



        HashMap clinicSignModel = new HashMap<>();


        String[] products = new String[3];

        for (int i = 0; i < products.length; i++) {
            products[i] = "test11"+i;
        }


        clinicSignModel.put("userFirstName", "MK");
        clinicSignModel.put("userLastName", "Patil");
        clinicSignModel.put("clinicName", "Impelsys");
        clinicSignModel.put("products", products); //pass the product list


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", clinicSignModel);
        jsonObject.put("emailType", "CLINIC_SIGNUP");

        jsonObject.put("mailTo", "patil.mk@analyticsquad4.com");



    try {
            String utilityServiceUrl = "http://201.182.128.61:123123/api/sendEmail";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");

            //Constructing email request
            HttpEntity<String> emailServiceRequest = new HttpEntity<>(jsonObject.toString(), httpHeaders);

            RestTemplate restTemplate = new RestTemplate();

            String emailServiceResponse = restTemplate.postForObject(utilityServiceUrl, emailServiceRequest, String.class);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return input.toString();
    }

    public String myHandler(Object name, Context context) {

      /*  HashMap clinicSignModel = new HashMap<>();


        String[] products = new String[3];

        for (int i = 0; i < products.length; i++) {
            products[i] = "test11"+i;
        }


        clinicSignModel.put("userFirstName", "MK");
        clinicSignModel.put("userLastName", "Patil");
        clinicSignModel.put("clinicName", "Impelsys");
        clinicSignModel.put("products", products); //pass the product list


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("model", clinicSignModel);
        jsonObject.put("emailType", "CLINIC_SIGNUP");

        jsonObject.put("mailTo", "patil.mk@analyticsquad4.com");




        try {
            String utilityServiceUrl = "http://206.189.128.67:12011/api/sendEmail";

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");

            //Constructing email request
            HttpEntity<String> emailServiceRequest = new HttpEntity<>(jsonObject.toString(), httpHeaders);

            RestTemplate restTemplate = new RestTemplate();

            String emailServiceResponse = restTemplate.postForObject(utilityServiceUrl, emailServiceRequest, String.class);

        } catch (Exception e) {
            e.printStackTrace();

        }*/

        return "testing";
       // return String.format("Hello %s.", name);
    }
}