(function() {
	define([ 't5/core/datepicker', 't5/core/dom', 'moment', 'jquery' ], function(DatePicker, dom, moment, $) {
		var datepicker = null, visible = false;
		
		$(document).ready(function() {
		    dom.scanner('[data-component-wrapper="datefield"]', function(container) {
		        container.attr("data-component-wrapper", null);
		        container.parent().findFirst('button').on('click', function() {
					datepicker = container.$;
				});
		    });
		});
		
		return function(format, event) {
			event = event || 'change'; 
			DatePicker.prototype.onchange = function() {
				// datepicker.val() to ckeck fisrt use
				if (datepicker && (!datepicker.val() || visible)) {
					var date = this.getDate();
					var value = moment(date).format(format);
					datepicker.trigger(event, value);
					visible = false;
				} else {
					visible = true;
				}
			};
		}
	});
}).call(this);