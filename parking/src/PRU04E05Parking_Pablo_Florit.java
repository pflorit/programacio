import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PRU04E05Parking_Pablo_Florit {
	public int places_no_discapacitats;
	public int places_discapacitats;
	public BufferedReader br;
	public BufferedWriter bw;
	public String path;
	public String pathEscritura;
	public String formatMatricula = "\\[0-9]{4}[A-Z]{3}";
	public boolean queden_normals=true;
	/**
	 * 
	 * @param places_no_discapacitats
	 * @param places_discapacitats
	 */
	public PRU04E05Parking_Pablo_Florit (int places_no_discapacitats, int places_discapacitats) {
		this.places_no_discapacitats=places_no_discapacitats;
		this.places_discapacitats=places_discapacitats;
	}
	/**
	 * metode per llegir matricules desde un fitxer
	 * @param path: ruta a on es troba el fitxer
	 * @throws Exception si no troba el fitxer
	 */
	public void llegirMatricules(String path) throws Exception{
		try {
			String matricula;
			br= new BufferedReader (new FileReader (path));
			matricula=br.readLine();
			while (matricula != null && queden_normals == true) {
				if (places_no_discapacitats == 1) {
					if (matricula.matches("[0-9]{4}[A-Z]{3}")) {
						System.out.println(matricula + " aparcat");
						System.out.println("Aquest era el darrer lloc,ja no poden entrar mes cotxes.");
						places_no_discapacitats=places_no_discapacitats-1;
						queden_normals = false;
					}else {
						System.out.println("La matricula "+matricula+" no compleix amb el patro pertinent \"AAAA111\"");
						matricula=br.readLine();
					}
				}else {
					if (matricula.matches("[0-9]{4}[A-Z]{3}")) {
						System.out.println(matricula+ " aparcat");
						places_no_discapacitats=places_no_discapacitats-1;
						matricula=br.readLine();
					}else {
						System.out.println("La matricula "+matricula+" no compleix amb el patro pertinent \"AAAA111\"");
						matricula=br.readLine();
					}
				}
			}br.close();
		}
		//possibles errors
		catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
		catch (IOException e) {
			System.out.println("can not be opened");
		}
	}
	/**
	 * metode per entrar un cotxe de no minusvalids donada una matricula
	 * @param matricula
	 * @return llocs que queden disponibles
	 */
	public int entraCotxe(String matricula){
		//primer es mira que la matricula compleixi amb el patro
		if (matricula.matches("[0-9]{4}[A-Z]{3}")) {
			//si no queden llocs ho diu y no permet entrar
			if (places_no_discapacitats == 0) {
				System.out.println("No poden entrar mes cotxes, no hi ha lloc.");
			}else {
				//si queden permet aparcar el cotxe
				int colar = (int) (Math.random()* 5)+1;
				switch (colar) {
				//de forma aleatoria un garrulo intentara aparcar a minusvalids, pero si no hi ha lloc aparacara a una placa normal
				case 1:
					if (places_discapacitats == 0) {
						System.out.println("Un garrulo volia aparcar a minusvalids, pero no ha trobat lloc.");
					}else {
						System.out.println("Un garrulo ha aparcat a minusvalids...");
						places_discapacitats = places_discapacitats - 1;
					}
					break;
				default:
					System.out.println("Cotche aparcat.");
					places_no_discapacitats = places_no_discapacitats - 1;
				}
			}
		}else{
			System.out.println("La matricula que has introduit no compleix amb el patro pertinent \"AAAA111\"");
		}return places_no_discapacitats;
	}
	/**
	 * metode per entrar un cotxe de minusvalids donada una matricula
	 * @param matricula
	 * @return llocs que queden disponibles
	 */
	public int entraCotxeDiscapacitat(String matricula){
		//primer es mira que la matricula compleixi amb el patro
		if (matricula.matches("[0-9]{4}[A-Z]{3}")) {
			//si no queden llocs ho diu y no permet entrar
			if (places_discapacitats == 0 && places_no_discapacitats == 0) {
				System.out.println("No poden entrar mes cotxes, no hi ha lloc.");
			}else if (places_discapacitats == 0) {
				//si queden, pero no de minusvalids, permet aparcar el cotxe
				System.out.println("No queden places de minusvalids, pero pot aparcar a un altre lloc.");
				System.out.println("Cotche aparcat.");
				places_no_discapacitats = places_no_discapacitats - 1;
			}else {
				//si queden de minusvalids pot aparcar
				System.out.println("Cotche aparcat.");
				places_discapacitats = places_discapacitats - 1;
			}
		}
		else{
			System.out.println("La matricula que has introduit no compleix amb el patro pertinent \"AAAA111\"");
		}return places_discapacitats;
	}
}