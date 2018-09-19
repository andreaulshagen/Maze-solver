
import java.util.*;
import java.io.*;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.ArrayList;

import java.util.Collections;

import java.util.List;

import java.util.Scanner;



public class Labyrint {

	public final Rute[][] ruter;

	final int hoyde, bredde;

	List<String> utveier;



	private Labyrint(Rute[][] ruter, int hoyde, int bredde) {

		this.ruter = ruter;

		this.hoyde = hoyde;

		this.bredde = bredde;

	}



	static Labyrint lesFraFil(File fil) throws FileNotFoundException {

		Scanner scanner = new Scanner(fil);

		String[] storrelser = scanner.nextLine().split(" ");

		int hoyde = Integer.parseInt(storrelser[0]);

		int bredde = Integer.parseInt(storrelser[1]);

		Rute[][] ruter = new Rute[hoyde][bredde];

		Labyrint labyrint = new Labyrint(ruter, hoyde, bredde);



		// Fyll rute-arrayet med ruter

		for (int rad = 0; rad < hoyde; rad++) {

			char[] tegn = scanner.nextLine().toCharArray();

			for (int kol = 0; kol < bredde; kol++) {

				ruter[rad][kol] = Rute.lagRute(tegn[kol], rad, kol, labyrint);

			}

		}



		// Sett nabo-referanser

		for (int rad = 1; rad < hoyde-1; rad++) {

			for (int kol = 1; kol < bredde-1; kol++) {

				ruter[rad][kol].nord = ruter[rad-1][kol];

				ruter[rad][kol].oest = ruter[rad][kol+1];

				ruter[rad][kol].soer = ruter[rad+1][kol];

				ruter[rad][kol].vest = ruter[rad][kol-1];

			}

		}

		return labyrint;

	}



	// Merk: Vi bruker java.util.List i stedet for Liste fra oblig 3. Ellers likt.

	List<String> finnUtveiFra(int kol, int rad) {

		utveier = new ArrayList<String>();


		ruter[rad][kol].finnUtvei();


		// Collections.sort() er for sortering av utveier etter lengde slik at vi kan finne

		// korteste utvei (valgfri del). Se opptaket av plenum (siste par minutter) for

		// forklaring. Collections.sort fungerer ikke med Liste fra oblig 3, da må dette

		// implementeres med en "wrapper-klasse" som implementerer Comparable i stedet.

		// Om det er uklart, er det bare å spørre på Piazza!

		Collections.sort(utveier, (a, b) -> {

			int lengdeA = a.length() - a.replace(",", "").length();

			int lengdeB = b.length() - b.replace(",", "").length();

			return lengdeA - lengdeB;

		});

		return utveier;

	}



	@Override

	public String toString() {

		String retur = "";

		for (Rute[] rad : ruter) {

			for (Rute rute : rad) {

				retur += rute.tilTegn();

			}

			retur += "\n";

		}

		return retur;

	}

}
/*public class Labyrint {
	static int antallRader;
	static int antallKolonner;
	static Rute[][] rutenett;
	static public Lenkeliste <String> utveier;


	private Labyrint(Rute[][] rute, int r, int k) {
		antallRader=r;
		antallKolonner=k;
		rutenett=rute;
		utveier=new Lenkeliste<String>();
		for (int rr=0; rr<r; rr++) {
			for (int kk=0;kk<k;kk++) {
				rutenett[rr][kk].setLabyrint(this);
				Rute rute1=rutenett[rr][kk];
				if (rr>0) rute1.settNord(rutenett[rr-1][kk]);
				if (rr<antallRader-1) rute1.settSoer(rutenett[rr+1][kk]);
				if (kk>0) rute1.settVest(rutenett[rr][kk-1]);
				if(kk<antallKolonner-1) rute1.settOest(rutenett[rr][kk+1]);
			}
		}
	}

	public static Labyrint leseFraFil(File fil)throws FileNotFoundException {

			Scanner sc=new Scanner(fil);
			String line=sc.nextLine();
			String[] dimensjoner = line.split(" ");

			antallKolonner=Integer.parseInt(dimensjoner[1]);
			antallRader=Integer.parseInt(dimensjoner[0]);


			Rute[][] rutenett=new Rute[antallRader][antallKolonner];
			int r=0;
			while(sc.hasNextLine()) {
				line=sc.nextLine();
				if (line.length()>0) {
					for (int i=0;i<antallKolonner;i++) {
						char c=line.charAt(i);
						if (c=='#') {
							rutenett[r][i]=new SortRute(r,i);
						}
						else if (c=='.') {
							if(r==0 || r==antallRader-1 || i==0 ||i==antallKolonner-1) {
								rutenett[r][i]=new Aapning(r,i);
							}
							else {
								rutenett[r][i]=new HvitRute(r,i);
							}
						}
					}
					r++;
				}
			}
			Labyrint l=new Labyrint(rutenett,antallRader,antallKolonner);

			return l;
		}


	public String toString() {
		String out=new String();
		for (int i=0; i<this.antallRader;i++) {
			for (int j=0; j<this.antallKolonner;j++) {
				out=out+rutenett[i][j].tilTegn();
			}
			out=out+"\n";
		}
		return out;
	}


	public Lenkeliste finnUtveiFra(int rad, int kol) {
		utveier = new Lenkeliste<String>();
		rutenett [rad][kol].gaa(rutenett[rad][kol],""); //this

		return utveier;
	}

}*/
