package it.unicampania.lsadm.mybeers


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.navigation.Navigation
import it.unicampania.lsadm.mybeers.datamodel.Birra
import it.unicampania.lsadm.mybeers.datamodel.DataBase
import kotlinx.android.synthetic.main.fragment_birra.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BirraFragment : Fragment() {

    var delbirra : Birra? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_birra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Estraggo il parametro (birra) dal bundle ed eventualmente lo visualizzo
        arguments?.let {
           val birra: Birra? = it.getParcelable("birra")   //TODO: Il nome dovrebbe essere in un unico punto!!
            delbirra= birra
            birra?.let {
                textNome.text = it.nome
                textProduttore.text = it.produttore
                textGradazione.text = String.format("%.2f", it.gradazione)
            }
        }
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

    override fun onOptionsItemSelected(item: MenuItem? ): Boolean {
        nascondiTastiera()

        when (item?.itemId) {

            R.id.menuElimina -> {      // Conferma

                    // elimino
                delbirra?.let { DataBase.eliminaBirra(it) }
                    Navigation.findNavController(view!!).navigateUp()

            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.delete_birra, menu)
    }
}
