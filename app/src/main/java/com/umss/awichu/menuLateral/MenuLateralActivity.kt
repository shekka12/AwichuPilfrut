package com.umss.awichu.menuLateral

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.umss.awichu.R
import com.umss.awichu.botonesInferiores.BotonRecordatorios
import com.umss.awichu.databinding.ActivityMenuLateralBinding
import com.umss.awichu.presentation.authentification.login.view.MainActivity

class MenuLateralActivity : AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMenuLateralBinding
    private lateinit var builder: AlertDialog.Builder

    val mAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuLateralBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarMenuLateral.toolbar)

        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_menu_lateral)
        val mAuth = FirebaseAuth.getInstance()
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.nav_home), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.menu_lateral, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logoutSesion -> {
                builder = AlertDialog.Builder(this)
                builder.setTitle("  ")
                    .setMessage("¿Seguro que desea cerrar sesión?")
                    .setCancelable(true)
                    .setPositiveButton("Aceptar"){dialogInterface, it ->
                        mAuth.signOut()
                        navigateToLogin()
                        finish()

                    }
                    .setNegativeButton("Cancelar"){dialogInterface, it ->
                        dialogInterface.cancel()
                    }
                    .show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_menu_lateral)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun navigateToLogin() {
        val intentLogin = Intent(this, MainActivity::class.java)
        intentLogin.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intentLogin)
    }
}