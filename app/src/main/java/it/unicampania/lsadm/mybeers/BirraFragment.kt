package it.unicampania.lsadm.mybeers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import it.unicampania.lsadm.mybeers.datamodel.Birra
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

}
