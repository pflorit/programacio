import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class PRU04E05Parking_Pablo_Florit {
	public int places_no_discapacitats;
	public int places_discapacitats;
	public BufferedReader br;
	public BufferedWriter bw;
	public String path;
	public String pathEscritura;
	//constructor principal
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
			br= new BufferedReader (new FileReader (path));
		}
		catch (FileNotFoundException e) {
			System.out.println("No trobat");
		}
	}
	/**
	 * metode per entrar un cotxe donada una matricula
	 * @param matricula
	 * @return llocs que queden disponibles
	 */
	public int entraCotxe(String matricula){
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
		}return places_no_discapacitats;
	}
}