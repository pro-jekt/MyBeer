package it.unicampania.lsadm.mybeers.datamodel

import android.net.Uri
import android.os.Parcelable
import it.unicampania.lsadm.mybeers.R
import it.unicampania.lsadm.mybeers.package_name
import kotlinx.android.parcel.Parcelize

/**
 * Classe per rappresentare una birra nella raccolta
 */
val tennents = Uri.parse("android.resource://"+ package_name + "/" + R.drawable.tennents)
val birra = Uri.parse("android.resource://"+ package_name + "/" + R.drawable.birra)
val corona = Uri.parse("android.resource://"+ package_name + "/" + R.drawable.corona)
val moretti = Uri.parse("android.resource://"+ package_name + "/" + R.drawable.moretti)

@Parcelize
data class Birra(var nome: String, var produttore: String, var tipologia: String, var gradazione: Float, var immagine: Uri) : Parcelable