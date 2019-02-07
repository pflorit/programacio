import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Mesclats {
	public static void main(String[] args) {
		//variables para almacenar los datos
		String parell ;
		String senar;
		try {
			//creacion de readers y writers
			BufferedWriter bw = new BufferedWriter (new FileWriter("C:\\Temp\\mesclats.dat"));
			BufferedReader br_senars= new BufferedReader (new FileReader("C:\\Temp\\senars.dat"));
			BufferedReader br_parells= new BufferedReader (new FileReader("C:\\Temp\\parells.dat"));
			//salto de linea
			String nl =System.getProperty("line.separator");
			for (int i=0; i<=500;i=i+2) {
				//vamos leyendo cada linea de cada fichero
				parell = br_parells.readLine();
				senar = br_senars.readLine();
				//mientras haya datos los escribira
				if (parell!=null) {
				bw.write(parell+nl);
				}
				if (senar!=null) {
				bw.write(senar+nl);
				}
			}
			//cerramos los ficheros
			br_senars.close();
			br_parells.close();
			bw.close();
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
