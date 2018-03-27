package org.scijava.log.examples;

import net.imagej.ImageJ;

/**
 * Start the demo of a {@link ParentPlugin}
 *
 * @author Matthias Arzt
 */
public class Main
{
	public static void main(String... args)
	{
		ImageJ imageJ = new ImageJ();
		imageJ.ui().showUI();
		imageJ.command().run( ParentPlugin.class, true );
	}
}
