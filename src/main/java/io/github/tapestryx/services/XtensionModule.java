package io.github.tapestryx.services;

import org.apache.tapestry5.annotations.Path;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.Resource;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Core;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StackExtension;

/**
 * This module overrides datefield component and confirm-click mixin in order to
 * resolve some blocking issues due to the closed API of these modules
 * <code>datefield.js</code> triggers a <code>change</code> event when a date is
 * selected and the popup is hidden. <code>confirm-click.js</code> appends
 * dialog to body just before any event is treated.
 * 
 * @see https://issues.apache.org/jira/browse/TAP5-2547
 * 
 *      To use these scripts import this module into the application main module
 * 
 *      As alternative for datefield take a look to
 *      <a href="https://github.com/got5/tapestry5-jquery"> They have 5*
 *      components and they are under active development
 * 
 * @author rveloso
 *
 */
public class XtensionModule {

	@Core
	@Contribute(JavaScriptStack.class)
	public static void setupCoreJavaScriptStack(OrderedConfiguration<StackExtension> configuration,
			@Inject @Path("/META-INF/modules/tapestryx/datefield.js") Resource datefield,
			@Inject @Path("/META-INF/modules/tapestryx/confirm-click.js") Resource confirm) {
		// replace for our own module
		configuration.add("t5/core/datefield", StackExtension.module(datefield.getFile()));
		configuration.add("t5/core/confirm-click", StackExtension.module(confirm.getFile()));
	}

	//	@Contribute(ModuleManager.class)
	//	public static void overrideShimModules(MappedConfiguration<String, JavaScriptModuleConfiguration> configuration,
	//			@Inject @Path("/META-INF/modules/tapestryx/datefield.js") Resource datefield,
	//			@Inject @Path("/META-INF/modules/tapestryx/confirm-click.js") Resource confirm) {
	//		configuration.add("t5/core/datefield", new JavaScriptModuleConfiguration(datefield));
	//		configuration.add("t5/core/confirm-click", new JavaScriptModuleConfiguration(confirm));
	//	}

}
