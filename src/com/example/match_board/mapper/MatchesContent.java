package com.example.match_board.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class MatchesContent {
	public static List<MatchItem> ITEMS = new ArrayList<MatchItem>();
	public static Map<String, MatchItem> ITEM_MAP = new HashMap<String, MatchItem>();
	public static void addItem(MatchItem item) {
		ITEMS.add(item);
		ITEM_MAP.put(item.id, item);
	}
	public static void getAllMatches(Context context){
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = "http://board-test.herokuapp.com/matches.json";
		JsonArrayRequest getRequest = new JsonArrayRequest(url,getResponseListener, getErrorListener);
		queue.add(getRequest);
	}
	private static final Listener<JSONArray> getResponseListener = new Response.Listener<JSONArray>(){
		@Override
		public void onResponse(JSONArray response) {
			String stringObject = response.toString();
			Gson parser = new Gson();
			MatchItem[] items = parser.fromJson(stringObject, MatchItem[].class );
			for(MatchItem item : items){
				addItem(item);
			}
			Log.w("JSON", items.toString());
		}
	};
	private static final Response.ErrorListener getErrorListener = new Response.ErrorListener() {
		@Override
		public void onErrorResponse(VolleyError error) {
			VolleyLog.e("Error: ", error.getMessage());			
		}
	};
	private static Response.Listener<String> createPostRequestListener(){
		Response.Listener<String> listener = new Response.Listener<String>(){
			@Override
			public void onResponse(String response) {
				// TODO Auto-generated method stub
				
			}
		};
		return listener;
	}
	private static Response.ErrorListener createPostErrorListener(){
		Response.ErrorListener listener = new Response.ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error) {
				// TODO Auto-generated method stub
				
			}
		};
		return listener;
	}
	public static void postMatch(Context context,Map<String, String> params){
		RequestQueue queue = Volley.newRequestQueue(context);
		String url = "http://board-test.herokuapp.com/matches.json";
		StringRequest postRequest = new StringRequest(Request.Method.POST, url,createPostRequestListener(),createPostErrorListener()){
			@Override
		    protected Map<String, String> getParams() 
		    {  
		            Map<String, String>  params = new HashMap<String, String>();  
		            params.put("name", "Alif");  
		            params.put("domain", "http://itsalif.info");
		             
		            return params;  
		    }
		};
		queue.add(postRequest);
	}
	public static List<String> stringMatches() {
		List<String> stringMatches = new ArrayList<String>();
		for(MatchItem item : ITEMS){
			stringMatches.add(item.toString());
		}
		return stringMatches;
	}
	public static class MatchItem {
		public String toString(){
			return matchLabel();
		}
		@SerializedName("id")
		public String id;
		@SerializedName("team_a")
		public String teamA;
		@SerializedName("team_b")
		public String teamB;
		@SerializedName("score_a")
		public int scoreA;
		@SerializedName("score_b")
		public int scoreB;
		@SerializedName("result")
		public String resultMessage;
		public MatchItem(){
			this.teamA = "Team A";
			this.teamB = "Team B";
			this.scoreA = 0;
			this.scoreB = 0;
			this.resultMessage = "";
		}
		public MatchItem(String teamA, String teamB, int scoreA, int scoreB, String resultMessage) {
			this.teamA = teamA;
			this.teamB = teamB;
			this.scoreA = scoreA;
			this.scoreB = scoreB;
			this.resultMessage = resultMessage;
		}
		public String matchLabel(){
			return teamA + " VS " + teamB; 
		}
	}
}
