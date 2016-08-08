(function() {
	define([ 'jquery' ], function($) {
		return function(startDateId, endDateId) {
			var sd = $(startDateId).val();
			var ed = $(endDateId).val();
			
			if(sd && ed && sd !== ed) {
				var ref;
				if(Date.parse(sd) > Date.parse(ed)) {
					ref = $(endDateId);
				} else {
					ref = $(startDateId);
				}
				
				ref.val(sd);
				ref.trigger('change');
			}
			return true;
		};
	});
}).call(this);