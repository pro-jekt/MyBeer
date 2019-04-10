package it.unicampania.lsadm.mybeers


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import it.unicampania.lsadm.mybeers.datamodel.Birra
import it.unicampania.lsadm.mybeers.datamodel.DataBase
import it.unicampania.lsadm.mybeers.datamodel.moretti
import kotlinx.android.synthetic.main.fragment_birra_edit.*


/**
 * Fragment per la creazione o la modifica di una birra.
 *
 */
class BirraEditFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_birra_edit, container, false)

    }

    /**
     * Passa alla creazione del menu
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.edit_birra, menu)
    }

    /**
     * Esegue le azioni relativie alla voci del menu
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        nascondiTastiera()

        when (item?.itemId) {

            R.id.menuConferma -> {      // Conferma
                if (campiValidi()) {
                    // Passa al salvataggio
                    val birra = Birra(editNome.text.toString(), editProduttore.text.toString(), "",editGradazione.text.toString().toFloat(),immagine = moretti)
                    DataBase.salvaBirra(birra)
                    Navigation.findNavController(view!!).navigateUp()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * Controlla che tutti campi siano validi
     */
    private fun campiValidi(): Boolean {
        val validi = editNome.text.length > 0 && editProduttore.text.length > 0 && editGradazione.text.length > 0
        if (!validi)
            Toast.makeText(activity, R.string.valCampiObbligatori, Toast.LENGTH_SHORT).show()
        return validi
    }

    /**
     * Nasconde la tastiera
     */
    private fun nascondiTastiera() {
        activity?.currentFocus?.let {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }
    
}
