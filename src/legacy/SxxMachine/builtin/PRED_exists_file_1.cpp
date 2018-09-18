using namespace std;

#include "PRED_exists_file_1.h"
#include "../../../machine/SxxMachine/Operation.h"
#include "../../../machine/SxxMachine/Term.h"
#include "../../../machine/SxxMachine/Prolog.h"
#include "../../../exceptions/SxxMachine/PrologException.h"
#include "../../../machine/SxxMachine/VariableTerm.h"
#include "../../../exceptions/SxxMachine/PInstantiationException.h"
#include "../../../machine/SxxMachine/SymbolTerm.h"
#include "../../../exceptions/SxxMachine/IllegalDomainException.h"

namespace SxxMachine
{
	using Operation = SxxMachine::Operation;
	using Predicate = SxxMachine::Predicate;
	using Prolog = SxxMachine::Prolog;
	using SymbolTerm = SxxMachine::SymbolTerm;
	using Term = SxxMachine::Term;
	using VariableTerm = SxxMachine::VariableTerm;
	using IllegalDomainException = SxxMachine::IllegalDomainException;
	using PInstantiationException = SxxMachine::PInstantiationException;
	using PrologException = SxxMachine::PrologException;

	PRED_exists_file_1::PRED_exists_file_1(Term *a1, Operation next)
	{
	  LARG[0] = a1;
	  cont = next;
	}

	Operation PRED_exists_file_1::exec(Prolog *engine) throw(PrologException)
	{
	  engine->requireFeature(Prolog::Feature::IO, this, LARG[0]);
	  engine->setB0();

	  Term *a1 = LARG[0]->dref();
	  if ((dynamic_cast<VariableTerm*>(a1) != nullptr))
	  {
		  throw PInstantiationException(this, 1);
	  }
	  if (!(dynamic_cast<SymbolTerm*>(a1) != nullptr))
	  {
		  throw IllegalDomainException(this, 1, "file", a1);
	  }

	  File *file = new File(a1->name());
	  if (file->isFile())
	  {
		return cont;
	  }
	  else
	  {
		return engine->fail();
	  }
	}
}
