(function() {
	define([ 't5/core/dom', 'moment', './datefieldwrapper' ], function(dom, moment, dtrigger) {
		var dformat;
		
		var set = function(elem, date) {
			var value = exports.format(date, dformat);
			elem.value(value);
		}
		
		var parse = function(timestamp, format) {
			return moment(timestamp, format);
		};
		
		var format = function(timestamp, format) {
			return moment(timestamp).format(format);
		};
		
		var validate = function(sdateId, edateId) {
			var startDateElem = dom(sdateId);
			var endDateElem = dom(edateId);

			startDateElem.on('change', function(event, value) {
				var reference = endDateElem.value();
				if(value && reference) {
					var ds = exports.parse(value, dformat);
					var de = exports.parse(reference, dformat);
					if(ds.isAfter(de)) {
						set(endDateElem, ds);
					}
				}
				return true;
			});

			endDateElem.on('change', function(event, value) {
				var reference = startDateElem.value();
				if(value && reference) {
					var de = exports.parse(value, dformat);
					var ds = exports.parse(reference, dformat);
					if(de.isBefore(ds)) {
						set(startDateElem, de);
					}
				}
				return true;
			});
		}
		
		return exports = {
			parse: parse,
			format: format,
			validate: function(startDateId, endDateId, format) {
				dformat = format;
				dtrigger(format);
				validate(startDateId, endDateId);
			}
		};
	});
}).call(this);