#pragma once

#include "../../terms/SxxMachine/Source.h"

//JAVA TO C++ CONVERTER NOTE: Forward class declarations:
namespace SxxMachine
{
	class Nonvar;
	class Prog;
	class Term;
	class stop;
}

namespace SxxMachine
{

	/**
	 * Maps a Term to an Source for iterating over its arguments
	 */
	class TermSource : public Source
	{
	public:
		virtual ~TermSource()
		{
			delete val;
		}

		TermSource(Nonvar *val, Prog *p);

	private:
		Nonvar *val;

		int pos = 0;

	public:
		Term *getElement() override;

		void stop() override;
	};

}