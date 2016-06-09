define([ 't5/core/dom', 't5/core/events' ], function(dom, events) {
	return function(elemId, zoneId, event, url) {
		var zone = dom(zoneId);
		if (!zone) {
			throw new Error('Invalid zone container');
		}

		var target = dom(elemId);
		target.on(event, function(event, parameter) {
			// use any received parameter or the field value
			var value = parameter || this.value();
			zone.trigger(events.zone.refresh, {
				url : url,
				parameters : {
					value : value
				}
			});
		});
	};
});
