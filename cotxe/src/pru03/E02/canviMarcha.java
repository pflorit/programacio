package pru03.E02;

public class canviMarcha extends PRU03E02Cotxe_Pablo_Florit implements InterfaceCotxe{

	public canviMarcha(String marca, String model, TipusCanvi tipuscanvi) {
		super(marca, model, tipuscanvi);
		this.marca=marca;
		this.model=model;
		this.tipuscanvi=tipuscanvi;
	}
	//metodos
	public enum MarxesAutomatiques {
		F,N,R;
	}
	public enum MarxesManuals{
		PRIMERA,SEGONA,TERCERA,QUARTA,CINQUENA,SISENA,R;
	}
	@Override
	public void pujarMarxa() throws Exception {
		if (motor.equals(EstatsMotorCotxe.EnMarxa)){
			 if(tipuscanvi.equals(TipusCanvi.CanviAutomatic)) {
				 MarxesAutomatiques marxa=MarxesAutomatiques.N;
				 switch (marxa){
				 case F:
					 marxa=MarxesAutomatiques.N;
					System.out.println("Ara vas en \"N\"");
					 break;
				 case N:
					 marxa=MarxesAutomatiques.R;
					System.out.println("Ara vas en \"R\"");
					 break;
				 case R:
					 System.out.println("Vas en \"R\", no pots pujar mes");
				 }
			 }
			 else if(tipuscanvi.equals(TipusCanvi.CanviManual)){
					MarxesManuals marxa=MarxesManuals.PRIMERA;
				 switch (marxa){
				 case R:
					 marxa=MarxesManuals.PRIMERA;
					System.out.println("Ara vas en \"PRIMERA\"");
					 break;
				 case PRIMERA:
					 marxa=MarxesManuals.SEGONA;
					System.out.println("Ara vas en \"SEGONA\"");
					 break;
				 case SEGONA:
					 marxa=MarxesManuals.TERCERA;
					System.out.println("Ara vas en \"TERCERA\"");
					 break; 
				 case TERCERA:
					marxa=MarxesManuals.QUARTA;
					System.out.println("Ara vas en \"QUARTA\"");
					 break;
				 case QUARTA:
					 marxa=MarxesManuals.CINQUENA;
					System.out.println("Ara vas en \"CINQUENA\"");
					 break; 
				 case CINQUENA:
					 marxa=MarxesManuals.SISENA;
					System.out.println("Ara vas en \"SISENA\"");
					 break;
				 case SISENA:
					System.out.println("Ja vas en \"SISENA\", no pots pujar mes");
					 break;
				 }
			 }
		else {
			throw new Exception ("Error controlat==> No pots canviar marxa amb el cotxe aturat ");
		} 
	}
	}
	@Override
	public void devallarMarxa() throws Exception {

					if (motor.equals(EstatsMotorCotxe.EnMarxa)){
						 if(tipuscanvi.equals(TipusCanvi.CanviAutomatic)) {
							 MarxesAutomatiques marxa=MarxesAutomatiques.N;
							 switch (marxa){
							 case R:
								 marxa=MarxesAutomatiques.N;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case N:
								 marxa=MarxesAutomatiques.F;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case F:
								 System.out.println("Vas en "+marxa+" , no pots devallar mes");
							 }
						 }
						 else if(tipuscanvi.equals(TipusCanvi.CanviManual)){
							 MarxesManuals marxa=MarxesManuals.PRIMERA;
							 switch (marxa){
							 case SISENA:
								 marxa=MarxesManuals.CINQUENA;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case CINQUENA:
								 marxa=MarxesManuals.QUARTA;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case QUARTA:
								 marxa=MarxesManuals.TERCERA;
								System.out.println("Ara vas en "+marxa);
								 break; 
							 case TERCERA:
								marxa=MarxesManuals.SEGONA;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case SEGONA:
								 marxa=MarxesManuals.PRIMERA;
								System.out.println("Ara vas en "+marxa);
								 break; 
							 case PRIMERA:
								 marxa=MarxesManuals.R;
								System.out.println("Ara vas en "+marxa);
								 break;
							 case R:
								System.out.println("Ja vas en "+marxa+" , no pots devallar mes");
								 break;
							 }

						 }
					else {
						throw new Exception ("Error controlat==> No pots canviar marxa amb el cotxe aturat ");
					} 
					
				}
	}
		
}