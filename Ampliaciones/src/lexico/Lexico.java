/* The following code was generated by JFlex 1.4.1 on 14/05/18 19:01 */

// ************  C�digo a incluir ********************

package lexico;
import sintactico.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 14/05/18 19:01 from the specification file
 * <tt>lexico/lexico.jflex</tt>
 */
public class Lexico {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\24\1\13\2\0\1\24\22\0\1\24\1\26\3\0\1\25"+
    "\1\30\1\11\2\25\1\23\1\21\1\25\1\21\1\17\1\22\1\2"+
    "\1\3\1\4\1\50\1\5\1\6\1\7\3\1\1\25\1\25\1\32"+
    "\1\27\1\33\1\25\1\0\4\10\1\20\25\10\1\25\1\12\1\25"+
    "\1\34\1\16\1\0\1\37\1\10\1\51\1\40\1\36\1\45\1\10"+
    "\1\43\1\42\2\10\1\44\1\54\1\14\1\47\2\10\1\35\1\46"+
    "\1\15\1\53\1\52\1\41\3\10\1\25\1\31\1\25\103\0\1\10"+
    "\7\0\1\10\3\0\1\10\3\0\1\10\1\0\1\10\6\0\1\10"+
    "\6\0\1\10\7\0\1\10\3\0\1\10\3\0\1\10\1\0\1\10"+
    "\6\0\1\10\uff05\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\1\1\4\5\5\2\1"+
    "\3\5\12\3\1\6\3\0\1\4\1\0\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\2\3\1\17"+
    "\3\3\1\20\7\3\1\6\1\0\1\21\6\0\5\3"+
    "\1\22\1\3\1\23\3\3\1\24\1\3\1\0\1\25"+
    "\1\26\1\3\1\27\1\30\3\3\1\31\1\3\1\32"+
    "\1\33\1\3\1\34\1\35\2\3\1\36\1\3\1\37"+
    "\1\40";

  private static int [] zzUnpackAction() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\55\0\132\0\207\0\264\0\55\0\55\0\341"+
    "\0\u010e\0\u013b\0\u0168\0\u0195\0\u01c2\0\u01ef\0\u021c\0\u0249"+
    "\0\u0276\0\u02a3\0\u02d0\0\u02fd\0\u032a\0\u0357\0\u0384\0\u03b1"+
    "\0\u03de\0\u040b\0\u0438\0\u0465\0\u0492\0\u04bf\0\u04ec\0\u0519"+
    "\0\55\0\55\0\55\0\55\0\55\0\55\0\55\0\55"+
    "\0\u0546\0\u0573\0\207\0\u05a0\0\u05cd\0\u05fa\0\207\0\u0627"+
    "\0\u0654\0\u0681\0\u06ae\0\u06db\0\u0708\0\u0735\0\u0762\0\u0762"+
    "\0\55\0\u078f\0\u07bc\0\u07e9\0\u0816\0\u0843\0\u0870\0\u089d"+
    "\0\u08ca\0\u08f7\0\u0924\0\u0951\0\207\0\u097e\0\207\0\u09ab"+
    "\0\u09d8\0\u0a05\0\207\0\u0a32\0\u0a5f\0\55\0\55\0\u0a8c"+
    "\0\207\0\207\0\u0ab9\0\u0ae6\0\u0b13\0\207\0\u0b40\0\207"+
    "\0\207\0\u0b6d\0\207\0\207\0\u0b9a\0\u0bc7\0\207\0\u0bf4"+
    "\0\207\0\207";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\7\3\1\4\1\5\1\2\1\6\2\4\1\2"+
    "\1\7\1\4\1\7\1\10\1\11\1\6\1\7\1\12"+
    "\1\13\1\14\1\15\1\16\1\17\1\20\1\21\1\22"+
    "\1\4\1\23\1\24\1\25\2\4\1\26\1\27\1\4"+
    "\1\3\1\30\1\31\1\4\1\32\56\0\7\3\7\0"+
    "\1\33\1\34\15\0\1\34\11\0\1\3\5\0\10\4"+
    "\3\0\3\4\1\0\1\4\14\0\20\4\12\35\1\36"+
    "\1\0\41\35\22\0\1\37\1\40\54\0\1\41\60\0"+
    "\1\42\54\0\1\43\55\0\1\44\55\0\1\45\52\0"+
    "\1\46\54\0\1\47\61\0\1\50\21\0\10\4\3\0"+
    "\3\4\1\0\1\4\14\0\1\4\1\51\16\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\7\4\1\52"+
    "\10\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\12\4\1\53\5\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\1\54\5\4\1\55\11\4\1\0\10\4"+
    "\3\0\1\56\2\4\1\0\1\4\14\0\10\4\1\57"+
    "\7\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\7\4\1\60\2\4\1\61\3\4\1\62\1\4\1\0"+
    "\10\4\3\0\1\4\1\63\1\4\1\0\1\4\14\0"+
    "\20\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\6\4\1\64\11\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\2\4\1\65\15\4\1\0\10\4\3\0"+
    "\3\4\1\0\1\4\14\0\2\4\1\66\15\4\1\0"+
    "\7\33\10\0\1\34\15\0\1\34\11\0\1\33\5\0"+
    "\7\67\11\0\1\70\26\0\1\67\15\0\1\71\44\0"+
    "\1\72\1\35\1\73\1\74\3\72\1\0\1\71\2\0"+
    "\1\75\1\76\32\0\1\72\4\0\13\37\1\0\41\37"+
    "\23\40\1\77\31\40\1\0\10\4\3\0\1\4\1\100"+
    "\1\4\1\0\1\4\14\0\2\4\1\101\15\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\11\4\1\102"+
    "\6\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\5\4\1\103\12\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\5\4\1\104\12\4\1\0\10\4\3\0"+
    "\1\4\1\105\1\4\1\0\1\4\14\0\20\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\12\4\1\106"+
    "\5\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\1\107\17\4\1\0\10\4\3\0\1\110\2\4\1\0"+
    "\1\4\14\0\20\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\1\111\17\4\1\0\10\4\3\0\3\4"+
    "\1\0\1\4\14\0\2\4\1\112\15\4\1\0\10\4"+
    "\3\0\3\4\1\0\1\4\14\0\1\113\17\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\5\4\1\114"+
    "\12\4\1\0\7\67\40\0\1\67\5\0\7\35\1\0"+
    "\1\71\36\0\1\35\5\0\7\72\1\0\1\71\36\0"+
    "\1\72\5\0\1\35\4\72\1\115\1\35\1\0\1\71"+
    "\36\0\1\72\15\0\1\116\54\0\1\117\43\0\22\40"+
    "\1\6\1\77\31\40\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\16\4\1\120\1\4\1\0\10\4\3\0"+
    "\3\4\1\0\1\4\14\0\3\4\1\121\14\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\1\4\1\122"+
    "\16\4\1\0\10\4\3\0\1\4\1\123\1\4\1\0"+
    "\1\4\14\0\20\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\7\4\1\124\10\4\1\0\10\4\3\0"+
    "\3\4\1\0\1\4\14\0\2\4\1\125\15\4\1\0"+
    "\10\4\3\0\3\4\1\0\1\4\14\0\14\4\1\126"+
    "\3\4\1\0\10\4\3\0\3\4\1\0\1\4\14\0"+
    "\16\4\1\127\1\4\1\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\1\130\17\4\1\0\10\4\3\0\1\131"+
    "\2\4\1\0\1\4\14\0\20\4\2\0\6\35\1\0"+
    "\1\71\36\0\1\35\5\0\10\4\3\0\3\4\1\0"+
    "\1\4\14\0\1\132\17\4\1\0\10\4\3\0\3\4"+
    "\1\0\1\4\14\0\1\4\1\133\16\4\1\0\10\4"+
    "\3\0\3\4\1\0\1\4\14\0\1\4\1\134\16\4"+
    "\1\0\10\4\3\0\1\4\1\135\1\4\1\0\1\4"+
    "\14\0\20\4\1\0\10\4\3\0\3\4\1\0\1\4"+
    "\14\0\14\4\1\136\3\4\1\0\10\4\3\0\1\137"+
    "\2\4\1\0\1\4\14\0\20\4\1\0\10\4\3\0"+
    "\3\4\1\0\1\4\14\0\13\4\1\140\4\4\1\0"+
    "\10\4\3\0\1\4\1\141\1\4\1\0\1\4\14\0"+
    "\20\4\1\0\3\4\1\142\4\4\3\0\3\4\1\0"+
    "\1\4\14\0\20\4";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3105];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\3\1\2\11\24\1\3\0\1\1\1\0"+
    "\10\11\17\1\1\0\1\11\6\0\15\1\1\0\2\11"+
    "\23\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[98];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
// ************  Atributos y m�todos ********************
// * Para acceder al n�mero de l�nea (yyline es package)
public int getLinea() { 
	// * Flex empieza en cero
	return yyline+1;
}

// * Para acceder al n�mero de columna (yycolumn es package)
public int getColumna() { 
	// * Flex empieza en cero
	return yycolumn+1;
}

// * Valor semantico del token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}



  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexico(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Lexico(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 186) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 7: 
          { this.yylval = yytext();	return Parser.POW;
          }
        case 33: break;
        case 26: 
          { this.yylval = yytext();	return Parser.CHAR;
          }
        case 34: break;
        case 8: 
          { this.yylval = yytext();	return Parser.NOT_EQ;
          }
        case 35: break;
        case 23: 
          { this.yylval = yytext();	return Parser.READ;
          }
        case 36: break;
        case 32: 
          { this.yylval = yytext();	return Parser.FLOAT;
          }
        case 37: break;
        case 21: 
          { this.yylval = '\n';	return Parser.CTE_CHAR;
          }
        case 38: break;
        case 6: 
          { this.yylval = new Float(yytext());	return Parser.CTE_REAL;
          }
        case 39: break;
        case 30: 
          { this.yylval = yytext();	return Parser.RETURN;
          }
        case 40: break;
        case 16: 
          { this.yylval = yytext();	return Parser.IF;
          }
        case 41: break;
        case 18: 
          { this.yylval = yytext();	return Parser.INT;
          }
        case 42: break;
        case 24: 
          { this.yylval = yytext();	return Parser.ELSE;
          }
        case 43: break;
        case 1: 
          { System.err.println("Error in line: ["+getLinea()+"] column: ["+getColumna()+ "] --> "+yytext().toString() );
          }
        case 44: break;
        case 20: 
          { this.yylval = yytext();	return Parser.VAR;
          }
        case 45: break;
        case 2: 
          { this.yylval = new Integer(yytext());	return Parser.CTE_ENTERA;
          }
        case 46: break;
        case 28: 
          { this.yylval = yytext();	return Parser.WRITE;
          }
        case 47: break;
        case 14: 
          { this.yylval = yytext();	return Parser.XOR;
          }
        case 48: break;
        case 5: 
          { this.yylval = yytext();	return (int)yytext().charAt(0);
          }
        case 49: break;
        case 31: 
          { this.yylval = yytext();	return Parser.STRUCT;
          }
        case 50: break;
        case 15: 
          { this.yylval = yytext();	return Parser.DO;
          }
        case 51: break;
        case 27: 
          { this.yylval = yytext(); 	return Parser.MAIN;
          }
        case 52: break;
        case 10: 
          { this.yylval = yytext();	return Parser.AND;
          }
        case 53: break;
        case 11: 
          { this.yylval = yytext(); 	return Parser.OR;
          }
        case 54: break;
        case 22: 
          { this.yylval = '\t';	return Parser.CTE_CHAR;
          }
        case 55: break;
        case 3: 
          { this.yylval = new String(yytext());	return Parser.ID;
          }
        case 56: break;
        case 19: 
          { this.yylval = yytext();	return Parser.FOR;
          }
        case 57: break;
        case 13: 
          { this.yylval = yytext();	return Parser.G_EQ;
          }
        case 58: break;
        case 17: 
          { this.yylval = yytext().charAt(1);	return Parser.CTE_CHAR;
          }
        case 59: break;
        case 9: 
          { this.yylval = yytext();	return Parser.EQ;
          }
        case 60: break;
        case 29: 
          { this.yylval = yytext();	return Parser.WHILE;
          }
        case 61: break;
        case 12: 
          { this.yylval = yytext();	return Parser.L_EQ;
          }
        case 62: break;
        case 25: 
          { this.yylval = yytext();	return Parser.FUNC;
          }
        case 63: break;
        case 4: 
          { 
          }
        case 64: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}