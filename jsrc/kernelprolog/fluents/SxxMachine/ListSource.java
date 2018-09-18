package SxxMachine;

import SxxMachine.Nonvar;
import SxxMachine.Copier;
import SxxMachine.Prog;

/**
  Builds an iterator from a list
*/
public class ListSource extends IterableSource {
	public ListSource(Nonvar xs, Prog p) {
		super(Copier.ConsToVector(xs), p);
	}
}
