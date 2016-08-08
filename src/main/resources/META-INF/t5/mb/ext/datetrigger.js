(function() {
	define([ 't5/core/datepicker', 't5/core/dom', 'moment', 'jquery' ], function(DatePicker, dom, moment, $) {
		var datepicker = null, visible = false;
		
		// watch out => this makes use of internal jQuery structure
		$.fn.bindFirst = function(name, fn) {
			this.bind(name, fn);
			
			var event = name.split('.')[0];
			for(i = 0; i < this.length; i++) {
				var elem = this[i];
				var handlers = $._data(elem).events[event];
				handlers.unshift(handlers.pop());
			}
		};
		
		$(document).ready(function() {
		    dom.scanner('[data-component-wrapper="datefield"]', function(container) {
		        container.attr("data-component-wrapper", null);
		        var button = container.parent().findFirst('button');
		        button.$.bindFirst('click', function() {
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