import javafx.scene.control.Button;

public class HvitRute extends Rute{

  HvitRute(int rad, int kol, Labyrint labyrint) {

    super(rad, kol, labyrint);

  }



  @Override

  public void gaa(String vei, Rute komFra) {

    //if (besokt) return; // For å løse sykliske labyrinter (valgfri del)



    //besokt = true; // For å løse sykliske labyrinter (valgfri del)

    vei += toString() + " --> ";



    if (komFra != nord) nord.gaa(vei, this);

    if (komFra != oest) oest.gaa(vei, this);

    if (komFra != soer) soer.gaa(vei, this);

    if (komFra != vest) vest.gaa(vei, this);



    //besokt = false; // For å løse sykliske labyrinter (valgfri del)

  }



  @Override

  public char tilTegn() {

    return '.';

  }

}

/* class HvitRute extends Rute{

  public HvitRute(int rad, int kolonne){
    super(rad, kolonne);
  }

  @Override
  public char tilTegn(){
    return '.';
  }
  @Override
  public void gaa(Rute forrige,String utvei) {

    for (Rute r:hentNaboer()) {
      if (r!=null
          && !r.equals(forrige)
          && r.ytterrute(forrige)==false) {
        utvei+="("+this.kolonne+"/"+this.rad+")";
        utvei+="-->";
        r.gaa(this,utvei);
      }
    }
    if (ytterrute(this)) {
      //utvei+="("+this.kolonne+"/"+this.rad+")";
      this.l.utveier.leggTil(utvei);
    }
  }
}*/
