package al.bruno.pdfdemo

import al.bruno.pdfdemo.databinding.ActivityMainBinding
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.pdftron.pdf.config.ViewerConfig
import com.pdftron.pdf.controls.DocumentActivity
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.configuration.page.PageFitMode
import com.pspdfkit.configuration.page.PageScrollMode
import com.pspdfkit.ui.PdfActivity


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // ÇanoTupja-TermInf.pdf
//        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//
//        setSupportActionBar(binding.toolbar)
//
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        appBarConfiguration = AppBarConfiguration(navController.graph)
//        setupActionBarWithNavController(navController, appBarConfiguration)
        val config = PdfActivityConfiguration
            .Builder(this)
            .animateScrollOnEdgeTaps(true)
            .fitMode(PageFitMode.FIT_TO_WIDTH)
            .allowMultipleBookmarksPerPage(true)
            .autosaveEnabled(true)
            .scrollMode(PageScrollMode.PER_PAGE)
            .build()

        val viewerConfig = ViewerConfig
            .Builder()
            .useStandardLibrary(true)
            .fullscreenModeEnabled(true)
            .useCompactViewer(true)
            .hidePresetBar(true)
            .openUrlCachePath(this.cacheDir.absolutePath)
            .build()
        binding.pdftron.setOnClickListener {
            DocumentActivity.openDocument(
                this,
                Uri.parse("file:///android_asset/ÇanoTupja-TermInf.pdf"),
                viewerConfig
            )
        }
        binding.pspdf.setOnClickListener {
            PdfActivity
                .showDocument(
                    this,
                    Uri.parse("file:///android_asset/ÇanoTupja-TermInf.pdf"),
                    config
                )
        }

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