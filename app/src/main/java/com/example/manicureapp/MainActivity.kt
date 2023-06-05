package com.example.manicureapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room
import com.example.manicureapp.db.AppDatabase
import com.example.manicureapp.db.DbSingleton
import com.example.manicureapp.db.entities.Master
import com.example.manicureapp.db.entities.Service
import kotlinx.android.synthetic.main.sign_up_fragment.*

class MainActivity : AppCompatActivity() {

  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    val db = DbSingleton.getInstance(applicationContext)
    val firstStartKey = "first_start"
    val sharedPreference = applicationContext
      .getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

    if (sharedPreference.getString(firstStartKey, null) == null) {
      db.masterDao().insertAll(
        Master("Мария Савкова", "маникюр", "avatar1"),
        Master("Джаннета Юсупова", "маникюр, педикюр", "avatar2"),
        Master("Светлана Кех", "маникюр", "avatar3"),
        Master("Юля Шепс", "маникюр", "avatar4"),
        Master("Рузана Светлова", "маникюр", "avatar5"),
        Master("Евгения Поклонская", "маникюр, педикюр", "avatar6")
      )

      db.serviceDao().insertAll(
        Service("Аппаратный маникюр",
          "Аппаратный маникюр искусственных и натуральных ногтей – это современная техника, которая дает комплекс преимуществ для мастера и клиента.",
          "service1",
          400.00
        ),
        Service("Наращивание ногтей",
          "Наращивание — это химический процесс приклеивания материала к ногтю. Акрилу не нужна лампа, только лишь в конце она понадобится для заключительного этапа.",
          "service2",
          500.00
        ),
        Service("Коррекция ногтей",
          "Под коррекцией ногтей подразумевается нанесение дополнительным слоем моделирующего материала на отросшую часть ногтевой пластины.\n",
          "service3",
          700.00
        ),
        Service("Покрытие ногтей гель-лаком",
          "Гель-лак — это синтетическое покрытие для ногтей, созданное на основе геля для наращивания и лака",
          "service4",
          600.00
        ),
        Service("Spa-маникюр",
          "SPA маникюр - это комплекс ухода за руками включающий в себя пилинг, смягчение, увлажнение, а также витаминное питание кожи рук и ногтей.",
          "service5",
          300.00
        ),
        Service("Аппаратный педикюр",
          "Аппаратный педикюр – это механическая процедура по уходу за ногтями на пальцах ног, а также за кожей стопы.",
          "service6",
          700.00
        ),
        Service("Покрытие ногтей ног лаком или гелем",
          "Сначала ногти покрывают базой и помещают в лампу на 30-60 сек. Затем наносят тонкий слой гель-лака",
          "service7",
          400.00
        ),
        Service("Параиновое обертывание для ног",
          "приятная и эффективная процедура, которая станет спасением для грубой и сухой кожи. Парафиновое обертывание глубоко увлажняет дерму и заживляет трещины. При этом стимулируется кровообращение, уходят отеки и усталость.",
          "service8",
          500.00
        ),
        Service("Скрабирование стоп",
          "В состав средства для пилинга обязательно входят абразивные и ухаживающие компоненты. В салонах чаще всего применяют препараты на основе соли или сахара. Кроме того, в любой скраб для ног, профессиональный или изготовленный в домашних условиях, включают питательные масла (оливковое, лавандовое, чайного дерева, цитрусовое и др.), которые благотворно действуют на кожу, делают процедуру приятнее.",
          "service9",
          400.00
        ),
      )

      val editor = sharedPreference.edit()
      editor.putString(firstStartKey, "second")
      editor.apply()
    }

    val navHostFragment = supportFragmentManager
      .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
    navController = navHostFragment.navController

    val appBarConfiguration = AppBarConfiguration(setOf(
      R.id.authorizationFragment,
      R.id.registrationFragment,
      R.id.accountFragment,
      R.id.mastersFragment,
      R.id.priceListFragment,
      R.id.serviceInfoFragment,
      R.id.signUpFragment,
      R.id.homeFragment,
    ))

    setupActionBarWithNavController(navController, appBarConfiguration)
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater = menuInflater
    inflater.inflate(R.menu.menu, menu)
    return true
  }
}