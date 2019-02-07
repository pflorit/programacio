public class test {
	public static void main(String[] args) {
		PRU04E05Parking_Pablo_Florit a = new PRU04E05Parking_Pablo_Florit(100,5);
		for (int i = 0; i <110; i++) {
			a.entraCotxe("4858DDB");
			System.out.println("Queden "+a.places_no_discapacitats +" places de no discapacitats");
		}
	}
}
