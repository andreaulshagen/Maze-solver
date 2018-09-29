import javafx.scene.control.Button;

public class HvitRute extends Rute{

  HvitRute(int rad, int kol, Labyrint labyrint) {

    super(rad, kol, labyrint);

  }



  @Override

  public void gaa(String vei, Rute komFra) {

    //if (besokt) return; // For å løse sykliske labyrinter 



    //besokt = true; // For å løse sykliske labyrinter 

    vei += toString() + " --> ";



    if (komFra != nord) nord.gaa(vei, this);

    if (komFra != oest) oest.gaa(vei, this);

    if (komFra != soer) soer.gaa(vei, this);

    if (komFra != vest) vest.gaa(vei, this);



    //besokt = false; // For å løse sykliske labyrinter 

  }



  @Override

  public char tilTegn() {

    return '.';

  }

}


