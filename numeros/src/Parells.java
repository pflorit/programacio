import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public class Parells {
	public static void main(String[] args) {
		String a ;
		try {
			BufferedWriter bw = new BufferedWriter (new FileWriter("C:\\Temp\\parells.dat"));
			String nl =System.getProperty("line.separator");
			for (int i=0; i<=500;i=i+2) {
				//pasamos el valor de i a string, lo guardamos en a y lo escribimos en el fichero
				a = String.valueOf(i);
				bw.write(a+nl);
			}
			bw.close();
		}//posibles fallos	
		catch (FileNotFoundException e) {
			System.out.println("No trobat");
		}
		catch (IOException e) {
		System.out.println("No es pot obrir");
		}	
	}
}