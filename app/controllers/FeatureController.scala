package controllers

import javax.inject._
import play.api.mvc._
import utils.LaunchDarklyClient
import com.launchdarkly.sdk.LDUser
import com.launchdarkly.sdk.LDContext
import views.html.feature
import scala.util.Random

@Singleton
class FeatureController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request: Request[AnyContent] =>
    val userKey = Random.alphanumeric.take(4).mkString
    // val user = new LDUser.Builder(userKey).build()
    val context = LDContext.builder(userKey)
  .name("Demo")
  .build();
    val showFeature = LaunchDarklyClient.client.boolVariation("scalaFeature", context, false)

    val showString = LaunchDarklyClient.client.stringVariation("scalaString", context, "default")
    
    println(s"Feature flag status: $showFeature") // Debug print

    // Generate an error 30% of the time
    val randomValue = Random.nextFloat()
    if (showFeature && randomValue < 0.3) {
      val errorMessage = "Generated a test error for tracking purposes."
      println(errorMessage) // Debug print for error

      // Track the error using LaunchDarkly
      LaunchDarklyClient.client.track("page_load_error", context)
      LaunchDarklyClient.client.flush()
    }

    // Ensure that the parameter is being passed correctly
    Ok(feature(showFeature, showString))
  }
}