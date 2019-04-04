
package SxxMachine;

import static SxxMachine.pterm.TermData.CONST;
import static SxxMachine.pterm.TermData.Jv;
import static SxxMachine.pterm.TermData.S;

public class pred_concat_3 extends Code {

    final static Code concat1 = new Concat1();
    final static Code concat2 = new Concat2();

    @Override
    public int arity() {
        return 3;
    }

    protected String getAsString(Term o) {
        if (o.isAtomOrObject()) {
            return ((Const) o).portrayTerm();
        }
        if (o.isCompound()) {
            final AFunct f = (AFunct) o;
            if (f.arity() == 0)
                return f.fname();
        }
        return null;
    }

    @Override
    public Code exec(PrologMachine mach) {
        final Term[] local_aregs = mach.getAreg();
        final Term v1 = local_aregs[0].dref();
        final Term v2 = local_aregs[1].dref();
        final Term v3 = local_aregs[2].dref();
        String v1Str = getAsString(v1);
        String v2Str = getAsString(v2);
        String v3Str = getAsString(v3);
        if (v1Str != null) {
            if (v2Str != null) {
                v3Str = v1Str + v2Str;
                if (!v3.unifyJP(CONST(v3Str)))
                    return mach.Fail0;
            } else {
                if (v3Str == null)
                    return mach.Fail0;
                if (!v3Str.startsWith(v1Str))
                    return mach.Fail0;
                v2Str = v3Str.substring(v1Str.length());
                if (!v2.unifyJP(CONST(v2Str)))
                    return mach.Fail0;
            }
        } else if (v2Str != null) {
            //v1Str is wel null
            if (v3Str == null)
                return mach.Fail0;
            if (!v3Str.endsWith(v2Str))
                return mach.Fail0;
            v1Str = v3Str.substring(0, v3Str.length() - v2Str.length());
            if (!v1.unifyJP(CONST(v1Str)))
                return mach.Fail0;
        } else {
            if (v3Str == null)
                return mach.Fail0;
            //Opsplitsing teken per teken teruggeven
            mach.createChoicePoint(new Term[] { v1, v2, v3, local_aregs[3] });
            return concat1.exec(mach);
        }
        mach.setCont(local_aregs[0] = local_aregs[3]);
        local_aregs[3] = local_aregs[2] = local_aregs[1] = null;
        return mach.getCall1();
    }

}

class Concat1 extends pred_concat_3 {

    @Override
    public Code exec(PrologMachine mach) {
        mach.fillAlternative(concat2);
        final Term[] local_aregs = mach.getAreg();
        final Term v1 = local_aregs[0].dref();
        final Term v2 = local_aregs[1].dref();
        final Term v3 = local_aregs[2].dref();
        if (!v1.unifyJP(CONST("")))
            return mach.Fail0;
        if (!v2.unifyJP(v3))
            return mach.Fail0;
        local_aregs[0] = local_aregs[3];
        local_aregs[3] = local_aregs[2] = local_aregs[1] = null;
        return mach.getCall1();
    }

}

class Concat2 extends pred_concat_3 {

    @Override
    public Code exec(PrologMachine mach) {
        mach.removeChoice();
        final Term[] local_aregs = mach.getAreg();
        final Term v1 = local_aregs[0].dref();
        final Term v2 = local_aregs[1].dref();
        final Term v3 = local_aregs[2].dref();
        final String v3Str = getAsString(v3);
        if (v3Str == null)
            return mach.Fail0;
        final JpVar var = Jv(mach);
        mach.setCont(local_aregs, 0, S("concat", var, v2, CONST(v3Str
                .substring(1)), S("concat", CONST(v3Str.substring(0, 1)), var, v1, local_aregs[3])));
        local_aregs[3] = local_aregs[2] = local_aregs[1] = null;
        return mach.getCall1();
    }

}