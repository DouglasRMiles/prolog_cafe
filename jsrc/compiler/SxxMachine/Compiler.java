package SxxMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.LinkedList;

import SxxMachine.pterm.ListTerm;
import SxxMachine.pterm.TermData;

/**
 * The <code>Compiler</code> class provides methods for translating Prolog
 * programs into Java programs.
 *
 * The <code>Compiler</code> class supports the following compiler options. All
 * of them are set to <code>true</code> in default setting.
 * <ul>
 * <li>Eliminate disjunctions
 * <li>Arithmetic compilation
 * <li>Inline expansion
 * <li>Optimisation of recursive call
 * <li>2nd. level indexing (<code>switch_on_hash</code>)
 * </ul>
 *
 * Let us show a sample session for translating a Prolog program
 * <code>$PLCAFEDIR/examples/prolog/list.pl</code> into Java. The
 * <code>list.pl</code> contains predicates <code>append/3</code>,
 * <code>nrev/2</code>, and <code>range/3</code>.
 * <ul>
 * <li>From Command line<br>
 * 
 * <pre>
 *    % java -cp $PLCAFEDIR/plcafe.jar SxxMachine.compiler.Compiler:$CLASSPATH $PLCAFEDIR/examples/prolog/list.pl
 *    Prolog Cafe X.X.X (YYY)
 *    Copyright(C) 1997-200X M.Banbara and N.Tamura
 *    % ls
 *    PRED_append_3.java      PRED_nrev_2.java        PRED_range_3.java
 * </pre>
 * 
 * <li>From Java program<br>
 * 
 * <pre>
 * import SxxMachine.compiler.Compiler;
 * 
 * public class T {
 * 	public static void main(String argv[]) {
 * 		Compiler comp = new Compiler();
 * 		comp.prologToJava(argv[0], ".");
 * 	}
 * }
 * </pre>
 * 
 * <pre>
 *    % javac -classpath $PLCAFEDIR/plcafe.jar:$CLASSPATH T.java
 *    % java -classpath $PLCAFEDIR/plcafe.jar:$CLASSPATH T $PLCAFEDIR/examples/prolog/list.pl
 *    % ls
 *    PRED_append_3.java      PRED_nrev_2.java        PRED_range_3.java
 * </pre>
 * </ul>
 *
 * It is noted that our Prolog-to-Java translator is originally witten in
 * Prolog, and then bootstrapped. Please see the following two Prolog programs
 * in details.
 * <ul>
 * <li><code>$PLCAFEDIR/src/compiler/pl2am.pl</code><br>
 * Translates a Prolog program into a WAM-based intermediate code.
 * <li><code>$PLCAFEDIR/src/compiler/am2j.pl</code><br>
 * Translates a WAM-based intermediate code generated by <code>pl2am.pl</code>
 * into Java programs.
 * </ul>
 *
 * @author Mutsunori Banbara (banbara@kobe-u.ac.jp)
 * @author Naoyuki Tamura (tamura@kobe-u.ac.jp)
 * @version 1.2
 */
public class Compiler {
    public static enum Option {
        eliminateDisjunctions("ed", true), arithmeticCompilation("ac", true), inlineExpansion("ie",
                true), optimiseRecursiveCall("rc", true), switchOnHash("idx", true), generateClosure("clo", false);
        final Functor symbol;
        final boolean onByDefault;

        Option(String symbol, boolean onByDefault) {
            this.symbol = TermData.SYM(symbol);
            this.onByDefault = onByDefault;
        }
    }

    /** Prolog context running the compiler/translater tools. */
    private final BufferingPrologControl pcl;
    private final EnumSet<Option> options;

    /** Initialize a new compiler instance. */
    public Compiler() {
        this.pcl = new BufferingPrologControl();
        this.pcl.setPrologClassLoader(new PrologClassLoader(Compiler.class.getClassLoader()));
        this.options = EnumSet.noneOf(Option.class);
        enableDefaultOptions();
    }

    /**
     * Translates a Prolog program into a WAM-based intermediate code.
     *
     * @param _prolog
     *            an input Prolog file
     * @param _wam
     *            an output file for WAM-based intermediate code.
     */
    public void prologToWAM(String _prolog, String _wam) throws CompileException {
        if (!fileExists(_prolog))
            throw new CompileException(new FileNotFoundException(_prolog));
        // Create arguments
        Term prolog = TermData.createAtomic(_prolog);
        Term wam = TermData.createAtomic(_wam);
        Term op = Prolog.Nil;
        for (Option opt : this.options)
            op = TermData.CONS(opt.symbol, op);
        ListTerm args = TermData.LIST(prolog, wam, op);
        try {
            if (!this.pcl.execute("SxxMachine.compiler.pl2am", "pl2am", args))
                throw new CompileException("Unknown Error");
        } catch (PrologException err) {
            throw new CompileException("Error compiling " + _prolog, err);
        }
    }

    /**
     * Translates WAM-based intermediate code into Java source.
     *
     * @param _wam
     *            an input file for WAM-based intermediate code.
     * @param _dir
     *            a destination directory for java files.
     * @see #prologToWAM(String, String)
     */
    public void wamToJavaSource(String _wam, String _dir) throws CompileException {
        if (!fileExists(_wam))
            throw new CompileException(new FileNotFoundException(_wam));
        if (!fileExists(_dir) && !new File(_dir).mkdirs())
            throw new CompileException(new FileNotFoundException(_dir));
        // Create arguments
        Term wam = TermData.createAtomic(_wam);
        Term dir = TermData.createAtomic(_dir);
        ListTerm args = TermData.LIST(wam, dir);
        try {
            if (!this.pcl.execute("SxxMachine.compiler.am2j", "am2j", args))
                throw new CompileException("Unknown Error");
        } catch (PrologException err) {
            throw new CompileException("Error converting " + _wam, err);
        }
    }

    /**
     * Translates a Prolog program into Java source files.
     *
     * @param prolog
     *            an input Prolog file
     * @param dir
     *            a destination directory for java files. The directory must already
     *            exist.
     * @see #prologToWAM(String, String)
     * @see #wamToJavaSource(String, String)
     */
    public void prologToJavaSource(String prolog, String dir) throws CompileException {
        File tmp;
        try {
            tmp = File.createTempFile("PrologCafe_", ".am");
        } catch (IOException e) {
            throw new CompileException("Cannot create temporary file", e);
        }
        try {
            prologToWAM(prolog, tmp.getPath());
            wamToJavaSource(tmp.getPath(), dir);
        } finally {
            if (!tmp.delete() && tmp.exists())
                tmp.deleteOnExit();
        }
    }

    public static void main(String argv[]) throws Exception {
        Compiler comp = new Compiler();
        String out = ".";
        String amdir = null;
        boolean stackTrace = false;
        boolean suppressBanner = false;
        LinkedList<String> plsrc = new LinkedList<String>();
        int argi = 0;
        for (; argi < argv.length; argi++) {
            String a = argv[argi];
            if (a.equals("--")) {
                argi++;
                break;
            }
            if (a.equals("-q")) {
                suppressBanner = true;
            } else if (a.equals("-O")) {
                comp.enableDefaultOptions();
            } else if (a.equals("-O:none")) {
                comp.options.clear();
            } else if (a.startsWith("-O:")) {
                String optname = a.substring("-O:".length());
                Option opt = findOptionByName(optname);
                if (opt != null)
                    comp.enable(opt);
            } else if (a.equals("-s")) {
                if (++argi == argv.length)
                    usage();
                out = argv[argi];
            } else if (a.equals("-am")) {
                if (++argi == argv.length)
                    usage();
                amdir = argv[argi];
            } else if (a.equals("-h") || a.equals("--help") || a.equals("-help")) {
                usage();
            } else if (a.equals("--show-stack-trace")) {
                stackTrace = true;
            } else if (a.startsWith("-")) {
                System.err.println("error: Unsupported flag '" + a + "'");
                usage();
            } else {
                plsrc.add(a);
            }
        }
        if (argi < argv.length)
            plsrc.addAll(Arrays.asList(argv).subList(argi, argv.length));
        if (plsrc.isEmpty())
            usage();
        if (!suppressBanner)
            banner();
        for (String pl : plsrc) {
            System.err.println("Translating " + pl);
            try {
                if (amdir != null) {
                    String base;
                    if (pl.endsWith(".pl"))
                        base = pl.substring(0, pl.length() - 3);
                    else
                        base = pl;
                    File am = new File(new File(amdir), base + ".am");
                    am.getParentFile().mkdirs();
                    comp.prologToWAM(pl, am.getPath());
                    comp.wamToJavaSource(am.getPath(), out);
                } else {
                    comp.prologToJavaSource(pl, out);
                }
            } catch (CompileException err) {
                if (stackTrace)
                    err.printStackTrace();
                else
                    System.err.println("error: " + err.getMessage());
                System.exit(1);
            }
        }
    }

    private static Option findOptionByName(String optname) {
        for (Option opt : Option.values()) {
            if (opt.toString().equalsIgnoreCase(optname))
                return opt;
            if (opt.symbol.pprint().equalsIgnoreCase(optname))
                return opt;
        }
        System.err.println("error: Unsupported option '" + optname + "'");
        System.exit(1);
        throw new RuntimeException("System.exit(1)");
    }

    private static void usage() {
        System.err.print("usage: ");
        System.err.print("java ");
        System.err.print(Compiler.class.getName());
        System.err.print(" [options]");
        System.err.print(" prolog_source...");
        System.err.println();
        banner();
        String optfmt = "  %-20s %s";
        System.err.format(optfmt, "-s <directory>", "where to place generated source files");
        System.err.println();
        System.err.format(optfmt, "-am <directory>", "save WAM intermediate files");
        System.err.println();
        System.err.format(optfmt, "-O", "enable all optimizations");
        System.err.println();
        System.err.format(optfmt, "-O:none", "disable all optimizations");
        System.err.println();
        // Special options not related to building Prolog programs.
        System.err.println();
        System.err.format(optfmt, "-h, --help", "display this message");
        System.err.println();
        System.err.format(optfmt, "--show-stack-trace", "show Java stack trace on failure");
        System.err.println();
        System.exit(1);
    }

    private static void banner() {
        System.err.println("Prolog Cafe");
        System.err.println("Copyright(C) 1997-2009 M.Banbara and N.Tamura");
        System.err.println();
    }

    private static boolean fileExists(String _file) {
        try {
            return new File(_file).exists();
        } catch (SecurityException e) {
        }
        return false;
    }

    public boolean isEnabled(Option opt) {
        return this.options.contains(opt);
    }

    public void enable(Option opt) {
        this.options.add(opt);
    }

    public void disable(Option opt) {
        this.options.remove(opt);
    }

    public void setEnabled(Option opt, boolean on) {
        if (on)
            enable(opt);
        else
            disable(opt);
    }

    private void enableDefaultOptions() {
        for (Option opt : Option.values())
            if (opt.onByDefault)
                this.options.add(opt);
    }
}
