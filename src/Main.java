import com.paralleldots.paralleldots.App;
import org.json.simple.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.util.*;

public class Main {
	static JSONParser parser = new JSONParser();
	private static String key = "sWFSPYcy1W4lt9jLblN8FnHNYKzUC5gbPQvIH6UyeUw";
	static App pd = new App(key);
	static String loc = "data/1.jpg";
	static File img = new File("data/1.jpg");
	static String loc2 = "data/2.jpg";
	static String loc3 = "data/3.jpg";
	
	public static void main(String[] args) {
		
		
		// TODO Auto-generated method stub
		System.out.println("Facial Emotion");
		String facial_emotion;
//		String dest = "data/analysis2.txt";
		File plData = new File("data/analysis.txt");
		double currMax = 0;
		try {
			
			/* Read in playlist data*/
			String buff = FileUtils.readFileToString(plData, "UTF-8");
			JSONObject data = (JSONObject) parser.parse(buff);
			JSONArray dataArray = (JSONArray) data.get("emotion");
			JSONObject firstPlaylist = (JSONObject) dataArray.get(0);
			JSONObject playlist;
			String firstP = (String) firstPlaylist.toString();
			System.out.println(firstP);
			
			
			facial_emotion = pd.facial_emotion(loc2);
			//System.out.println(facial_emotion);
			JSONObject obj = (JSONObject) parser.parse(facial_emotion);
			String x = obj.toString();
			
			JSONArray jArray = (JSONArray) obj.get("facial_emotion");
			JSONObject firstEntry; // = (JSONObject) jArray.get(0);
			//JSONObject firstTag = (JSONObject) firstEntry.get("tag");
			
//			String x2 = jArray.toString();
//			String first = firstEntry.toString();
			
			String firstT = ""; // = (String) firstEntry.get("tag");
			
			//int score = (int) Math.round( (Double) firstEntry.get("score"));
			double firstScore = 0; // = (Double) firstEntry.get("score");
			String list = "";
			double difference = 100;
			double smallestDifference = 100;
			double testDifference = 0.1509191075;
			double testHold = 0.0;

			
			for(int j = 0; j < dataArray.size(); j++)
			{
//				list = (String) playlist.toString();
				
				for(int i = 0; i < 7; i++){
					firstEntry = (JSONObject) jArray.get(i);
					firstT = (String) firstEntry.get("tag");
					firstScore = (Double) firstEntry.get("score");
					
					difference = (firstScore - testDifference);
					if(difference < 0)
						difference *= -1;
					
					if((smallestDifference > difference)){
						testHold = firstScore;
						smallestDifference = difference;
						System.out.println("Emotion: " + firstEntry.toString());
						System.out.println("Number that is closest: " + testHold);
						System.out.println("Current smallest difference: " + smallestDifference);
					}
				}
			}
			
			
			//Thinking that the threshold should be lowered to .6-.65
			/*if (firstScore < .75)
			{
				JSONObject secondEntry = (JSONObject) jArray.get(1);
				String secondT = (String) secondEntry.get("tag");
				double secondScore = (Double) secondEntry.get("score");
				System.out.println(x);
				//System.out.println(x2);
				//System.out.println(first);
				System.out.printf("\nThis is the first tag: %s, and this is the first score: %.2f",firstT,firstScore);
				System.out.printf("\nThis is the second tag: %s, and this is the second score: %.2f\n",secondT,secondScore);
				*/


				/* Text Emotion Analysis -
				 Used to send playlist titles to the PD API, returns emotional analysis as a JSON-formatted String 
				 should be commented out during normal use*/
				
				
//				System.out.println("Emotion");
//				FileWriter writer = new FileWriter("data/analysis.txt", true);
//				JSONArray text_list = (JSONArray)parser.parse("[\"The Writer's Playlist\",\"Sin Estrés\",\"Winter Sounds\",\"Boozy Brunch\",\"Country Heartache\",\"Edvard Grieg – Classical Moods: Nostalgia\",\"Soul Coffee\",\"Despierta y Sonríe\"]");
//				String emotion = pd.emotion_batch(text_list);
//				writer.write(emotion);
//				System.out.println(emotion);
//				writer.close();
			/*
			} else {
			System.out.println(x);
			//System.out.println(x2);
			//System.out.println(first);
			System.out.printf("\nThis is the first tag: %s, and this is the first score: %.2f",firstT,firstScore);
			} */
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
