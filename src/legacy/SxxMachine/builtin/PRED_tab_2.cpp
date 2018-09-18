using namespace std;

#include "PRED_tab_2.h"
#include "../../../machine/SxxMachine/Operation.h"
#include "../../../machine/SxxMachine/Term.h"
#include "../../../machine/SxxMachine/Prolog.h"
#include "../../../machine/SxxMachine/VariableTerm.h"
#include "../../../exceptions/SxxMachine/PInstantiationException.h"
#include "../../../machine/SxxMachine/IntegerTerm.h"
#include "../../../machine/SxxMachine/Arithmetic.h"
#include "../../../exceptions/SxxMachine/BuiltinException.h"
#include "../../../machine/SxxMachine/SymbolTerm.h"
#include "../../../exceptions/SxxMachine/ExistenceException.h"
#include "../../../machine/SxxMachine/FFIObjectTerm.h"
#include "../../../exceptions/SxxMachine/IllegalDomainException.h"
#include "../../../exceptions/SxxMachine/PermissionException.h"

namespace SxxMachine
{
	using Arithmetic = SxxMachine::Arithmetic;
	using FFIObjectTerm = SxxMachine::FFIObjectTerm;
	using IntegerTerm = SxxMachine::IntegerTerm;
	using NumberTerm = SxxMachine::NumberTerm;
	using Operation = SxxMachine::Operation;
	using Predicate = SxxMachine::Predicate;
	using Prolog = SxxMachine::Prolog;
	using SymbolTerm = SxxMachine::SymbolTerm;
	using Term = SxxMachine::Term;
	using VariableTerm = SxxMachine::VariableTerm;
	using BuiltinException = SxxMachine::BuiltinException;
	using ExistenceException = SxxMachine::ExistenceException;
	using IllegalDomainException = SxxMachine::IllegalDomainException;
	using PInstantiationException = SxxMachine::PInstantiationException;
	using PermissionException = SxxMachine::PermissionException;

	PRED_tab_2::PRED_tab_2(Term *a1, Term *a2, Operation cont)
	{
		LARG[0] = a1;
		LARG[1] = a2;
		this->cont = cont;
	}

	Operation PRED_tab_2::exec(Prolog *engine)
	{
		engine->setB0();
		Term *a1, *a2;
		a1 = LARG[0];
		a2 = LARG[1];
	int n;
		//	String s = "";
	any stream = nullptr;

	// Char
	a2 = a2->dref();
	if ((dynamic_cast<VariableTerm*>(a2) != nullptr))
	{
		throw PInstantiationException(this, 2);
	}
	if (!(dynamic_cast<IntegerTerm*>(a2) != nullptr))
	{
		try
		{
		a2 = Arithmetic::evaluate(a2);
		}
		catch (const BuiltinException &e)
		{
		e->goal = this;
		e->argNo = 2;
		throw e;
		}
	}
	n = a2->asNumberlTerm().intValue();
	// S_or_a
	a1 = a1->dref();
	if ((dynamic_cast<VariableTerm*>(a1) != nullptr))
	{
		throw PInstantiationException(this, 1);
	}
	else if ((dynamic_cast<SymbolTerm*>(a1) != nullptr))
	{
		if (!engine->getStreamManager()->containsKey(a1))
		{
		throw ExistenceException(this, 1, "stream", a1, "");
		}
		stream = (engine->getStreamManager()->get(a1)).object();
	}
	else if ((dynamic_cast<FFIObjectTerm*>(a1) != nullptr))
	{
		stream = a1->object();
	}
	else
	{
		throw IllegalDomainException(this, 1, "stream_or_alias", a1);
	}
	if (!(dynamic_cast<PrintWriter*>(stream) != nullptr))
	{
		throw PermissionException(this, "output", "stream", a1, "");
	}
	// tab
	for (int i = 0; i < n; i++)
	{
		//	    s.append(" ");
		(any_cast<PrintWriter*>(stream)).print(" ");
	}
	return cont;
	}
}
