package net.andrew.expressions.random;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.andrew.Main;
import sun.net.www.http.HttpClient;


@SuppressWarnings("unused")
public class ExprHastebin extends SimpleExpression<String>{
	private static Expression<String> text;
	private Integer label;
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		text = (Expression<String>) expr[0];
		label = matchedPattern;
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "[new] hastebin [key|identifier] [for|of] [text|string] %text%";
	}

	@Override
	@Nullable
		protected String[] get(Event e) {
		try{
			String rawData = text.getSingle(e);
			String type = "text/plain";
			String encodedData = URLEncoder.encode(rawData, "UTF-8"); 
			encodedData = encodedData.replaceAll("\n", "\r\n");
			encodedData = encodedData.replaceAll("%0A", "\r\n");
			URL u = new URL("http://hastebin.com/documents");
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty( "Content-Type", type );
			conn.setRequestProperty( "Content-Length", String.valueOf(encodedData.length()));
			OutputStream os = conn.getOutputStream();
			os.write(encodedData.getBytes());
			InputStream in = conn.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder result = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
			    result.append(line);
			}
			String[] split1 = result.toString().split(":");
			String str = split1[1].replace("\"", "");
			str = str.replace("}", "");
			if (label == 1)
				return new String[]{"http://hastebin.com/" + str};
			else{
				return new String[]{str};
			}
		}
		catch (Exception e1){
			
		}
		return null;
		}
}

