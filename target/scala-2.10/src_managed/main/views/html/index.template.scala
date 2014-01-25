
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import play.api.i18n._
import play.api.mvc._
import play.api.data._
import views.html._
import org.bson.types.ObjectId
/**/
object index extends BaseScalaTemplate[play.api.templates.HtmlFormat.Appendable,Format[play.api.templates.HtmlFormat.Appendable]](play.api.templates.HtmlFormat) with play.api.templates.Template1[String,play.api.templates.HtmlFormat.Appendable] {

    /**/
    def apply/*1.2*/(message: String):play.api.templates.HtmlFormat.Appendable = {
        _display_ {

Seq[Any](format.raw/*1.19*/("""

"""),_display_(Seq[Any](/*3.2*/main("Short URL")/*3.19*/ {_display_(Seq[Any](format.raw/*3.21*/("""    
    <section class="mainContent">    	 
    	
    	<div class="urlInput">
    		<div class="inputgroup">
    			<input type="text" id="urlInput" class="input-text" placeholder=""""),_display_(Seq[Any](/*8.74*/Messages("application.labelForURLBox"))),format.raw/*8.112*/("""">
    			<button id="clearinput" class="cancel typcn typcn-delete" />
    		</div>    		    	
    		
    		<div id="customcode" class="customcode">
    			"""),_display_(Seq[Any](/*13.9*/Messages("application.customcode"))),format.raw/*13.43*/(""" <span class="customtagurl">"""),_display_(Seq[Any](/*13.72*/Messages("application.orledomainurl"))),format.raw/*13.109*/("""</span>
    			<input type="text" id="customcodeinput" class="customcodeinput" placeholder=""""),_display_(Seq[Any](/*14.86*/Messages("application.customcodeformat"))),format.raw/*14.126*/("""" >
    		</div>
    		
    		<button class= "myButton" id="shortenButton" shortenURL=""""),_display_(Seq[Any](/*17.65*/routes/*17.71*/.Application.shorten)),format.raw/*17.91*/("""">"""),_display_(Seq[Any](/*17.94*/Messages("application.shortenButtonText"))),format.raw/*17.135*/("""</button>
    	</div>    
    	
    	<div class="shortinfo" id="shortinfo">
    		<div class="shortdetails" id="shortdetails">
	   			<div class="shortenedURL" id="shortenedURL"></div>  
	   			<div class="totalClicks" id="totalClicks"></div>
	   		</div>
    		<div class="error" id="orleerror"></div>
    		
	   		
	   		<div id="shareMainDiv"></div>
	   		
	   		
	   		 		
	    	<div id="shortText" shortText=""""),_display_(Seq[Any](/*32.39*/Messages("application.shortText"))),format.raw/*32.72*/(""""></div>
	    	<div id="clicksText" clicksText=""""),_display_(Seq[Any](/*33.41*/Messages("application.clicksText"))),format.raw/*33.75*/(""""></div>
	    	<div id="shareittext" data=""""),_display_(Seq[Any](/*34.36*/Messages("application.shareit"))),format.raw/*34.67*/(""""></div>
	    	<div id="orledomain" data="""),_display_(Seq[Any](/*35.34*/message)),format.raw/*35.41*/("""></div>
	    	<div id="domainerrortext" orledomainerror=""""),_display_(Seq[Any](/*36.51*/Messages("application.orledomainurlerror"))),format.raw/*36.93*/("""" invalidurlerror=""""),_display_(Seq[Any](/*36.113*/Messages("application.invalidurlerror"))),format.raw/*36.152*/("""" invalidcustomcode=""""),_display_(Seq[Any](/*36.174*/Messages("application.invalidcustomcode"))),format.raw/*36.215*/(""""</div>
	
	   		
	   		<textarea id="urlpreviewtrigger" class="hidden"></textarea>
	    	<div class="liveurl-loader"></div>
	    	
	    	<div class="liveurl">
	            <div class="close hidden" title="Close"></div>
	            <div class="inner">
	                <div class="image"> </div>
	                <div class="details">
	                    <div class="info">
	                        <div class="title"> </div>
	                        <div class="description"> </div> 
	                        <div class="url"> </div>
	                    </div>
	                </div>
	                <div id="qrcode" class="qrcode"></div>
	            </div>
	            
	        </div>	
	        
	        
        </div>
    	
    </section>
    
    <script>
    var previousShortenedURL = "";
    $(document).ready(function() """),format.raw/*65.34*/("""{"""),format.raw/*65.35*/("""
    	$("#urlInput").keypress(
    		function(event) """),format.raw/*67.23*/("""{"""),format.raw/*67.24*/("""
    			if (event.keyCode == 13) """),format.raw/*68.33*/("""{"""),format.raw/*68.34*/("""
    				shortenURL();
    			"""),format.raw/*70.8*/("""}"""),format.raw/*70.9*/("""
    		"""),format.raw/*71.7*/("""}"""),format.raw/*71.8*/(""");
    	$("#clearinput").click(function ()"""),format.raw/*72.40*/("""{"""),format.raw/*72.41*/("""
    		$("#urlInput").val('');
    		$("#customcodeinput").val('');
    		$("#urlpreviewtrigger").val('');
    		$("#shortinfo").hide();
    		$('.close').trigger('click');
    		previousShortenedURL="";
    	"""),format.raw/*79.6*/("""}"""),format.raw/*79.7*/(""");
    	
    	$("#orleerror").hide();
    	$("#shortenedURL").hide();
    	$("#shortdetails").hide();

    	//attach url preview to text area. 
    	var curImages = new Array();

    	$("#urlpreviewtrigger").liveUrl("""),format.raw/*88.38*/("""{"""),format.raw/*88.39*/("""
    						loadStart : function() """),format.raw/*89.34*/("""{"""),format.raw/*89.35*/("""
    							$('.liveurl-loader').show();
    						"""),format.raw/*91.11*/("""}"""),format.raw/*91.12*/(""",
    						loadEnd : function() """),format.raw/*92.32*/("""{"""),format.raw/*92.33*/("""
    							$('.liveurl-loader').hide();
    						"""),format.raw/*94.11*/("""}"""),format.raw/*94.12*/(""",
    						success : function(data) """),format.raw/*95.36*/("""{"""),format.raw/*95.37*/("""
    							var output = $('.liveurl');
    							output.find('.title').text(data.title);
    							output.find('.description').text(data.description);
    							output.find('.url').text(data.url);
    							output.find('.image').empty();
    							output.find('.close').one('click',
    											function() """),format.raw/*102.27*/("""{"""),format.raw/*102.28*/("""
    												var liveUrl = $(this).parent();
    												liveUrl.hide('fast');
    												liveUrl.find('.video').html('').hide();
    												liveUrl.find('.image').html('');
    												liveUrl.find('.image').hide();
    												$('textarea').trigger('clear');
    												curImages = new Array();
    											"""),format.raw/*110.16*/("""}"""),format.raw/*110.17*/(""");
    							output.show('fast');
    						"""),format.raw/*112.11*/("""}"""),format.raw/*112.12*/(""",
    						addImage : function(image) """),format.raw/*113.38*/("""{"""),format.raw/*113.39*/("""
    							var output = $('.liveurl');
    							var jqImage = $(image);
    							jqImage.attr('alt','Preview');

    							if ((image.width / image.height) > 7
    									|| (image.height / image.width) > 4) """),format.raw/*119.51*/("""{"""),format.raw/*119.52*/("""
    								// we dont want extra large images...
    								return false;
    							"""),format.raw/*122.12*/("""}"""),format.raw/*122.13*/("""

    							curImages.push(jqImage.attr('src'));
    							output.find('.image').append(jqImage);

    							if (curImages.length == 1) """),format.raw/*127.39*/("""{"""),format.raw/*127.40*/("""
    								// first image...
    								output.find('.image').show();
    								jqImage.addClass('active');
    							"""),format.raw/*131.12*/("""}"""),format.raw/*131.13*/("""

    							if (curImages.length == 2) """),format.raw/*133.39*/("""{"""),format.raw/*133.40*/("""
    								output.find('.controls .next').removeClass('inactive');
    							"""),format.raw/*135.12*/("""}"""),format.raw/*135.13*/("""
    						"""),format.raw/*136.11*/("""}"""),format.raw/*136.12*/("""
    					"""),format.raw/*137.10*/("""}"""),format.raw/*137.11*/(""");

    """),format.raw/*139.5*/("""}"""),format.raw/*139.6*/(""")

    $("#shortenButton").click(function() """),format.raw/*141.42*/("""{"""),format.raw/*141.43*/("""	
    	shortenURL();
    	
    """),format.raw/*144.5*/("""}"""),format.raw/*144.6*/(""");

    					

    function shortenURL() """),format.raw/*148.27*/("""{"""),format.raw/*148.28*/("""
    	if ($("#urlInput").val()) """),format.raw/*149.32*/("""{"""),format.raw/*149.33*/("""
    	var opts = """),format.raw/*150.17*/("""{"""),format.raw/*150.18*/("""
    		lines : 7, // The number of lines to draw
    		length : 0, // The length of each line
    		width : 10, // The line thickness
    		radius : 11, // The radius of the inner circle
    		corners : 1, // Corner roundness (0..1)
    		rotate : 0, // The rotation offset
    		direction : 1, // 1: clockwise, -1: counterclockwise
    		color : '#000', // #rgb or #rrggbb or array of colors
    		speed : 1, // Rounds per second
    		trail : 24, // Afterglow percentage
    		shadow : false, // Whether to render a shadow
    		hwaccel : false, // Whether to use hardware acceleration
    		className : 'spinner', // The CSS class to assign to the spinner
    		zIndex : 2e9, // The z-index (defaults to 2000000000)
    		top : 'auto', // Top position relative to parent in px
    		left : 'auto' // Left position relative to parent in px
    	"""),format.raw/*167.6*/("""}"""),format.raw/*167.7*/(""";

    	var urlToShorten = $("#urlInput").val();
    	

    	//check if the url has http:// or https:// at the beginning. If not, pre-pend http:// by default.
    	var httpsubstring = urlToShorten.substring(0, 7);
    	var httpssubstring = urlToShorten.substring(0, 8);

    	if (httpsubstring !== "http://"
    			&& httpssubstring !== "https://") """),format.raw/*177.42*/("""{"""),format.raw/*177.43*/("""
    		urlToShorten = "http://" + urlToShorten;
    	"""),format.raw/*179.6*/("""}"""),format.raw/*179.7*/("""

    	//verify if the URL is safe it's posted back to generate a short url.
    	var orledomain = $("#orledomain").attr("data");

    	if(urlToShorten.toLowerCase().indexOf(orledomain) >=0)"""),format.raw/*184.60*/("""{"""),format.raw/*184.61*/("""
    		var errortext = $("#domainerrortext").attr("orledomainerror");
    		$("#orleerror").text(errortext);
    		$("#shortinfo").show();
    		$("#orleerror").show();
    		$("#shortdetails").hide();																
    		$("#urlpreviewtrigger").val('');
    		$("#shareMainDiv").hide();
    		previousShortenedURL = "";
    		$("#urlpreviewtrigger").val('');
    		$("#urlpreviewtrigger").trigger('change');
    		$('.close').trigger('click');
    		$("#qrcodeimage").remove();
    		return;
    	"""),format.raw/*198.6*/("""}"""),format.raw/*198.7*/("""

    	var links = $.urlHelper.getLinks(urlToShorten);
    	if(links === null)"""),format.raw/*201.24*/("""{"""),format.raw/*201.25*/("""
    		var errortext = $("#domainerrortext").attr("invalidurlerror");
    		$("#orleerror").text(errortext);
    		$("#shortinfo").show();
    		$("#orleerror").show();
    		$("#shortdetails").hide();																
    		$("#urlpreviewtrigger").val('');
    		$("#shareMainDiv").hide();
    		previousShortenedURL = "";
    		$("#urlpreviewtrigger").val('');
    		$("#urlpreviewtrigger").trigger('change');
    		$('.close').trigger('click');
    		$("#qrcodeimage").remove();
    		return;
    	"""),format.raw/*215.6*/("""}"""),format.raw/*215.7*/("""

    	var customCode = $("#customcodeinput").val();
    	var chk = (/([A-Za-z0-9\_]"""),format.raw/*218.32*/("""{"""),format.raw/*218.33*/("""1,"""),format.raw/*218.35*/("""}"""),format.raw/*218.36*/(""")/.test(customCode));
    	
    	if(customCode !== "" && !chk)"""),format.raw/*220.35*/("""{"""),format.raw/*220.36*/("""
    		var errortext = $("#domainerrortext").attr("invalidcustomcode");
    		$("#orleerror").text(errortext);
    		$("#shortinfo").show();
    		$("#orleerror").show();
    		$("#shortdetails").hide();																
    		$("#urlpreviewtrigger").val('');
    		$("#shareMainDiv").hide();
    		previousShortenedURL = "";
    		$("#urlpreviewtrigger").val('');
    		$("#urlpreviewtrigger").trigger('change');
    		$('.close').trigger('click');
    		$("#qrcodeimage").remove();
    		return;
    	"""),format.raw/*234.6*/("""}"""),format.raw/*234.7*/("""
        //We append a "_" to custom code supplied by user, to avoid conflict with the id generated by Base62Counter. Hence a GET should also search for the custom code with a "_" appended.
    	if(customCode !== "") """),format.raw/*236.28*/("""{"""),format.raw/*236.29*/("""
        	 customCode = "_" + customCode;
    	"""),format.raw/*238.6*/("""}"""),format.raw/*238.7*/("""
    	
    	if (urlToShorten !== previousShortenedURL && links !== null) """),format.raw/*240.67*/("""{"""),format.raw/*240.68*/("""
    		$("#orleerror").hide();
    		$("#shortdetails").show();
    		$("#shareMainDiv").show();
    		$("#shortenedURL").html('');
    		$("#totalClicks").html('');
    		$("#urlpreviewtrigger").val('');
    		$('.close').trigger('click');
    		$("#qrcodeimage").remove();

    		var target = document
    				.getElementById('shortenedURL');
    		var spinner = new Spinner(opts).spin(target);
    		//target.appendChild(spinner.el);

    		var shortenURLPostBack = $("#shortenButton").attr("shortenURL");

    		previousShortenedURL = urlToShorten;

    		setTimeout(function() """),format.raw/*259.29*/("""{"""),format.raw/*259.30*/("""
    		"""),format.raw/*260.7*/("""}"""),format.raw/*260.8*/(""", 5000);

    		var getUrl = false;
    		var dataToSend = new Object();
    		dataToSend.urlToShorten = urlToShorten;
    		dataToSend.customCode = customCode;
    		
    		//START NEXT AJAX CALL TO POST LONG URL BACK
    		var hello = "hello";
    			$.ajax("""),format.raw/*269.15*/("""{"""),format.raw/*269.16*/("""
    					type : "POST",
    					url : shortenURLPostBack,
    					data : JSON.stringify(dataToSend),
    					dataType : "json",
    					contentType : "application/json; charset=utf-8",
    					success : function(data) """),format.raw/*275.35*/("""{"""),format.raw/*275.36*/("""
    						$("#shortinfo").show();
    						var link = data.url;
    						var error = data.error;
    						if(error != "") """),format.raw/*279.27*/("""{"""),format.raw/*279.28*/("""
    							$("#orleerror").text(error);
    				    		$("#orleerror").show();
    						"""),format.raw/*282.11*/("""}"""),format.raw/*282.12*/("""
        						
    						var pointsto = data.pointsto;
    	
    						var shortText = $("#shortText").attr("shortText");
    						var clicksText = $("#clicksText").attr("clicksText");
    	
    						//reset the text and remove the previous share buttons
    						$("#shortdetails").show();
    						$("#shortenedURL").show();
    						
    						$("#shortenedURL").html(shortText + " ");
    	
    						$("#sharerdiv").remove();
    	
    						var a = $('<div />');
    						//a.attr('href', link);
    						//a.attr('target', '_blank');
    						$("#shortenedURL").text(link);
    						//$("#shortenedURL").append(a);
    	
    						var openLinkButton = $('<a />');																
    						openLinkButton.attr('id', 'openLinkButton');
    						openLinkButton.attr('class', 'util typcn typcn-export');
    						openLinkButton.attr('title', 'Open link');
    						openLinkButton.attr('href', link);
    						openLinkButton.attr('target', '_blank');
    						$("#shortenedURL").append(openLinkButton);
    						
    						//var openLinkButton = $('<button />');
    						//openLinkButton.attr('id', 'openlinkbutton');
    						//openLinkButton.attr('class', 'util typcn typcn-export-outline');
    						//$("#shortenedURL").append(openLinkButton);		
    	
    						var totalClicks = data.clicks;
    						$("#totalClicks").html(clicksText + " "+ totalClicks);
    	
    						//generate preview of the url		
    						//update the value of the text area to trigger preview
    						$("#urlpreviewtrigger").val(urlToShorten);
    						$("#urlpreviewtrigger").trigger('change');
    						//end of preview.
    						
    						//qrcode image
    						var qrcodeimage = $('<img />');
    						var qrcodeurl = "https://chart.googleapis.com/chart?cht=qr&choe=UTF-8&chs=177x177&chl="+link;
    						qrcodeimage.attr('id', "qrcodeimage");
    						qrcodeimage.attr('src', qrcodeurl);
    						qrcodeimage.attr('alt', "qrcode");
    	
    						$("#qrcode").append(qrcodeimage);
    						$("#qrcodeimage").show();
    	
    						//facebook share
    						var sharerDiv = $('<div />');
    						sharerDiv.attr("id","sharerdiv");
    						sharerDiv.attr("class", "sharerdiv");
    	
    						$("#shareMainDiv").append(sharerDiv);
    	
    						var facebookLink = $('<button />'); 
    						facebookLink.attr("class","share typcn typcn-social-facebook");
    						facebookLink.attr("id","facebooksharebutton");
    						facebookLink.click(function() """),format.raw/*345.41*/("""{"""),format.raw/*345.42*/("""
    									window.open('https://www.facebook.com/sharer/sharer.php?u='+ encodeURIComponent(link),'_blank');
    									return false;
    								"""),format.raw/*348.13*/("""}"""),format.raw/*348.14*/(""");
    						//facebookLink.html("f");
    	
    						//twitter share
    						var twitterLink = $('<button />');
    						twitterLink.attr("class","share typcn typcn-social-twitter");
    						twitterLink.attr("id", "twittersharebutton");
    						twitterLink.click(function() """),format.raw/*355.40*/("""{"""),format.raw/*355.41*/("""
    							window.open('http://twitter.com/share?text='+ link +"&url=",'_blank');
    							return false;
    	
    						"""),format.raw/*359.11*/("""}"""),format.raw/*359.12*/(""");
    						//twitterLink.html("t");
    	
    						//google+ share
    						var googleLink = $('<button />');
    						googleLink.attr("class","share google");
    						googleLink.attr("id", "googlesharebutton");
    						googleLink.click(function() """),format.raw/*366.39*/("""{"""),format.raw/*366.40*/("""
    									window.open('https://plus.google.com/share?url='+ encodeURIComponent(link),'_blank');
    									return false;
    	
    								"""),format.raw/*370.13*/("""}"""),format.raw/*370.14*/(""");
    						googleLink.html("g+");
    	
    						$("#sharerdiv").append(facebookLink);
    						$("#sharerdiv").append(twitterLink);
    						$("#sharerdiv").append(googleLink);
    	
    					"""),format.raw/*377.10*/("""}"""),format.raw/*377.11*/(""",
    					error : function(xhr) """),format.raw/*378.32*/("""{"""),format.raw/*378.33*/("""
    						$("#shortdetails").hide();
    						$("#shortinfo").show();
    						$("#shortenedURL").hide();
    						$("#orleerror").text(xhr.responseText);
    						$("#orleerror").show();
    								
    						$("#sharerdiv").remove();
    						previousShortenedURL = "";
    					"""),format.raw/*387.10*/("""}"""),format.raw/*387.11*/("""
    			"""),format.raw/*388.8*/("""}"""),format.raw/*388.9*/(""");
    		//END AJAX CALL TO POST LONG URL BACK
    		"""),format.raw/*390.7*/("""}"""),format.raw/*390.8*/("""
    	"""),format.raw/*391.6*/("""}"""),format.raw/*391.7*/("""
    """),format.raw/*392.5*/("""}"""),format.raw/*392.6*/("""
    </script>   
""")))})),format.raw/*394.2*/("""
"""))}
    }
    
    def render(message:String): play.api.templates.HtmlFormat.Appendable = apply(message)
    
    def f:((String) => play.api.templates.HtmlFormat.Appendable) = (message) => apply(message)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jan 14 15:06:15 PST 2014
                    SOURCE: E:/ScalaDevelopment/shortURL/app/views/index.scala.html
                    HASH: 0e4fad5987d6434cd02c89eb6a0083f595b8960c
                    MATRIX: 587->1|698->18|735->21|760->38|799->40|1017->223|1077->261|1269->418|1325->452|1390->481|1450->518|1579->611|1642->651|1766->739|1781->745|1823->765|1862->768|1926->809|2377->1224|2432->1257|2517->1306|2573->1340|2653->1384|2706->1415|2784->1457|2813->1464|2907->1522|2971->1564|3028->1584|3090->1623|3149->1645|3213->1686|4078->2523|4107->2524|4188->2577|4217->2578|4278->2611|4307->2612|4364->2642|4392->2643|4426->2650|4454->2651|4524->2693|4553->2694|4789->2903|4817->2904|5061->3120|5090->3121|5152->3155|5181->3156|5260->3207|5289->3208|5350->3241|5379->3242|5458->3293|5487->3294|5552->3331|5581->3332|5925->3647|5955->3648|6327->3991|6357->3992|6431->4037|6461->4038|6529->4077|6559->4078|6804->4294|6834->4295|6951->4383|6981->4384|7149->4523|7179->4524|7332->4648|7362->4649|7431->4689|7461->4690|7570->4770|7600->4771|7640->4782|7670->4783|7709->4793|7739->4794|7775->4802|7804->4803|7877->4847|7907->4848|7966->4879|7995->4880|8065->4921|8095->4922|8156->4954|8186->4955|8232->4972|8262->4973|9137->5820|9166->5821|9544->6170|9574->6171|9655->6224|9684->6225|9903->6415|9933->6416|10461->6916|10490->6917|10597->6995|10627->6996|11155->7496|11184->7497|11297->7581|11327->7582|11358->7584|11388->7585|11479->7647|11509->7648|12039->8150|12068->8151|12314->8368|12344->8369|12419->8416|12448->8417|12550->8490|12580->8491|13191->9073|13221->9074|13256->9081|13285->9082|13574->9342|13604->9343|13857->9567|13887->9568|14042->9694|14072->9695|14190->9784|14220->9785|16732->12268|16762->12269|16941->12419|16971->12420|17283->12703|17313->12704|17466->12828|17496->12829|17782->13086|17812->13087|17986->13232|18016->13233|18243->13431|18273->13432|18335->13465|18365->13466|18682->13754|18712->13755|18748->13763|18777->13764|18858->13817|18887->13818|18921->13824|18950->13825|18983->13830|19012->13831|19063->13850
                    LINES: 20->1|23->1|25->3|25->3|25->3|30->8|30->8|35->13|35->13|35->13|35->13|36->14|36->14|39->17|39->17|39->17|39->17|39->17|54->32|54->32|55->33|55->33|56->34|56->34|57->35|57->35|58->36|58->36|58->36|58->36|58->36|58->36|87->65|87->65|89->67|89->67|90->68|90->68|92->70|92->70|93->71|93->71|94->72|94->72|101->79|101->79|110->88|110->88|111->89|111->89|113->91|113->91|114->92|114->92|116->94|116->94|117->95|117->95|124->102|124->102|132->110|132->110|134->112|134->112|135->113|135->113|141->119|141->119|144->122|144->122|149->127|149->127|153->131|153->131|155->133|155->133|157->135|157->135|158->136|158->136|159->137|159->137|161->139|161->139|163->141|163->141|166->144|166->144|170->148|170->148|171->149|171->149|172->150|172->150|189->167|189->167|199->177|199->177|201->179|201->179|206->184|206->184|220->198|220->198|223->201|223->201|237->215|237->215|240->218|240->218|240->218|240->218|242->220|242->220|256->234|256->234|258->236|258->236|260->238|260->238|262->240|262->240|281->259|281->259|282->260|282->260|291->269|291->269|297->275|297->275|301->279|301->279|304->282|304->282|367->345|367->345|370->348|370->348|377->355|377->355|381->359|381->359|388->366|388->366|392->370|392->370|399->377|399->377|400->378|400->378|409->387|409->387|410->388|410->388|412->390|412->390|413->391|413->391|414->392|414->392|416->394
                    -- GENERATED --
                */
            