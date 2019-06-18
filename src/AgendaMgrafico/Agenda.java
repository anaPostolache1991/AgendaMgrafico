package AgendaMgrafico;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

public class Agenda {
	 static SortedMap<String,String>agenda=new TreeMap<>();
	public String exec(String cmd) throws IOException {
		String result=" ";
		
		   
	    String nombre=" ";
	    String token=" ";
	   String ruta=" ";
	    String telefono=" ";
	    
	    boolean fin=false;
	  
	    	System.out.println(">");
	    	
	    	Scanner sc = new Scanner(cmd);
	    	//System.out.println(token);
	    	
	    	int estado=0;
	    	while(estado!=8) {
	    		switch(estado) {
	    		case 0:
					try {
						token = sc.skip("fin|buscar:|borrar:|guardar:|cargar:|\\p{L}+(\\s+\\p{L}+)*").match().group();
						if (token.equals("fin")) {
							estado = 8;
							fin = true;
						}
						else if (token.equals("buscar:"))
							estado = 4;
						else if (token.equals("borrar:"))
							estado = 3;
						else if (token.equals("guardar:"))
							estado = 2;
						else if (token.equals("cargar:"))
							estado = 7;
						else {
							nombre = token;
							estado = 1;
						}
					} catch (NoSuchElementException e) {
						result="Se esperaba 'buscar: o guardar: o borrar: o cargar:' o 'fin' o un nombre";
						estado = 8;
					}
					break;
				case 1:
					try {
						sc.skip("-");
						estado = 5;
					}catch (NoSuchElementException e) {
						result="Se esperaba '-'";
						estado = 8;
					}
					break;
				case 2:
				
					try {
					//C:/Users/Ana/prueba/agenda
					
						
						ruta=sc.skip("C:/\\w+/\\w+/\\w+/\\w+").match().group();
						System.out.println(ruta);
						 escribir(ruta);
						 result="Se han guardado los datos correctamente";
						estado = 8;
					}catch (NoSuchElementException e) {
						result="Se esperaba 'ruta'";
						estado = 8;
					}
					
					
	             break;
				case 3:
					try {
						nombre=sc.skip("\\p{L}+").match().group();
						if(agenda.containsKey(nombre)) {
							agenda.remove(nombre);
							result="se borro";
						}else {
							result="no existe";
						}
						estado = 8;
					}catch (NoSuchElementException e) {
						result="Se esperaba 'nombre borar'";
						estado = 8;
					}
	             break;
				case 4:
					
					try {
						nombre=sc.skip("\\p{L}+").match().group();
					    //telefono=agenda.get(token);
						
						if(agenda.containsKey(nombre)) {//si contiene el nombre 
							telefono=agenda.get(nombre);
							result=nombre+"->"+telefono;//nos da el telefono
						}else {
							result="no esta en la agenda "+nombre;//no esta en la agenda
						}
						
						
						
						estado=8;
					}catch (NoSuchElementException e) {
						result="Se esperaba 'nombre'";
						estado = 8;
					}
	             break;
				case 5:
					try {
					 telefono=sc.skip("\\d+").match().group();
					 if(!agenda.containsKey(nombre)) {
						 agenda.put(nombre, telefono);
						 result="se inserto "+nombre+"->"+telefono;
						 
					 }else {
						 agenda.put(nombre, telefono);
						 result="se actualizo "+nombre+"->"+telefono;
					 }
					 
					estado=8;
					}catch (NoSuchElementException e) {
						result="Se esperaba 'telefono'";
						estado = 8;
					}
	             break;
				case 7:
					
						//C:/Users/Ana/prueba/agenda
						
							try {
							ruta=sc.skip("C:/\\w+/\\w+/\\w+/\\w+").match().group();
							System.out.println(ruta);
							 leer(ruta);
							 estado=8;
							}catch (NoSuchElementException e) {
								result="Se esperaba ruta";
								estado = 8;
							}
						
					break;
	    		}
	    		
	    		
	    		}
	    	
	    
	    
		
		return result;
	}

 void escribir(String ruta) {
		// TODO Auto-generated method stub
		FileWriter fw;
		try {
			fw = new FileWriter(ruta);
			BufferedWriter bw= new BufferedWriter(fw);
			PrintWriter pw=new PrintWriter(bw);
			
			Set<String> conjuntoClaves=agenda.keySet();
			Iterator<String> it=conjuntoClaves.iterator();
			
			while (it.hasNext()) {
				String clave=(String) it.next();
				String tf= agenda.get(clave);
				pw.println(clave+"-"+tf);
			}
			pw.close();
		} catch (IOException e) {
			System.out.println("Error al buscar el fichero para escribir");
		}
		
	}

	void leer(String ruta) {
		// TODO Auto-generated method stub
		File f=new File(ruta);
		try {
			FileReader fr=new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			String linea;
			while ((linea=br.readLine())!=null) {
				String [] datos=linea.split("-");
				agenda.put(datos[0], datos[1]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error al leer el fichero");
		} catch (IOException e) {
			System.out.println("No existe el fichero, inserte datos en la agenda y guárdelos");
		} 
		
	}
}
