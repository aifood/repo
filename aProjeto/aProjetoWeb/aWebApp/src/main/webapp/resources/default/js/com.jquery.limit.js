(function($){ 
    $.fn.extend({  
        limit: function(element) {
			
			var interval;
			var self = $(this);
			
			$(this).focus(function(){
				interval = window.setInterval("100");
			});
			
			$(this).keyup(function(){
				clearInterval(interval);
				$.each(self, limitsize);
			});
			
			function limitsize(index, valor){
				var length = $(valor).val().length;
				var limit = $(valor).attr("class").substr(0,4);
				
				if(length > limit) {
					$(valor).val($(valor).val().substring(0, limit));
				}
				
				return true;
			}
       } 
   });
})(jQuery);