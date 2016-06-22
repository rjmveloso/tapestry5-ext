(function() {
	define([], function() {
		Number.prototype.match = function(regexp) {
			var result = this.toString().match(regexp);
			for(i = 0; i < result.length; i++) {
				result[i] = parseInt(result[i]);
			}
			return result;
		}
	});
}).call(this);