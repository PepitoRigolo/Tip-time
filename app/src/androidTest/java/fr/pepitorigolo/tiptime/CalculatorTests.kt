package fr.pepitorigolo.tiptime


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//test de la fonctionnalités pour un pourboire à 20%
@RunWith(AndroidJUnit4::class)
class CalculatorTests {

    @get:Rule()
    //pour intéragir avec MainActivity
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    //logique de test fait avec la bibliothèque Espresso
    fun calculate_20_percent_tip() {
        /*
        pour interagir avec un composant d'interface utilisateur comme le textInputEditText
        on utilise onView qui va récupérer la l'id de mon champ à l'aide de la fonction withId
        Pour saisir du txt on appel perform()
        Pour le renvoie d'un viewAction on utilise typeText
        Pour vérifier que le résulat obtenu est bon on utilise check et on met le comtant que l'on
        devrait obtenir

        Donc ici on simule l'écriture dans le input qui vaut 50.00
        Puis on simule le click sur le btn calculer
        Enfin on vérifie si le rsultat est bien 10.00
         */
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$10.00"))))
    }

    @Test
    fun calculate_18_percent_tip() {

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.option_eighteen_percent)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$9.00"))))
    }


    @Test
    fun calculate_15_percent_tip() {

        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))

        onView(withId(R.id.option_fifteen_percent)).perform(click())

        onView(withId(R.id.round_up_switch)).perform(click())

        onView(withId(R.id.calculate_button)).perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$7.50"))))
    }
}