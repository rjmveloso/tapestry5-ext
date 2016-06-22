package net.maskbit.tapestry.mixins;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.InjectContainer;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.corelib.components.DateField;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.ThreadLocale;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.thoughtworks.selenium.webdriven.commands.Check;

/**
 * This mixin allows to trigger an event when a datefield value is changed
 * 
 * @author rveloso
 */
public class DateTrigger {

	@Environmental
	private JavaScriptSupport javaScriptSupport;

	@Inject
	private ThreadLocale locale;

	/**
	 * The element we attach ourselves to
	 */
	@InjectContainer
	private DateField field;

	/**
	 * Event name to be triggered when datefield value has changed
	 * 
	 * @see jQuery.trigger()
	 */
	@Parameter(required = true, allowNull = false, value = "change")
	private String eventType;

	/**
	 * Format to be used by moment.js
	 * 
	 * @see Check moment.js to see date formats
	 */
	@Parameter(required = true, allowNull = false)
	private String format;

	String defaultFormat() {
		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, locale.getLocale());
		return ((SimpleDateFormat) format).toPattern();
	}

	@BeginRender
	void beginRender(MarkupWriter writer) {
		writer.attributes("data-component-wrapper", "datefield");
	}

	@AfterRender
	void afterRender() {
		String format = this.format.toUpperCase();
		javaScriptSupport.require("t5/mb/ext/datetrigger").with(format, eventType);
	}
}
