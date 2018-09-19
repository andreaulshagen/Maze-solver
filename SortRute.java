
public class SortRute extends Rute {

  SortRute(int rad, int kol, Labyrint labyrint) {

    super(rad, kol, labyrint);

  }



  @Override

  public void gaa(String vei, Rute komFra) {


    return; // Her skal vi ikke gjøre noe

  }



  @Override

  public char tilTegn() {

    return '#';

  }

}
/*class SortRute extends Rute{

  public SortRute(int rad, int kolonne){
    super(rad, kolonne);
  }
  @Override
  public char tilTegn(){
    return '#';
  }
  @Override
  public void gaa(Rute forrige,String utvei){
    return;
  }
}*/
