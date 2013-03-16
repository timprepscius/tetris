package tetris.imp.gwt;

public class ConstantsGwt {

	static final boolean PRODUCTION = Bridge.isProduction();
	
	static final String HOST;
	static final int PORT;
	
	static {
		if (PRODUCTION)
		{
			HOST = "mail.mailiverse.com";
			PORT = 80;
		}
		else
		{
			HOST = "red";
			PORT = 8080;
		}
	}
}
