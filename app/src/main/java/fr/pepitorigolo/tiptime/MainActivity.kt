package fr.pepitorigolo.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.pepitorigolo.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat


class MainActivity : AppCompatActivity() {

    /* variable de niveau supérieur qui sera utile dans pls méthodes de la class MainActivity
        lateinit c'est pour dire que l'on va initialiser la variable avant de l'utiliser
     */
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //la ligne du dessous initialise la variable binding pour l'accès à la vue activity_main.xml
        binding = ActivityMainBinding.inflate(layoutInflater)
         /* définit l'affichage du contenu de l'activity
         * Maintenant lorsque l'on a besoin d'une référence à View dans l'app on passe par binding
         * au lieu de findViewById()
         * Avant:
         * val myButton: Button = findViewById(R.id.my_button)
         * myButton.text = "A button"
         * Après:
         * binding.myButton.text = "A button"
         * */
        setContentView(binding.root)

        //On définit un écouteur de clic sur le bouton Calculer qui appel la fonction calculateTip()
        binding.calculateButton.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        /*récupère l'attribut de text du Cost of Serviceet on l'affecte à la variable stringInTextField
        binding.costOfService, fait référence à l'élément UI pour le coût du service
        .textà la fin indique de prendre ce résultat (un EditText objet) et d'en obtenir la propriété text
        .toString convertit le stringInTextField en chaine de caractère
         */
        val stringInTextField = binding.costOfService.text.toString()
        //convertir le text en décimal
        val cost = stringInTextField.toDoubleOrNull()
        //si le cout est null alors le text sera vide
        if (cost == null) {
            binding.tipResult.text= ""
            return
        }

        //permet d'obtenir le pourcentage de pourboire
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        //calcul du pourboire et l'arrondir
        var tip = tipPercentage * cost
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }
        //afichage du pourboire dans le TextView
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        //tipResult fait référence a l'id mon textView de mon layout activity_main et tip_amount au text de cette vue.
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}
