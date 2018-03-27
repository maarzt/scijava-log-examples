package org.scijava.log.examples;

import org.scijava.command.Command;
import org.scijava.log.Logger;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example for a very simple SciJava plugin. That logs a "Hello World" message.
 *
 * @author Matthias Arzt
 */
@Plugin(type = Command.class)
public class HelloWorldPlugin implements Command {

	private static AtomicInteger counter = new AtomicInteger();

	@Parameter
	Logger logger;

	@Override
	public void run() {
		logger.info("Hello World! (" + counter.incrementAndGet() + ")");
	}
}
