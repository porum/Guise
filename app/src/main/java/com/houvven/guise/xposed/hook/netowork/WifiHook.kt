package com.houvven.guise.xposed.hook.netowork

import android.annotation.SuppressLint
import android.net.wifi.ScanResult
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import com.houvven.guise.xposed.LoadPackageHandler
import com.houvven.ktx_xposed.hook.setMethodResult

@SuppressLint("WrongConstant")
internal class WifiHook : LoadPackageHandler {
    override fun onHook() {
        WifiInfo::class.java.run {
            if (config.wifiSSID.isNotBlank()) setMethodResult("getSSID", "\"${config.wifiSSID}\"")
            if (config.wifiBSSID.isNotBlank()) setMethodResult("getBSSID", config.wifiBSSID)
            if (config.wifiMacAddress.isNotBlank()) setMethodResult("getMacAddress", config.wifiMacAddress)
        }

        WifiManager::class.java.run {
            if (config.wifiScanResult.isNotEmpty()) {
                val scanResults = config.wifiScanResult.map {
                    ScanResult().apply {
                        SSID = it.SSID
                        BSSID = it.BSSID
                        capabilities = it.capabilities
                        level = it.level
                        channelWidth = it.channelWidth
                        frequency = it.frequency
                        centerFreq0 = it.centerFreq0
                        centerFreq1 = it.centerFreq1
                        timestamp = it.timestamp
                    }
                }
                setMethodResult("getScanResults", scanResults)
            }
        }
    }

}