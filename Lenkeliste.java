import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

class Lenkeliste <T> implements Liste<T> {
  protected Node head, tail;
  protected int size;

  @Override
  public Iterator<T> iterator() {
    return new ListeIterator();
    }

            protected class Node {
              T data;
              Node next;
              Node prev;

              Node(T elm) { data = elm; }
            }


  public class ListeIterator implements Iterator<T>{
    // tester iteratoren i TestLenkelistefilen.
    int index = 0;
    int currentSize = size;

    @Override
    public boolean hasNext() {
      return index < currentSize && hent(index) != null;
    }

    @Override
    public T next() {
      if(hasNext()){
        return hent(index++);
        }
        throw new NoSuchElementException();
      }

    @Override
    public void remove() {
      throw new UnsupportedOperationException();
        }
}

  public Lenkeliste() {
    head = new Node(null);
    tail = new Node(null);
    head.next = tail;
    tail.prev = head;
  }

  @Override
  public int stoerrelse() {
    return size;
  }

@Override
public T hent(int index){

  Node n = head.next;

  if(head.next == tail){
    throw new UgyldigListeIndeks(-1);
  }

  else if(index == 0 && head.next != tail){
    return n.data;
  }

  else if(index > 0 && index < stoerrelse()){
    for(int i = 0; i < index; i++){
        n = n.next;
      }
      return n.data;
    }else{
      throw new UgyldigListeIndeks(index);
    }
}

@Override
public void leggTil(int index, T t){

  Node n = head.next;
  Node ny = new Node(t);

  if(index == 0 || index == 0 && stoerrelse() == 0){
    ny.prev = n.prev;
    ny.next = n;
    n.prev.next = ny;
    n.prev = ny;
    size++;
  }

  else if(index > 0 && index < stoerrelse()){

    for (int i = 0; i < index; i++) {
        n = n.next;
      }

      ny.prev = n.prev;
      ny.next = n;
      n.prev.next = ny;
      n.prev = ny;

      size++;
    }

  else if(index == stoerrelse()){
    leggTil(t);
    }else{
      throw new UgyldigListeIndeks(index);
    }
}

@Override
public void sett(int index, T t){
  Node n = head.next;
  Node ny = new Node(t);

  if(stoerrelse() == 0){
    throw new UgyldigListeIndeks(-1);
  }

  else if(index == 0){
    n.data = ny.data;
  }
  else if(index > 0 && index < stoerrelse()){
    for (int i = 0; i < index; i++) {
        n = n.next;
      }
    n.data = ny.data;
  }else{
    throw new UgyldigListeIndeks(index);
  }
}



@Override
public T fjern(int index){

  Node n = head.next;

  if(head.next == null){
    throw new UgyldigListeIndeks(-1);
  }

  else if(index == 0 && head.next != tail){
    n.prev.next = n.next;
    n.next.prev = n.prev;
    size--;
    return n.data;
  }

  else if(index > 0 && index < stoerrelse()){
    for (int i = 0; i < index; i++) {
        n = n.next;
      }
      n.prev.next = n.next;
      n.next.prev = n.prev;
      size--;
      return n.data;
    }else{
    throw new UgyldigListeIndeks(index);
    }
}

@Override
public T fjern(){

  Node n = head.next;

  if(head.next != tail){
    head.next = n.next;
    n.next.prev = head;

    size--;
    return n.data;

  }else{
    throw new UgyldigListeIndeks(-1);
  }
}

@Override
public void leggTil(T t){
  Node ny = new Node(t);
  Node n = tail.prev;
    ny.prev = n;
    ny.next = tail;
    n.next = ny;
    tail.prev = ny;

    size++;
  }

@Override
//tester rekursiv print i testprogrammet.
public void skrivListe(){
  Node n = head.next;
  if(n == null){
    return;
  }else {
      printN(n);
    }
  }

public void printN(Node n){
        System.out.println(n.data);
        if(n.next == tail){
          return;
        }else{
          printN(n.next);
        }
      }

}
