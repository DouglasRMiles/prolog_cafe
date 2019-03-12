// Generated java file - release 0.2 - do not edit !
// Copyright August 16, 1996, KUL and CUM
// Authors: Bart Demoen and Paul Tarau

package SxxMachine;

import static SxxMachine.pterm.TermData.CONST;
import static SxxMachine.pterm.TermData.Integer;
import static SxxMachine.pterm.TermData.Jv;
import static SxxMachine.pterm.TermData.S;
import static SxxMachine.pterm.TermData.internS;

import java.util.Iterator;

class pred_nullify_2_consts {
    Code entry_code;
    Code is3cont;
    Code cut2cont;
    final static String string0 = internS("cut");
    final static String string1 = internS("nullify");
    final static String string2 = internS("is");
    final static String string3 = internS("-");
    final static String string4 = internS("writel");
    final static String string5 = internS(".");
    final static String string6 = internS("local_aregs[");
    final static String string7 = internS("] = ");
    final static String string8 = internS("[]");
    final static String string9 = internS("cut");
    final static String string10 = internS("write");
    final static String string11 = internS("null ;");
    final static String string12 = internS("nl");
    final static String string13 = internS("fail");
    final static NumberTerm posint1 = Integer(1);
    final static NumberTerm posint0 = Integer(0);
}

public class pred_nullify_2 extends Code {
    private final pred_nullify_2_consts consts;

    public pred_nullify_2() {
        consts = new pred_nullify_2_consts();
        initAlternatives();
    }

    protected pred_nullify_2(pred_nullify_2 c) {
        consts = c.getConsts();
        ALT = null;
    }

    protected final pred_nullify_2_consts getConsts() {
        return consts;
    }

    private Alternatives ALT = new Alternatives();

    private void initAlternatives() {
        ALT.disable();
        ALT.addAlternative(new pred_nullify_2_1(this));
        ALT.addAlternative(new pred_nullify_2_2(this));

    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        consts.entry_code = this;
        consts.is3cont = mach.loadPred("is", 2);
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final Term aregs[] = mach.createAregCopy(3);
        mach.createChoicePoint(aregs);
        final Iterator<Code> todo = ALT.getIndexedAlternatives(aregs);
        mach.fillAlternatives(todo);
        return todo.next().exec(mach);

    }
}

class pred_nullify_2_1 extends pred_nullify_2 {
    pred_nullify_2_1(pred_nullify_2 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var1 = new DummyVar();

        final Term arg0 = pred_nullify_2_consts.posint0;
        final Term arg1 = var1;
        return new Term[] { arg0, arg1 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final Term local_aregs[] = mach.getAreg();
        final Term continuation = local_aregs[2];
        final Term areg1 = local_aregs[1].dref();
        final Term areg0 = local_aregs[0].dref();
        final Term var1 = Jv(mach);

        if (!(areg0.unifyJP(pred_nullify_2_consts.posint0)))
            return mach.Fail0;
        if (!(areg1.unifyJP(var1.dref())))
            return mach.Fail0;
        mach.doCut(mach.getCUTB());

        local_aregs[0] = S(pred_nullify_2_consts.string10, CONST(pred_nullify_2_consts.string11), S(pred_nullify_2_consts.string12, S(pred_nullify_2_consts.string13, continuation)));
        mach.updateCUTB();
        local_aregs[2] = null;
        return mach.Call1;
    }
}

class pred_nullify_2_2 extends pred_nullify_2 {
    pred_nullify_2_2(pred_nullify_2 consts) {
        super(consts);
    }

    @Override
    @SuppressWarnings("static-access")
    protected Term[] getArgs() {

        final Term var2 = new DummyVar();

        final Term var1 = new DummyVar();
        final Term arg0 = var1;
        final Term arg1 = var2;
        return new Term[] { arg0, arg1 };
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final Term local_aregs[] = mach.getAreg();
        final Term continuation = local_aregs[2];
        final Term areg1 = local_aregs[1].dref();
        final Term areg0 = local_aregs[0].dref();
        final Term var4 = Jv(mach);
        final Term var3 = Jv(mach);
        final Term var2 = Jv(mach);
        final Term var1 = Jv(mach);
        if (!(areg0.unifyJP(var1.dref())))
            return mach.Fail0;
        if (!(areg1.unifyJP(var2.dref())))
            return mach.Fail0;
        local_aregs[0] = var3;

        local_aregs[1] = S(pred_nullify_2_consts.string3, var2.dref(), pred_nullify_2_consts.posint1);

        local_aregs[2] = S(pred_nullify_2_consts.string4, S(pred_nullify_2_consts.string5, CONST(pred_nullify_2_consts.string6), S(pred_nullify_2_consts.string5, var3
                .dref(), S(pred_nullify_2_consts.string5, CONST(pred_nullify_2_consts.string7), CONST(pred_nullify_2_consts.string8)))), S(pred_nullify_2_consts.string2, var4, S(pred_nullify_2_consts.string3, var1
                        .dref(), pred_nullify_2_consts.posint1), S(pred_nullify_2_consts.string1, var4
                                .dref(), var3.dref(), continuation)));
        mach.updateCUTB();
        return getConsts().is3cont;
    }
}
