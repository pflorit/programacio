import java.util.Scanner;
public class PREX1AVAEX01_pablo_florit {
	//atributos:
	private static String nom;
	private static int edat;
	private static int categoria;
	private static int antiguitat;
	
	public static final int CAT_EMPLEAT=0;
	public static final int CAT_ENCARREGAT=1;
	public static final int CAT_DIRECTIU=2;
	public static final int ANT_JUNIOR=0;
	public static final int ANT_SENIOR=1;
	public static final int ANT_EXPERT=2;
	//constructores
	public PREX1AVAEX01_pablo_florit(String nom, int edat, int categoria, int antiguitat) {	
		Scanner sc=new Scanner(System.in);
		//nombre
		System.out.println("Insterta el nombre del empleado ");
		nom=sc.nextLine();
		PREX1AVAEX01_pablo_florit.nom = nom;
		//edad
		System.out.println("Insterta la edad del empleado: ");
		edat=sc.nextInt();
		PREX1AVAEX01_pablo_florit.edat = edat;
		//categoria
		System.out.println("Insterta la categoria: Empleado(1), Encargado(2) o Directivo(3): ");
		categoria=sc.nextInt();
		//comprovacion de validez
		if (categoria>2 || categoria<0) {
			System.out.println("IllegalArgumentException");
		}
		else {
			PREX1AVAEX01_pablo_florit.categoria = categoria;
		//antiguedad
		System.out.println("Insterta la antigüedad: Junior(1), Senior(2) o Expert(3): ");
		categoria=sc.nextInt();
		//comprobacion de validez
		}
		if (antiguitat>2 || antiguitat<0) {
			System.out.println("IllegalArgumentException");
		}
		else {
			PREX1AVAEX01_pablo_florit.antiguitat = antiguitat;
		}
	}
	//getters setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		PREX1AVAEX01_pablo_florit.nom = nom;
	}

	public int getEdat() {
		return edat;
	}

	public void setEdat(int edat) {
		PREX1AVAEX01_pablo_florit.edat = edat;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		PREX1AVAEX01_pablo_florit.categoria = categoria;
	}

	public int getAntiguitat() {
		return antiguitat;
	}

	public void setAntiguitat(int antiguitat) {
		PREX1AVAEX01_pablo_florit.antiguitat = antiguitat;
	}

	public static int getCatEmpleat() {
		return CAT_EMPLEAT;
	}

	public static int getCatEncarregat() {
		return CAT_ENCARREGAT;
	}

	public static int getCatDirectiu() {
		return CAT_DIRECTIU;
	}

	public static int getAntJunior() {
		return ANT_JUNIOR;
	}

	public static int getAntSenior() {
		return ANT_SENIOR;
	}

	public static int getAntExpert() {
		return ANT_EXPERT;
	}
	//calculo del sueldo
	public double calculateSou() {
		double sou_final=900;
		//sueldo con categoria
		if (categoria==0){
			sou_final=sou_final*0.15;
		}
		else if (categoria==1) {
			sou_final=sou_final*0.35;
		}
		else if (categoria==2) {
			sou_final=sou_final*0.60;
		}
		
		//sueldo con antigüedad
		if (antiguitat==0){
			sou_final=sou_final+150;
		}
		else if (antiguitat==1) {
			sou_final=sou_final+300;
		}
		else if (antiguitat==2) {
			sou_final=sou_final+600;
		}
		return sou_final;
	}	

}

