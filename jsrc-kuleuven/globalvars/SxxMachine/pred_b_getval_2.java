
package SxxMachine;

import static SxxMachine.pterm.TermData.CONST;

public class pred_b_getval_2 extends Code {

    private static Code unification;

    @Override
    public int arity() {
        return 2;
    }

    @Override
    public void init(PredikatenPrologMachine mach) {
        if (unification == null)
            unification = mach.loadPred("unify", 2);
    }

    @Override
    public Code exec(PrologMachine mach) {
        final Term local_aregs[] = mach.getAreg();
        Term name = local_aregs[0].dref();

        if (name .isCompound()) {
            final AFunct f = (AFunct) name;
            if (f.arity() == 0) {
                name = CONST(f.fname());
            }
        }
        if (name .isConst()) {
            final Const cname = (Const) name;

            final Term oldValue = GlobalVarsModule.getTable(mach).get(cname);
            if (oldValue != null) {
                local_aregs[0] = oldValue;
                // System.out.println("b_getval(" + name + "," + oldValue + ")");
                return unification;
            } else {
                //System.out.println("b_getval(" + name + ",null)");
                local_aregs[2] = null;
                local_aregs[1] = null;
                local_aregs[0] = null;
                return mach.Fail0;
            }
        } else {
            local_aregs[2] = null;
            local_aregs[1] = null;
            local_aregs[0] = null;
            return mach.Fail0;
        }
    }

}
