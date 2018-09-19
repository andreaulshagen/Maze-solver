import javafx.scene.control.Button;

public abstract class Rute extends Button {

	final int rad, kol;

	final Labyrint labyrint;

	Rute nord, oest, soer, vest;

	boolean besokt = false;



	protected Rute(int rad, int kol, Labyrint labyrint) {

		this.rad = rad;

		this.kol = kol;

		this.labyrint = labyrint;

	}



	public abstract char tilTegn();

	public abstract void gaa(String vei, Rute komFra);



	static Rute lagRute(char tegn, int rad, int kol, Labyrint labyrint) {

		if (tegn == '#') {

			return new SortRute(rad, kol, labyrint);

		} else if (tegn == '.' && rad > 0 && kol > 0 && rad < labyrint.hoyde-1 && kol < labyrint.bredde-1) {

			return new HvitRute(rad, kol, labyrint);

		} else if (tegn == '.') {

			return new Aapning(rad, kol, labyrint);

		} else {

			throw new RuntimeException("Ugyldig tegn funnet: " + tegn);

		}

	}



	public void finnUtvei() {

		gaa("", null);

	}



	@Override

	public String toString() {

		return String.format("(%d, %d)", kol, rad);

	}

}
/*
public abstract class Rute extends Button{
	int rad;
	int kolonne;
	Labyrint l;
	Rute nord;
	Rute vest;
	Rute soer;
	Rute oest;
	Boolean harBesoekt=false;


	public Rute(int rad, int kolonne) {
		this.rad=rad;
		this.kolonne=kolonne;
		this.l=null;
	}
	public void setLabyrint(Labyrint l) {
		this.l=l;
	}

	public abstract char tilTegn();
	public void settNord(Rute n) {
		nord=n;
	}
	public void settOest(Rute o) {
		oest=o;
	}
	public void settSoer(Rute s) {
		soer=s;
	}
	public void settVest(Rute v) {
		vest=v;
	}

	public String hentKoordinater() {
		String string="("+this.kolonne+"/"+this.rad+")";
		return string;
	}

	public void gaa(Rute forrige,String utvei) {
	}

	public boolean ytterrute(Rute r) {
		if (l.antallRader-1==r.rad || l.antallKolonner-1==r.kolonne|| r.rad==0|| r.kolonne==0) {
			return true;
		}
		return false;
	}

	public void finnUtvei() {
		gaa(this,"");
	}

	public Rute[] hentNaboer() {
		Rute ruteliste []={nord,oest,soer,vest};
		return ruteliste;
	}
}*/
