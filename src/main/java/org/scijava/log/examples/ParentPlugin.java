package org.scijava.log.examples;

import net.miginfocom.swing.MigLayout;
import org.scijava.Context;
import org.scijava.command.Command;
import org.scijava.command.CommandService;
import org.scijava.log.Logger;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.swing.console.LoggingPanel;

import javax.swing.*;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * Example for a SciJava plugin, that has it's own {@link LoggingPanel}
 * and executes a "child" plugin. The log messages of the parent & child
 * plugin appear in the {@link LoggingPanel}.
 *
 * @author Matthias Arzt
 */
@Plugin( type = Command.class )
public class ParentPlugin implements Command
{
	@Parameter(label = "parent plugin")
	private Logger logger;

	@Parameter
	private Context context;

	@Parameter
	private CommandService commandService;

	@Override
	public void run()
	{
		JFrame frame = initFrame();
		frame.setVisible( true );
	}

	private JFrame initFrame()
	{
		JFrame frame = new JFrame();
		frame.setTitle( "Parent Plugin" );
		frame.setSize( 800, 500 );
		frame.setDefaultCloseOperation( DISPOSE_ON_CLOSE );
		frame.setLayout( new MigLayout("", "[grow]", "[][][grow]") );
		frame.add( new JLabel("This is an example of an versatile plugin with it's own logging window."), "wrap");
		frame.add( initRunButton(), "wrap" );
		frame.add( initLoggingPanel(), "wrap, grow" );
		return frame;
	}

	private LoggingPanel initLoggingPanel()
	{
		LoggingPanel loggingPanel = new LoggingPanel( context );
		logger.addLogListener( loggingPanel );
		return loggingPanel;
	}

	private JButton initRunButton()
	{
		JButton button = new JButton( "Run child plugin", null );
		button.addActionListener( action -> runChildPlugin() );
		return button;
	}

	private void runChildPlugin()
	{
		logger.info( "run child plugin" );
		Logger childLogger = logger.subLogger( "child plugin" );
		commandService.run( HelloWorldPlugin.class, true, "logger", childLogger )	;
	}
}
