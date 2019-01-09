package pru03.E02;
public class PRU03E02Cotxe_Pablo_Florit extends CotxeAbstracte implements InterfaceCotxe {
	EstatsMotorCotxe motor = EstatsMotorCotxe.Aturat;
	//constructors per al cotxe
	public PRU03E02Cotxe_Pablo_Florit(String marca, String model, TipusCanvi tipuscanvi) {
		super(marca, model, tipuscanvi);
		this.marca=marca;
		this.model=model;
		this.tipuscanvi=tipuscanvi;
	}
	/**metodes:
	 * arrancar motor: si ja es troba en marxa donara error, si no es posara en maxa
	 * comprovar motor: tornara l'estat actual del motor
	 * getrevoucions: si esta aturant torna 0, si no un random de 1 a 6500
	 * aturar motor: si ja es troba aturat donara error, si no s'aturara
	 */
	@Override
	public void arrancarMotor() throws Exception {
		if (motor.equals(EstatsMotorCotxe.EnMarxa)){
			throw new Exception ("Error controlat==> el cotxe ja esta en marxa.");
		}
		else if (motor.equals(EstatsMotorCotxe.Aturat)){
			System.out.println("Has arrancat el cotxe");
			 motor = EstatsMotorCotxe.EnMarxa;
		}
	}

	@Override
	public EstatsMotorCotxe comprovaMotor() {
			return motor;
	}
	@Override
	public int getRevolucions() {
		int revolucions;
		if (motor.equals(EstatsMotorCotxe.Aturat)) {
			revolucions=0;
			}
			else {
				revolucions=(int)(Math.random()*6500)+1;
				}
		return revolucions;
	}
	@Override
	public void aturarMotor() throws Exception {
		if (motor.equals(EstatsMotorCotxe.Aturat)){
			throw new Exception ("Error controlat==> el cotxe ja esta aturat.");
		}
		else {
			System.out.println("Has aturat el cotxe");
			 motor = EstatsMotorCotxe.Aturat;
		}
	}
	@Override
	public void pujarMarxa() throws Exception {
		
	}
	@Override
	public void devallarMarxa() throws Exception {
		
	}
}