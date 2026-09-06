package com.springboot.mySpringBootCRUDapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import com.springboot.mySpringBootCRUDapp.entity.OrderItem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/eCommerceApi/productsRatedStates")
public class UserDataController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Client client;
    public UserDataController(Client client) {
        this.client = client;
    }
    
    @PostMapping("/getStateById")
    public Boolean getHoldings(@RequestBody Integer productId, Principal principal) {
        User user = client.getUser(principal.getName());

        ArrayList<Integer> productsRatedStatesFromOkta = (ArrayList<Integer>) user.getProfile().get("productsRatedStates");

        if (productsRatedStatesFromOkta != null) {
            try {
            	for (Integer productRatedState : productsRatedStatesFromOkta) {
	            	if(productRatedState==productId) {
	            		return true;
	            	}
	            }
            } catch (Exception e) {
                logger.error("Error marshalling Okta custom data: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return false;
    }
    
    @PostMapping("/addProductState")
    public Boolean saveHoldings(@RequestBody Integer productId, Principal principal) {
    	User user = client.getUser(principal.getName());
        ArrayList<Integer> productsRatedStatesFromOkta = (ArrayList<Integer>) user.getProfile().get("productsRatedStates");
        if (productsRatedStatesFromOkta != null) {
	        try {
	        	for (Integer productRatedState : productsRatedStatesFromOkta) {
	            	if(productRatedState==productId) {
	            		return false;
	            	}
	            }
	        	productsRatedStatesFromOkta.add(productId);
	            user.getProfile().put("productsRatedStates", productsRatedStatesFromOkta);
	            user.update();
	        } catch (Exception e) {
	            logger.error("Error saving Okta custom data: " + e.getMessage());
	            e.printStackTrace();
	        }
        }
        return true;
    }
}