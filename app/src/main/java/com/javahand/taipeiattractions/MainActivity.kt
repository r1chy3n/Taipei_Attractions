package com.javahand.taipeiattractions

import android.content.Context
import android.content.ContextWrapper
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.PopupMenu
import com.javahand.taipeiattractions.databinding.ActivityMainBinding
import com.javahand.taipeiattractions.i18n.LanguagePreference
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController =
            findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_lang -> showPopupLang()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showPopupLang() = true.also {
        PopupMenu(this, findViewById(R.id.action_lang)).run {
            inflate(R.menu.popup_lang)
            setOnMenuItemClickListener {
                if (switchLang(it.itemId)) {
                    recreate()
                } // if

                true
            } // setOnMenuItemClickListener
            show()
        } // let
    } // fun showPopupLang( MenuItem )

    private fun switchLang(itemId: Int): Boolean {
        return when (itemId) {
            R.id.lang_tw -> switchLocale(Locale.TRADITIONAL_CHINESE)
            R.id.lang_cn -> switchLocale(Locale.SIMPLIFIED_CHINESE)
            R.id.lang_en -> switchLocale(Locale.ENGLISH)
            R.id.lang_ja -> switchLocale(Locale.JAPANESE)
            R.id.lang_ko -> switchLocale(Locale.KOREAN)
            R.id.lang_es -> switchLocale(Locale("es"))
            R.id.lang_th -> switchLocale(Locale("th"))
            R.id.lang_vi -> switchLocale(Locale("vi"))
            else -> false
        } // when
    } // fun switchLang( Int )

    private fun switchLocale(toLocale: Locale): Boolean {
        val switch = LanguagePreference.getLocale(this) != toLocale

        if (switch) {
            LanguagePreference.setLocale(toLocale, this)
        } // if

        return switch
    } // fun switchLocale(Locale)

    override fun attachBaseContext(newBase: Context?) {
        val newContext = newBase?.run {
            resources?.configuration?.let {
                it.setLocale(LanguagePreference.getLocale(this))
                ContextWrapper(createConfigurationContext(it))
            } ?: this
        } ?: newBase

        super.attachBaseContext(newContext)
    } // fun attachBaseContext( Context?)

    override fun onSupportNavigateUp(): Boolean {
        val navController =
            findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}