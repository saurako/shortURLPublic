package controllerTests

import org.scalatest.FlatSpec
import org.specs2.mutable._
import play.api.test._
import play.api.test.Helpers._
import play.api.libs.json.Json
import play.api.libs.json._
import controllers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 * For more information, consult the wiki.
 */
class ApplicationSpec extends FlatSpec {

  //sequential

  "Application" should "send 404 on a bad request" in {
    running(FakeApplication()) {
      val url = route(FakeRequest(GET, "/something")).get

      assert(status(url) == BAD_REQUEST)
    }
  }

    //    
	it should "render the index page" in {
	  running(FakeApplication()) {
	    val home = route(FakeRequest(GET, "/")).get
	
	    assert(status(home) == OK)
	    assert(contentType(home).get contains "text/html")
	    //assert(contentAsString(home) contains "Shorten your URLs")
	  }
	}

    val initialID = "";

    it should "shorten a url" in {
      running(FakeApplication()) {
        val json = Json.stringify(JsString("http://test.com"))
        val request = FakeRequest(
          method = "POST",
          uri = routes.Application.shorten.url,
          headers = FakeHeaders(
            Seq("Content-type" -> Seq("application/json"))),
          body = json)

        val result = route(request).get

        assert (status(result) == OK)

      }
    }

    it should "return an already existing url id" in {
	      running(FakeApplication()){
		     val json = Json.toJson(
              Map(
                "urlToShorten" -> "http://test.com",
                "customCode" -> ""                
                ))
                
		      val request = FakeRequest(
		        method = "POST",
		        uri = routes.Application.shorten.url,
		        headers = FakeHeaders(
		        		Seq("Content-type" -> Seq("application/json"))), body = json)
		
		      val result = route(request).get
		
		      assert(status(result) == OK)
	    }

    }

    it must "redirect to url" in {
      running(FakeApplication()) {
        val url = route(FakeRequest(GET, "/2")).get

        assert(status(url) == SEE_OTHER)
        assert(redirectLocation(url) == Some("http://netflix.com"))

      }
    }
}