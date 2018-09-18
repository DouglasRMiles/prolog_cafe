using namespace std;

#include "PRED_$get_exception_1.h"
#include "../../../machine/SxxMachine/Operation.h"
#include "../../../machine/SxxMachine/Term.h"
#include "../../../machine/SxxMachine/Prolog.h"

namespace SxxMachine
{
	using Operation = SxxMachine::Operation;
	using Predicate = SxxMachine::Predicate;
	using Prolog = SxxMachine::Prolog;
	using Term = SxxMachine::Term;

	PRED_$get_exception_1::PRED_$get_exception_1(Term *a1, Operation cont)
	{
	LARG[0] = a1;
	this->cont = cont;
	}

	Operation PRED_$get_exception_1::exec(Prolog *engine)
	{
		engine->setB0();
	Term *a1;
	a1 = LARG[0];

	if (!a1->unify(engine->getException(), engine->trail))
	{
		return engine->fail();
	}
	return cont;
	}
}
