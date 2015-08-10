/*
* @Copyright (c) 2008 Aurélio Saraiva (aureliosaraiva@gmail.com)
* @Page http://inovaideia.com.br/maskInputMoney

* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use,
* copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the
* Software is furnished to do so, subject to the following
* conditions:
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
* OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
* HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
* WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
* OTHER DEALINGS IN THE SOFTWARE.
*/

/*
 * @Version: 0.2
 * @Release: 2008-07-25
 */
(function($) {
    $.fn.maskPercent = function(settings) {
        settings = $.extend({
            decimal: ".",
            precision: 2,
            thousands: ","
        }, settings);

        return this.each(function() {
            var input=$(this);
            function money(e) {
                e=e||window.event;
                var k=e.charCode||e.keyCode||e.which;
                if (k == 8) { // tecla backspace
                    preventDefault(e);
                    var x = input.val().substring(0,input.val().length-1);
                    if ((x == "0,0")|| (x == "")){
                        input.val(x);
                    }else if (x == "0," ){                            x = "0";
                        input.val(x);
                    }else{
                        input.val(maskValue(x));
                    }
                    return false;
                } else if (k == 9) { // tecla tab
                    return true;
                }
                if (k < 48 || (k > 57 && k < 96)) {
                    preventDefault(e);
                    return true;
                }               
                if (input.attr("maxlength") > 0){
                    if(input.val().length > input.attr("maxlength"))
                        return false;
                }

                var key = k; 
                if ($.browser.msie){
                    key = transformaParaUnicode(k);
                }
                key = String.fromCharCode(key);  // Valor para o código da Chave
                preventDefault(e);
                input.val(maskValue(input.val()+key));
            }

            function preventDefault(e) {
                if (e.preventDefault) { //standart browsers
                    e.preventDefault();
                } else { // internet explorer
                    e.returnValue = false;
                }
            }
                        function transformaParaUnicode(k){
                var chave = k;
                if ((chave >= 96) && (chave <=105)){
                    chave = chave - 48;
                }
                return chave;
            }

            function maskValue(v) {
                var a = '';
                var strCheck = '0123456789';
                var len = v.length;
                var t = "";
                if (len == 0) {
                    t = "0.00";
                }
                for (var i = 0; i < len; i++)
                    if ((v.charAt(i) != '0') && (v.charAt(i) != settings.decimal))
                        break;

                for (; i < len; i++) {
                    if (strCheck.indexOf(v.charAt(i))!=-1) a+= v.charAt(i);
                }

                var n = parseFloat(a);
                n = isNaN(n) ? 0 : n/Math.pow(10, settings.precision);
                t = n.toFixed(settings.precision);

                var p, d = (t=t.split("."))[1].substr(0, settings.precision);
                for (p = (t=t[0]).length; (p-=3) >= 1;) {
                    t = t.substr(0,p) + settings.thousands + t.substr(p);
                }
                return t+settings.decimal+d+Array(
                        (settings.precision+1)-d.length).join(0);
            }
                        function blurEvent() {
                if ((input.val() == "0,0") || (input.val() == "0")){
                    input.val("0,00");
                }
            }

            function getDefaultMask() {
                var n = parseFloat("0")/Math.pow(10, settings.precision);
                return (n.toFixed(settings.precision)).replace(
                        new RegExp("\\.", "g"), settings.decimal);
            }

            if ($.browser.msie){
                input.bind("keydown",money);
            }else if ($.browser.mozilla){
                input.bind("keypress",money);
            }
            input.bind("blur",blurEvent);

            input.one("unmaskMoney",function() {
                if ($.browser.msie){
                    input.unbind("keydown",money);
                    this.onpaste= null;
                }else if ($.browser.mozilla){
                    input.unbind("keypress",money);
                    this.removeEventListener('input',blurEvent,false);
                }
            });
        });
    }

    $.fn.unmaskMoney=function() {
        return this.trigger("unmaskMoney");
    };
})(jQuery);
