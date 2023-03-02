package com.houvven.guise.xposed.config

import kotlinx.serialization.Serializable

@Serializable
data class WiFiScanResult(
  var SSID: String = "",
  var BSSID: String = "",
  var capabilities: String = "",
  var level: Int = 0,
  var channelWidth: Int = -1,
  var frequency: Int = 0,
  var centerFreq0: Int = 0,
  var centerFreq1: Int = 0,
  var timestamp: Long = 0,
)