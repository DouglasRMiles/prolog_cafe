package SxxMachine;

// CONST;
import static SxxMachine.pterm.TermData.*;

/**
 * Representation error.<br>
 * There will be a representation error when an implementation defined limit has
 * been breached.
 *
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.0
 */
public class RepresentationException extends BuiltinException {
    /** A functor symbol of <code>representation_error/3</code>. */
    public static final Functor REPRESENTATION_ERROR = F("representation_error", 3);
    /*
     * flag ::= character | character_code | in_character_code | max_arity |
     * max_integer | min_integer
     */
    /** Holds a string representation of flag. */
    public final String flag;

    /** Constructs a new <code>RepresentationException</code> with a flag. */
    public RepresentationException(String _flag) {
        this.flag = _flag;
    }

    /**
     * Constructs a new <code>RepresentationException</code> with the given
     * arguments.
     */
    public RepresentationException(Operation _goal, int _argNo, String _flag) {
        this.goal = _goal;
        this.argNo = _argNo;
        this.flag = _flag;
    }

    /**
     * Returns a term representation of this <code>RepresentationException</code>:
     * <code>representation_error(goal,argNo,flag)</code>.
     */
    @Override
    public Term getMessageTerm() {
        Term[] args = { FFIObject(this.goal), Integer(this.argNo), createAtomic(this.flag) };
        return createErrorTerm(this, REPRESENTATION_ERROR, args);
    }

    /**
     * Returns a string representation of this <code>RepresentationException</code>.
     */
    @Override
    public String toString() {
        String s = "{REPRESENTATION ERROR: " + this.goal.toString();
        if (this.argNo > 0)
            s += " - arg " + this.argNo;
        s += ": limit of " + this.flag + " is breached";
        s += "}";
        return s;
    }

    @Override
    public String getMessage() {
        return toString();
    }

}
