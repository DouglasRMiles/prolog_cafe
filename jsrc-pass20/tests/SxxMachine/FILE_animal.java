package SxxMachine;

import static SxxMachine.pterm.TermData.*;

@SuppressWarnings("unused")
public class FILE_animal extends FILE_system {
	/**
	 * PREDICATE: animal/1 from:
	 * /mnt/gggg/opt/CYC_JRTL_with_CommonLisp/SxxMachine/jsrc-pass20/tests/animal.pl
	 */
	// main(animal/1,public)
	final static Atomic ATOM_goat = SYM("goat");
	final static Atomic ATOM_elephant = SYM("elephant");
	final static Atomic ATOM_please$0020type$0020one$0020more$0020animal$0020name$003A$0020 = SYM(
			"please type one more animal name: ");

	public static Operation PRED_animal_1_static_exec(Prolog m) {
		final Operation cont = m.cont;
		TermArray LARG = m.AREGS;
		final Operation thiz = m.pred;
		m.cont = cont;
		m.setB0();
		return m.switch_on_term(FILE_animal::animal_1_var, FILE_animal::animal_1_3, FILE_animal::animal_1_3,
				FILE_animal::animal_1_var, FILE_animal::animal_1_3, FILE_animal::animal_1_3);
	}

	private final static Operation animal_1_var(Prolog m) {
		m.jtry1(null, FILE_animal::animal_1_var_1);
		return animal_1_1(m);
	}

	private final static Operation animal_1_var_1(Prolog m) {
		m.retry(null, FILE_animal::animal_1_var_2);
		return animal_1_2(m);
	}

	private final static Operation animal_1_var_2(Prolog m) {
		m.trust(null);
		return animal_1_3(m);
	}

	private final static Operation animal_1_1(Prolog m) {
		// animal(goat):-true
		Term a1;
		Operation cont;
		TermArray MARG = m.AREGS;
		a1 = MARG.getAreg0();
		cont = m.cont;
		// animal(goat):-[]
		if (!ATOM_goat.unify(a1, m.trail))
			return m.fail();
		return cont;
	}

	private final static Operation animal_1_2(Prolog m) {
		// animal(elephant):-true
		Term a1;
		Operation cont;
		TermArray MARG = m.AREGS;
		a1 = MARG.getAreg0();
		cont = m.cont;
		// animal(elephant):-[]
		if (!ATOM_elephant.unify(a1, m.trail))
			return m.fail();
		return cont;
	}

	private final static Operation animal_1_3(Prolog m) {
		// animal(A):-write('please type one more animal name: '),flush_output,read(A)
		Term a1;
		Operation p1, p2;
		Operation cont;
		TermArray MARG = m.AREGS;
		a1 = MARG.getAreg0();
		cont = m.cont;
		// animal(A):-[write('please type one more animal name: '),flush_output,read(A)]
		return //
		Op("write", FILE_animal::PRED_write_1_static_exec,
				VA(ATOM_please$0020type$0020one$0020more$0020animal$0020name$003A$0020), //
				Op("flush_output", FILE_animal::PRED_flush_output_0_static_exec, VA(), //
						Op("read", FILE_animal::PRED_read_1_static_exec, VA(a1), cont)));
	}

	static {
		loadPreds();
	}

	static public void loadPreds() {
		PredTable.registerPredicate("animal", 1, FILE_animal::PRED_animal_1_static_exec);
	}
}
