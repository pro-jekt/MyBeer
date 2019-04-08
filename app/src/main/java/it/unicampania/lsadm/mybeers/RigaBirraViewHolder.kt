package it.unicampania.lsadm.mybeers

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.riga_birra.view.*

/**
 * ViewHolder utilizzato per le righe della RecyclerView con l'elenco delle birre
 * contiene le proprietà corrispondenti agli elementi della riga
 */
class RigaBirraViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvNome = view.textNome
    val tvProduttore = view.textProduttore
    val tvImmagine = view.imageView4
}