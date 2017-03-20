package com.cmz.concurrent.design;

public class PrintService {
	private static PrintService[] services__;

	protected PrintService neighbor_ = null; // node to take from
	protected Printer printer_ = null; // unique

	public synchronized void print(String doc) {
		if (printer_ == null) // get printer from neighbor
			printer_ = neighbor_.takePrinter();
		printer_.printDocument(doc);
	}

	synchronized Printer takePrinter() { // called from others
		if (printer_ != null) {
			Printer p = printer_; // implement take protocol
			printer_ = null;
			return p;
		} else
			return neighbor_.takePrinter(); // propagate
	}

	// initialization methods called only from start-up
	synchronized void setNeighbor(PrintService n) {
		neighbor_ = n;
	}

	synchronized void givePrinter(Printer p) {
		printer_ = p;
	}

	public static PrintService at(int i) {
		return services__[i];
	}

	// initialize a ring of new services
	public synchronized static void startUpServices(int nServices, Printer p) {
		if (nServices <= 0 || p == null)
			throw new IllegalArgumentException();

		services__ = new PrintService[nServices];
		for (int i = 0; i < nServices; ++i)
			services__[i] = new PrintService();

		for (int i = 0; i < nServices; ++i)
			services__[i].setNeighbor(services__[(i + 1) % nServices]);

		services__[0].givePrinter(p);
	}
}
