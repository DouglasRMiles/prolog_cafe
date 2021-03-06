// Generated java file - release 0.2 - do not edit !
// Copyright August 16, 1996, KUL and CUM
// Authors: Bart Demoen and Paul Tarau

package SxxMachine;

import static SxxMachine.pterm.TermData.*;

import java.util.Iterator;

class pred_genjava_1_consts {
    Code entry_code;
    Code genjavaforpred2cont;
    final static String string0 = internS("cut");
    final static String string1 = internS("genjava");
    final static String string2 = internS(".");
    final static String string3 = internS("genjavaforpred");
    final static String string4 = internS("[]");
    final static String string5 = internS("call");
}

public class pred_genjava_1 extends Code {
    private final pred_genjava_1_consts consts;

    public pred_genjava_1() {
        consts = new pred_genjava_1_consts();
        initAlternatives();
    }

    protected pred_genjava_1(pred_genjava_1 c) {
        consts = c.getConsts();
        ALT = null;
    }

    protected final pred_genjava_1_consts getConsts() {
        return consts;
    }

    private Alternatives ALT = new Alternatives();

    private void initAlternatives() {
        ALT.disable();
        ALT.addAlternative(new pred_genjava_1_1(this));
        ALT.addAlternative(new pred_genjava_1_2(this));

    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        consts.entry_code = this;
        consts.genjavaforpred2cont = mach.loadPred("genjavaforpred", 1);
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

class pred_genjava_1_1 extends pred_genjava_1 {
    pred_genjava_1_1(pred_genjava_1 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term arg0 = CONST(pred_genjava_1_consts.string4);
        return new Term[] { arg0 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 1);
        final Term areg0 = local_aregs.getTermDRef(0);

        if (!(areg0.unifyJP(CONST(pred_genjava_1_consts.string4))))
            return mach.Fail0;
        mach.setCont(local_aregs, 0, continuation);
        mach.updateCUTB();
        mach.setARegENull(local_aregs, 1);
        return mach.getCall1();
    }
}

class pred_genjava_1_2 extends pred_genjava_1 {
    pred_genjava_1_2(pred_genjava_1 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var2 = new DummyVar();

        final Term var1 = new DummyVar();

        final Term arg0 = S(pred_genjava_1_consts.string2, var1, var2);
        return new Term[] { arg0 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 1);
        final Term areg0 = local_aregs.getTermDRef(0);
        final Term var2 = Jv(mach);
        final Term var1 = Jv(mach);

        if (!(areg0.unifyJP(S(pred_genjava_1_consts.string2, var1.dref(), var2.dref()))))
            return mach.Fail0;
        local_aregs.setAreg0((var1.dref()));

        mach.setCont(local_aregs, 1, S(pred_genjava_1_consts.string1, var2.dref(), continuation));
        mach.updateCUTB();
        return getConsts().genjavaforpred2cont;
    }
}
