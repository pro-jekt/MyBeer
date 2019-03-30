package it.unicampania.lsadm.mybeers


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import it.unicampania.lsadm.mybeers.datamodel.DataBase
import kotlinx.android.synthetic.main.fragment_birre.*


/**
 * Fragmant con la visualizzazione dell'elenco delle birre
 *
 */
class BirreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_birre, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Imposto il layout manager a lineare per avere scrolling in una direzione
        listBirre.layoutManager = LinearLayoutManager(activity)

        // Associo l'adapter alla RecyclerView
        listBirre.adapter = BirreAdapter(DataBase.getElencoBirre(), requireContext())

        fabAggiungiBirra.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_birreFragment_to_birraEditFragment)
        }
    }

}
