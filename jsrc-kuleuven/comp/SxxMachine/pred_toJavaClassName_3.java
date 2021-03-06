// Generated java file - release 0.2 - do not edit !
// Copyright August 16, 1996, KUL and CUM
// Authors: Bart Demoen and Paul Tarau

package SxxMachine;

import static SxxMachine.pterm.TermData.*;

import java.util.Iterator;

class pred_toJavaClassName_3_consts {
    Code entry_code;
    Code makename3cont;
    final static String string0 = internS("cut");
    final static String string1 = internS("toJavaClassName");
    final static String string2 = internS("makename");
    final static String string3 = internS(".");
    final static String string4 = internS("pred_");
    final static String string5 = internS("_");
    final static String string6 = internS("[]");
    final static String string7 = internS("?-");
    final static String string8 = internS("query");
    final static String string9 = internS("call");
    final static NumberTerm posint1 = Integer(1);
}

public class pred_toJavaClassName_3 extends Code {
    private final pred_toJavaClassName_3_consts consts;

    public pred_toJavaClassName_3() {
        consts = new pred_toJavaClassName_3_consts();
        initAlternatives();
    }

    protected pred_toJavaClassName_3(pred_toJavaClassName_3 c) {
        consts = c.getConsts();
        ALT = null;
    }

    protected final pred_toJavaClassName_3_consts getConsts() {
        return consts;
    }

    private Alternatives ALT = new Alternatives();

    private void initAlternatives() {
        ALT.disable();
        ALT.addAlternative(new pred_toJavaClassName_3_1(this));
        ALT.addAlternative(new pred_toJavaClassName_3_2(this));

    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        consts.entry_code = this;
        consts.makename3cont = mach.loadPred("makename", 2);
    }

    @Override
    public int arity() {
        return 3;
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final Term aregs[] = mach.createAregCopy(4);
        mach.createChoicePoint(aregs);
        final Iterator<Code> todo = ALT.getIndexedAlternatives(aregs);
        mach.fillAlternatives(todo);
        return todo.next().exec(mach);

    }
}

class pred_toJavaClassName_3_1 extends pred_toJavaClassName_3 {
    pred_toJavaClassName_3_1(pred_toJavaClassName_3 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term arg0 = CONST(pred_toJavaClassName_3_consts.string7);

        final Term arg1 = pred_toJavaClassName_3_consts.posint1;

        final Term arg2 = CONST(pred_toJavaClassName_3_consts.string8);
        return new Term[] { arg0, arg1, arg2 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 3);
        final Term areg2 = local_aregs.getTermDRef(2);
        final Term areg1 = local_aregs.getTermDRef(1);
        final Term areg0 = local_aregs.getTermDRef(0);

        if (!(areg0.unifyJP(CONST(pred_toJavaClassName_3_consts.string7))))
            return mach.Fail0;

        if (!(areg1.unifyJP(pred_toJavaClassName_3_consts.posint1)))
            return mach.Fail0;

        if (!(areg2.unifyJP(CONST(pred_toJavaClassName_3_consts.string8))))
            return mach.Fail0;
        mach.setCont(local_aregs, 0, continuation);
        mach.updateCUTB();
        mach.setARegENull(local_aregs, 3, 1);
        return mach.getCall1();
    }
}

class pred_toJavaClassName_3_2 extends pred_toJavaClassName_3 {
    pred_toJavaClassName_3_2(pred_toJavaClassName_3 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var3 = new DummyVar();

        final Term var2 = new DummyVar();

        final Term var1 = new DummyVar();
        final Term arg0 = var1;
        final Term arg1 = var2;
        final Term arg2 = var3;
        return new Term[] { arg0, arg1, arg2 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 3);
        final Term areg2 = local_aregs.getTermDRef(2);
        final Term areg1 = local_aregs.getTermDRef(1);
        final Term areg0 = local_aregs.getTermDRef(0);
        final Term var3 = Jv(mach);
        final Term var2 = Jv(mach);
        final Term var1 = Jv(mach);
        if (!(areg0.unifyJP(var1.dref())))
            return mach.Fail0;
        if (!(areg1.unifyJP(var2.dref())))
            return mach.Fail0;
        if (!(areg2.unifyJP(var3.dref())))
            return mach.Fail0;

        local_aregs.setAreg0((S(pred_toJavaClassName_3_consts.string3, CONST(pred_toJavaClassName_3_consts.string4), S(pred_toJavaClassName_3_consts.string3, var1
                .dref(), S(pred_toJavaClassName_3_consts.string3, CONST(pred_toJavaClassName_3_consts.string5), S(pred_toJavaClassName_3_consts.string3, var2
                        .dref(), CONST(pred_toJavaClassName_3_consts.string6)))))));
        local_aregs.setAreg1((var3.dref()));
        mach.setCont(local_aregs, 2, continuation);
        mach.updateCUTB();
        mach.setARegENull(local_aregs, 3);
        return getConsts().makename3cont;
    }
}
