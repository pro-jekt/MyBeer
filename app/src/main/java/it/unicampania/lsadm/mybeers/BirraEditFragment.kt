package it.unicampania.lsadm.mybeers




import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.navigation.Navigation
import it.unicampania.lsadm.mybeers.datamodel.Birra
import it.unicampania.lsadm.mybeers.datamodel.DataBase
import kotlinx.android.synthetic.main.fragment_birra_edit.*


/**
 * Fragment per la creazione o la modifica di una birra.
 *
 */
class BirraEditFragment : Fragment() {
    var picked_image : Uri? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_birra_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pick_image.setOnClickListener{
            if(permission_granted!!)
                pickImageFromGallery()
            else
                Toast.makeText(this.context, "Permesso negato", Toast.LENGTH_SHORT).show()
        }
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
                    val birra = picked_image?.let { Birra(editNome.text.toString(), editProduttore.text.toString(), "",editGradazione.text.toString().toFloat(),immagine = it) }
                    birra?.let { DataBase.salvaBirra(it) }
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

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        val PERMISSION_CODE = 1001;
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            pick_image.setImageURI(data?.data)
            picked_image = data?.data
        }
    }

}
