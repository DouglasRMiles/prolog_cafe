#pragma once

#include "../../../machine/SxxMachine/Predicate.h"
#include <vector>
#include <any>
#include "exceptionhelper.h"

//JAVA TO C++ CONVERTER NOTE: Forward class declarations:
namespace SxxMachine
{
	class SymbolTerm;
	class Operation;
	class Term;
	class Prolog;
}

namespace SxxMachine
{


	using Operation = SxxMachine::Operation;
	using Predicate = SxxMachine::Predicate;
	using Prolog = SxxMachine::Prolog;
	using SymbolTerm = SxxMachine::SymbolTerm;
	using Term = SxxMachine::Term;
	/**
	 * <code>close/2</code><br>
	 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
	 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
	 * @version 1.0
	*/
	class PRED_close_2 : public Predicate::P2
	{
	private:
		static SymbolTerm *const SYM_ALIAS_1;
		static SymbolTerm *const SYM_FORCE_1;
		static SymbolTerm *const SYM_TRUE;
		static SymbolTerm *const SYM_FALSE;

	public:
		PRED_close_2(Term *a1, Term *a2, Operation cont);

		Operation exec(Prolog *engine) override;
	};

}