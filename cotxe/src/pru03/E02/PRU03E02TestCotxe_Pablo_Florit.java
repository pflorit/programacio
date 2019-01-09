package pru03.E02;
import java.util.Scanner;
public class PRU03E02TestCotxe_Pablo_Florit {
	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		String marca;
		String model;
		int opc;
		TipusCanvi tipuscanvi=TipusCanvi.CanviAutomatic;
		//primer demanam al usuari per la marca, model i tipus de canvi del cotxe
		System.out.print("Insereix la marca del cotxe: ");
			marca=sc.nextLine();
		System.out.print("Insereix el model del cotxe: ");
			model=sc.nextLine();
		/**nomes agafara el tipus de canvi si inserim 1 o 2, auomatic o manual succesivament
		 * si inserim altra cosa tornara a demanar
		 */
			do {
		System.out.print("Insereix el tipus de canvi ([1]automatic o [2]manual): ");
			opc=sc.nextInt();
			switch (opc){
			case 1:
				tipuscanvi=TipusCanvi.CanviAutomatic;
			break;
			case 2:
				tipuscanvi=TipusCanvi.CanviManual;
			break;
			default:
				System.out.println("Insereix 1 per automatic o 2 per manual: ");
			}
			} while (opc<1||opc>2);
			//cream el cotxe amb la informacio que tenim
		PRU03E02Cotxe_Pablo_Florit A = new PRU03E02Cotxe_Pablo_Florit(marca, model, tipuscanvi);
			//menu amb les diferents opcions a elegir, fins que no premi [7], sortir, el rpograma seguira funcionant.
			do {
			System.out.println("Que vols fer ara?");
			System.out.println("[1] arrancar motor, [2] comprovar motor, [3] veure les revolucions, [4] aturar el motor, [5] pujar marxa, [6] devallar marxa o [7] sortir: ");
			opc=sc.nextInt();
			switch (opc){
			case 1:
				A.arrancarMotor();
			break;
			case 2:
				System.out.println("El motor es troba "+ A.comprovaMotor());
			break;
			case 3:
				System.out.println("Les revoluciions son "+ A.getRevolucions());
			break;
			case 4:
				A.aturarMotor();
			break;
			case 5:
				A.pujarMarxa();
			break;
			case 6:
				A.devallarMarxa();
			break;
			case 7:
				System.out.println("ADEU!");
			break;
			default:
				System.out.println("Per favor, revisa les opcions.");
			}
			}while (opc!=7);
	}

}
