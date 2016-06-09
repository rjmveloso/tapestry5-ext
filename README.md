# Tapestry 5.4 extension utilities
This package gives a set of scripts to allow a zone to be triggered by a datefield change

New datefield component is based on DatePicker and has a RequireJS wrapper which closes the access to the interaction between component and DatePicker.
`datefieldwrapper.js` detects the datefield that was changed and triggers a defined event.
`zoneupdater.js` allows to register an handler for the defined event and triggers a zone refresh event.

=== Example of usage
Add `data-component-wrapper="datefield"` atribute to datefield component.
This is used as a common strategy for other Tapestry components, which allows the dom scanner to detect new datefield components if those are inside the zone to be updated.

Include this by the after render lifecycle stage
----
@AfterRender
void afterRender() {
ajaxResponseRenderer.addCallback(new JavaScriptCallback() {
	private String getDateFormat() {
		return "yyyy-MM-dd"; // get by locale
	}
	
	@Override
	public void run(JavaScriptSupport javaScriptSupport) {
		// format to be used by moment.js
		String dformat = getDateFormat().toUpperCase();
		javaScriptSupport.require("t5/mb/ext/datefieldwrapper").with(dformat, "blur");
	}
});
----

Configure your component. Notice that events MUST match
----
	<input t:type="datefield" t:id="date" t:mixins="zoneupdater" t:zone="container"
		t:clientEvent="blur" t:serverEvent="dateChanged" data-component-wrapper="datefield" />
----

