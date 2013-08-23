package com.dnstechpack.installer.util;

import java.io.FileReader;
import java.io.IOException;

import com.google.gson.stream.JsonReader;

public class JsonReaderTemp {

	public static void main(String[] args) throws IOException {

		JsonReader jsonReader = new JsonReader(new FileReader(InstallerUtils.mcDefault + "/launcher_profiles.json"));

		jsonReader.beginObject();

		while (jsonReader.hasNext()) {

			String name = jsonReader.nextName();
			if (name.equals("profiles")) {
				readApp(jsonReader);
			}
		}

		jsonReader.endObject();
		jsonReader.close();

	}

	public static void readApp(JsonReader jsonReader) throws IOException {
		jsonReader.beginObject();
		
		while (jsonReader.hasNext()) {
			String name = jsonReader.nextName();
			System.out.println(name);
		}
		
		jsonReader.endObject();		
	}
}