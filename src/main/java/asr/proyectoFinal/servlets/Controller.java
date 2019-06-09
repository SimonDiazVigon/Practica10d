package asr.proyectoFinal.servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.nio.file.Files;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body><center><a href=\"https://asrtomcatejemplocloudantsimon.eu-gb.mybluemix.net/asrTomcatEjemploCloudant\"><img src=\"asemga.png\" ></a><br><br>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Ganaderos en la base de datos:<br />" + store.getAll());
				break;
				
			case "/insertar":
				//Palabra palabra1 = new Palabra();
				//Palabra palabra2 = new Palabra();
				//Palabra palabra3 = new Palabra();
				
				String nombre = request.getParameter("nombre");
				String vacas = request.getParameter("vacas");
				String caballos = request.getParameter("caballos");
				
				if(nombre=="")
				{
					out.println("usage: /insertar?nombre=nombre");
				}
				else
				{
					if(vacas=="")
					{
						vacas="Cero";
					}
					if(caballos=="")
					{
						caballos="Cero";
					}
					if(store.getDB() == null) 
					{
						out.println("Error en la base de datos");
					}
					else
					{
						
						try {
						//palabra1.setName(nombre);
						//store.persist(palabra1);
						//palabra2.setName(vacas);
						//store.persist(palabra2);
						//palabra3.setName(caballos);
						//store.persist(palabra3);
							
						vacas = Traductor.translate(vacas, "es", "en", false);
						caballos = Traductor.translate(caballos, "es", "en", false);
						
						JSONObject eljson = new JSONObject();
						eljson.put("NombreG", nombre);
						eljson.put("Nvacas", vacas);
						eljson.put("Ncaballos", caballos);
						
						store.persist(eljson);
						
					    out.println("Informaci&oacute;n guardada correctamente, <br><a href=\"listar\">Mostrar ganaderos</a> <br> ");

						} catch (Exception e) {
							out.println("Error en el codigo de fallo "+e.toString());
						}
					}
				}
				break;
		}
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
