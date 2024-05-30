package utils

import com.launchdarkly.sdk.server.{LDClient, LDConfig}

object LaunchDarklyClient {
  val sdkKey = "sdk-9903154d-c181-4711-83be-16f4e481a55f" // Replace with your actual SDK key
  val client = new LDClient(sdkKey)

  sys.addShutdownHook {
    client.close()
  }
}
