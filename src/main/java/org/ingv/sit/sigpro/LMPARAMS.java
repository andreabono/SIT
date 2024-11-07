
package org.ingv.sit.sigpro;


/**
 *
 *
 * @author Andrea Bono
 * I.N.G.V. Istituto Nazionale di Geofisica e Vulcanologia
 * O.N.T. Osservatorio Nazionale Terremoti
 * Via di Vigna Murata 605
 * 00143 Rome
 * ITALY
 *
 * andrea.bono@ingv.it
 * +39 0651860290
 * 
 */
public class LMPARAMS {
    public 
        double maxDist;         /* Maximum epicentral distance for a station       */
        double waitTime;       /* Seconds from origin to wait for wave propagation */
        int    waitNow;	/* 1 = wait from now, 0 = wait from origin time      */
        double SgSpeed;         /* Speed (km/sec of Sg phase                       */
        int    searchTimesModeFlag; /* LM_MODE_DEFAULT, LM_MODE_SEARCHTIMES or LM_MODE_TAUS_FUNC   */
        double peakSearchStart; /* Fraction of P - S to search for peak before S   */
        double peakSearchStartMin; /* Minimum number of seconds to search before S */
        double peakSearchEnd;   /* Fraction of P - S to search for peak after S    */
        double peakSearchEndMin; /* Minimum numberr of seconds to search after S   */
        double K1TauS;              /* K1 in searchTimesTauSFunc formula */
        double K2TauS;              /* K2 in searchTimesTauSFunc formula */
        double peakSearchStartTauS; /* Seconds before estimated P to search for peak */
        double peakSearchEndTauS;   /* Seconds after searchTimesTauSFunc value to search for peak */
        double traceDeltaStartTauS; /* Seconds before peakSearchStart() */
        double traceDeltaEndTauS;   /* Seconds after  peakSearchEnd() */
        double slideLength;     /* Length of sliding window in seconds             */
        double traceStart;      /* Seconds before P_est to start trace             */
        double traceEnd;        /* Seconds after S_est to end trace                */
        double z2pThresh;       /* Signal-to-noize ratio threshold to pick amps    */
      // MTH: Added for linear correction to ML
        double LC_slope;        /* Slope of linear correction [Default = 1.0]      */ 
        double LC_intercept;    /* Intercept of linear correction [Default = 0]    */ 
        double LC_minML;        /* Apply linear correction if minML < ML < maxML   */
        double LC_maxML;        /* Apply linear correction if minML < ML < maxML   */
//
//  DBACCESS *pDBaccess;    /* access to the database                          */
//  SCNLSEL *pAdd;           /* SCN selections lists                            */
//  SCNLSEL *pDel;           /* SCN deletion lists                              */
//  SCNLPAR *pSCNLPar;        /* Array of SCNL parameter structures               */
//  WA_PARAMS *pWA;         /* optional Wood-Anderson coefficients             */
//  WS_ACCESS *pWSV;        /* wave_server access information                  */
//  LMEW *pEW;
        long maxTrace;          /* Maximum number of trace data points             */
        int HeartBeatInterval;  /* Earthworm heartbeat interval in seconds         */
        int debug;              /* debug level                                     */
        int fEWTP;              /* Flag to use earthworm transport (1) or not (0)  */
        int fDist;              /* Flag for distance in logA0 relation             */
        int fGetAmpFromSource;  /* TRUE if amplitudes are to be read from source   */
        int fMeanCompMags;      /* TRUE if taking mean of component magnitudes     */
        int fWAsource;          /* TRUE if source contains Wood-Anderson traces    */
        int eventSource;        /* source of event location and time               */
        int minStationsMl;      /* minimum number of stations for Ml Avg reporting */
        int require2Horizontals;      /* Flag (1) to require 2 horiz channels for Ml from a sta or (0)-default to not */
        int allowVerticals;      /* Flag (1) to Z channels for Ml from a sta or (0)-default to not */
        int useMedian;          /* Flag (1) to use median instead of mean (default)*/
        int maxSCNLPar;          /* How many SCN parameter entries allocated        */
        int maxSta;             /* How many stations to use                        */
        int nLtab;              /* Number of entries in la0tab                     */
        int numSCNLPar;          /* Number of SCN parameter entries used            */
        int outputFormat;       /* type of output for localmag                     */
        int respSource;         /* source of instrument response data              */
        int saveTrace;          /* whether and how to save Wood-Anderson traces    */
        int searchStartPhase;   /* phase (P or S) to use for search start calcs    */
        int staLoc;             /* station location source                         */
        int traceSource;        /* source for trace data                           */
        int wsTimeout;          /* wave_server timeout in milliseconds             */
        int eventXML;           /* Flag (1=output event xml) default is (0) not to     */
        int saveSCNL;           /* Flag (1=output L of SCNL in message) (0) to just do SCN (old style)     */
//  char *saveXMLdir;	  /* if set, use this dir instead of sacOutDir  for option above...automagically turns on eventXML flag */
//  enum Version LookAtVersion;	 /* Which version to look at, default is vAll */
//  char *eventID;          /* event ID, for accessing event from EW databese  */
//  char *loga0_file;       /* Nmae of the logA0 table file                    */
//  char *outputNameFormat; /* format of output filenames                      */
//  char *respDir;          /* directory for response files, if needed         */
//  char *respNameFormat;   /* format of response filename                     */
//  char *sacInDir;         /* directory for reading and writing SAC files     */
//  char *sacOutDir;        /* Where to write SAC output files                 */
//  char *saveDirFormat;    /* format string for SAC output directory          */
//  char *saveNameFormat;   /* format of WA traces filenames                   */
//  char *sourceNameFormat; /* format string of SAC filenames                  */
//  char *staLocFile;       /* station location filename, if needed            */
//  char *outputFile;       /* output filename, if needed                      */
//  char *MlmsgOutDir;      /* directory for writing Mlmsg                     */
//#ifdef UW
//  char *UWpickfile;       /* name of UW-format pickfile                      */
//#endif
//  char ChannelNumberMap[5];	/* map channel numbers to LM_ orientation codes */
        int  SkipStationsNotInArc; /* flag for excluding stations that are not in ARC message */
        double MinWeightPercent;   /* MinWeightPercent based on the maximum weight of
                                      ther ARC message.  Used only with SkipStationsNotInArc */
        double MLQpar1;            /* First parameter for computing the quality of magnitude */
}
