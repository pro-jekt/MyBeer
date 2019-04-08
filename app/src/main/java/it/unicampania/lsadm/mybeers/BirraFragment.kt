package it.unicampania.lsadm.mybeers


import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.util.Log
import android.util.LogPrinter
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

    var delbirra: Birra? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_birra, container, false)
    }

    fun createAlert (){
        getActivity()?.let {
            AlertDialog.Builder(it)
                .setCancelable(false)
                .setTitle("Attenzione!")
                .setMessage("Sei sicuro di voler eliminare la birra?")

                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->

                }).setPositiveButton("Sì", DialogInterface.OnClickListener
                { dialog, which ->
                    delbirra?.let { DataBase.eliminaBirra(it) }
                    Navigation.findNavController(view!!).navigateUp()
                }).create().show()
        }
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
                imageView.setImageResource()
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

            R.id.menuElimina -> {
            createAlert()
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.delete_birra, menu)
    }

    }
