 	import java.io.BufferedReader;
	import java.io.BufferedWriter;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Collections;
	public class PRU04E01_1 {
		public static void main(String[] args) {
			String paraula="";
			try {
				String nl =System.getProperty("line.separator");
				BufferedWriter bw = new BufferedWriter (new FileWriter("C:\\Temp\\"+args[0]+"_sort.txt"));
				BufferedReader br = new BufferedReader (new FileReader ("C:\\Temp\\"+args[0]+".txt"));
				ArrayList<String> paraules=new ArrayList<String>();
				
				while (paraula!=null) {
					paraula=br.readLine();
					if(paraula!=null) {
						paraules.add(paraula);
						System.out.println(paraula);
						}
					}
				Collections.sort(paraules);
				for (int i=0;i<paraules.size();i++) {
					bw.write(paraules.get(i)+nl);
				}
				
				//cerramos los ficheros
				bw.close();
				br.close();
			}
			//posibles fallos
			catch (FileNotFoundException e) {
				System.out.println("No trobat");
			}
			catch (IOException e) {
			System.out.println("No es pot obrir");
			}	
		}
	}
