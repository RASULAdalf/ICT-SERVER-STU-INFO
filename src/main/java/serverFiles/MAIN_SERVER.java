package serverFiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MAIN_SERVER extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count = 0;
	private static ArrayList<String> list_index = new ArrayList<String>();
	private static ArrayList<String> list_phone = new ArrayList<String>();
	private static String checkResultIndex = "";
	private static String checkResultPhone = "";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		checkResultPhone = "";
		checkResultIndex = "";
		
		if(request.getParameter("name")==null || request.getParameter("index")==null || request.getParameter("phone")==null) {
			
			response.getWriter().println("wrong accessing path");
		}else {
		
		String name = request.getParameter("name");
		String index = request.getParameter("index");
		String phone = request.getParameter("phone");
		
		for(String check : list_index) {
			if(check.equals(index)) {
				checkResultIndex ="duplicate_index"; 
			}
		}
		
		for(String check : list_phone) {
			if(check.equals(phone)) {
			checkResultPhone = "duplicate contact";
			}
		}
		
		
		if(checkResultIndex=="" && checkResultPhone=="") {
			count++;
			list_index.add(index);
			list_phone.add(phone);
			
			FileWriter fW = new FileWriter("OUTPUT.txt",true);
			BufferedWriter pr = new BufferedWriter(fW);
			if(count!=1) {
				pr.newLine();
			}
			pr.write(count+". "+name+"   -"+index+"   -"+phone);
			pr.close();
			
			response.getWriter().println("Dear "+name+", Your data has been submitted successfully");
			
			
		
		}else {
				
			response.getWriter().println("duplicate data");
		}
		
	}

}
}
