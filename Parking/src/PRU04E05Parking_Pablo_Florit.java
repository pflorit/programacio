public class PRU04E05Parking_Pablo_Florit {
	public int places_no_discapacitats;
	public int places_discapacitats;

	public PRU04E05Parking_Pablo_Florit (int places_no_discapacitats, int places_discapacitats) {
		this.places_no_discapacitats=places_no_discapacitats;
		this.places_discapacitats=places_discapacitats;


	}
	public int entraCotxe(String matricula){
		int colar = (int) (Math.random()* 5)+1;
		switch (colar) {
		case 1:
			if (places_discapacitats == 0) {
				System.out.println("Un garrulo volia aparcar a minusvalids, pero no ha trobat lloc.");
				if (places_no_discapacitats >0) {
					System.out.println("El garrulo ha aparcat a un altre lloc");
					places_no_discapacitats=places_no_discapacitats-1;
				}else {
					System.out.println("No queda lloc disponible, el garrulo s'en va a fotre a un altra lloc.");
				}
			}else {
				System.out.println("Un garrulo ha aparcat a minusvalids...");
				places_discapacitats = places_discapacitats - 1;
			}
			break;
		default:
			if (places_no_discapacitats == 0) {
				System.out.println("No hi ha lloc per aparcar");
			}else {
				System.out.println("Cotche aparcat.");
				places_no_discapacitats = places_no_discapacitats - 1;
			}
		}return places_no_discapacitats;
	}
}