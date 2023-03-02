package com.houvven.guise.util.android

import android.Manifest
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.houvven.guise.ContextAmbient
import com.houvven.guise.xposed.config.WiFiScanResult

object WiFiUtils {

  @JvmStatic
  fun getScanResult(): List<WiFiScanResult> {
    if (ActivityCompat.checkSelfPermission(
        ContextAmbient.current,
        Manifest.permission.ACCESS_FINE_LOCATION
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      return emptyList()
    }
    return ContextCompat.getSystemService(
      ContextAmbient.current,
      WifiManager::class.java
    )?.scanResults?.map {
      WiFiScanResult(
        SSID = it.SSID,
        BSSID = it.BSSID,
        capabilities = it.capabilities,
        level = it.level,
        channelWidth = it.channelWidth,
        frequency = it.frequency,
        centerFreq0 = it.centerFreq0,
        centerFreq1 = it.centerFreq1,
        timestamp = it.timestamp
      )
    } ?: emptyList()
  }
}

