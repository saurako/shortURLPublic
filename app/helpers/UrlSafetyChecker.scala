package helpers

import play.api.libs.ws._
import scala.concurrent.Future
import play.api.libs.ws.WS.WSRequestHolder
import java.net.URL
import java.net.URLEncoder
import orle.in.util.UnsafeURLException
import scala.concurrent.Await
import akka.util.Timeout
import scala.concurrent.duration.FiniteDuration
import java.util.concurrent.TimeUnit

object UrlSafetyChecker {
  
  val MALWARE = "malware"
  val PHISHING = "phishing"

  private val googleSafeBrowsingApiUrl = "https://sb-ssl.google.com/safebrowsing/api/lookup"
  private val apiKey = "ABQIAAAAJLWtHcgSds3vVmO0SolKOxQsbVgqOfrZQ08Sezyq8r7aVD6URA"
  private val appName = "orleinapi"
  private val appversion = "0.1"
  private val pver = "3.0"

  private val holder: WSRequestHolder = WS.url(googleSafeBrowsingApiUrl)

  private val complexHolder: WSRequestHolder = holder.withQueryString("client" -> appName)
    .withQueryString("apikey" -> apiKey)
    .withQueryString("appver" -> appversion)
    .withQueryString("pver" -> pver)

  def safetyLookup(lookupUrl: String): Any = {
    try {

      val urlFromLookupString = new URL(lookupUrl)
      val encodedURL = URLEncoder.encode(lookupUrl, "ISO-8859-1")

      val lookupHolder = complexHolder.withQueryString("url" -> encodedURL)

      implicit val context = scala.concurrent.ExecutionContext.Implicits.global
      implicit val timeout = Timeout(new FiniteDuration(20, TimeUnit.SECONDS))

      val future = lookupHolder.withHeaders("Accept" -> "application/octet-stream").get()
      val response: Response = Await.result(future, timeout.duration)

      val body = future.value.get.get.body 
      val status = future.value.get.get.status

      response.status match {
        case 200 => {
          val bodyString = body
          val exeception = new UnsafeURLException(bodyString)
          throw exeception
        }
        case 400 | 401 | 503 =>
          throw new RuntimeException
        case 204 => return true;

      }

    }

  }

}