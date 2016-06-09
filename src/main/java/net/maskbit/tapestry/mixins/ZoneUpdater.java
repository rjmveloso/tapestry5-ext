package net.maskbit.tapestry.mixins;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

/**
 * This mixin allows to update a zone when the event attached to the component
 * is triggered
 * 
 * @author rveloso
 *
 */
public class ZoneUpdater {

	@Inject
	private ComponentResources componentResources;

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	/**
	 * The element we attach ourselves to
	 */
	@InjectContainer
	private ClientElement element;

	/**
	 * The event to listen for on the client.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
	private String eventType;

	/**
	 * The event to listen for in your component class
	 * 
	 * TODO: If not specified this will defaults to
	 * <code>element.getClientId() + $event</code>
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
	private String event;

	/**
	 * The zone to be updated by us.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, required = true)
	private String zone;

	/**
	 * Context that will be made available to event handler method of this
	 * component
	 */
	@Parameter
	private Object[] context;

	/**
	 * Set secure to true if https is being used, else set to false.
	 */
	@Parameter(defaultPrefix = BindingConstants.LITERAL, value = "false")
	private boolean secure;

	String defaultEvent() {
		return element.getClientId() + capitalize(eventType);
	}

	private String capitalize(String value) {
		return Character.toUpperCase(value.charAt(0)) + value.substring(1);
	}

	@AfterRender
	void afterRender() {
		String listenerURI = componentResources.createEventLink(event, context).toAbsoluteURI(secure);
		javaScriptSupport.require("t5/mb/ext/zoneupdater").with(element.getClientId(), zone, eventType, listenerURI);
	}
}
