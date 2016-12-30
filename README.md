# Tapestry 5.4 extension utilities
This package gives a set of scripts to allow a zone to be triggered by a datefield change

New datefield component is based on DatePicker and has a RequireJS wrapper which closes the access to the interaction between component and DatePicker.  
`datetrigger.js` detects datefield component that was changed and triggers a defined event.  
`zoneupdater.js` allows to register an handler for the defined event and triggers a zone refresh event.

## Example of usage: datetrigger
Configure your component - tml.
```html
<input t:type="datefield" t:id="date" t:mixins="datetrigger" t:trigger="change" />
```

Configure your component - Java
```java
@AfterRender
void afterRender() {
	// moment js format
	String dformat = "YYYY-MM-DD"; // get by locale
	javaScriptSupport.require("t5/mb/ext/datetrigger").with(dformat);
});
```

## Example of usage: zoneupdater
Configure your component - tml.
```html
<input t:type="datefield" t:id="date" t:mixins="zoneupdater"
	t:on="change" t:event="valueChangedFromDate" t:zone="container" />
```

Configure your component - Java
```java
@OnEvent(value = "valueChangedFromDate")
void onValueChangedFromDate() {
	String value = request.getParameter("value");
	...
}
```
