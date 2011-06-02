package com.googlecode.prolog_cafe.builtin;
import com.googlecode.prolog_cafe.lang.*;
/**
   <code>'$get_prolog_impl_flag'/2</code><br>
   @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
   @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
   @version 1.0
*/
class PRED_$get_prolog_impl_flag_2 extends Predicate.P2 {
    private static final SymbolTerm TRUE                      = SymbolTerm.makeSymbol("true");
    private static final SymbolTerm FALSE                     = SymbolTerm.makeSymbol("false");
    private static final SymbolTerm BOUNDED                   = SymbolTerm.makeSymbol("bounded");
    private static final SymbolTerm MAX_INTEGER               = SymbolTerm.makeSymbol("max_integer");
    private static final SymbolTerm MIN_INTEGER               = SymbolTerm.makeSymbol("min_integer");
    private static final SymbolTerm INTEGER_ROUNDING_FUNCTION = SymbolTerm.makeSymbol("integer_rounding_function");
    private static final SymbolTerm CHAR_CONVERSION           = SymbolTerm.makeSymbol("char_conversion");
    private static final SymbolTerm DEBUG                     = SymbolTerm.makeSymbol("debug");
    private static final SymbolTerm MAX_ARITY                 = SymbolTerm.makeSymbol("max_arity");
    private static final SymbolTerm UNKNOWN                   = SymbolTerm.makeSymbol("unknown");
    private static final SymbolTerm DOUBLE_QUOTES             = SymbolTerm.makeSymbol("double_quotes");
    private static final SymbolTerm PRINT_STACK_TRACE         = SymbolTerm.makeSymbol("print_stack_trace");

    public PRED_$get_prolog_impl_flag_2(Term a1, Term a2, Operation cont) {
        arg1 = a1;
        arg2 = a2;
        this.cont = cont;
    }

    public Operation exec(Prolog engine) {
        engine.setB0();
        Term a1, a2;
        a1 = arg1;
        a2 = arg2;
	a1 = a1.dereference();
	a2 = a2.dereference();

	if (a1.equals(BOUNDED)) {
	    if (engine.isBounded()) {
		if (! a2.unify(TRUE, engine.trail))
		    return engine.fail();
	    } else {
		if (! a2.unify(FALSE, engine.trail))
		    return engine.fail();
	    }
	} else if (a1.equals(MAX_INTEGER)) {
	    if (! a2.unify(new IntegerTerm(engine.getMaxInteger()), engine.trail))
		return engine.fail();
	} else if (a1.equals(MIN_INTEGER)) {
	    if (! a2.unify(new IntegerTerm(engine.getMinInteger()), engine.trail))
		return engine.fail();
	} else if (a1.equals(INTEGER_ROUNDING_FUNCTION)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getIntegerRoundingFunction()), engine.trail))
		return engine.fail();
	} else if (a1.equals(CHAR_CONVERSION)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getCharConversion()), engine.trail))
		return engine.fail();
	} else if (a1.equals(DEBUG)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getDebug()), engine.trail))
		return engine.fail();
	} else if (a1.equals(MAX_ARITY)) {
	    if (! a2.unify(new IntegerTerm(engine.getMaxArity()), engine.trail))
		return engine.fail();
	} else if (a1.equals(UNKNOWN)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getUnknown()), engine.trail))
		return engine.fail();
	} else if (a1.equals(DOUBLE_QUOTES)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getDoubleQuotes()), engine.trail))
		return engine.fail();
	} else if (a1.equals(PRINT_STACK_TRACE)) {
	    if (! a2.unify(SymbolTerm.makeSymbol(engine.getPrintStackTrace()), engine.trail))
		return engine.fail();
	} else {
	    return engine.fail();
	}
        return cont;
    }
}
