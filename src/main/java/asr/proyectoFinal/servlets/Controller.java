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
					out.println("Ganaderos en la base de datos:<br />" + store.getAll());
				break;
				
			case "/insertar":
				Palabra palabra1 = new Palabra();
				Palabra palabra2 = new Palabra();
				Palabra palabra3 = new Palabra();

				String nombre = request.getParameter("nombre");
				String vacas = request.getParameter("vacas");
				String caballos = request.getParameter("caballos");

				
				if(nombre==null)
				{
					out.println("usage: /insertar?nombre=nombre");
				}
				else
				{
					if(vacas==null)
					{
						vacas="Cero";
					}
					if(caballos==null)
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
						palabra1.setName(nombre);
						store.persist(palabra1);
						palabra2.setName(vacas);
						store.persist(palabra2);
						caballos = Traductor.translate(caballos, "es", "en", false);
						palabra3.setName(caballos);
						store.persist(palabra3);
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
