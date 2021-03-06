// Generated java file - release 0.2 - do not edit !
// Copyright August 16, 1996, KUL and CUM
// Authors: Bart Demoen and Paul Tarau

package SxxMachine;

// Jv;
import static SxxMachine.pterm.TermData.*;

class pred_wrargs_2_consts {
    //Code entry_code;
    Code wrargs4cont;
    final static String string0 = internS("cut");
    final static String string1 = internS("wrargs");
}

public class pred_wrargs_2 extends Code {
    static final pred_wrargs_2_consts consts = new pred_wrargs_2_consts();

    protected pred_wrargs_2(pred_wrargs_2 c) {
    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        consts.wrargs4cont = mach.loadPred("wrargs", 3);
    }

    @Override
    public int arity() {
        return 2;
    }

    @Override
    @SuppressWarnings("static-access")
    public Code exec(PrologMachine mach) {
        final TermArray local_aregs = mach.getAreg();
        final Term continuation = mach.getCont(local_aregs, 2);
        final Term areg1 = local_aregs.getTermDRef(1);
        final Term areg0 = local_aregs.getTermDRef(0);
        final Term var3 = Jv(mach);
        final Term var2 = Jv(mach);
        final Term var1 = Jv(mach);
        if (!(areg0.unifyJP(var1)))
            return mach.Fail0;
        if (!(areg1.unifyJP(var2)))
            return mach.Fail0;
        local_aregs.setAreg0((var1.dref()));
        local_aregs.setAreg1((var2.dref()));
        local_aregs.setAreg2((var3));
        mach.setCont(local_aregs, 3, continuation);
        mach.updateCUTB();
        return consts.wrargs4cont;

    }
}
