package com.example.myapplication

import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.BuildConfig.DEBUG
import com.contus.flycommons.Constants.TO_JID
import com.contus.flycommons.Constants.USER_IDENTIFIER
import com.contus.flycommons.LogMessage
import com.contus.flycommons.models.MessageType
import com.contusflysdk.ChatSDK
import com.contusflysdk.api.*
import com.contusflysdk.api.models.ChatMessage
import com.example.myapplication.*
import com.example.myapplication.databinding.ActivityMainBinding
import org.json.JSONObject

@Suppress("UNUSED_ANONYMOUS_PARAMETER")
class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    val LICENSE = resources.getString(R.string.your_license_key_resource_id_here)

    private fun onMessageReceived(chatMessage: Message, function: () -> Unit) {
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogMessage.enableDebugLogging(DEBUG)

        val SDK_BASE_URL = ""
        ChatSDK.Builder()
            .setDomainBaseUrl(SDK_BASE_URL)
            .setLicenseKey(LICENSE)
            .build()
        FlyCore.registerUser(USER_IDENTIFIER) { isSuccess, throwable, data ->
            if(isSuccess) {
                val responseObject = data["data"] as JSONObject
            } else {
            }
        }
        ChatManager.connect(object : ChatConnectionListener {
            override fun onConnected() {
            }
            override fun onDisconnected() {
            }
            override fun onConnectionNotAuthorized() {
                           }
        })
        FlyMessenger.sendTextMessage(TO_JID, MessageType.TEXT.toString(), listener = object : SendMessageListener {
            override fun onResponse(isSuccess: Boolean, chatMessage: ChatMessage?) {
                          }
        })


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
               return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}

class ActivityMainBinding {

}
