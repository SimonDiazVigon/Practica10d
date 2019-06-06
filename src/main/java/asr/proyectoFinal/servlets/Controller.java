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

import asr.proyectoFinal.dao.CloudantPalabraStore;
import asr.proyectoFinal.dominio.Palabra;
import asr.proyectoFinal.services.Traductor;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = {"/listar", "/insertar"})
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta charset=\"UTF-8\"></head><body>");
		
		CloudantPalabraStore store = new CloudantPalabraStore();
		System.out.println(request.getServletPath());
		switch(request.getServletPath())
		{
			case "/listar":
				if(store.getDB() == null)
					  out.println("No hay DB");
				else
					out.println("Palabras en la BD Cloudant:<br />" + store.getAll());
				break;
				
			case "/insertar":
				Palabra palabra1 = new Palabra();
				String nombre = request.getParameter("nombre");
				String animales = request.getParameter("animales");

				if(nombre==null)
				{
					out.println("usage: /insertar?nombre=nombre");
				}
				else
				{
					if(animales==null)
					{
						animales="Diez Vacas y Cuatro Caballos";
					}
					if(store.getDB() == null) 
					{
						out.println("Error en la base de datos");
					}
					else
					{
						
						try {
						palabra1.setName(nombre);
						store.persist(palabra1);
					    out.println("Informaci&oacute;n guardada correctamente, <br><a href=\"listar\">mostrar favoritos</a> <br> ");		
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
