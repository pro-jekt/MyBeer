package it.unicampania.lsadm.mybeers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.unicampania.lsadm.mybeers.datamodel.Birra
import it.unicampania.lsadm.mybeers.datamodel.DataBase
import kotlinx.android.synthetic.main.fragment_birra.*

/**
 * A simple [Fragment] subclass.
 *
 */
class BirraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Estraggo il parametro (birra) dal bundle ed eventualmente lo visualizzo
        arguments?.let {
            val birra: Birra? = it.getParcelable("birra")   //TODO: Il nome dovrebbe essere in un unico punto!!
            birra?.let {
                textNome.text = it.nome
                textProduttore.text = it.produttore
                textGradazione.text = String.format("%.2f", it.gradazione)
            }
        }
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        nascondiTastiera()

        when (item?.itemId) {

            R.id.menuConferma -> {      // Conferma

                    // elimino
                    val birra = Birra(editNome.text.toString(), editProduttore.text.toString(), "",editGradazione.text.toString().toFloat())
                    DataBase.eliminaBirra(birra)
                    Navigation.findNavController(view!!).navigateUp()

            }
        }
        return super.onOptionsItemSelected(item)
    }
}
