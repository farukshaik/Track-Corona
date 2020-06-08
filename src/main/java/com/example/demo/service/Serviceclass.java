package com.example.demo.service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import com.example.demo.model.Modelclass;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class Serviceclass {

	public Modelclass getData(String stateName) throws Exception {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet getReq = new HttpGet("https://api.covid19india.org/data.json");
		HttpResponse response = httpClient.execute(getReq);
		
		InputStream ipStream = response.getEntity().getContent();
		
		String data = IOUtils.toString(ipStream, "UTF-8");
		
		JsonParser js = new JsonParser();
		JsonObject obj = js.parse(data).getAsJsonObject();
		
		Modelclass[] arr = new Gson().fromJson(obj.get("statewise").getAsJsonArray().toString(), Modelclass[].class);
		
		Map<String, Modelclass> map = Arrays.asList(arr).stream().collect(Collectors.toMap(Modelclass::getState, e->e));
		return map.get(stateName);
	}

}
