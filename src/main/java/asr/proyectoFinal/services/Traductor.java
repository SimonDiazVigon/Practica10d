package asr.proyectoFinal.services;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v3.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.developer_cloud.language_translator.v3.model.TranslationResult;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

public class Traductor
{
	public static String translate(String palabra, String sourceModel, String destModel, boolean conversational)
	{
		String model;
		if(sourceModel.equals("en") || destModel.equals("en"))
		{
			model=sourceModel+"-"+destModel;
			if(conversational) 
				model+="-conversational";
		}
		else
			return translate(translate(palabra,sourceModel,"en",conversational),"en",destModel,conversational); //translate to english, then to dest

		LanguageTranslator languageTranslator = new LanguageTranslator("2019-04-09");
		languageTranslator.setUsernameAndPassword("22f90372-0c71-403b-9fc9-df11e8317f6f","RJ1ZNDGS7ERkHgbFjm9FaiAMbUGed_jnPy8JroEwbJxx");

		languageTranslator.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		
		TranslateOptions translateOptions = new
		TranslateOptions.Builder()
		 .addText(palabra)
		 .modelId(model)
		 .build();

		TranslationResult translationResult = languageTranslator.translate(translateOptions).execute();
		
		System.out.println(translationResult);

		String traduccionJSON = translationResult.toString();
		JsonParser parser = new JsonParser();
		JsonObject rootObj =
		parser.parse(traduccionJSON).getAsJsonObject();
		JsonArray traducciones = rootObj.getAsJsonArray("translations");
		String traduccionPrimera = palabra;
		if(traducciones.size()>0)
		traduccionPrimera =
		traducciones.get(0).getAsJsonObject().get("translation").getAsString();
		return traduccionPrimera;
	}
}