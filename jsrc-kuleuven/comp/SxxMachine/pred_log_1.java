// Generated java file - release 0.2 - do not edit !
// Copyright August 16, 1996, KUL and CUM
// Authors: Bart Demoen and Paul Tarau

package SxxMachine;

import static SxxMachine.pterm.TermData.*;

import java.util.Iterator;

class pred_log_1_consts {
    Code entry_code;
    Code nb_current3cont;
    final static String string0 = internS("cut");
    final static String string1 = internS("log");
    final static String string2 = internS("call");
    final static String string3 = internS("nb_current");
    final static String string4 = internS("VERBOSE");
    final static String string5 = internS("true");
    final static String string6 = internS("cut");
    final static String string7 = internS("writel");
    final static String string8 = internS("nl");
    final static NumberTerm posint1 = Integer(1);
}

public class pred_log_1 extends Code {
    private final pred_log_1_consts consts;

    public pred_log_1() {
        consts = new pred_log_1_consts();
        initAlternatives();
    }

    protected pred_log_1(pred_log_1 c) {
        consts = c.getConsts();
        ALT = null;
    }

    protected final pred_log_1_consts getConsts() {
        return consts;
    }

    private Alternatives ALT = new Alternatives();

    private void initAlternatives() {
        ALT.disable();
        ALT.addAlternative(new pred_log_1_1(this));
        ALT.addAlternative(new pred_log_1_2(this));

    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        consts.entry_code = this;
        consts.nb_current3cont = mach.loadPred("nb_current", 2);
    }

    @Override
    public int arity() {
        return 1;
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final Term aregs[] = mach.createAregCopy(2);
        mach.createChoicePoint(aregs);
        final Iterator<Code> todo = ALT.getIndexedAlternatives(aregs);
        mach.fillAlternatives(todo);
        return todo.next().exec(mach);

    }
}

class pred_log_1_1 extends pred_log_1 {
    pred_log_1_1(pred_log_1 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var1 = new DummyVar();
        final Term arg0 = var1;
        return new Term[] { arg0 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 1);
        final Term areg0 = local_aregs.getTermDRef(0);
        final Term var1 = Jv(mach);
        if (!(areg0.unifyJP(var1.dref())))
            return mach.Fail0;

        local_aregs.setAreg0((CONST(pred_log_1_consts.string4)));

        local_aregs.setAreg1((CONST(pred_log_1_consts.string5)));

        mach.setCont(local_aregs, 2, S(pred_log_1_consts.string0, new HeapChoice(mach
                .getCUTB()), S(pred_log_1_consts.string7, var1.dref(), S(pred_log_1_consts.string8, continuation))));
        mach.updateCUTB();
        return getConsts().nb_current3cont;
    }
}

class pred_log_1_2 extends pred_log_1 {
    pred_log_1_2(pred_log_1 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var1 = new DummyVar();
        final Term arg0 = var1;
        return new Term[] { arg0 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 1);
        final Term areg0 = local_aregs.getTermDRef(0);
        final Term var1 = Jv(mach);
        if (!(areg0.unifyJP(var1.dref())))
            return mach.Fail0;
        mach.setCont(local_aregs, 0, continuation);
        mach.updateCUTB();
        mach.setARegENull(local_aregs, 1);
        return mach.getCall1();
    }
}
